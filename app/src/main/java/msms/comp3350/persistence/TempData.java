package msms.comp3350.persistence;

import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;

/**
 * Created by Chris on 2018-02-08.
 */

public class TempData {

    private ArrayList<Movie> movies;
    private ArrayList<User> users;

    public TempData() {
        Movie movie;
        User user;
        ArrayList<String> drama = new ArrayList<>();
        drama.add("DRAMA");
        ArrayList<String> fantasy = new ArrayList<>();
        drama.add("FANTASY");
        ArrayList<String> horror = new ArrayList<>();
        drama.add("HORROR");
        ArrayList<String> action = new ArrayList<>();
        drama.add("ACTION");
        ArrayList<String> comedy = new ArrayList<>();
        drama.add("COMEDY");

        Calendar date1 = Calendar.getInstance(); // Obviously, each object  should get their own date object
        date1.set(2021, 1, 1);  // But for the sake of my time and sanity, I'm making them share
        Calendar date2 = Calendar.getInstance();
        date2.set(2022, 2, 2);
        Calendar date3 = Calendar.getInstance();
        date3.set(2023, 3, 3);
        Calendar date4 = Calendar.getInstance();
        date4.set(2024, 4, 4);
        Calendar date5 = Calendar.getInstance();
        date1.set(2025, 5, 5);
        Calendar date6 = Calendar.getInstance();
        date1.set(2025, 6, 6);

        movies = new ArrayList<Movie>();
        users = new ArrayList<User>();


        movie = new Movie(1, "The Shawshank Redemption", 1994, 9, drama, date1, "ipsum lorem");
        movies.add(movie);
        movie = new Movie(2, "The Lord of the Rings", 2001, 8, fantasy, date2, "ipsum lorem");
        movies.add(movie);
        movie = new Movie(3, "A Nightmare on Elm Street", 1984, 7, horror, date3, "ipsum lorem");
        movies.add(movie);
        movie = new Movie(4, "Raiders of the Lost Ark", 1981, 6, action, date4, "ipsum lorem");
        movies.add(movie);
        movie = new Movie(5, "A Fish Called Wanda", 1988, 5, comedy, date5, "ipsum lorem");
        movies.add(movie);

        user = new User(1, "Mike McMahon", "anime4life", 21, "MALE", date1);
        users.add(user);
        user = new User(2, "Chris Scatliff", "getoffmylawn", 82, "MALE", date2);
        users.add(user);
        user = new User(3, "Andrew Kozar", "iheartmybeard", 23, "MALE", date3);
        users.add(user);
        user = new User(4, "Alex Carriere", "supertester", 24, "MALE", date4);
        users.add(user);
        user = new User(5, "Jaivir Bali", "walmartisevil", 25, "MALE", date5);
        users.add(user);
        user = new User(6, "Diana Prince", "wonderwoman", 86, "FEMALE", date6);
        users.add(user);
    }
    public String getMoviesAll(ArrayList<Movie> currentMovies) {
        currentMovies.addAll(movies);
        return null;
    }

    public String insertMovie(Movie currentMovie) {
        int index = movies.indexOf(currentMovie);
        if(index != -1) {
            return "'" + currentMovie.getTitle() + "' is already added.";
        } else {
            movies.add(currentMovie);
        }
        return null;
    }

    public String updateMovie(Movie currentMovie) {
        int index = movies.indexOf(currentMovie);
        if(index == -1) {
            return "'" + currentMovie.getTitle() + "' cannot be found.";
        } else {
            movies.set(index, currentMovie);
        }
        return null;
    }

    public String deleteMovie(Movie currentMovie) {
        int index = movies.indexOf(currentMovie);
        if(index == -1) {
            return "'" + currentMovie.getTitle() + "' cannot be found.";
        } else {
            movies.remove(index);
        }
        return null;
    }

    public String getUsersAll(ArrayList<User> currentUsers) {
        currentUsers.addAll(users);
        return null;
    }

    public String insertUser(User currentUser) {
        int index = users.indexOf(currentUser);
        if(index != -1) {
            return "'" + currentUser.getName() + "' is already added.";
        } else {
            users.add(currentUser);
        }
        return null;
    }

    public String updateUser(User currentUser) {
        int index = users.indexOf(currentUser);
        if(index == -1) {
            return "'" + currentUser.getName() + "' cannot be found.";
        } else {
            users.set(index, currentUser);
        }
        return null;
    }

    public String deleteUser(User currentUser) {
        int index = users.indexOf(currentUser);
        if(index == -1) {
            return "'" + currentUser.getName() + "' cannot be found.";
        } else {
            movies.remove(index);
        }
        return null;
    }
}
