/**
 * Represents a fleet of ships in the Battleship Game.
 *
 *<p>A fleet manages a collection of ships and provides operations
 * to add ships, retrieve ships by category, find floating ships,
 * and determine which ship occupies a given position.</p>
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

public class Fleet implements IFleet {
    /**
     * This operation prints all the given ships
     *
     * @param ships The list of ships
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------
    /**
     * The list of ships belonging to this fleet.
     */
    
    private List<IShip> ships;

     /**
     * Creates an empty fleet.
     */
    
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Returns all the ships belonging to this fleet.
     *
     * @return the list of ships in the fleet
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

   /**
     * Adds a ship to the fleet if it is inside the board,
     * does not collide with another ship, and the fleet has
     * not reached its maximum size.
     *
     * @param s the ship to add
     * @return {@code true} if the ship was successfully added;
     *         {@code false} otherwise
     */
    
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

     /**
     * Returns all ships belonging to the specified category.
     *
     * @param category the category of ships to search for
     * @return a list containing all ships that belong to the
     *         specified category
     */
    
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

   /**
     * Returns all ships that are still floating.
     *
     * @return a list containing the ships that have not yet sunk
     */
    
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Finds the ship occupying the specified position.
     *
     * @param pos the position to search for
     * @return the ship occupying the position, or {@code null}
     *         if no ship occupies that position
     */
    
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Checks whether a ship is completely inside the game board.
     *
     * @param s the ship to check
     * @return {@code true} if the ship is inside the board;
     *         {@code false} otherwise
     */

    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks whether a ship is too close to any ship already
     * belonging to the fleet.
     *
     * @param s the ship to check
     * @return {@code true} if the ship is too close to another
     *         ship; {@code false} otherwise
     */

    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Displays the current state of the fleet.
     *
     * <p>The status includes all ships, floating ships,
     * and ships grouped by their respective categories.</p>
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * This operation prints all the ships of a fleet belonging to a particular
     * category
     *
     * @param category The category of ships of interest
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * This operation prints all the ships of a fleet but not yet shot
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * This operation prints all the ships of a fleet
     */
    void printAllShips() {
        printShips(ships);
    }

}
