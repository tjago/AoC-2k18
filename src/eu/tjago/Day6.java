package eu.tjago;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

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

        Grid grid = new Grid().initGrid(locations);

        System.out.println(grid);

        grid.setGrid(locations);

        //loop on Grid and set coordinates values to closest locations index values

    }

    class Location {
        int x, y;
        boolean cornerLocation;

        Location() {}

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Integer calcDistanceValeToCoordinate(int x, int y) {
            return abs(this.x - x) + abs(this.y - y);
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

        void setGridFieldValue(List<Location> locations) {

        }

        void setGrid(List<Location> locations) {

            for (int x = 0 ; x < maxWidth; x++)
                for (int y = 0; y < maxLength; y++) {
                    final int parmx = x, parmy = y;
                    Map<Location, Integer> temp = locations.stream()
                            .collect(Collectors.toMap(Function.identity(),
                                    o -> ((Location)o).calcDistanceValeToCoordinate(parmx,parmy)));
                            int setBreakpoint = 0;
                }
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
     * 3. create Grid
     * 4. loop all grid coordinates, for every point find closest Loc
     */
}
