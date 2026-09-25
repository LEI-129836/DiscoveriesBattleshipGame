/**
 *
 */
package iscteiul.ista.battleship;
/**
 * Represents a Caravel ship in the Battleship game.
 *
 * <p>A Caravel occupies two consecutive positions on the board.
 * Its positions are determined by its initial position and bearing.
 * For a north/south bearing, the ship is positioned vertically;
 * for an east/west bearing, it is positioned horizontally.</p>
 */
public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Creates a Caravel with the specified bearing and initial position.
     *
     * <p>The Caravel occupies {@value #SIZE} consecutive positions
     * starting at the given position. The positions are calculated
     * according to the specified bearing.</p>
     *
     * @param bearing the bearing that determines the direction of the Caravel
     * @param pos     the initial position of the Caravel
     * @throws NullPointerException     if {@code bearing} is {@code null}
     * @throws IllegalArgumentException if the bearing is invalid
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }
    }

    /**
     * Returns the size of the Caravel.
     *
     * @return the number of positions occupied by the Caravel
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}

