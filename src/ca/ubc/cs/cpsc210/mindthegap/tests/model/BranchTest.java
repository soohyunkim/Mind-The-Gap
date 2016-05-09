package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.model.Branch;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by soohyunkim on 15-10-28.
 */
public class BranchTest {
    private Branch branch1;
    private Branch branch2;
    private String lineString;

    @Before
    public void setUp() throws Exception {
        branch1 = new Branch("[[[0.093493,51.6037],[0.091015,51.5956],[0.088596,51.5857]]]");
        branch2 = new Branch("[[[0.092077,51.6134],[0.075051,51.6179],[0.043657,51.6172],[0.03398,51.6069]]]");
    }

    @Test
    public void TestGetPts() {
        List<LatLon> latLons = new ArrayList<LatLon>();
        LatLon latlon1 = new LatLon(51.6037, 0.093493);
        LatLon latlon2 = new LatLon(51.5956, 0.091015);
        LatLon latlon3 = new LatLon(51.5857, 0.088596);
        assertEquals(branch1.getPoints().get(0), latlon1);
        assertEquals(branch1.getPoints().get(1), latlon2);
        assertEquals(branch1.getPoints().get(2), latlon3);
    }

    @Test
    public void TestEqualsandHashcode() throws JSONException {
        lineString = "[[[0.093493,51.6037],[0.091015,51.5956],[0.088596,51.5857]]]";
        Branch b = new Branch(lineString);
        assertNotSame(branch1, b);
        assertTrue(branch1.equals(b));
        assertTrue(b.equals(branch1) && branch1.equals(b));
        assertEquals(branch1.hashCode(), b.hashCode());

    }

}
