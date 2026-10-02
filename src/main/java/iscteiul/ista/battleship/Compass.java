/**
 *
 */
package iscteiul.ista.battleship;

/**
 * @author fba
 */
/**
 * Represents the cardinal compass directions used to specify ship orientations in the Battleship game.
 *
 * <p>The directions correspond to North ('n'), South ('s'), East ('e'),
 * West ('o'), and an Unknown/invalid direction ('u').</p>
 */
public enum Compass {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('o'), UNKNOWN('u');

    private final char c;

    /**
     * Constructs a Compass direction with its associated character representation.
     *
     * @param c the character representing the compass direction
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Returns the character representation of this compass direction.
     *
     * @return the direction character ('n', 's', 'e', 'o', or 'u')
     */
    public char getDirection() {
        return c;
    }

    /**
     * Returns a string representation containing the direction character.
     *
     * @return a string containing the character representation of this direction
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converts a character into its corresponding {@link Compass} enum constant.
     *
     * <p>Valid characters are 'n' (North), 's' (South), 'e' (East), and 'o' (West).
     * Any other character will resolve to {@link #UNKNOWN}.</p>
     *
     * @param ch the character representing a direction
     * @return the matching {@link Compass} direction, or {@link #UNKNOWN} if invalid
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
