package ca.ubc.cs.cpsc210.mindthegap.model;

import ca.ubc.cs.cpsc210.mindthegap.model.exception.StationException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import ca.ubc.cs.cpsc210.mindthegap.util.SphericalGeometry;


import java.util.*;

/**
 * Manages all tube stations on network.
 * <p/>
 * Singleton pattern applied to ensure only a single instance of this class that
 * is globally accessible throughout application.
 */
public class StationManager implements Iterable<Station> {
    public static final int RADIUS = 10000;
    private static StationManager instance;
    private Set<Station> stns;
    private Station selected;
    private String id;
    private List<Line> lines;


    /**
     * Constructs station manager with empty set of stations and null as the selected station
     */
    private StationManager() {
        stns = new HashSet<Station>();
        lines = new ArrayList<Line>();
        this.selected = null;
    }

    /**
     * Gets one and only instance of this class
     *
     * @return instance of class
     */
    public static StationManager getInstance() {
        // Do not modify the implementation of this method!
        if (instance == null) {
            instance = new StationManager();
        }

        return instance;
    }

    public Station getSelected() {
        return selected;
    }

    /**
     * Get station with given id or null if no such station is found in this manager
     *
     * @param id the id of this station
     * @return station with given id or null if no such station is found
     */
    public Station getStationWithId(String id) {
        Station retStation = null;
        for (Station current : stns) {
            if (current.getID().equals(id))
                retStation = current;
        }
        return retStation;
    }


    /**
     * Set the station selected by user
     *
     * @param selected station selected by user
     * @throws StationException when station manager doesn't contain selected station
     */
    public void setSelected(Station selected) throws StationException {
        if (!stns.contains(selected)) {
            throw new StationException();}
        else this.selected = selected;}

    /**
     * Clear selected station (selected station is null)
     */
    public void clearSelectedStation() {
        this.selected = null;

    }


    /**
     * Add all stations on given line. Station added only if it is not already in the collection.
     *
     * @param line the line from which stations are to be added
     */
    public void addStationsOnLine(Line line) {
        for (Station li : line.getStations()) {
            if (!stns.contains(li))
                stns.add(li);
            // access station from line and put them into station manager stations
        }
    }

    /**
     * Get number of stations managed
     *
     * @return number of stations added to manager
     */
    public int getNumStations() {
        return stns.size();
    }

    /**
     * Remove all stations from station manager
     */
    public void clearStations() {
        stns.clear();
    }

    /**
     * Find nearest station to given point. Returns null if no station is closer than RADIUS metres.
     *
     * @param pt point to which nearest station is sought
     * @return station closest to pt but less than 10,000m away; null if no station is within RADIUS metres of pt
     */
    public Station findNearestTo(LatLon pt) {
        List<Station> list = new ArrayList<Station>(stns);
        List<Station> filteredList = new ArrayList<Station>();
        List<Double> distanceList = new ArrayList<Double>();
        double min = RADIUS;
        Station minStation = null;
        for (Station current : list) {
            double lon1 = current.getLocn().getLongitude();
            double lon2 = pt.getLongitude();
            double lat1 = current.getLocn().getLatitude();
            double lat2 = pt.getLatitude();
            LatLon pt1 = new LatLon(lat1, lon1);
            LatLon pt2 = new LatLon(lat2, lon2);
            if (SphericalGeometry.distanceBetween(pt1, pt2) < min) {
                min = SphericalGeometry.distanceBetween(pt1, pt2);
                minStation = current;
            }
        }
        return minStation;
    }

    @Override
    public Iterator<Station> iterator() {
        // Do not modify the implementation of this method!
        return stns.iterator();
    }
}


