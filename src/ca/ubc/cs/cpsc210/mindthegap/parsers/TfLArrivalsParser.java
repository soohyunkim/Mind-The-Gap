package ca.ubc.cs.cpsc210.mindthegap.parsers;

import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLArrivalsDataMissingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A parser for the data returned by the TfL station arrivals query
 */
public class TfLArrivalsParser extends TfLAbstractParser {

    /**
     * Parse arrivals from JSON response produced by TfL query.  All parsed arrivals are
     * added to given station assuming that corresponding JSON object as all of:
     * timeToStation, platformName, lineID and one of destinationName or towards.  If
     * any of the aforementioned elements is missing, the arrival is not added to the station.
     *
     * @param stn          station to which parsed arrivals are to be added
     * @param jsonResponse the JSON response produced by TfL
     * @throws JSONException                   when JSON response does not have expected format
     * @throws TfLArrivalsDataMissingException when all arrivals are missing at least one of the following:
     *                                         <ul>
     *                                         <li>timeToStation</li>
     *                                         <li>platformName</li>
     *                                         <li>lineId</li>
     *                                         <li>destinationName and towards</li>
     *                                         </ul>
     */
//    public static void parseArrivals(Station stn, String jsonResponse)
//            throws JSONException, TfLArrivalsDataMissingException {
//        JSONArray jsonArray = new JSONArray(jsonResponse);
//
//
//        boolean dataMissing = true;
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonArrival = jsonArray.getJSONObject(i);
//            try {
//                addArrivalToStn(stn, jsonArrival);
//            } catch (TfLArrivalsDataMissingException e) {
//                dataMissing = false;
//            }
//        }
//
//
//        if (dataMissing == true) {
//            throw new TfLArrivalsDataMissingException();
//        }
//    }
//
//
//    public static void addArrivalToStn(Station stn, JSONObject jsonArrival)
//            throws JSONException, TfLArrivalsDataMissingException {
//
//        int timeToStation;
//        String destinationName;
//        String platformName;
//        String lineId;
//
//        if (!jsonArrival.has("timeToStation") ||
//                (!jsonArrival.has("destinationName") &&
//                        !jsonArrival.has("towards")) ||
//                !jsonArrival.has("platformName") ||
//                !jsonArrival.has("lineId")
//                )
//            throw new TfLArrivalsDataMissingException();
//
//        timeToStation = jsonArrival.getInt("timeToStation");
//
//        try {
//            destinationName = TfLAbstractParser.parseName(jsonArrival.getString("destinationName"));
//        } catch (JSONException e) {
//            destinationName = jsonArrival.getString("towards");
//        }
//
//        platformName = jsonArrival.getString("platformName");
//
//        lineId = jsonArrival.getString("lineId");
//
//
//        Arrival arrival = new Arrival(timeToStation, destinationName, platformName);
//
//        for (Line l : stn.getLines()) {
//            if (l.getId().equals(lineId))
//                stn.addArrival(l, arrival);
//        }
//    }
//}

    public static void parseArrivals(Station stn, String jsonResponse)
            throws JSONException, TfLArrivalsDataMissingException {
        JSONArray arrivals = new JSONArray(jsonResponse);
        int countMissingArrivals = 0;

        for (int i = 0; i < arrivals.length(); i++) {
            JSONObject jsonArrival = arrivals.getJSONObject(i);
            try {
                addArrivalToStn(stn, jsonArrival);
            } catch(TfLArrivalsDataMissingException e) {
                countMissingArrivals++;
            }
        }
        if (countMissingArrivals == arrivals.length()) {
            throw new TfLArrivalsDataMissingException();
        }
    }

    public static void addArrivalToStn(Station stn, JSONObject jsonArrival)
            throws JSONException, TfLArrivalsDataMissingException {

        int timeToStation;
        String destinationName;
        String platformName;
        String lineId;

        if (!jsonArrival.has("timeToStation") ||
                (!jsonArrival.has("destinationName") &&
                        !jsonArrival.has("towards")) ||
                !jsonArrival.has("platformName") ||
                !jsonArrival.has("lineId")
                )
            throw new TfLArrivalsDataMissingException();

        timeToStation = jsonArrival.getInt("timeToStation");

        try {
            destinationName = TfLAbstractParser.parseName(jsonArrival.getString("destinationName"));
        } catch (JSONException e) {
            destinationName = jsonArrival.getString("towards");
        }

        platformName = jsonArrival.getString("platformName");

        lineId = jsonArrival.getString("lineId");


        Arrival arrival = new Arrival(timeToStation, destinationName, platformName);

        for (Line l : stn.getLines()) {
            if (l.getId().equals(lineId))
                stn.addArrival(l, arrival);
        }
    }
}


