package msms.comp.main.tests.business;

import junit.framework.TestCase;

import msms.comp.business.ChartData;

public class ChartDataTest extends TestCase {

    public ChartDataTest(String arg0)
    {
        super(arg0);
    }

    public void testChartDataGlobal()
    {
        String[][] data;

        data = ChartData.getGlobalLists();

        assertEquals(3, data.length);
        assertEquals(6, data[0].length);
        assertEquals(6, data[1].length);
        assertEquals(6, data[2].length);

        assertEquals("Movies by Category", data[0][0]);
        assertEquals("Movies by Decade", data[0][1]);
        assertEquals("Movies by Rating", data[0][2]);
        assertEquals("Users by Age Range", data[0][3]);
        assertEquals("Users by Gender", data[0][4]);
        assertEquals("Users by Rating", data[0][5]);

        assertEquals("pie", data[1][0]);
        assertEquals("bar", data[1][1]);
        assertEquals("bar", data[1][2]);
        assertEquals("bar", data[1][3]);
        assertEquals("pie", data[1][4]);
        assertEquals("bar", data[1][5]);

        assertEquals("categories", data[2][0]);
        assertEquals("decades", data[2][1]);
        assertEquals("ratings", data[2][2]);
        assertEquals("ages", data[2][3]);
        assertEquals("genders", data[2][4]);
        assertEquals("ratings", data[2][5]);
    }

    public void testChartDataMovie()
    {
        String[][] data;

        data = ChartData.getMovieLists();

        assertEquals(3, data.length);
        assertEquals(3, data[0].length);
        assertEquals(3, data[1].length);
        assertEquals(3, data[2].length);

        assertEquals("Movies by Category", data[0][0]);
        assertEquals("Movies by Decade", data[0][1]);
        assertEquals("Movies by Rating", data[0][2]);

        assertEquals("pie", data[1][0]);
        assertEquals("bar", data[1][1]);
        assertEquals("bar", data[1][2]);

        assertEquals("categories", data[2][0]);
        assertEquals("decades", data[2][1]);
        assertEquals("ratings", data[2][2]);
    }

    public void testChartDataUser()
    {
        String[][] data;

        data = ChartData.getUserLists();

        assertEquals(3, data.length);
        assertEquals(3, data[0].length);
        assertEquals(3, data[1].length);
        assertEquals(3, data[2].length);

        assertEquals("Users by Age Range", data[0][0]);
        assertEquals("Users by Gender", data[0][1]);
        assertEquals("Users by Rating", data[0][2]);

        assertEquals("bar", data[1][0]);
        assertEquals("pie", data[1][1]);
        assertEquals("bar", data[1][2]);

        assertEquals("ages", data[2][0]);
        assertEquals("genders", data[2][1]);
        assertEquals("ratings", data[2][2]);
    }

}