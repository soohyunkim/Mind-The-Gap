package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.model.StationManager;
import ca.ubc.cs.cpsc210.mindthegap.model.exception.StationException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for StationManager
 */
public class StationManagerTest {
    private StationManager stnManager;
    private static StationManager instance;
    private Station selected;

    @Before
    public void setUp() {
        stnManager = StationManager.getInstance();
        stnManager.clearSelectedStation();
        stnManager.clearStations();
    }

    @Test
    public void testStationManagerConstructor() {
        assertEquals(stnManager.getSelected(), null);
    }

    @Test
    public void testGetSelected() {
        assertEquals(stnManager.getSelected(), selected);
    }

    @Test
    public void testStations() throws StationException {
        Line li = new Line(LineResourceData.BAKERLOO, "bakerloo", "Bakerloo");
        Station station = new Station("940GZZLUBKE", "Barkingside Underground Station", new LatLon(51.585695, 0.08859599999999999));
        stnManager.addStationsOnLine(li);
        li.addStation(station);
        stnManager.clearSelectedStation();
        try {
        stnManager.setSelected(station);
        } catch (StationException e) {
        }
        assertEquals(stnManager.getStationWithId("940GZZLUBKE"), selected);
        stnManager.clearSelectedStation();
        assertEquals(stnManager.getSelected(), null);
        Line line = new Line(LineResourceData.BAKERLOO, "bakerloo", "Bakerloo");
        line.addStation(station);
        stnManager.addStationsOnLine(line);
        assertEquals(stnManager.getNumStations(), 1);
        stnManager.clearStations();
        assertEquals(stnManager.getNumStations(), 0);
    }

    @Test
    public void testFindNearestTo() throws StationException {
        LatLon pt = new LatLon(49.2827, -123.1207);
        List<Station> stns = new ArrayList<Station>();
        Station stn1 = new Station("hello", "weird", new LatLon(49.2611, -123.2531));
        Station stn2 = new Station("bye", "okay", new LatLon(30003210, 837493));
        Station stn3 = new Station("hello", "bye", new LatLon(49.2611, -123.2531));
        stns.add(stn1);
        stns.add(stn2);
        stns.add(stn3);
        Line line = new Line(LineResourceData.BAKERLOO, "bakerloo", "Bakerloo");
        line.addStation(stn1);
        line.addStation(stn2);
        line.addStation(stn3);
        stnManager.addStationsOnLine(line);
        assertTrue(stnManager.findNearestTo(pt) == stn1);
    }

}