package DomainObjects;

public class Movie 
{	
	//Implementation of genres for now...
	enum Category 
	{
	    COMEDY, DRAMA, HORROR, ACTION, FANTASY, FAMILY, RECENT, TRENDING
	}

	private String title;
	private int releaseYear;
	private int userScore;
	private Category cat1;
	private Category cat2;
	private Category cat3;
	private int licenseEndDate;
	private String description;
	
	//Constructor
	public Movie (String title, int releaseYear, int userScore, String category1, int licenseEndDate, String description)
	{
		this.title = title;
		this.releaseYear = releaseYear;
		this.userScore = userScore;
		this.cat1 = getCategory(category1);
		cat2 = cat3 = null;						//for now...
		this.licenseEndDate = licenseEndDate;
		this.description = description;
	}
	
	public Category getCategory(String test)
	{
		if (test.equalsIgnoreCase("comedy"))
		{
			return Category.COMEDY;
		}
		else if (test.equalsIgnoreCase("drama"))
		{
			return Category.DRAMA;			
		}
		else if (test.equalsIgnoreCase("horror"))
		{
			return Category.HORROR;			
		}
		else if (test.equalsIgnoreCase("action"))
		{
			return Category.ACTION;			
		}
		else if (test.equalsIgnoreCase("fantasy"))
		{
			return Category.FANTASY;			
		}
		else if (test.equalsIgnoreCase("family"))
		{
			return Category.FAMILY;			
		}
		else if (test.equalsIgnoreCase("recent"))
		{
			return Category.RECENT;			
		}
		else if (test.equalsIgnoreCase("trending"))
		{
			return Category.TRENDING;			
		}
		
		return null;
	}
	
	public Boolean compareTo(String test)
	{
		if (test.equalsIgnoreCase(title))
		{
			return true;
		}
		
		return false;
	}
	
	//Prints just the title (when clicking to see entire list of movies)
	public void printTitle()
	{
		System.out.println(title);
	}
	
	//Prints all movie details (when movie clicked on in UI)
	public void print()
	{
		printTitle();
		System.out.println("Released in " + releaseYear + "/tLicense Expires: " + licenseEndDate);
		System.out.println("User Score: " + userScore + "/tCategory: " + cat1);
		System.out.println("Description: " + description + "\n");
		System.out.println();
	}
}
