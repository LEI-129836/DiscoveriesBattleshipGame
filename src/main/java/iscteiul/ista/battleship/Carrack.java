/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents a Carrack ship in the Battleship game.
 *
 * <p>A Carrack occupies three consecutive positions on the board.
 * Its positions are determined by its initial position and bearing.
 * For a north/south bearing, the ship is positioned vertically;
 * for an east/west bearing, it is positioned horizontally.</p>
 */
public class Carrack extends Ship {
    private static final int SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Creates a Carrack with the specified bearing and initial position.
     *
     * <p>The Carrack occupies {@value #SIZE} consecutive positions
     * starting at the given position. The positions are calculated
     * according to the specified bearing.</p>
     *
     * @param bearing the bearing that determines the direction of the Carrack
     * @param pos     the initial position of the Carrack
     * @throws IllegalArgumentException if the bearing is invalid
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Returns the size of the Carrack.
     *
     * @return the size of the ship
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
