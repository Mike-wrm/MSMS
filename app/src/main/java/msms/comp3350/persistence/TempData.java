package msms.comp3350.persistence;

import java.util.ArrayList;
import java.util.Calendar;

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
        date1.set(2021,1,1);  // But for the sake of my time and sanity, I'm making them share
        Calendar date2 = Calendar.getInstance();
        date2.set(2022,2,2);
        Calendar date3 = Calendar.getInstance();
        date3.set(2023,3,3);
        Calendar date4 = Calendar.getInstance();
        date4.set(2024,4,4);
        Calendar date5 = Calendar.getInstance();
        date1.set(2025,5,5);
        Calendar date6 = Calendar.getInstance();
        date1.set(2025,6,6);

        allMovies = new ArrayList<Movie>();
        allUsers = new ArrayList<User>();


        movie = new Movie("The Shawshank Redemption", 1994, 9, drama, date1, "ipsum lorem");
        allMovies.add(movie);
        movie = new Movie("The Lord of the Rings", 2001, 8, fantasy, date2 , "ipsum lorem");
        allMovies.add(movie);
        movie = new Movie("A Nightmare on Elm Street", 1984, 7, horror, date3, "ipsum lorem");
        allMovies.add(movie);
        movie = new Movie("Raiders of the Lost Ark", 1981, 6, action, date4 , "ipsum lorem");
        allMovies.add(movie);
        movie = new Movie("A Fish Called Wanda", 1988, 5, comedy, date5 ,"ipsum lorem");
        allMovies.add(movie);

        user = new User("Mike McMahon", "anime4life", 21, "MALE", date1);
        allUsers.add(user);
        user = new User("Chris Scatliff", "getoffmylawn", 82, "MALE", date2);
        allUsers.add(user);
        user = new User("Andrew Kozar", "iheartmybeard", 23, "MALE", date3);
        allUsers.add(user);
        user = new User("Alex Carriere", "supertester", 24, "MALE", date4);
        allUsers.add(user);
        user = new User("Jaivir Bali", "walmartisevil", 25, "MALE", date5);
        allUsers.add(user);
        user = new User("Diana Prince", "wonderwoman", 86, "FEMALE", date6);
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
