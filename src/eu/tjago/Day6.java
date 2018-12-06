package eu.tjago;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 {
    public static void main(String[] args) {
        new Day6().run();
    }

    private void run() {

        List<String> input = Common.getStringArraysOutOfFile("res/Day6input.txt");

        List<Location> locations =
            input.stream()
                .map(s -> new Location(Integer.parseInt(s.split(", ")[0]), Integer.parseInt(s.split(", ")[1])))
                .collect(Collectors.toList());


        System.out.println(locations);
    }

    class Location {
        int x, y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "\nLocation{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    /**
     * Algirthm
     * 1. fill locations
     * 2. find location closest points
     * 3. calculate the grid map
     *
     * v2
     * loop all grid coordinates, for every
     */
}
