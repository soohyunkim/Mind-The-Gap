package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.*;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by soohyunkim on 15-11-01.
 */
public class StationTest {
    private Station station;
    private String id;
    private String name;
    private LatLon locn;
    private Line line1;
    private String travelDirn1;
    private Line line2;
    private String travelDirn2;
    private ArrivalBoard arrivalBoard1;
    private ArrivalBoard arrivalBoard2;

    @Before
    public void setUp() throws Exception {
        station = new Station("940GZZLUBKE", "Barkingside Underground Station", new LatLon(51.585695, 0.08859599999999999));
    }

    @Test
    public void TestGetName() {
        assertEquals(station.getName(), "Barkingside Underground Station");
    }

    @Test
    public void TestGetLocn() {
        assertEquals(station.getLocn(), new LatLon(51.585695, 0.08859599999999999));
    }

    @Test
    public void TestGetId() {
        assertEquals(station.getID(), "940GZZLUBKE");
    }


    @Test
    public void TestLines() {
        assertTrue(station.getLines().isEmpty());
        line1 = new Line(LineResourceData.BAKERLOO, "bakerloo", "Bakerloo");
        line2 = new Line(LineResourceData.CENTRAL, "central", "Central");
        station.addLine(line1);
        station.addLine(line2);
        assertTrue(station.getLines().contains(line1));
        assertTrue(station.getLines().contains(line2));
        station.removeLine(line1);
        assertEquals(station.getLines().size(), 1);
        assertTrue(station.getLines().contains(line2));
        assertTrue(station.hasLine(line2));
        assertFalse(station.hasLine(line1));
    }

    @Test
    public void TestArrival() {
        travelDirn1 = "North";
        travelDirn2 = "South";
        assertTrue(station.getNumArrivalBoards() == 0);
        Arrival arrival1 = new Arrival(80, "Surrey", "South - KingGeorge");
        Arrival arrival2 = new Arrival(190, "Vancouver", "North -   Oakridge");
        Arrival arrival3 = new Arrival(40, "Coquitlam", "East-Lougheed");
        arrivalBoard1 = new ArrivalBoard(line1, travelDirn1);
        arrivalBoard1.addArrival(arrival1);
        arrivalBoard1.addArrival(arrival2);
        arrivalBoard2 = new ArrivalBoard(line2, travelDirn2);
        arrivalBoard2.addArrival(arrival3);
        station.addArrival(line1, arrival1);
        assertEquals(station.getNumArrivalBoards(), 1);
        station.clearArrivalBoards();
        assertEquals(station.getNumArrivalBoards(), 0);
    }

    @Test
    public void testEqualsAndHashcode() {
        id = "940GZZLUBKE";
        name = "Barkingside Underground Station";
        locn = new LatLon(51.585695, 0.08859599999999999);
        Station stn = new Station(id, name, locn);
        assertNotSame(station, stn);
        assertTrue(stn.equals(station));
        assertTrue(stn.equals(station) && station.equals(stn));
        assertEquals(station.hashCode(), stn.hashCode());
    }


}
