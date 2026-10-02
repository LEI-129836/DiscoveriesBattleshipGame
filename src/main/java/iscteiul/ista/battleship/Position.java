/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Represents a position on the Battleship game board grid.
 *
 * <p>A position is defined by its row and column coordinates.
 * It maintains state regarding whether it is occupied by a ship
 * and whether it has been targeted/hit during the game.</p>
 */
public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Creates a new position with the specified row and column coordinates.
     *
     * <p>By default, a newly created position is not occupied by any ship
     * and has not been hit.</p>
     *
     * @param row    the row coordinate of the position
     * @param column the column coordinate of the position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IPosition#getRow()
     */
    /**
     * Returns the row coordinate of this position.
     *
     * @return the row coordinate
     */
    @Override
    public int getRow() {
        return row;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IPosition#getColumn()
     */
    /**
     * Returns the column coordinate of this position.
     *
     * @return the column coordinate
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Computes a hash code value for this position based on its coordinates,
     * occupation state, and hit state.
     *
     * @return a hash code value for this position
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IPosition#equals(java.lang.Object)
     */
    /**
     * Compares this position to the specified object for equality.
     *
     * <p>Two positions are considered equal if they are both instances
     * of {@link IPosition} and share the same row and column coordinates.</p>
     *
     * @param otherPosition the object to compare with this position
     * @return {@code true} if the given object represents the same position;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Checks whether this position is adjacent to another position on the board.
     *
     * <p>Positions are considered adjacent if they are horizontally, vertically,
     * or diagonally neighboring (differing by at most 1 unit in both row and column).</p>
     *
     * @param other the other position to check adjacency against
     * @return {@code true} if the other position is adjacent; {@code false} otherwise
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IPosition#occupy()
     */
    /**
     * Marks this position as occupied by a ship.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IPosition#shoot()
     */
    /**
     * Marks this position as shot/hit.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IPosition#isOccupied()
     */
    /**
     * Checks whether this position is occupied by a ship.
     *
     * @return {@code true} if this position is occupied; {@code false} otherwise
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.IPosition#isHit()
     */
    /**
     * Checks whether this position has been shot/hit.
     *
     * @return {@code true} if this position has been hit; {@code false} otherwise
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Returns a string representation of this position.
     *
     * @return a string containing the row and column coordinates
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
