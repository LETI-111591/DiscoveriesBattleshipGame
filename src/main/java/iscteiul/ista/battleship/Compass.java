package iscteiul.ista.battleship;

/**
 * Representa as direções (pontos cardeais) usadas no jogo de Batalha Naval.
 * <p>
 * Cada direção é identificada internamente por um {@code char} (minúsculo),
 * permitindo uma conversão simples entre a representação textual usada nos
 * ficheiros de configuração do jogo e a constante do enum. A direção
 * {@link #UNKNOWN} é usada como valor por omissão quando um caractere
 * desconhecido é fornecido.
 * </p>
 *
 * <p>Mapeamento entre caractere e direção:</p>
 * <ul>
 *   <li>{@code 'n'} → {@link #NORTH} (Norte)</li>
 *   <li>{@code 's'} → {@link #SOUTH} (Sul)</li>
 *   <li>{@code 'e'} → {@link #EAST} (Este)</li>
 *   <li>{@code 'o'} → {@link #WEST} (Oeste)</li>
 *   <li>qualquer outro → {@link #UNKNOWN} (Desconhecida)</li>
 * </ul>
 *
 * @author fba
 * @version 1.1
 */
public enum Compass {

    /** Direção Norte, representada pelo caractere {@code 'n'}. */
    NORTH('n'),

    /** Direção Sul, representada pelo caractere {@code 's'}. */
    SOUTH('s'),

    /** Direção Este, representada pelo caractere {@code 'e'}. */
    EAST('e'),

    /** Direção Oeste, representada pelo caractere {@code 'o'}. */
    WEST('o'),

    /** Direção desconhecida, usada como valor por omissão. */
    UNKNOWN('u');

    /** Caractere que representa esta direção. */
    private final char c;

    /**
     * Constrói uma constante do enum {@code Compass} associada ao caractere
     * indicado.
     *
     * @param c o caractere que representa a direção (por exemplo, {@code 'n'}
     *          para Norte)
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Devolve o caractere associado a esta direção.
     *
     * @return o caractere que representa a direção (por exemplo, {@code 'n'}
     *         para {@link #NORTH})
     */
    public char getDirection() {
        return c;
    }

    /**
     * Devolve a representação textual desta direção, que corresponde
     * exatamente ao seu caractere identificador.
     *
     * @return uma {@code String} contendo apenas o caractere da direção
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um caractere na constante de {@code Compass} correspondente.
     * <p>
     * Se o caractere não corresponder a nenhuma direção conhecida
     * ({@code 'n'}, {@code 's'}, {@code 'e'} ou {@code 'o'}), é devolvida a
     * constante {@link #UNKNOWN}.
     * </p>
     *
     * @param ch o caractere a converter
     * @return a constante de {@code Compass} correspondente ao caractere, ou
     *         {@link #UNKNOWN} se o caractere não for reconhecido
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
