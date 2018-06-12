package msms.comp.main.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;

import msms.comp.application.Services;
import msms.comp.business.UserCharts;
import msms.comp.main.tests.persistence.TempData;
import msms.comp.objects.Movie;
import msms.comp.objects.User;
import msms.comp.persistence.DataAccessor;

public class UserChartsTest extends TestCase{

    DataAccessor testData;

    public UserChartsTest(String arg0)
    {
        super(arg0);
    }

    public void setUp()
    {
        TempData newData = new TempData();
        testData = Services.createDataAccess(newData);
        testData.open("temp");
    }

    public void tearDown()
    {
        System.out.println("Finished a UserCharts Test");
        testData.close();
    }

    public void testUserChartsAgeGet()
    {
        // Lets see if Accessing everyting goes ok with base data.
        String[][] data = UserCharts.getUserAges();

        System.out.println("\nStarting a getAges Access Test in UserCharts");

        assertEquals(2, data.length);
        assertEquals(8, data[0].length);
        assertEquals(8, data[1].length);

        assertEquals("", data[0][0]);
        assertEquals("0-19", data[0][1]);
        assertEquals("20-29", data[0][2]);
        assertEquals("30-39", data[0][3]);
        assertEquals("40-49", data[0][4]);
        assertEquals("50-59", data[0][5]);
        assertEquals("60+", data[0][6]);
        assertEquals("", data[0][7]);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("4", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);
    }

    public void testUserChartsAgeChange()
    {
        String[][] data;
        ArrayList<User> users = new ArrayList<>();
        User user;

        System.out.println("\nStarting a getAges change Test in UserCharts");

        // delete the first person
        testData.getUsersAll(users);
        user = users.get(0);

        testData.deleteUser(user);

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("3", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);

        // delete the second
        user = users.get(1);

        testData.deleteUser(user);

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("3", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);

        // delete the third
        user = users.get(2);

        testData.deleteUser(user);

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("2", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);

        // delete the fourth
        user = users.get(3);

        testData.deleteUser(user);

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);

        // delete the fifth
        user = users.get(4);

        testData.deleteUser(user);

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);

        // and delete the last one.
        user = users.get(5);

        testData.deleteUser(user);

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        //now lets add some people.
        //Start of by adding some in the first age range
        testData.insertUser(new User(55, "TestPerson", "TestPassword", 18, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]); // Have to make sure adding another one didnt change anything else.
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        //Second age range
        testData.insertUser(new User(56, "TestPerson", "TestPassword", 25, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        //Third age range
        testData.insertUser(new User(57, "TestPerson", "TestPassword", 35, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        //Fourth age Range
        testData.insertUser(new User(58, "TestPerson", "TestPassword", 45, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        //Fifth Age Range
        testData.insertUser(new User(59, "TestPerson", "TestPassword", 55, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        //Sixth Age Range
        testData.insertUser(new User(60, "TestPerson", "TestPassword", 65, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);

        // Now lets add some weird inputs
        // add someone with the age 0
        testData.insertUser(new User(61, "TestPerson", "TestPassword", 0, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("2", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);

        // add someone with a negative age
        testData.insertUser(new User(62, "TestPerson", "TestPassword", -5, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);

        // add someone with a large age
        testData.insertUser(new User(63, "TestPerson", "TestPassword", 5000, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);

        //Lets add some people on the border of the categories
        //Test out the first border
        testData.insertUser(new User(64, "TestPerson", "TestPassword", 20, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("2", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);

        //Test out the second border
        testData.insertUser(new User(65, "TestPerson", "TestPassword", 30, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("2", data[1][2]);
        assertEquals("2", data[1][3]);
        assertEquals("1", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);

        //Test out the third border
        testData.insertUser(new User(66, "TestPerson", "TestPassword", 40, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("2", data[1][2]);
        assertEquals("2", data[1][3]);
        assertEquals("2", data[1][4]);
        assertEquals("1", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);

        //Test out the fourth border
        testData.insertUser(new User(67, "TestPerson", "TestPassword", 50, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("2", data[1][2]);
        assertEquals("2", data[1][3]);
        assertEquals("2", data[1][4]);
        assertEquals("2", data[1][5]);
        assertEquals("2", data[1][6]);
        assertEquals("0", data[1][7]);

        //Test out the last border
        testData.insertUser(new User(68, "TestPerson", "TestPassword", 60, 'M', Calendar.getInstance()));

        data = UserCharts.getUserAges();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("2", data[1][2]);
        assertEquals("2", data[1][3]);
        assertEquals("2", data[1][4]);
        assertEquals("2", data[1][5]);
        assertEquals("3", data[1][6]);
        assertEquals("0", data[1][7]);
    }

    public void testUserChartAgeUserGive()
    {
        String[][] data;
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie;

        System.out.println("\nStarting a getGender Access Test in UserCharts");

        testData.getMoviesAll(movies);
        movie = movies.get(0);
        data = UserCharts.getUserAges(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        movie = movies.get(1);
        data = UserCharts.getUserAges(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        movie = movies.get(2);
        data = UserCharts.getUserAges(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        movie = movies.get(3);
        data = UserCharts.getUserAges(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        movie = movies.get(4);
        data = UserCharts.getUserAges(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        movie = movies.get(5);
        data = UserCharts.getUserAges(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);

        movie = movies.get(6);
        data = UserCharts.getUserAges(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);

        movie = movies.get(7);
        data = UserCharts.getUserAges(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("1", data[1][6]);
        assertEquals("0", data[1][7]);

        movie = movies.get(8);
        data = UserCharts.getUserAges(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);
        assertEquals("0", data[1][4]);
        assertEquals("0", data[1][5]);
        assertEquals("0", data[1][6]);
        assertEquals("0", data[1][7]);
    }

    public void testUserChartsGenderGet()
    {
        // Lets see if Accessing everyting goes ok with base data.
        String[][] data = UserCharts.getUserGenders();

        System.out.println("\nStarting a getGender Access Test in UserCharts");

        assertEquals(2, data.length);
        assertEquals(4, data[0].length);
        assertEquals(4, data[1].length);

        assertEquals("None", data[0][0]);
        assertEquals("Males", data[0][1]);
        assertEquals("Females", data[0][2]);
        assertEquals("Other", data[0][3]);

        assertEquals("0", data[1][0]);
        assertEquals("5", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);
    }

    public void testUserChartsGenderChange()
    {
        String[][] data;
        ArrayList<User> users = new ArrayList<>();
        User user;

        System.out.println("\nStarting a getAges change Test in UserCharts");

        testData.getUsersAll(users);

        // delete the first person
        user = users.get(0);
        testData.deleteUser(user);
        data = UserCharts.getUserGenders();

        assertEquals("0", data[1][0]);
        assertEquals("4", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);

        // delete the second person
        user = users.get(1);
        testData.deleteUser(user);
        data = UserCharts.getUserGenders();

        assertEquals("0", data[1][0]);
        assertEquals("3", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);

        // delete the third person
        user = users.get(2);
        testData.deleteUser(user);
        data = UserCharts.getUserGenders();

        assertEquals("0", data[1][0]);
        assertEquals("2", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);

        // delete the fourth person
        user = users.get(3);
        testData.deleteUser(user);
        data = UserCharts.getUserGenders();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);

        // delete the fifth person
        user = users.get(4);
        testData.deleteUser(user);
        data = UserCharts.getUserGenders();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);

        // delete the sixth person
        user = users.get(5);
        testData.deleteUser(user);
        data = UserCharts.getUserGenders();

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);

        //Lets add some people
        // Add a male
        testData.insertUser(new User(1, "testUser", "test", 21, 'M', Calendar.getInstance()));

        data = UserCharts.getUserGenders();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);

        // Add a female
        testData.insertUser(new User(2, "testUser", "test", 21, 'F', Calendar.getInstance()));

        data = UserCharts.getUserGenders();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);

        // Add an other
        testData.insertUser(new User(3, "testUser", "test", 21, 'O', Calendar.getInstance()));

        data = UserCharts.getUserGenders();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("1", data[1][3]);

        // Add a empty char
        testData.insertUser(new User(4, "testUser", "test", 21, ' ', Calendar.getInstance()));

        data = UserCharts.getUserGenders();

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("2", data[1][3]);
    }

    public void testUserChartsGenderMovieGive()
    {
        String[][] data;
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie;

        System.out.println("\nStarting a getGender Access Test in UserCharts");

        testData.getMoviesAll(movies);
        movie = movies.get(0);
        data = UserCharts.getUserGenders(movie);

        assertEquals("0", data[1][0]);
        assertEquals("2", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);

        testData.getMoviesAll(movies);
        movie = movies.get(1);
        data = UserCharts.getUserGenders(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);

        testData.getMoviesAll(movies);
        movie = movies.get(2);
        data = UserCharts.getUserGenders(movie);

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);

        testData.getMoviesAll(movies);
        movie = movies.get(3);
        data = UserCharts.getUserGenders(movie);

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);

        testData.getMoviesAll(movies);
        movie = movies.get(4);
        data = UserCharts.getUserGenders(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);

        testData.getMoviesAll(movies);
        movie = movies.get(5);
        data = UserCharts.getUserGenders(movie);

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);

        testData.getMoviesAll(movies);
        movie = movies.get(6);
        data = UserCharts.getUserGenders(movie);

        assertEquals("0", data[1][0]);
        assertEquals("1", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);

        testData.getMoviesAll(movies);
        movie = movies.get(7);
        data = UserCharts.getUserGenders(movie);

        assertEquals("0", data[1][0]);
        assertEquals("0", data[1][1]);
        assertEquals("1", data[1][2]);
        assertEquals("0", data[1][3]);

        testData.getMoviesAll(movies);
        movie = movies.get(8);
        data = UserCharts.getUserGenders(movie);

        assertEquals("0", data[1][0]);
        assertEquals("2", data[1][1]);
        assertEquals("0", data[1][2]);
        assertEquals("0", data[1][3]);
    }

}
