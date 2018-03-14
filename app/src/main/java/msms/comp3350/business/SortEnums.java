package msms.comp3350.business;


public abstract class SortEnums
{
    public enum MovieSortField
    {
        TITLE
        {
            public String toString()
            {
                return "UPPER(TITLE)";
            }
        },
    }
}

