package eu.tjago;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


        System.out.println(new Grid().initGrid(locations));

        //loop on Grid and set coordinates values to closest locations index values

    }

    class Location {
        int x, y;
        boolean cornerLocation;

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

    class Grid {
        int maxLength, maxWidth;

        Integer array[][];

        Grid() {}

        Grid(int maxLength, int maxWidth) {
            this.maxLength = maxLength;
            this.maxWidth = maxWidth;
            array = new Integer[maxLength][maxWidth];
        }

        Grid initGrid(List<Location> locations) {
            Optional<Integer> maxX = locations.stream()
                    .map(location -> location.x)
                    .max(Integer::compareTo);

            Optional<Integer> maxY = locations.stream()
                    .map(location -> location.y)
                    .max(Integer::compareTo);

            if (maxX.isPresent() && maxY.isPresent()) {
                return new Grid(maxX.get(), maxY.get());
            }

            throw new RuntimeException("Could not instantiate new Grid");

        }

        @Override
        public String toString() {
            return "Grid{" +
                    "maxLength=" + maxLength +
                    "maxWidth=" + maxWidth +
                    '}';
        }
    }

    /**
     * Algorithm concept
     *
     * 1. fill locations
     * 2. find location closest points
     * 3. create Grid
     * 4. loop all grid coordinates, for every point find closest Loc
     */
}
