package msms.comp3350.business;


public abstract class SortEnums
{
    public enum UserSortField
    {
        USERNAME
        {
            public String toString()
            {
                return "UPPER(USERNAME)";
            }
        },
    }

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

