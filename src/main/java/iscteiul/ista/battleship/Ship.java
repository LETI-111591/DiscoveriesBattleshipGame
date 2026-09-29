/**
 * Package que contém as classes principais do jogo Batalha Naval da época dos Descobrimentos.
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe abstrata que serve de base para todos os tipos de navios do jogo.
 * Implementa a interface {@link IShip} e gere o estado, posições e interações dos navios.
 * 
 * @author Iscte / ISTA
 */
public abstract class Ship implements IShip {

    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Método fábrica (Factory) para criar instâncias de navios com base no tipo especificado.
     * 
     * @ o tipo de navio a ser criado (ex: barca, caravela, nau, fragata, galeao)
     * @ bearing a orientação do navio no tabuleiro
     * @ pos a posição inicial do navio
     * @return uma nova instância da subclasse de {@link Ship} correspondente, ou null se o tipo for inválido
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }


    private String category;
    private Compass bearing;
    private IPosition pos;
    protected List<IPosition> positions;


    /**
     * Construtor para inicializar um navio com a sua categoria, orientação e posição base.
     * 
     * @category a categoria ou nome do navio
     * @bearing a orientação/direção do navio
     * @pos a posição inicial de referência do navio
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Obtém a categoria do navio.
     * 
     * @return a categoria em formato String
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Obtém a lista de todas as posições ocupadas pelo navio no tabuleiro.
     * 
     * @return a lista de posições do navio
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Obtém a posição inicial de referência do navio.
     * 
     * @return a posição de origem
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Obtém a orientação atual do navio.
     * 
     * @return a orientação (direção cardinal)
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Verifica se o navio ainda se encontra a flutuar (ou seja, se tem pelo menos uma parte por atingir).
     * 
     * @return true se ainda tiver partes flutuantes, false caso contrário (afundado)
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Obtém a coordenada da linha mais acima (topo) ocupada pelo navio.
     * 
     * @return o índice da linha superior
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Obtém a coordenada da linha mais abaixo (fundo) ocupada pelo navio.
     * 
     * @return o índice da linha inferior
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Obtém a coordenada da coluna mais à esquerda ocupada pelo navio.
     * 
     * @return o índice da coluna esquerda
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Obtém a coordenada da coluna mais à direita ocupada pelo navio.
     * 
     * @return o índice da coluna direita
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Verifica se o navio ocupa uma determinada posição no tabuleiro.
     * 
     * @pos a posição a verificar
     * @return true se o navio ocupar essa posição, false caso contrário
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Verifica se este navio está demasiado próximo de outro navio (regrando impedimento de toque entre frotas).
     * 
     * @other o outro navio a verificar
     * @return true se estiver adjacente/demasiado perto, false caso contrário
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Verifica se este navio está demasiado próximo de uma determinada posição.
     * 
     * @pos a posição a testar
     * @return true se estiver adjacente, false caso contrário
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }


    /**
     * Executa uma ação de disparo sobre o navio numa dada posição.
     * Se a posição coincidir com uma parte do navio, essa parte é marcada como atingida.
     * 
     * @pos a posição onde foi efetuado o tiro
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }


    /**
     * Retorna uma representação em texto do navio (categoria, orientação e posição).
     * 
     * @return string descritiva do navio
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
