class BottleSong {
    private static String numberToWords(int num) {
        String[] words = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
        return words[num];
    }

    public static String recite(int startBottles, int takeDown) {
        StringBuilder lyrics = new StringBuilder();
        int bottles = startBottles;

        while (bottles > 0 && takeDown > 0) {
            String bottleWord = numberToWords(bottles);
            lyrics.append(bottleWord).append(" green bottle");
            if (bottles > 1) {
                lyrics.append("s");
            }
            lyrics.append(" hanging on the wall,\n");
            lyrics.append(bottleWord).append(" green bottle");
            if (bottles > 1) {
                lyrics.append("s");
            }
            lyrics.append(" hanging on the wall,\n");
            lyrics.append("And if one green bottle should accidentally fall,\n");
            bottles--;
            String newBottleWord = (bottles == 0) ? "no" : numberToWords(bottles);
            lyrics.append("There'll be ").append(newBottleWord.toLowerCase()).append(" green bottle");
            if (bottles > 1 || bottles == 0) {
                lyrics.append("s");
            }
            lyrics.append(" hanging on the wall.\n");

            takeDown--;
            if (bottles > 0 && takeDown > 0) {
                lyrics.append("\n");
            }
        }
        return lyrics.toString();
    }

}