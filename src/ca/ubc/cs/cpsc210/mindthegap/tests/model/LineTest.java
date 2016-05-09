package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.Branch;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by soohyunkim on 15-10-28.
 */
public class LineTest {
    private Line line;
    private List<Station> stns;
    private Set<Branch> branches;
    private LineResourceData ld1;
    private String strID;
    private String strn;

    @Before
    public void setUp() throws Exception {
        line = new Line(LineResourceData.BAKERLOO, "bakerloo", "Bakerloo");
        branches = new HashSet<Branch>();
    }

    @Test
    public void TestGetName() {
        assertEquals(line.getName(), "Bakerloo");
    }

    @Test
    public void TestGetId() {
        assertEquals(line.getId(), "bakerloo");
    }

    @Test
    public void TestGetColour() {
        assertEquals(line.getColour(), LineResourceData.BAKERLOO.getColour());
    }

    @Test
    public void TestStations() {
        line.clearStations();
        assertTrue(line.getStations().isEmpty());
        LatLon l = new LatLon(-1023.3, 10093);
        LatLon k = new LatLon(-20309, 1.3948);
        Station stn1 = new Station("9382HELLO", "MetroTown", l);
        Station stn2 = new Station("2938BYE", "NewWestminster", k);
        line.addStation(stn1);
        line.addStation(stn2);
        assertEquals(line.getStations().size(), 2);
        line.removeStation(stn2);
        assertEquals(line.getStations().size(), 1);
        assertTrue(line.hasStation(stn1));
        assertFalse(line.hasStation(stn2));
        line.addStation(stn2);
        line.clearStations();
        assertTrue(line.getStations().isEmpty());
        assertFalse(stn1.hasLine(line));
    }

    @Test
    public void TestBranches() throws JSONException {
        line.getBranches().clear();
        assertTrue(line.getBranches().isEmpty());
        Branch branch1 = new Branch("[[[0.093493,51.6037],[0.091015,51.5956],[0.088596,51.5857]]]");
        Branch branch2 = new Branch("[[[0.092077,51.6134],[0.075051,51.6179],[0.043657,51.6172],[0.03398,51.6069]]]");
        line.addBranch(branch1);
        assertTrue(line.getBranches().contains(branch1));
        line.addBranch(branch2);
        assertTrue(line.getBranches().contains(branch2));
        assertEquals(line.getBranches().size(), 2);
    }

    @Test
    public void TestEqualsAndHashcode() {
        Line li = new Line(LineResourceData.BAKERLOO, "bakerloo", "Bakerloo");
        assertNotSame(line, li);
        assertTrue(line.equals(li));
        assertTrue(li.equals(line) && line.equals(li));
        assertEquals(line.hashCode(), li.hashCode());
    }
}

