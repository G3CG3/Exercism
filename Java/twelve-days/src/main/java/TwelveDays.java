class TwelveDays {
    private static final String[] DAYS = {
            "first", "second", "third", "fourth", "fifth", "sixth", "seventh",
            "eighth", "ninth", "tenth", "eleventh", "twelfth"
    };

    private static final String[] GIFTS = {
            "a Partridge in a Pear Tree.",
            "two Turtle Doves, and ",
            "three French Hens, ",
            "four Calling Birds, ",
            "five Gold Rings, ",
            "six Geese-a-Laying, ",
            "seven Swans-a-Swimming, ",
            "eight Maids-a-Milking, ",
            "nine Ladies Dancing, ",
            "ten Lords-a-Leaping, ",
            "eleven Pipers Piping, ",
            "twelve Drummers Drumming, "
    };

    public String verse(int verseNumber) {
        StringBuilder verse = new StringBuilder("On the " + DAYS[verseNumber - 1] + " day of Christmas my true love gave to me: ");
        for (int i = verseNumber - 1; i >= 0; i--) {
            verse.append(GIFTS[i]);
        }
        return verse.append("\n").toString();
    }

    public String verses(int startVerse, int endVerse) {
        StringBuilder verses = new StringBuilder();
        for (int i = startVerse; i <= endVerse; i++) {
            if (i < endVerse) {
                verses.append(verse(i)).append("\n");
            } else {
                verses.append(verse(i).trim());
            }
        }
        return verses.append("\n").toString();
    }

    public String sing() {
        return verses(1, 12);
    }
}

