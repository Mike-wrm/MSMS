//Testing adding and printing
package DomainObjects;

import java.io.*;
import java.util.ArrayList;

public class Printing 
{
	public static void main (String args[])
	{
		ArrayList movieList = new ArrayList();
		ArrayList userList = new ArrayList();
		
		BufferedReader inputFile;
		String inputLine;
		
		
		String title = null;
		int releaseYear = 0;
		int userScore = 0;
		String category = null;
		int expiryDate = 0;
		String description = null;
		
		try
		{
			inputFile = new BufferedReader(new FileReader("movies.txt"));		//Reading the file

			inputLine = inputFile.readLine();	//movie title
			while (inputLine != null)
			{
				title = inputLine.trim();
				
				inputLine = inputFile.readLine();
				releaseYear = Integer.parseInt(inputLine.trim());

				inputLine = inputFile.readLine();
				userScore = Integer.parseInt(inputLine.trim());
				
				inputLine = inputFile.readLine();
				category = inputLine.trim();

				inputLine = inputFile.readLine();
				expiryDate = Integer.parseInt(inputLine.trim());
				
				inputLine = inputFile.readLine();
				description = inputLine.trim();
				
				Movie inputMovie = new Movie(title, releaseYear, userScore, category, expiryDate, description);
				movieList.add(inputMovie);
				
				inputLine = inputFile.readLine();	//next movie title
			}

			inputFile.close();
		}
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}
		
		for (int i = 0; i < movieList.size(); i++)
		{
			Movie MovieX = (Movie) movieList.get(i);
			MovieX.print();
		}
	}
}
