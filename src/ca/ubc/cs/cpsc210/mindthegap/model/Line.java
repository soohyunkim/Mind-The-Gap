package ca.ubc.cs.cpsc210.mindthegap.model;

import java.util.*;

/**
 * Represents a line on the underground with a name, id, list of stations and set of branches.
 * <p/>
 * Invariants:
 * - no duplicates in list of stations
 * - iterator iterates over stations in the order in which they were added to the line
 */
public class Line implements Iterable<Station> {
    private List<Station> stns;
    private LineResourceData lmd;
    private String id;
    private String name;
    private Set<Branch> branches;

    /**
     * Constructs a line with given resource data, id and name.
     * List of stations and list of branches are empty.
     *
     * @param lmd  the line meta-data
     * @param id   the line id
     * @param name the name of the line
     */
    public Line(LineResourceData lmd, String id, String name) {
        this.lmd = lmd;
        this.id = id;
        this.name = name;
        stns = new ArrayList<Station>();
        branches = new HashSet<Branch>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    /**
     * Get colour specified by line resource data
     *
     * @return colour in which to plot this line
     */
    public int getColour() {
        return lmd.getColour();
    }

    /**
     * Add station to line.
     *
     * @param stn the station to add to this line
     */
    public void addStation(Station stn) {
        if (!stns.contains(stn)) {
            stns.add(stn);
            stn.addLine(this);
        }
    }

    /**
     * Remove station from line
     *
     * @param stn the station to remove from this line
     */
    public void removeStation(Station stn) {
        if (stns.contains(stn)) {
            stns.remove(stn);
            stn.removeLine(this);
        }
    }

    /**
     * Clear all stations from this line
     */
    public void clearStations() {

        for (Station stns : this.stns) {
            stns.getLines().remove(this);
        }
        stns.clear();
//
//        List<Station> tempstations = new ArrayList<Station>();
//        tempstations.addAll(stns);
//        stns.clear();
//
//        for (int i = 0; i < tempstations.size(); ++i){
//            tempstations.get(i).removeLine(this);
//        }

    }

    public List<Station> getStations() {
        return stns;
//                Collections.unmodifiableList(stns);
    }

    /**
     * Determines if this line has a stop at a given station\
     *
     * @param stn the station
     * @return true if line has a stop at given station
     */
    public boolean hasStation(Station stn) {
        if (stns.contains(stn))
            return true;
        else return false;
    }

    /**
     * Add a branch to this line
     *
     * @param b the branch to add to line
     */
    public void addBranch(Branch b) {
        branches.add(b);
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        return id.equals(line.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public Iterator<Station> iterator() {
        // Do not modify the implementation of this method!
        return stns.iterator();
    }
}
