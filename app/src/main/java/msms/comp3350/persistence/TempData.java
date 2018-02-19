package msms.comp3350.persistence;

import java.util.ArrayList;
import java.util.Calendar;

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
        ArrayList<String> drama = new ArrayList<>();
        drama.add("Drama");
        ArrayList<String> fantasy = new ArrayList<>();
        fantasy.add("Fantasy");
        ArrayList<String> horror = new ArrayList<>();
        horror.add("Horror");
        ArrayList<String> action = new ArrayList<>();
        action.add("Action");
        ArrayList<String> comedy = new ArrayList<>();
        comedy.add("Comedy");

        Calendar date1 = Calendar.getInstance(); // Obviously, each object  should get their own date object
        date1.set(2021, 1, 1);  // But for the sake of my time and sanity, I'm making them share
        Calendar date2 = Calendar.getInstance();
        date2.set(2021, 2, 2);
        Calendar date3 = Calendar.getInstance();
        date3.set(2021, 3, 3);
        Calendar date4 = Calendar.getInstance();
        date4.set(2021, 4, 4);
        Calendar date5 = Calendar.getInstance();
        date5.set(2021, 5, 5);
        Calendar date6 = Calendar.getInstance();
        date6.set(2021, 6, 6);

        movies = new ArrayList<Movie>();
        users = new ArrayList<User>();


        movie = new Movie("The Shawshank Redemption", 1994, 9, drama, date1, "ipsum lorem");
        movies.add(movie);
        movie = new Movie("The Lord of the Rings", 2001, 8, fantasy, date2, "ipsum lorem");
        movies.add(movie);
        movie = new Movie("A Nightmare on Elm Street", 1984, 7, horror, date3, "ipsum lorem");
        movies.add(movie);
        movie = new Movie("Raiders of the Lost Ark", 1981, 6, action, date4, "ipsum lorem");
        movies.add(movie);
        movie = new Movie("A Fish Called Wanda", 1988, 5, comedy, date5, "ipsum lorem");
        movies.add(movie);

        user = new User(1, "Mike McMahon", "anime4life", 21, 'm', date1);
        users.add(user);
        user = new User(2, "Chris Scatliff", "getoffmylawn", 82, 'm', date2);
        users.add(user);
        user = new User(3, "Andrew Kozar", "iheartmybeard", 23, 'm', date3);
        users.add(user);
        user = new User(4, "Alex Carriere", "supertester", 24, 'm', date4);
        users.add(user);
        user = new User(5, "Jaivir Bali", "walmartisevil", 25, 'm', date5);
        users.add(user);
        user = new User(6, "Diana Prince", "wonderwoman", 86, 'f', date6);
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
