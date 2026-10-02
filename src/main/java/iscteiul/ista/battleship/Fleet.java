/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a fleet of ships in the Battleship game.
 *
 * <p>A fleet manages a collection of ships on the board, enforcing rules
 * regarding board boundaries, maximum fleet size, and proximity constraints
 * between ships.</p>
 *
 */
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
     * Constructs an empty Fleet.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Returns the list of ships currently in the fleet.
     *
     * @return the list of ships
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IFleet#addShip(battleship.IShip)
     */
    /**
     * Adds a ship to the fleet if it satisfies all placement rules.
     *
     * <p>The ship will only be added if the total fleet size does not exceed
     * {@link IFleet#FLEET_SIZE}, the ship is within board boundaries,
     * and it does not violate collision or proximity rules with existing ships.</p>
     *
     * @param s the ship to add
     * @return {@code true} if the ship was successfully added; {@code false} otherwise
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

    /*
     * (non-Javadoc)
     *
     * @see battleship.IFleet#getShipsLike(java.lang.String)
     */
    /**
     * Returns a list of ships in the fleet that belong to a specific category.
     *
     * @param category the category of ships to search for (e.g., "Galeao", "Fragata", "Nau", "Caravela", "Barca")
     * @return a list containing ships of the given category
     */

    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IFleet#getFloatingShips()
     */
    /**
     * Returns a list of ships in the fleet that are still floating.
     *
     * @return a list of ships that have not been sunk
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IFleet#shipAt(battleship.IPosition)
     */
    /**
     * Finds the ship occupying a given position on the board.
     *
     * @param pos the position to check
     * @return the {@link IShip} at the specified position, or {@code null} if no ship occupies it
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Checks whether a ship is completely positioned within the boundaries of the board.
     *
     * @param s the ship to validate
     * @return {@code true} if the ship is entirely inside the board; {@code false} otherwise
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks whether adding a ship creates a collision or proximity violation with any existing ship in the fleet.
     *
     * @param s the ship to validate
     * @return {@code true} if there is a collision risk or proximity conflict; {@code false} otherwise
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }


    /**
     * This operation shows the state of a fleet
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
