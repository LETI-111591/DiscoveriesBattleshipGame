package iscteiul.ista.battleship;

/**
 * Represents a Carrack ship in the Battleship game.
 *
 * <p>A Carrack occupies three consecutive positions on the board.
 * Its positions are determined by its initial position and bearing.</p>
 */
public class Carrack extends Ship {

    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Creates a new Carrack ship.
     *
     * @param bearing the direction in which the ship is placed
     * @param pos the initial position of the ship
     * @throws IllegalArgumentException if the bearing is not valid
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(
                        new Position(pos.getRow() + r, pos.getColumn())
                    );
                break;

            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(
                        new Position(pos.getRow(), pos.getColumn() + c)
                    );
                break;

            default:
                throw new IllegalArgumentException(
                    "ERROR! invalid bearing for the carrack"
                );
        }
    }

    /**
     * Returns the number of positions occupied by the Carrack.
     *
     * @return the size of the Carrack, which is 3
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }
}
