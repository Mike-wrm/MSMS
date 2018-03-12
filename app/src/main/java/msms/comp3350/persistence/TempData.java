package msms.comp3350.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;

import msms.comp3350.business.SortEnums;
import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.presentation.MainActivity;


public class TempData implements DataAccessor
{
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Movie> movies;
    private ArrayList<User> users;

    public TempData(String dbName)
    {
        this.dbName = dbName;
    }

    public TempData()
    {
        this(MainActivity.dbName);
    }

    public void open(String dbName)
    {
        Movie movie;
        User user;
        ArrayList<String> movie1 = new ArrayList<>();
        movie1.add("Comedy");

        ArrayList<String> movie2 = new ArrayList<>();
        movie2.add("Comedy");

        ArrayList<String> movie3 = new ArrayList<>();
        movie3.add("Comedy");
        movie3.add("Family");

        ArrayList<String> movie4 = new ArrayList<>();
        movie4.add("Comedy");
        movie4.add("Family");

        ArrayList<String> movie5 = new ArrayList<>();
        movie5.add("Horror");

        ArrayList<String> movie6 = new ArrayList<>();
        movie6.add("Horror");

        ArrayList<String> movie7 = new ArrayList<>();
        movie7.add("Action");
        movie7.add("Trending");

        ArrayList<String> movie8 = new ArrayList<>();
        movie8.add("Action");
        movie8.add("Recent");

        ArrayList<String> movie9 = new ArrayList<>();
        movie9.add("Action");
        movie9.add("Trending");

        Calendar date1 = Calendar.getInstance(); // January = 0, December = 11.
        date1.set(2018, 4, 3);
        Calendar date2 = Calendar.getInstance();
        date2.set(2018, 6, 23);
        Calendar date3 = Calendar.getInstance();
        date3.set(2018, 3, 11);
        Calendar date4 = Calendar.getInstance();
        date4.set(2019, 6, 7);
        Calendar date5 = Calendar.getInstance();
        date5.set(2021, 4, 1);
        Calendar date6 = Calendar.getInstance();
        date6.set(2018, 6, 23);
        Calendar date7 = Calendar.getInstance();
        date7.set(2018, 11, 31);
        Calendar date8 = Calendar.getInstance();
        date8.set(2019, 11, 31);
        Calendar date9 = Calendar.getInstance();
        date9.set(2020, 10, 1);

        Calendar userDate1 = Calendar.getInstance();
        userDate1.set(2018,3,30);
        Calendar userDate2 = Calendar.getInstance();
        userDate2.set(2020,11,31);


        movies = new ArrayList<Movie>();
        users = new ArrayList<User>();


        movie = new Movie("South Park: Bigger, Longer & Uncut", 1999, 7, movie1, date1, "Ipsum Lorem...");
        movie.setmID(111);
        movies.add(movie);

        movie = new Movie("Eddie Murphy: Raw", 1987, 5, movie2, date2, "Ipsum Lorem...");
        movie.setmID(222);
        movies.add(movie);

        movie = new Movie("Toy Story", 1995, 10, movie3, date3, "Ipsum Lorem...");
        movie.setmID(333);
        movies.add(movie);

        movie = new Movie("Shrek", 2001, 8, movie4, date4, "Ipsum Lorem...");
        movie.setmID(444);
        movies.add(movie);

        movie = new Movie("Friday the 13th", 2009, 3, movie5, date5, "Ipsum Lorem...");
        movie.setmID(555);
        movies.add(movie);

        movie = new Movie("The Ring", 2002, 6, movie6, date6, "Ipsum Lorem...");
        movie.setmID(666);
        movies.add(movie);

        movie = new Movie("Mission Impossible: Rogue Nation", 2015, 8, movie7, date7, "Ipsum Lorem...");
        movie.setmID(777);
        movies.add(movie);

        movie = new Movie("Transformers: The Last Knight", 2017, 2, movie8, date8, "Ipsum Lorem...");
        movie.setmID(888);
        movies.add(movie);

        movie = new Movie("Terminator 2: Judgement Day", 1991, 8, movie9, date9, "Ipsum Lorem...");
        movie.setmID(999);
        movies.add(movie);

        user = new User(111, "Miggles", "anime4life", 21, 'M', userDate1);
        users.add(user);

        user = new User(222, "Smoo", "getoffmylawn", 32, 'M', userDate1);
        users.add(user);

        user = new User(333, "Andrew_Sempai", "iheartmybeard", 23, 'M', userDate1);
        users.add(user);

        user = new User(444, "TestBoi", "supertester", 24, 'M', userDate1);
        users.add(user);

        user = new User(555, "JiffyPB", "walmartisevil", 21, 'M', userDate1);
        users.add(user);

        user = new User(666, "Wonder_Woman", "DCU", 82, 'F', userDate2);
        users.add(user);

    }

    public void close()
    {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public String getMoviesAll(ArrayList<Movie> currentMovies)
    {
        currentMovies.addAll(movies);
        return null;
    }

    public String getMoviesAllSorted(ArrayList<Movie> currentMovies, SortEnums.MovieSortField sortBy, boolean ascending)
    {
        currentMovies.addAll(movies);
        if (!ascending)
        {
            Collections.reverse(currentMovies);
        }
        return null;
    }

    public String insertMovie(Movie currentMovie)
    {
        int index = movies.indexOf(currentMovie);
        if(index != -1)
        {
            return "'" + currentMovie.getTitle() + "' is already added.";
        }
        else
        {
            movies.add(currentMovie);
        }

        return null;
    }

    public String updateMovie(Movie currentMovie)
    {
        int index = movies.indexOf(currentMovie);
        if(index == -1)
        {
            return "'" + currentMovie.getTitle() + "' cannot be found.";
        }
        else
        {
            movies.set(index, currentMovie);
        }

        return null;
    }

    public String deleteMovie(Movie currentMovie)
    {
        int index = movies.indexOf(currentMovie);
        if(index == -1)
        {
            return "'" + currentMovie.getTitle() + "' cannot be found.";
        }
        else
        {
            movies.remove(index);
        }

        return null;
    }

    public String getUsersAll(ArrayList<User> currentUsers)
    {
        currentUsers.addAll(users);
        return null;
    }

    public String getUsersAllSorted(ArrayList<User> currentUsers, SortEnums.UserSortField sortBy, boolean ascending)
    {
        currentUsers.addAll(users);
        if (!ascending)
        {
            Collections.reverse(currentUsers);
        }
        return null;
    }

    public String insertUser(User currentUser)
    {
        int index = users.indexOf(currentUser);
        if(index != -1)
        {
            return "'" + currentUser.getName() + "' is already added.";
        }
        else
        {
            users.add(currentUser);
        }

        return null;
    }

    public String updateUser(User currentUser)
    {
        int index = users.indexOf(currentUser);
        if(index == -1)
        {
            return "'" + currentUser.getName() + "' cannot be found.";
        }
        else
        {
            users.set(index, currentUser);
        }

        return null;
    }

    public String deleteUser(User currentUser)
    {
        int index = users.indexOf(currentUser);
        if(index == -1)
        {
            return "'" + currentUser.getName() + "' cannot be found.";
        }
        else
        {
            users.remove(index);
        }

        return null;
    }
}
