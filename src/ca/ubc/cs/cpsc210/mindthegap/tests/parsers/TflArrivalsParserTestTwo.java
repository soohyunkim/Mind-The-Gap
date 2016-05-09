package ca.ubc.cs.cpsc210.mindthegap.tests.parsers;

import ca.ubc.cs.cpsc210.mindthegap.TfL.DataProvider;
import ca.ubc.cs.cpsc210.mindthegap.TfL.FileDataProvider;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.parsers.TfLArrivalsParser;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLArrivalsDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import junit.framework.Assert;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by soohyunkim on 15-11-06.
 */
public class TflArrivalsParserTestTwo extends AbstractTfLArrivalsParserTest{

    private String badArrivalData;

    @Before
    public void setUp() throws Exception {
        DataProvider dataProvider = new FileDataProvider("./res/raw/badarrivaldata.json");
        badArrivalData = dataProvider.dataSourceToString();
        stn = new Station("id", "Oxford Circus", new LatLon(51.5, -0.1));
        stn.addLine(new Line(LineResourceData.CENTRAL, "central", "Central"));
        stn.addLine(new Line(LineResourceData.VICTORIA, "victoria", "Victoria"));
    }

//
//    @Test (expected = TfLArrivalsDataMissingException.class)
//    public void testArrivalsBasicParsing() throws TfLArrivalsDataMissingException {
//        try {
//            TfLArrivalsParser.parseArrivals(stn, badArrivalData);
//        } catch (JSONException e) {
//            fail("JSONException should not have been thrown while parsing data in arrivals_oxc.json");
//        }
//    }



    @Test
    public void testMissingDataStations() throws JSONException {
        String jsonResponse = badArrivalData;
        JSONArray arrivals = new JSONArray(jsonResponse);
        assertFalse(arrivals.getJSONObject(0).has("destinationName"));
        assertTrue(arrivals.getJSONObject(1).has("destinationName"));
        assertEquals(stn.getName(), "Oxford Circus");


    }


}