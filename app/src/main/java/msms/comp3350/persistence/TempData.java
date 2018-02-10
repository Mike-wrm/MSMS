package msms.comp3350.persistence;

import java.util.ArrayList;

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
        movies = new ArrayList<Movie>();
        users = new ArrayList<User>();

        movie = new Movie("The Shawshank Redemption", 1994, 9, "DRAMA", 1, 1, 2021, "ipsum lorem");
        movies.add(movie);
        movie = new Movie("The Lord of the Rings", 2001, 8, "FANTASY", 2, 2, 2022, "ipsum lorem");
        movies.add(movie);
        movie = new Movie("A Nightmare on Elm Street", 1984, 7, "HORROR", 3, 3, 2023, "ipsum lorem");
        movies.add(movie);
        movie = new Movie("Raiders of the Lost Ark", 1981, 6, "ACTION", 4, 4, 2024, "ipsum lorem");
        movies.add(movie);
        movie = new Movie("A Fish Called Wanda", 1988, 5, "COMEDY", 5, 5, 2021, "ipsum lorem");
        movies.add(movie);

        user = new User("Mike McMahon", "anime4life", 21, "MALE", 1, 1, 2021);
        users.add(user);
        user = new User("Chris Scatliff", "getoffmylawn", 82, "MALE", 2, 2, 2022);
        users.add(user);
        user = new User("Andrew Kozar", "iheartmybeard", 23, "MALE", 3, 3, 2023);
        users.add(user);
        user = new User("Alex Carriere", "supertester", 24, "MALE", 4, 4, 2024);
        users.add(user);
        user = new User("Jaivir Bali", "walmartisevil", 25, "MALE", 5, 5, 2025);
        users.add(user);
        user = new User("Diana Prince", "wonderwoman", 86, "FEMALE", 6, 6, 2026);
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
