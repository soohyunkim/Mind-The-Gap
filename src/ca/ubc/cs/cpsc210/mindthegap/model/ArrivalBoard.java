package ca.ubc.cs.cpsc210.mindthegap.model;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Represents an arrivals board for a particular station, on a particular line,
 * for trains traveling in a particular direction (as indicated by platform prefix).
 * <p/>
 * Invariant: maintains arrivals in order of time to station
 * (first train to arrive will be listed first).
 */
public class ArrivalBoard implements Iterable<Arrival> {
    private List<Arrival> arrivals;
    private Line line;
    private String travelDirn;


    /**
     * Constructs an arrival board for the given line with an empty list of arrivals
     * and given travel direction.
     *
     * @param line       line on which arrivals listed on this board operate (cannot be null)
     * @param travelDirn the direction of travel
     */
    public ArrivalBoard(Line line, String travelDirn) {
        this.line = line;
        this.travelDirn = travelDirn;
        arrivals = new ArrayList<Arrival>();
    }

    public Line getLine() {
        return line;
    }

    public String getTravelDirn() {
        return travelDirn;
    }


    /**
     * Get total number of arrivals posted on this arrival board
     *
     * @return total number of arrivals
     */
    public int getNumArrivals() {
        return arrivals.size();
    }

    /**
     * Add a train arrival this arrivals board.
     *
     * @param arrival the arrival to add to this arrivals board
     */
    public void addArrival(Arrival arrival) {
        arrivals.add(arrival);
        Collections.sort(arrivals);
    }

    /**
     * Clear all arrivals from this arrival board
     */
    public void clearArrivals() {
        arrivals.clear();
    }

    public List<Arrival> getArrivals() {
        return arrivals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrivalBoard that = (ArrivalBoard) o;

        if (line != null ? !line.equals(that.line) : that.line != null) return false;
        return !(travelDirn != null ? !travelDirn.equals(that.travelDirn) : that.travelDirn != null);

    }

    @Override
    public int hashCode() {
        int result = line != null ? line.hashCode() : 0;
        result = 31 * result + (travelDirn != null ? travelDirn.hashCode() : 0);
        return result;
    }

    /**
     * Two ArrivalBoards are equal if their lines are equal and travel directions are equal
     */



    @Override
    /**
     * Produces an iterator over the arrivals on this arrival board
     * ordered by time to arrival (first train to arrive is produced
     * first).
     */
    public Iterator<Arrival> iterator() {
        // Do not modify the implementation of this method!
        return arrivals.iterator();
    }
}
