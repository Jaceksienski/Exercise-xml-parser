package com.jaca;

/**
 *
 * As part of an administrator dashboard, a monitoring system parses an XML log file containing system events. Consider the
 * log below:
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <log>
 * <event timestamp="1614285589">
 * <description>Intrusion detected</description>
 * </event>
 * <event timestamp="1614286432">
 * <description>Intrusion ended</description>
 * </event>
 * </log>
 * The dashboard needs to aggregate event descriptions based on their timestamps. Implement the function
 * getTimestampsByDescription that returns the timestamps of the event entries with a specific description.
 * For example, getTimestampsByDescription for the log above and the string "Intrusion ended" should return
 * { 1614286432 }.
 */

import java.util.*;
class LogParser {
    public static Collection<Long> getTimestampsByDescription(String xml, String description) throws Exception {
        List<String> lines = new ArrayList<>();
        List<Long> longsCollection = new ArrayList<>();

        Scanner scanner = new Scanner(xml);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        scanner.close();

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains(description)){
                ArrayList<String> partsOfString = new ArrayList<>(Arrays.asList(lines.get(i - 1).split("\"")));
                for (String string :
                        partsOfString) {
                    if (isANumber(string)) longsCollection.add(Long.parseLong(string));;
                }
            }
        }
        return longsCollection;
    }

    public static boolean isANumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<log>\n" +
                        " <event timestamp=\"1614285589\">\n" +
                        " <description>Intrusion detected</description>\n" +
                        " </event>\n" +
                        " <event timestamp=\"1614286432\">\n" +
                        " <description>Intrusion ended</description>\n" +
                        " </event>\n" +
                        "</log>";
        Collection<Long> timestamps = getTimestampsByDescription(xml, "Intrusion ended");
        for(long timestamp: timestamps)
            System.out.println(timestamp);
    }
}