package msms.comp.business;

import java.util.ArrayList;

import msms.comp.objects.Movie;
import msms.comp.objects.WatchedEvent;

public class Calculate
{
    public static String avgRating (Movie currMovie)
    {
        String returnString = "N/A";

        AccessWatchedEvents access = new AccessWatchedEvents();
        ArrayList<WatchedEvent> userViews = new ArrayList<WatchedEvent>();
        access.getMoviesUsers(userViews, currMovie);

        int totalScore = 0;
        double avgScore = 0;
        int listSize = userViews.size();

        if (listSize > 0)
        {
            for (int i = 0; i < listSize; i++)
            {
                WatchedEvent currView = userViews.get(i);
                int currScore = currView.getRating();
                totalScore += currScore;
            }

            avgScore = ((double) totalScore) / ((double) listSize);
            returnString = String.format("%.1f/10", avgScore);
        }

        return returnString;
    }
}
