package msms.comp3350.persistence;

import java.util.ArrayList;

import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;

/**
 * Created by Chris on 2018-02-08.
 */

public class TempData {

    private ArrayList<Movie> allMovies;
    private ArrayList<User> allUsers;

    public TempData() {
        Movie movie;
        User user;
        allMovies = new ArrayList<Movie>();
        allUsers = new ArrayList<User>();

        movie = new Movie("The Shawshank Redemption", 1994, 9, "DRAMA", 1, 1, 2021, "ipsum lorem");
        allMovies.add(movie);
        movie = new Movie("The Lord of the Rings", 2001, 8, "FANTASY", 2, 2, 2022, "ipsum lorem");
        allMovies.add(movie);
        movie = new Movie("A Nightmare on Elm Street", 1984, 7, "HORROR", 3, 3, 2023, "ipsum lorem");
        allMovies.add(movie);
        movie = new Movie("Raiders of the Lost Ark", 1981, 6, "ACTION", 4, 4, 2024, "ipsum lorem");
        allMovies.add(movie);
        movie = new Movie("A Fish Called Wanda", 1988, 5, "COMEDY", 5, 5, 2021, "ipsum lorem");
        allMovies.add(movie);

        user = new User("Mike McMahon", "anime4life", 21, "MALE", 1, 1, 2021);
        allUsers.add(user);
        user = new User("Chris Scatliff", "getoffmylawn", 82, "MALE", 2, 2, 2022);
        allUsers.add(user);
        user = new User("Andrew Kozar", "iheartmybeard", 23, "MALE", 3, 3, 2023);
        allUsers.add(user);
        user = new User("Alex Carriere", "supertester", 24, "MALE", 4, 4, 2024);
        allUsers.add(user);
        user = new User("Jaivir Bali", "walmartisevil", 25, "MALE", 5, 5, 2025);
        allUsers.add(user);
        user = new User("Diana Prince", "wonderwoman", 86, "FEMALE", 6, 6, 2026);
        allUsers.add(user);
    }

    public String getMoviesAll(ArrayList<Movie> movies) {
        movies.addAll(allMovies);
        return null;
    }

    public String getUsersAll(ArrayList<User> users) {
        users.addAll(allUsers);
        return null;
    }
}
