package ca.ubc.cs.cpsc210.mindthegap.tests.parsers;

import ca.ubc.cs.cpsc210.mindthegap.TfL.DataProvider;
import ca.ubc.cs.cpsc210.mindthegap.TfL.FileDataProvider;
import ca.ubc.cs.cpsc210.mindthegap.model.ArrivalBoard;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.parsers.TfLArrivalsParser;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLArrivalsDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;

import static org.junit.Assert.*;

/**
 * Unit tests to check basic parsing for TfL Arrivals data
 */
public class TfLArrivalsParserBasicTest extends AbstractTfLArrivalsParserTest {
    private String arrivalsJsonData;

    @Before
    public void setUp() throws Exception {
        DataProvider dataProvider = new FileDataProvider("./res/raw/arrivals_oxc.json");
        arrivalsJsonData = dataProvider.dataSourceToString();
        stn = new Station("id", "Oxford Circus", new LatLon(51.5, -0.1));
        stn.addLine(new Line(LineResourceData.CENTRAL, "central", "Central"));
        stn.addLine(new Line(LineResourceData.VICTORIA, "victoria", "Victoria"));
        try {
            TfLArrivalsParser.parseArrivals(stn, arrivalsJsonData);
        } catch (JSONException e) {
            fail("JSONException should not have been thrown while parsing data in arrivals_oxc.json");
        } catch (TfLArrivalsDataMissingException e) {
            fail("TfLArrivalsDataMissingException should not have been thrown while parsing data in arrivals_oxc.json");
        }

    }

    @Test
    public void testArrivalsBasicParsing() {
        try {
            TfLArrivalsParser.parseArrivals(stn, arrivalsJsonData);
        } catch (JSONException e) {
            fail("JSONException should not have been thrown while parsing data in arrivals_oxc.json");
        } catch (TfLArrivalsDataMissingException e) {
            fail("TfLArrivalsDataMissingException should not have been thrown while parsing data in arrivals_oxc.json");
        }
    }

    @Test
    public void testFirstArrival() {
        assertEquals(stn.getArrivalBoards().get(0).getArrivals().get(0).getTimeToStationInMins(), 2);
        assertEquals(stn.getArrivalBoards().get(0).getArrivals().get(0).getDestination(), "North Acton");
        assertEquals(stn.getArrivalBoards().get(0).getArrivals().get(0).getTravelDirn(), "Westbound");
        assertEquals(stn.getArrivalBoards().get(0).getArrivals().get(0).getPlatform(), "Westbound - Platform 1");
        assertEquals(stn.getArrivalBoards().get(0).getArrivals().get(0).getPlatformName(), "Platform 1");
    }


    @Test
    public void testNumArrivalsOfFirst() {
        assertEquals(stn.getArrivalBoards().get(0).getArrivals().size(), 5);
    }



}
