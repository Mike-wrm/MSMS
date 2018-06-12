package msms.comp.main.tests.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;

import msms.comp.application.Main;
import msms.comp.business.SortEnums;
import msms.comp.objects.Movie;
import msms.comp.objects.User;
import msms.comp.persistence.DataAccessor;
import msms.comp.objects.WatchedEvent;


public class TempData implements DataAccessor
{
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Movie> movies;
    private ArrayList<User> users;
    private ArrayList<WatchedEvent> viewers;

    public TempData(String dbName)
    {
        this.dbName = dbName;
    }

    public TempData()   //used for testing
    {
        this(Main.dbName);
    }

    public void open(String dbName)
    {
        Movie movie;
        User user;
        WatchedEvent viewed;

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
        viewers = new ArrayList<WatchedEvent>();


        movie = new Movie(111, "South Park: Bigger, Longer & Uncut", 1999,"Comedy", date1, "Ipsum Lorem...");
        movies.add(movie);

        movie = new Movie(222,"Eddie Murphy: Raw", 1987,"Comedy", date2, "Ipsum Lorem...");
        movies.add(movie);

        movie = new Movie(333, "Toy Story", 1995,"Family", date3, "Ipsum Lorem...");
        movies.add(movie);

        movie = new Movie(444, "Shrek", 2001,"Family", date4, "Ipsum Lorem...");
        movies.add(movie);

        movie = new Movie(555, "Friday the 13th", 2009,"Horror", date5, "Ipsum Lorem...");
        movies.add(movie);

        movie = new Movie(666,"The Ring", 2002,"Horror", date6, "Ipsum Lorem...");
        movie.setmID(666);
        movies.add(movie);

        movie = new Movie(777,"Mission Impossible: Rogue Nation", 2015, "Action", date7, "Ipsum Lorem...");
        movies.add(movie);

        movie = new Movie(888, "Transformers: The Last Knight", 2017,"Action", date8, "Ipsum Lorem...");
        movies.add(movie);

        movie = new Movie(999, "Terminator 2: Judgement Day", 1991,"Action", date9, "Ipsum Lorem...");
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

        viewed = new WatchedEvent(111,111,"Miggles", "South Park: Bigger, Longer & Uncut", 7);
        viewers.add(viewed);

        viewed = new WatchedEvent(111,999,"Miggles", "Terminator 2: Judgement Day", 2);
        viewers.add(viewed);

        viewed = new WatchedEvent(222,111,"Smoo", "South Park: Bigger, Longer & Uncut", 5);
        viewers.add(viewed);

        viewed = new WatchedEvent(222,999,"Smoo", "Terminator 2: Judgement Day", 4);
        viewers.add(viewed);

        viewed = new WatchedEvent(333,444,"Andrew_Sempai", "Shrek", 2);
        viewers.add(viewed);

        viewed = new WatchedEvent(333,666,"Andrew_Sempai", "The Ring", 8);
        viewers.add(viewed);

        viewed = new WatchedEvent(555,333,"JiffyPB", "Toy Story", 3);
        viewers.add(viewed);

        viewed = new WatchedEvent(555,777,"JiffyPB", "Mission Impossible: Rogue Nation", 7);
        viewers.add(viewed);

        viewed = new WatchedEvent(666,666,"Wonder_Woman", "The Ring", 9);
        viewers.add(viewed);

        viewed = new WatchedEvent(666,888,"Wonder_Woman", "Transformers: The Last Knight", 2);
        viewers.add(viewed);
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

    //Hard coded for stub at this moment in time
    public String getMoviesAllSorted(ArrayList<Movie> currentMovies, SortEnums.MovieSortField sortBy, boolean ascending)
    {
        currentMovies.addAll(movies);
        sortMovieArrList(currentMovies);
        flipList(currentMovies, ascending);

        return null;
    }

    //Flips movie/user list if supposed to be descending
    public void flipList(ArrayList list, boolean ascending)
    {
        if (!ascending)
        {
            Collections.reverse(list);
        }
    }

    //temp solution in stub for now since only ordering by title here
    public ArrayList<Movie> sortMovieArrList(ArrayList<Movie> currentMovies)
    {
        for (int i = 0; i < currentMovies.size()-1; i++)
        {
            for (int j = 0; j < (currentMovies.size()-1)-i; j++)
            {
                if (((currentMovies.get(j)).getTitle()).compareToIgnoreCase(((currentMovies.get(j+1)).getTitle())) > 0)
                {
                    Movie temp = currentMovies.get(j);
                    currentMovies.set(j, currentMovies.get(j+1));
                    currentMovies.set(j+1, temp);
                }
            }
        }
        return currentMovies;
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

    //Hard coded for stub at this moment in time
    public String getUsersAllSorted(ArrayList<User> currentUsers, SortEnums.UserSortField sortBy, boolean ascending)
    {
        currentUsers.addAll(users);
        sortUserArrList(currentUsers);
        flipList(currentUsers, ascending);

        return null;
    }

    //temp solution in stub for now since only ordering by name here
    public ArrayList<User> sortUserArrList(ArrayList<User> currentUsers)
    {
        for (int i = 0; i < currentUsers.size()-1; i++)
        {
            for (int j = 0; j < (currentUsers.size()-1)-i; j++)
            {
                if (((currentUsers.get(j)).getName()).compareToIgnoreCase(((currentUsers.get(j+1)).getName())) > 0)
                {
                    User temp = currentUsers.get(j);
                    currentUsers.set(j, currentUsers.get(j+1));
                    currentUsers.set(j+1, temp);
                }
            }
        }
        return currentUsers;
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

    public String getAllWatchedEvents(ArrayList<WatchedEvent> allEvents)
    {
        allEvents.addAll(viewers);
        return null;
    }

    public String getMovieViews(ArrayList<WatchedEvent> currentMovieViews, Movie currentMovie)
    {
        currentMovieViews.clear();
        int testmID = currentMovie.getmID();

        for (int count = 0; count < viewers.size(); count++)
        {
            WatchedEvent test = viewers.get(count);
            if (test.getmID() == testmID)
            {
                currentMovieViews.add(test);
            }
        }

        return null;
    }

    public String getUserSublist(ArrayList<User> userSublist, Movie currentMovie)
    {
        ArrayList<WatchedEvent> Views = new ArrayList<WatchedEvent>();
        getMovieViews(Views, currentMovie);

        for (int count = 0; count < Views.size(); count++)
        {
            WatchedEvent currView = Views.get(count);

            for (int count2 = 0; count2 < users.size(); count2++)
            {
                User currUser = users.get(count2);

                if (currView.getuID() == currUser.getuID())
                {
                    userSublist.add(currUser);
                }
            }
        }

        return null;
    }

    public String getMovieSublist(ArrayList<Movie> movieSublist, User currentUser)
    {
        ArrayList<WatchedEvent> Views = new ArrayList<WatchedEvent>();
        getUserViews(Views, currentUser);

        for (int count = 0; count < Views.size(); count++)
        {
            WatchedEvent currView = Views.get(count);

            for (int count2 = 0; count2 < movies.size(); count2++)
            {
                Movie currMovie = movies.get(count2);

                if (currView.getmID() == currMovie.getmID())
                {
                    movieSublist.add(currMovie);
                }
            }
        }

        return null;
    }

    public String getUserViews(ArrayList<WatchedEvent> currentUserViews, User currentUser)
    {
        currentUserViews.clear();
        int testuID = currentUser.getuID();

        for (int count = 0; count < viewers.size(); count++)
        {
            WatchedEvent test = viewers.get(count);
            if (test.getuID() == testuID)
            {
                currentUserViews.add(test);
            }
        }

        return null;
    }

    public String insertWatchedEvent(WatchedEvent currEvent)
    {
        // This method is only used for repairing the DB when we delete users that have ratings attached to them
        viewers.add(currEvent);
        return null;
    }
}
