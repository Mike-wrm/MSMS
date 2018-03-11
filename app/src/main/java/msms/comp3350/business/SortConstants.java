package msms.comp3350.business;


public abstract class SortConstants
{
    enum uSortField
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

    enum mSortField
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

