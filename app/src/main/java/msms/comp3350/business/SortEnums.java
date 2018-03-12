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

        GENDER
        {
            public String toString()
            {
                return "GENDER";
            }
        }
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

        RELEASEYEAR
        {
            public String toString()
            {
                return "RELEASEYEAR";
            }
        }
    }

}

