package ca.ubc.cs.cpsc210.mindthegap.parsers;


import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Parser for route strings in TfL line data
 */
public class BranchStringParser {

    /**
     * Parse a branch string obtained from TFL
     *
     * @param branch branch string
     * @return list of lat/lon points parsed from branch string
     */
    public static List<LatLon> parseBranch(String branch) {
        List<LatLon> latLons = new ArrayList<LatLon>();

        try {
            JSONArray arr = new JSONArray(branch);
            JSONArray arr2 = arr.getJSONArray(0);

            for (int i = 0; i < arr2.length(); i++) {
                double lon = arr2.getJSONArray(i).getDouble(0);
                double lat = arr2.getJSONArray(i).getDouble(1);
                LatLon latLon = new LatLon(lat, lon);
                latLons.add(latLon);
            } return latLons;
        } catch (JSONException e) {
            return latLons;
        }
    }
}
