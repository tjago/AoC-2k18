package eu.tjago;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        new Day4().run();
    }

    private void run() {
        List<String> input = Common.getStringArraysOutOfFile("res/Day4input.txt");

        List<Entry> entries = new ArrayList<>();
        input.forEach(s -> entries.add(parseEntry(s)));
        entries.sort(Entry::compareTo);
        System.out.println(entries);
    }

    private Entry parseEntry(String line) {
        String dateTime = line.substring(1,17);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return new Entry(LocalDateTime.parse(dateTime, formatter), line.substring(19));
    }

    class Entry implements Comparable {
        private LocalDateTime dateTime;
        private String action;

        public Entry(LocalDateTime dateTime, String action) {
            this.dateTime = dateTime;
            this.action = action;
        }

        @Override
        public String toString() {
            return "\nEntry{" +
                    "dateTime=" + dateTime +
                    ", action='" + action + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            return this.dateTime.compareTo(((Entry)o).dateTime);
        }
    }
}
