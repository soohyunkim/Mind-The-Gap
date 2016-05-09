package ca.ubc.cs.cpsc210.mindthegap.tests.model;


import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by soohyunkim on 15-10-27.
 */

/**
 * Unit tests for StationManager
 */
public class ArrivalTest {
    private Arrival arrival1;
    private Arrival arrival2;
    private Arrival arrival3;

    @Before
    public void setUp() throws Exception {
        arrival1 = new Arrival(80, "Surrey", "South - KingGeorge");
        arrival2 = new Arrival(190, "Vancouver", "North -   Oakridge");
        arrival3 = new Arrival(40, "Coquitlam", "East-Lougheed");
    }

    @Test
    public void testGetTravelDirn() {
        assertEquals(arrival1.getTravelDirn(), "South");
        assertEquals(arrival2.getTravelDirn(), "North");
    }

    @Test
    public void testGetPlatformName() {
        assertEquals(arrival1.getPlatformName(), "KingGeorge");
        assertEquals(arrival2.getPlatformName(), "Oakridge");
    }

    @Test
    public void testGetTimeToStationInMins() {
        assertEquals(arrival1.getTimeToStationInMins(), 2);
        assertEquals(arrival2.getTimeToStationInMins(), 4);
        assertEquals(arrival3.getTimeToStationInMins(), 1);
    }

    @Test
    public void testGetDestination() {
        assertEquals(arrival1.getDestination(), "Surrey");
        assertEquals(arrival2.getDestination(), "Vancouver");
    }

    @Test
    public void testGetPlatform() {
        assertEquals(arrival1.getPlatform(), "South - KingGeorge");
        assertEquals(arrival2.getPlatform(), "North -   Oakridge");
    }

    @Test
    public void testCompareTo() {
        assertEquals(arrival1.compareTo(arrival2), -110);
    }


}