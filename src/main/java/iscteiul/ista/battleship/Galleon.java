/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents the Galleon ship in the battleship game.
 * <p>
 * The Galleon is a ship with an irregular shape made up of 5 positions,
 * whose layout on the board varies depending on the given bearing.
 * The ship's shape resembles an "L" or a "T", depending on its orientation.
 * </p>
 */
public class Galleon extends Ship {

    /** Number of positions (cells) occupied by the Galleon. */
    private static final Integer SIZE = 5;

    /** Identifying name of the ship. */
    private static final String NAME = "Galeao";

    /**
     * Creates a new Galleon at the given position, with the specified orientation.
     * <p>
     * Depending on the value of {@code bearing}, the corresponding position-filling
     * method is called ({@link #fillNorth(IPosition)}, {@link #fillEast(IPosition)},
     * {@link #fillSouth(IPosition)} or {@link #fillWest(IPosition)}).
     * </p>
     *
     * @param bearing the ship's bearing/orientation (NORTH, EAST, SOUTH or WEST)
     * @param pos      the reference (anchor) position from which the ship is built
     * @throws IllegalArgumentException if the given bearing does not match any of
     *                                  the valid values (NORTH, EAST, SOUTH, WEST)
     * @throws NullPointerException     if the given bearing is {@code null}
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    /**
     * Returns the size (number of positions) of the Galleon.
     *
     * @return the constant value {@link #SIZE}, corresponding to 5 positions
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Fills the Galleon's positions for the NORTH orientation.
     * <p>
     * Creates three positions in a horizontal line starting from {@code pos},
     * followed by two additional positions extending downward from the
     * middle column, forming an inverted "T".
     * </p>
     *
     * @param pos the reference (anchor) position of the ship
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Fills the Galleon's positions for the SOUTH orientation.
     * <p>
     * Creates two positions in a vertical line starting from {@code pos},
     * followed by three positions in a horizontal line on the row immediately
     * below, forming a "T".
     * </p>
     *
     * @param pos the reference (anchor) position of the ship
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Fills the Galleon's positions for the EAST orientation.
     * <p>
     * Creates the starting position at {@code pos}, three positions in a
     * horizontal line on the row below (shifted to the left), and a final
     * position vertically aligned with the first, forming an "L".
     * </p>
     *
     * @param pos the reference (anchor) position of the ship
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Fills the Galleon's positions for the WEST orientation.
     * <p>
     * Creates the starting position at {@code pos}, three positions in a
     * horizontal line on the row below (shifted to the right), and a final
     * position vertically aligned with the first, forming a mirrored "L".
     * </p>
     *
     * @param pos the reference (anchor) position of the ship
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
