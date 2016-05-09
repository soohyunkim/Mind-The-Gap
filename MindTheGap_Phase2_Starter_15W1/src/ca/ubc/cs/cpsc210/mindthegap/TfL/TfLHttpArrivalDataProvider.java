package ca.ubc.cs.cpsc210.mindthegap.TfL;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {
        String stopPointId = stn.getID();
        String acc = "";
        for (Line line : stn.getLines()) {
            if (line.getId() != null) {
                String id = line.getId().toString();
                if (!acc.equals(""))
                    acc = acc + "," + id;
                else acc = acc + id;
            }
        }
        String request = "https://api.tfl.gov.uk/Line/" + acc + "/Arrivals?stopPointId=" + stopPointId;

        return new URL(request);
    }
}
