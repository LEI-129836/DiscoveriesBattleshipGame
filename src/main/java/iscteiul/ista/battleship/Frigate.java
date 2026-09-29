/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents the Frigate ship in the battleship game.
 * <p>
 * The Frigate is a straight ship made up of 4 positions in a row, laid out
 * either vertically (NORTH or SOUTH bearing) or horizontally (EAST or WEST
 * bearing) starting from the reference position.
 * </p>
 */
public class Frigate extends Ship {

    /** Number of positions (cells) occupied by the Frigate. */
    private static final Integer SIZE = 4;

    /** Identifying name of the ship. */
    private static final String NAME = "Fragata";

    /**
     * Creates a new Frigate at the given position, with the specified orientation.
     * <p>
     * For a NORTH or SOUTH bearing, the ship's positions are laid out vertically,
     * extending downward from {@code pos}. For an EAST or WEST bearing, the
     * positions are laid out horizontally, extending rightward from {@code pos}.
     * </p>
     *
     * @param bearing the ship's bearing/orientation (NORTH, EAST, SOUTH or WEST)
     * @param pos      the reference (anchor) position from which the ship is built
     * @throws IllegalArgumentException if the given bearing does not match any of
     *                                  the valid values (NORTH, EAST, SOUTH, WEST)
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    /**
     * Returns the size (number of positions) of the Frigate.
     *
     * @return the constant value {@link #SIZE}, corresponding to 4 positions
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}