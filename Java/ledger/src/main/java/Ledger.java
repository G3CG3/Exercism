import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Ledger {
    public LedgerEntry createLedgerEntry(String date, String description, int change) {
        return new LedgerEntry(date, description, change);
    }

    public String format(String cur, String loc, LedgerEntry[] entries) {
        if (!cur.equals("USD") && !cur.equals("EUR")) {
            throw new IllegalArgumentException("Invalid currency");
        }
        if (!loc.equals("en-US") && !loc.equals("nl-NL")) {
            throw new IllegalArgumentException("Invalid locale");
        }

        String curSymb = (cur.equals("USD")) ? "$" : "€";
        String datPat = (loc.equals("nl-NL")) ? "dd/MM/yyyy" : "MM/dd/yyyy";
        String decSep = (loc.equals("nl-NL")) ? "," : ".";
        String thSep = (loc.equals("nl-NL")) ? "." : ",";
        String header = loc.equals("nl-NL") ?
                "Datum      | Omschrijving              | Verandering  " :
                "Date       | Description               | Change       ";

        StringBuilder result = new StringBuilder(header);

        if (entries.length > 0) {
            List<LedgerEntry> sortedEntries = new ArrayList<>(Arrays.asList(entries));
            sortedEntries.sort(Comparator
                    .comparing(LedgerEntry::getLocalDate)
                    .thenComparing(e -> e.getChange() < 0 ? -1 : (e.getChange() > 0 ? 1 : 0)));

            for (LedgerEntry e : sortedEntries) {
                String date = e.getLocalDate().format(DateTimeFormatter.ofPattern(datPat));
                String desc = e.getDescription().length() > 25 ? e.getDescription().substring(0, 22) + "..." : e.getDescription();
                String formattedAmount = formatAmount(e.getChange(), curSymb, decSep, thSep, loc);
                result.append(String.format("\n%s | %-25s | %13s", date, desc, formattedAmount));
            }
        }

        return result.toString();
    }

    private String formatAmount(double change, String curSymb, String decSep, String thSep, String loc) {
        boolean isNegative = change < 0;
        String converted = String.format("%.2f", Math.abs(change) / 100);
        String[] parts = converted.split("\\.");

        StringBuilder amount = new StringBuilder();
        int count = 0;
        for (int i = parts[0].length() - 1; i >= 0; i--) {
            if (count > 0 && count % 3 == 0) {
                amount.insert(0, thSep);
            }
            amount.insert(0, parts[0].charAt(i));
            count++;
        }

        String finalAmount = loc.equals("nl-NL") ? curSymb + " " + amount + decSep + parts[1] : curSymb + amount + decSep + parts[1];

        if (isNegative) {
            return loc.equals("en-US") ? "(" + finalAmount + ")" : curSymb + " -" + amount + decSep + parts[1] + " ";
        }
        return loc.equals("nl-NL") ? " " + finalAmount + " " :  finalAmount + " ";
    }

    public static class LedgerEntry {
        private final LocalDate localDate;
        private final String description;
        private final double change;

        public LedgerEntry(String date, String description, int change) {
            this.localDate = LocalDate.parse(date);
            this.description = description;
            this.change = change;
        }

        public LocalDate getLocalDate() { return localDate; }
        public String getDescription() { return description; }
        public double getChange() { return change; }
    }
}