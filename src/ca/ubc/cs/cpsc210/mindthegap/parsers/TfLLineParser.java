package ca.ubc.cs.cpsc210.mindthegap.parsers;

import ca.ubc.cs.cpsc210.mindthegap.model.*;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLLineDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A parser for the data returned by TFL line route query
 */
public class TfLLineParser extends TfLAbstractParser {

    /**
     * Parse line from JSON response produced by TfL.  No stations added to line if TfLLineDataMissingException
     * is thrown.
     *
     * @param lmd line meta-data
     * @return line parsed from TfL data
     * @throws JSONException               when JSON data does not have expected format
     * @throws TfLLineDataMissingException when
     *                                     <ul>
     *                                     <li> JSON data is missing lineName, lineId or stopPointSequences elements </li>
     *                                     <li> for a given sequence: </li>
     *                                     <ul>
     *                                     <li> the stopPoint array is missing </li>
     *                                     //     *                                     <li> all station elements are missing one of name, lat, lon or stationId elements </li>
     *                                     //     *                                     </ul>
     *                                     //     *                                     </ul>
     *                                     //
     */

    public static Line parseLine(LineResourceData lmd, String jsonResponse)
            throws JSONException, TfLLineDataMissingException {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        if (!(jsonObject.has("lineName") && jsonObject.has("lineId"))) {
            throw new TfLLineDataMissingException();
        }

        String lineName = jsonObject.getString("lineName");
        String lineId = jsonObject.getString("lineId");
        Line newLine = new Line(lmd, lineId, lineName);


        if (!jsonObject.has("lineStrings")) {
            throw new TfLLineDataMissingException();
        }

        JSONArray lineStrings = jsonObject.getJSONArray("lineStrings");

        for (int i = 0; i < lineStrings.length(); i++) {
            String branchString = lineStrings.getString(i);
            newLine.addBranch(new Branch(branchString));
        }

        if (!jsonObject.has("stopPointSequences")) {
            throw new TfLLineDataMissingException();
        }

        JSONArray jsonSequences = jsonObject.getJSONArray("stopPointSequences");

        try {
            for (int i = 0; i < jsonSequences.length(); i++) {
                JSONObject sequence = jsonSequences.getJSONObject(i);

                if (!sequence.has("stopPoint")) {
                    throw new TfLLineDataMissingException();
                }

                int countDataMissing = 0;
                JSONArray stopPoints = sequence.getJSONArray("stopPoint");

                for (int a = 0; a < stopPoints.length(); a++) {
                    JSONObject station = stopPoints.getJSONObject(a);
                    try {
                        if (!(station.has("name") && station.has("lat") && station.has("lon")
                                && station.has("stationId"))) {
                            throw new TfLLineDataMissingException();
                        }

                        String name = TfLAbstractParser.parseName(station.getString("name"));
                        double lat = station.getDouble("lat");
                        double lon = station.getDouble("lon");
                        String id = station.getString("stationId");
                        LatLon locn = new LatLon(lat, lon);

                        Station stn = StationManager.getInstance().getStationWithId(id);
                        if (stn != null) {
                            newLine.addStation(stn);
                        } else {
                            newLine.addStation(new Station(id, name, locn));
                        }

                    } catch (TfLLineDataMissingException e) {
                        countDataMissing++;
                    }
                }

                if (countDataMissing == stopPoints.length()) {
                    throw new TfLLineDataMissingException();
                }

            }
        } catch (TfLLineDataMissingException e) {
            newLine.clearStations();
            throw e;
        }

        return newLine;
    }
}

//    public static Line parseLine(LineResourceData lmd, String jsonResponse)
//            throws JSONException, TfLLineDataMissingException {
//
//        JSONObject jsonObject = new JSONObject(jsonResponse);
//        JSONArray jArray = jsonObject.getJSONArray("lineStrings");
//
//        if (!jsonObject.has("lineId") || !jsonObject.has("lineName") || !jsonObject.has("stopPointSequences")) {
//            throw new TfLLineDataMissingException();
//        }
//
//        String lineID = jsonObject.getString("lineId");
//        String lineName = jsonObject.getString("lineName");
//
//
//        Line line = new Line(lmd, lineID, lineName);
//
//        List<Branch> branches = new ArrayList<Branch>();
//
//        for (int i = 0; i < jArray.length(); i++) {
//            Branch br = new Branch(jArray.getString(i));
//            branches.add(br);
//            line.addBranch(br);
//        }
//
//        JSONArray stationArray = jsonObject.getJSONArray("stopPointSequences");
//
//
//        for (int i = 0; i < stationArray.length(); i++) {
//
//            if (!stationArray.getJSONObject(i).has("stopPoint")) {
//                throw new TfLLineDataMissingException();
//            }
//
//            int dataMissing = 0;
//            JSONArray stopPointArray = stationArray.getJSONObject(i).getJSONArray("stopPoint");
//            for (int a = 0; a < stopPointArray.length(); a++) {
//                JSONObject stopPoint = stationArray.getJSONObject(i).getJSONArray("StopPoint").getJSONObject(i);
//
//
//                try {
//                    if (!stopPointArray.getJSONObject(a).has("id") || !stopPointArray.getJSONObject(a).has("name")
//                            || !stopPointArray.getJSONObject(a).has("lat") || !stopPointArray.getJSONObject(a).has("lon"))
//                        throw new TfLLineDataMissingException();
//
//                    String id = stopPointArray.getJSONObject(a).getString("id");
//                    String name = TfLAbstractParser.parseName(stopPointArray.getJSONObject(a).getString("name"));
//                    double lat = stopPointArray.getJSONObject(a).getDouble("lat");
//                    double lon = stopPointArray.getJSONObject(a).getDouble("lon");
//
//                    LatLon locn = new LatLon(lat, lon);
//                    Station stn;
//                    stn = new Station(id, name, locn);
//                    line.addStation(stn);
//
//                } catch (TfLLineDataMissingException e) {
//                    dataMissing++;
//                }
//            }
//
//
//        } return line;
//    }
//}