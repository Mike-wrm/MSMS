package msms.comp3350.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.presentation.MainActivity;

public class DataAccessorObject implements DataAccessor
{
    private String dbName;
    private String dbType;

    private Connection connectionX;
    private Statement statement1, statement2;
    private ResultSet resultSet1, resultSet2, resultSet3;

    private ArrayList<Movie> movies;
    private ArrayList<User> users;

    private String command;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessorObject (String dbName)
    {
        this.dbName = dbName;
    }

    public void open (String dbPath)
    {
        String url;

        try
        {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            connectionX = DriverManager.getConnection(url, "GROUPD", "");
            statement1 = connectionX.createStatement();
            statement2 = connectionX.createStatement();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public void close()
    {
        try
        {	// commit all changes to the database
            command = "shutdown compact";
            resultSet1 = statement1.executeQuery(command);
            connectionX.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public String getMoviesAll(ArrayList<Movie> currentMovies)
    {
        Movie movieX;
        int myID, myReleaseYear, myUserScore, myEndMonth, myEndDay, myEndYear;
        String myTitle, myCat1, myCat2, myDescription;
        myTitle = myCat1 = myCat2 = myDescription = EOF;
        myID = myReleaseYear = myUserScore = myEndDay = myEndMonth = myEndYear = 0;

        result = null;

        try
        {
            command = "Select * from Movies";
            resultSet1 = statement1.executeQuery(command);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        try
        {
            while (resultSet1.next())
            {
                myID = resultSet1.getInt("mID");
                myTitle = resultSet1.getString("title");
                myReleaseYear = resultSet1.getInt("releaseYear");
                myUserScore = resultSet1.getInt("userScore");
                myCat1 = resultSet1.getString("category1");
                myCat2 = resultSet1.getString("category2");
                ArrayList<String> categories = new ArrayList<String>();
                categories.add(myCat1);
                categories.add(myCat2);
                myEndMonth = resultSet1.getInt("endMonth");
                myEndDay = resultSet1.getInt("endDay");
                myEndYear = resultSet1.getInt("endYear");
                Calendar expDate = Calendar.getInstance();
                expDate.set(myEndYear, myEndMonth-1, myEndDay);
                myDescription = resultSet1.getString("description");

                movieX = new Movie (myID, myTitle, myReleaseYear, myUserScore, categories, expDate, myDescription);
                currentMovies.add(movieX);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String insertMovie(Movie currentMovie)
    {
        return null;
    }

    public String updateMovie(Movie currentMovie)
    {
        return null;
    }
    public String deleteMovie(Movie currentMovie)
    {
        return null;
    }



    public String getUsersAll(ArrayList<User> currentUsers)
    {
        User userX;
        int myID, myAge, myEndMonth, myEndDay, myEndYear;
        String myUserName, myPassword, myGender;
        myUserName = myPassword = myGender = EOF;
        myID = myAge = myEndDay = myEndMonth = myEndYear = 0;

        result = null;

        try
        {
            command = "Select * from Movies";
            resultSet2 = statement2.executeQuery(command);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        try
        {
            while (resultSet1.next())
            {
                myID = resultSet2.getInt("uID");
                myUserName = resultSet2.getString("userName");
                myPassword = resultSet2.getString("password");
                myAge = resultSet2.getInt("age");
                myGender = resultSet2.getString("gender");
                char myGenderChar = myGender.charAt(0);
                myEndMonth = resultSet2.getInt("expMonth");
                myEndDay = resultSet2.getInt("expDay");
                myEndYear = resultSet2.getInt("expYear");
                Calendar expDate = Calendar.getInstance();
                expDate.set(myEndYear, myEndMonth-1, myEndDay);

                userX = new User (myID, myUserName, myPassword, myAge, myGenderChar, expDate);
                currentUsers.add(userX);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }
    public String insertUser(User currentUser)
    {
        return null;
    }
    public String updateUser(User currentUser)
    {
        return null;
    }
    public String deleteUser(User currentUser)
    {
        return null;
    }


    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
