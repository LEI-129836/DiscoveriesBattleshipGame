package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the main game session in the Battleship game.
 *
 * <p>Manages the fleet, tracks fired shots, handles shot validation,
 * records statistics for hits, sinks, invalid, and repeated shots,
 * and provides visualization methods to display the board grid.</p>
 */
public class Game implements IGame {

    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;

    /**
     * Creates a {@code Game} instance with the specified fleet.
     *
     * <p>Initializes the shot history list and resets shot counters.</p>
     *
     * @param fleet the fleet to be used in this game session
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        countHits = 0;
        countSinks = 0;
        this.fleet = fleet;
    }

    /**
     * Fires a shot at the specified position on the board.
     *
     * <p>Validates the shot position and verifies if it was already targeted.
     * If valid and new, checks for ship hits and records whether the ship was sunk.</p>
     *
     * @param pos the targeted board position
     * @return the hit {@link IShip} if the shot sinks it; {@code null} otherwise
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns the list of valid shots fired during the game.
     *
     * @return the list of targeted positions
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Returns the total count of repeated shots fired.
     *
     * @return the number of repeated shots
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Returns the total count of invalid shots fired.
     *
     * @return the number of invalid shots
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Returns the total count of successful hits.
     *
     * @return the number of hits
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Returns the total number of ships sunk so far.
     *
     * @return the count of sunk ships
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Returns the number of ships in the fleet that are still floating.
     *
     * @return the count of remaining floating ships
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Checks if the specified position is within the valid board grid boundaries.
     *
     * @param pos the position to validate
     * @return {@code true} if the position is valid; {@code false} otherwise
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Checks if a shot has already been fired at the specified position.
     *
     * @param pos the position to check
     * @return {@code true} if the position was already targeted; {@code false} otherwise
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Renders a character-based map of the board displaying the specified positions.
     *
     * @param positions the list of positions to mark on the board
     * @param marker    the character marker to place at the specified positions
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }

    /**
     * Prints the board grid showing all valid shots that have been fired.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Prints the board grid showing the layout of the fleet.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}