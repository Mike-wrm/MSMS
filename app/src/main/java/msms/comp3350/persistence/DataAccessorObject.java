package msms.comp3350.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.Calendar;

import msms.comp3350.objects.Movie;
import msms.comp3350.objects.User;
import msms.comp3350.objects.WatchedEvent;
import msms.comp3350.business.SortEnums;

public class DataAccessorObject implements DataAccessor
{
    private String dbName;
    private String dbType;

    private Connection connectionX;
    private Statement statement1, statement2, statement3, statement4;
    private ResultSet resultSet1, resultSet2, resultSet3, resultSet4;

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
            statement3 = connectionX.createStatement();
            statement4 = connectionX.createStatement();
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


    //generates basic select movies from DB
    public String getMoviesAll(ArrayList<Movie> currentMovies)
    {
        String strCommand = "Select * from Movies";
        return getMoviesSQL(currentMovies, strCommand);
    }

    //generates sorted select movies from DB
    public String getMoviesAllSorted(ArrayList<Movie> currentMovies, SortEnums.MovieSortField sortBy, boolean ascending)
    {
        String order;
        if (ascending)
        {
            order = " ASC";
        }
        else
        {
            order = " DESC";
        }

        String strCommand = "Select * from Movies ORDER BY " + sortBy.toString() + order;
        return getMoviesSQL(currentMovies, strCommand);
    }

    //Handles DB processing for getting movies, strCommand varies based on caller
    public String getMoviesSQL (ArrayList<Movie> currentMovies, String strCommand)
    {
        Movie movieX;
        int myID, myReleaseYear, myEndMonth, myEndDay, myEndYear;
        String myTitle, myCat, myDescription;
        myTitle = myCat = myDescription = EOF;
        myID = myReleaseYear = myEndDay = myEndMonth = myEndYear = 0;

        result = null;

        try
        {
            command = strCommand;
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
                myCat = resultSet1.getString("category1");
                myEndMonth = resultSet1.getInt("endMonth");
                myEndDay = resultSet1.getInt("endDay");
                myEndYear = resultSet1.getInt("endYear");
                Calendar expDate = Calendar.getInstance();
                expDate.set(myEndYear, myEndMonth-1, myEndDay);
                myDescription = resultSet1.getString("description");

                movieX = new Movie (myID, myTitle, myReleaseYear, myCat, expDate, myDescription);
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
        String values;
        result = null;
        String myTitle, myCat, myDescription;
        myTitle = myCat = myDescription = "";

        try
        {
            myTitle = currentMovie.getTitle();
            myTitle = formatApostrophe(myTitle);
            myCat = currentMovie.getCategory();
            myCat = formatApostrophe(myCat);
            myDescription = currentMovie.getDescription();
            myDescription = formatApostrophe(myDescription);

            values = currentMovie.getmID()
                    +", '" +myTitle
                    +"', " +currentMovie.getReleaseYear()
                    +", '" +myCat
                    +"', " +currentMovie.getEndMonth()
                    +", " +currentMovie.getEndDay()
                    +", " +currentMovie.getEndYear()
                    +", '" +myDescription
                    +"'";
            command = "Insert into Movies " +" Values(" +values +")";
            updateCount = statement1.executeUpdate(command);
            result = checkWarning(statement1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String updateMovie(Movie currentMovie)
    {
        String values;
        String where;
        String myTitle, myCat, myDescription;
        myTitle = myCat = myDescription = "";

        result = null;
        try
        {
            myTitle = currentMovie.getTitle();
            myTitle = formatApostrophe(myTitle);
            myCat = currentMovie.getCategory();
            myCat = formatApostrophe(myCat);
            myDescription = currentMovie.getDescription();
            myDescription = formatApostrophe(myDescription);

            values = "title='" +myTitle
                    +"', releaseYear=" +currentMovie.getReleaseYear()
                    +", category1='" +myCat
                    +"', endMonth=" +currentMovie.getEndMonth()
                    +", endDay=" +currentMovie.getEndDay()
                    +", endYear=" +currentMovie.getEndYear()
                    +", description='" +myDescription
                    +"'";
            where = "where mID=" +currentMovie.getmID();
            command = "Update Movies " +" Set " +values +" " +where;
            updateCount = statement1.executeUpdate(command);
            result = checkWarning(statement1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String deleteMovie(Movie currentMovie)
    {
        result = null;

        try
        {
            command = "Delete from Movies where mID=" +currentMovie.getmID() +"";
            //System.out.println(cmdString);
            updateCount = statement1.executeUpdate(command);
            result = checkWarning(statement1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    //generates basic select movies from DB
    public String getUsersAll(ArrayList<User> currentUsers)
    {
        String strCommand = "Select * from Users";
        return getUsersSQL(currentUsers, strCommand);
    }

    //generates basic select movies from DB
    public String getUsersAllSorted(ArrayList<User> currentUsers, SortEnums.UserSortField sortBy, boolean ascending)
    {
        String order;
        if (ascending)
        {
            order = " ASC";
        }
        else
        {
            order = " DESC";
        }

        String strCommand = "Select * from Users ORDER BY " + sortBy.toString() + order;
        return getUsersSQL(currentUsers, strCommand);
    }

    //Handles DB processing for getting users, strCommand varies based on caller
    public String getUsersSQL (ArrayList<User> currentUsers, String strCommand)
    {
        User userX;
        int myID, myAge, myEndMonth, myEndDay, myEndYear;
        String myUserName, myPassword, myGender;
        myUserName = myPassword = myGender = EOF;
        myID = myAge = myEndDay = myEndMonth = myEndYear = 0;

        result = null;

        try
        {
            command = strCommand;
            resultSet2 = statement2.executeQuery(command);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        try
        {
            while (resultSet2.next())
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
        String values;
        result = null;
        String myUserName, myPassword;
        myUserName = myPassword = "";

        try
        {
            myUserName = currentUser.getName();
            myUserName = formatApostrophe(myUserName);
            myPassword = currentUser.getPass();
            myPassword = formatApostrophe(myPassword);

            values = currentUser.getuID()
                    +", '" +myUserName
                    +"', '" +myPassword
                    +"', " +currentUser.getAge()
                    +", '" +currentUser.getGender()
                    +"', " +currentUser.getEndMonth()
                    +", " +currentUser.getEndDay()
                    +", " +currentUser.getEndYear()
                    +"";
            command = "Insert into Users " +" Values(" +values +")";
            updateCount = statement2.executeUpdate(command);
            result = checkWarning(statement2, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String updateUser(User currentUser)
    {
        String values;
        String where;
        String myUserName, myPassword;
        myUserName = myPassword = "";

        result = null;
        try
        {
            myUserName = currentUser.getName();
            myUserName = formatApostrophe(myUserName);
            myPassword = currentUser.getPass();
            myPassword = formatApostrophe(myPassword);

            values = "userName='" +myUserName
                    +"', password='" +myPassword
                    +"', age=" +currentUser.getAge()
                    +", gender='" +currentUser.getGender()
                    +"', expMonth=" +currentUser.getEndMonth()
                    +", expDay=" +currentUser.getEndDay()
                    +", expYear=" +currentUser.getEndYear()
                    +"";
            where = "where uID=" +currentUser.getuID();
            command = "Update Users " +" Set " +values +" " +where;
            updateCount = statement2.executeUpdate(command);
            result = checkWarning(statement2, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }
    
    public String deleteUser(User currentUser)
    {
        result = null;

        try
        {
            command = "Delete from Users where uID=" +currentUser.getuID() +"";
            updateCount = statement2.executeUpdate(command);
            result = checkWarning(statement2, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String formatApostrophe (String string)
    {
        return string.replaceAll("'","''");
    }

    //returns all WatchedEvents
    public String getAllWatchedEvents(ArrayList<WatchedEvent> allEvents)
    {
        WatchedEvent viewX;
        int myMID, myUID, myRating;
        String myUserName, myTitle;
        myUserName = myTitle = EOF;
        myMID = myUID = myRating = 0;

        result = null;

        try
        {
            command = "Select * from Users,Views,Movies where Users.uID=Views.uID and Movies.mID=Views.mID";
            resultSet3 = statement3.executeQuery(command);

            while (resultSet3.next())
            {
                myUID = resultSet3.getInt("uID");
                myMID = resultSet3.getInt("mID");
                myUserName = resultSet3.getString("userName");
                myTitle = resultSet3.getString("title");
                myRating = resultSet3.getInt("rating");

                viewX = new WatchedEvent (myUID, myMID, myUserName, myTitle, myRating);
                allEvents.add(viewX);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    //returns a sublist of all users who have watched a certain movie
    public String getUserSublist(ArrayList<User> userSublist, Movie currentMovie)
    {
        User userX;
        int myID, myAge, myEndMonth, myEndDay, myEndYear;
        String myUserName, myPassword, myGender;
        myUserName = myPassword = myGender = EOF;
        myID = myAge = myEndDay = myEndMonth = myEndYear = 0;

        result = null;

        try
        {
            command = "Select * from Users,Views where Users.uID=Views.uID and mID=" + currentMovie.getmID();
            resultSet3 = statement3.executeQuery(command);

            while (resultSet3.next())
            {
                myID = resultSet3.getInt("uID");
                myUserName = resultSet3.getString("userName");
                myPassword = resultSet3.getString("password");
                myAge = resultSet3.getInt("age");
                myGender = resultSet3.getString("gender");
                char myGenderChar = myGender.charAt(0);
                myEndMonth = resultSet3.getInt("expMonth");
                myEndDay = resultSet3.getInt("expDay");
                myEndYear = resultSet3.getInt("expYear");
                Calendar expDate = Calendar.getInstance();
                expDate.set(myEndYear, myEndMonth-1, myEndDay);

                userX = new User (myID, myUserName, myPassword, myAge, myGenderChar, expDate);
                userSublist.add(userX);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    //returns all WatchedEvent queries related to a movie (movie with all its users)
    public String getMovieViews(ArrayList<WatchedEvent> currentMovieViews, Movie currentMovie)
    {
        WatchedEvent viewX;
        int myMID, myUID, myRating;
        String myUserName, myTitle;
        myUserName = myTitle = EOF;
        myMID = myUID = myRating = 0;

        result = null;

        try
        {
            command = "Select * from Users,Views where Users.uID=Views.uID and mID=" + currentMovie.getmID();
            resultSet3 = statement3.executeQuery(command);

            while (resultSet3.next())
            {
                myUID = resultSet3.getInt("uID");
                myMID = resultSet3.getInt("mID");
                myUserName = resultSet3.getString("userName");
                myTitle = currentMovie.getTitle();
                myRating = resultSet3.getInt("rating");

                viewX = new WatchedEvent (myUID, myMID, myUserName, myTitle, myRating);
                currentMovieViews.add(viewX);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    //returns a sublist of all movies watched by a user
    public String getMovieSublist(ArrayList<Movie> movieSublist, User currentUser)
    {
        Movie movieX;
        int myID, myReleaseYear, myEndMonth, myEndDay, myEndYear;
        String myTitle, myCat, myDescription;
        myTitle = myCat = myDescription = EOF;
        myID = myReleaseYear = myEndDay = myEndMonth = myEndYear = 0;

        result = null;

        try
        {
            command = "Select * from Movies,Views where Movies.mID=Views.mID and uID=" + currentUser.getuID();
            resultSet4 = statement4.executeQuery(command);

            while (resultSet4.next())
            {
                myID = resultSet4.getInt("mID");
                myTitle = resultSet4.getString("title");
                myReleaseYear = resultSet4.getInt("releaseYear");
                myCat = resultSet4.getString("category1");
                myEndMonth = resultSet4.getInt("endMonth");
                myEndDay = resultSet4.getInt("endDay");
                myEndYear = resultSet4.getInt("endYear");
                Calendar expDate = Calendar.getInstance();
                expDate.set(myEndYear, myEndMonth-1, myEndDay);
                myDescription = resultSet4.getString("description");

                movieX = new Movie (myID, myTitle, myReleaseYear, myCat, expDate, myDescription);
                movieSublist.add(movieX);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    //returns all WatchedEvent queries related to a user (user with all their movies)
    public String getUserViews(ArrayList<WatchedEvent> currentUserViews, User currentUser)
    {
        WatchedEvent viewX;
        int myMID, myUID, myRating;
        String myUserName, myTitle;
        myUserName = myTitle = EOF;
        myMID = myUID = myRating = 0;

        result = null;

        try
        {
            command = "Select * from Movies,Views where Movies.mID=Views.mID and uID=" + currentUser.getuID();
            resultSet4 = statement4.executeQuery(command);

            while (resultSet4.next())
            {
                myUID = resultSet4.getInt("uID");
                myMID = resultSet4.getInt("mID");
                myUserName = currentUser.getName();
                myTitle = resultSet4.getString("title");
                myRating = resultSet4.getInt("rating");

                viewX = new WatchedEvent (myUID, myMID, myUserName, myTitle, myRating);
                currentUserViews.add(viewX);
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String insertWatchedEvent(WatchedEvent currEvent)
    {
        // This method is only used for repairing the DB when we delete users that have ratings attached to them
        String values;
        result = null;

        try
        {
            values = currEvent.getuID()
                    +", " +currEvent.getmID()
                    +", " +currEvent.getRating()
                    +"";
            command = "Insert into Views " +" Values(" +values +")";
            updateCount = statement1.executeUpdate(command);
            result = checkWarning(statement1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
