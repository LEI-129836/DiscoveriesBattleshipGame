/**
 *
 */
package iscteiul.ista.battleship;
/**
 * Represents a barge in the Battleship game.
 *
 * <p>A barge occupies a single position on the game board.</p>
 */
public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Creates a new barge.
     *
     * @param bearing the bearing of the barge
     * @param pos the upper-left position occupied by the barge
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }
    /**
     * Returns the size of the barge.
     *
     * @return the size of the barge, which is always {@value #SIZE}
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
