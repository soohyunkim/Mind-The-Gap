package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import ca.ubc.cs.cpsc210.mindthegap.model.ArrivalBoard;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by soohyunkim on 15-10-27.
 */


public class ArrivalBoardTest {
    private ArrivalBoard arrivalBoard1;
    private ArrivalBoard arrivalBoard2;
    private Line line1;
    private String travelDirn1;
    private Line line2;
    private String travelDirn2;

    @Before
    public void setUp() throws Exception {
        arrivalBoard1 = new ArrivalBoard(line1, travelDirn1);
        arrivalBoard2 = new ArrivalBoard(line2, travelDirn2);
    }

    @Test
    public void testGetLine() {
        assertEquals(arrivalBoard1.getLine(), line1);
        assertEquals(arrivalBoard2.getLine(), line2);
    }

    @Test
    public void testGetTravelDirn() {
        assertEquals(arrivalBoard1.getTravelDirn(), travelDirn1);
        assertEquals(arrivalBoard2.getTravelDirn(), travelDirn2);
    }

    @Test
    public void testArrivals() {
        arrivalBoard1.clearArrivals();
        assertEquals(arrivalBoard1.getNumArrivals(), 0);
        Arrival arrival1 = new Arrival(80, "Surrey", "South - KingGeorge");
        Arrival arrival2 = new Arrival(190, "Vancouver", "North -   Oakridge");
        Arrival arrival3 = new Arrival(40, "Coquitlam", "East-Lougheed");
        arrivalBoard1.addArrival(arrival1);
        arrivalBoard1.addArrival(arrival2);
        arrivalBoard1.addArrival(arrival3);
        assertEquals(arrivalBoard1.getNumArrivals(), 3);
        arrivalBoard1.clearArrivals();
        assertEquals(arrivalBoard1.getNumArrivals(), 0);
    }

    @Test
    public void testEqualsAndHashcode() {
        ArrivalBoard ab = new ArrivalBoard(line1, travelDirn1);
        assertNotSame(arrivalBoard1, ab);
        assertTrue(arrivalBoard1.equals(ab));
        assertTrue(ab.equals(arrivalBoard1) && arrivalBoard1.equals(ab));
        assertEquals(arrivalBoard1.hashCode(), ab.hashCode());
    }

}
