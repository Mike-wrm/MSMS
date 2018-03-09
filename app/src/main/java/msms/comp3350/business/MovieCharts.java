package msms.comp3350.business;

import java.util.ArrayList;

import msms.comp3350.objects.Movie;
import msms.comp3350.presentation.SpinnerArrays;

public abstract class MovieCharts
{
    public static String[][] getMovieCategories()
    {
        AccessMovies access = new AccessMovies();
        ArrayList<Movie> movies = new ArrayList();
        access.getMovies(movies);
        int numMovies = movies.size();
        String[] categories = SpinnerArrays.getCategories();
        int[] rawData = new int[categories.length];
        for(int i = 0 ; i < numMovies ; i++)
        {
            Movie currMovie = movies.get(i);
            ArrayList<String> catList = currMovie.getCategory();
            for(int j = 0 ; j < catList.size() ; j++)
            {
                for(int k = 1 ; k < categories.length ; k++)
                {
                    if(catList.get(j).equals(categories[k]))
                    {
                        rawData[k]++;
                        break;
                    }
                }
            }
        }
        String[] data = new String[rawData.length];
        for(int i = 0 ; i < data.length ; i++)
        {
            data[i] = Integer.toString(rawData[i]);
        }
        return new String[][]{ categories, data };
    }
}
