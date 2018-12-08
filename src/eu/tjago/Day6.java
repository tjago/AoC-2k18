package eu.tjago;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        Set<Integer> filteredLocations = grid.filterCornerLocations();

        Set<Integer> properLocations = setProperLocations(locations, filteredLocations);

        System.out.println("filtered:" + filteredLocations);

        System.out.println("curated list:" + properLocations);

        Optional<Long> resultPart1 = grid.countBiggestAreaAmongLocations(properLocations);

        System.out.println("result part 1: " + resultPart1);


        Grid grid2 = new Grid().initGrid(locations);

        System.out.println("Part 2 result: " + grid.markGridFields(locations));
    }

    private Set<Integer> setProperLocations(List<Location> locations, Set<Integer> filteredLocations) {
        return locations.stream()
                .filter(location -> !filteredLocations.contains(location.getID()))
                .mapToInt(Location::getID)
                .boxed()
                .collect(Collectors.toSet());
    }

    class Location {
        int x, y;

        Location() {
        }

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Integer calcDistanceValueToCoordinate(int x, int y) {
            return abs(this.x - x) + abs(this.y - y);
        }

        Integer getID() {
            return Integer.parseInt(String.valueOf(x) + y);
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
        int maxLength, maxHeight;

        Integer array[][];

        int BUFFER_SIZE = 50;

        Grid() {
        }

        Grid(int maxLength, int maxHeight) {
            this.maxLength = maxLength;
            this.maxHeight = maxHeight;
            array = new Integer[maxLength][maxHeight];
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

        void setGrid(List<Location> locations) {
            long counter = 0;
            for (int x = 0; x < maxLength; x++)
                for (int y = 0; y < maxHeight; y++) {
                    final int parmx = x, parmy = y;
                    Map<Location, Integer> locationDistanceVals = locations.stream()
                            .collect(Collectors.toMap(Function.identity(),
                                    o -> ((Location) o).calcDistanceValueToCoordinate(parmx, parmy)));
                    array[x][y] = this.findSmallestManhattanDistance(locationDistanceVals);
                }
        }

        int markGridFields(List<Location> locations) {
            int counter = 0;
            for (int x = 0; x < maxLength; x++)
                for (int y = 0; y < maxHeight; y++) {
                    final int parmx = x, parmy = y;
                    int sumOfLocationDistanceVals =
                            locations.stream()
                            .mapToInt(value -> value.calcDistanceValueToCoordinate(parmx, parmy))
                            .sum();
                    if (sumOfLocationDistanceVals < 10_000) {
                        counter++;
                    }
                }
            return counter;
        }

        private Integer findSmallestManhattanDistance(Map<Location, Integer> items) {

            //find Min value
            Optional<Map.Entry<Location, Integer>> minVal =
                    items.entrySet().stream()
                            .min(Comparator.comparingInt(Map.Entry::getValue));

            long counter = items.entrySet().stream()
                    .filter(locationIntegerEntry -> locationIntegerEntry.getValue() == minVal.get().getValue())
                    .count();

            if (counter == 1) return minVal.get().getKey().getID();
            else return -1;
        }

        @Override
        public String toString() {
            return "Grid{" +
                    "maxLength=" + maxLength +
                    "maxHeight=" + maxHeight +
                    '}';
        }

        Set<Integer> filterCornerLocations() {
            Set<Integer> cornerLocationIDs;
            cornerLocationIDs =
                    IntStream
                            .range(0, maxHeight)
                            .boxed()
                            .map(integer -> array[0][integer])
                            .collect(Collectors.toSet());

            cornerLocationIDs.addAll(
                    IntStream
                            .range(0, maxLength)
                            .boxed()
                            .map(integer -> array[integer][0])
                            .collect(Collectors.toSet())
            );


            cornerLocationIDs.addAll(
                    IntStream
                            .range(0, maxHeight)
                            .boxed()
                            .map(integer -> array[maxLength - 1][integer])
                            .collect(Collectors.toSet())
            );
            cornerLocationIDs.addAll(
                    IntStream
                            .range(0, maxLength)
                            .boxed()
                            .map(integer -> array[integer][maxHeight - 1])
                            .collect(Collectors.toSet())
            );
            return cornerLocationIDs;
        }

        public Optional<Long> countBiggestAreaAmongLocations(Set<Integer> locations) {

            return locations.stream()
                    .map(id -> IntStream
                            .range(0, maxHeight)
                            .mapToLong(y -> IntStream.range(0, maxLength)
                                    .filter(x -> array[x][y].equals(id))
                                    .count())
                            .sum())
                    .peek(aLong -> System.out.println("Sum for next location: " + aLong))
                    .max(Long::compareTo);
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
