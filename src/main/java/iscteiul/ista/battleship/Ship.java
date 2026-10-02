package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an abstract ship in the Battleship game.
 *
 * <p>Provides core functionality and state shared across all ship types,
 * including position tracking, boundary detection, shot processing,
 * and proximity rules between ships on the board grid.</p>
 */
public abstract class Ship implements IShip {

    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Factory method that creates a concrete {@code Ship} instance of the specified kind.
     *
     * @param shipKind the type of ship to build
     * @param bearing  the bearing that determines the direction of the ship
     * @param pos      the initial position of the ship
     * @return a concrete {@code Ship} object, or {@code null} if the ship kind is unrecognized
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
     * Creates a {@code Ship} with the specified category, bearing, and initial position.
     *
     * @param category the category name of the ship
     * @param bearing  the bearing that determines the direction of the ship
     * @param pos      the initial position of the ship
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
     * Returns the category of the ship.
     *
     * @return the ship category
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns the list of positions occupied by the ship on the board.
     *
     * @return the list of occupied positions
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Returns the initial reference position of the ship.
     *
     * @return the initial position of the ship
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Returns the bearing of the ship.
     *
     * @return the compass bearing of the ship
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Determines whether the ship is still floating.
     *
     * <p>A ship is considered floating if at least one of its
     * occupied positions has not been hit.</p>
     *
     * @return {@code true} if at least one position remains unhit; {@code false} otherwise
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Returns the topmost (minimum row) position index occupied by the ship.
     *
     * @return the minimum row index occupied by the ship
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
     * Returns the bottommost (maximum row) position index occupied by the ship.
     *
     * @return the maximum row index occupied by the ship
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
     * Returns the leftmost (minimum column) position index occupied by the ship.
     *
     * @return the minimum column index occupied by the ship
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
     * Returns the rightmost (maximum column) position index occupied by the ship.
     *
     * @return the maximum column index occupied by the ship
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
     * Checks if the ship occupies the specified position.
     *
     * @param pos the position to check
     * @return {@code true} if the ship occupies the position; {@code false} otherwise
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
     * Checks if the ship is too close to another ship.
     *
     * @param other the other ship to check against
     * @return {@code true} if any position of the other ship is adjacent to this ship; {@code false} otherwise
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
     * Checks if the ship is too close to the specified position.
     *
     * @param pos the position to check
     * @return {@code true} if the position is adjacent to any occupied position of this ship; {@code false} otherwise
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * Shoots at the specified position.
     *
     * <p>If the position matches an occupied position of this ship,
     * it is marked as hit.</p>
     *
     * @param pos the targeted position
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
     * Returns a string representation of the ship.
     *
     * @return a string describing the ship's category, bearing, and initial position
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
