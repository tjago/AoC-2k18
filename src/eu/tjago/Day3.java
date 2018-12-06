package eu.tjago;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day3 {


    List<FabricField> desiredFarbicsFields = new ArrayList<>();

    public static void main(String[] args) {
        new Day3().run();
    }

    private void run() {

        List<String> inputs = Common.getStringArraysOutOfFile("res/Day3input.txt");

        inputs.stream()
//                .limit(10)
                .forEach(s -> desiredFarbicsFields.add(this.fillData(s)));

//        System.out.println(desiredFarbicsFields);

        Optional<FabricField> widest =
                desiredFarbicsFields.stream()
                        .reduce(FabricField::widerFabric);

        Optional<FabricField> longest =
                desiredFarbicsFields.stream()
                        .reduce(FabricField::longestFabric);
        System.out.println("Fabric most to far right: " + widest.get());
        System.out.println("Fabric most to far bottom: " + longest.get());

        //Part 1
        IntStream
                .rangeClosed(1, widest.get().posX + widest.get().width)
                .forEach(i -> {
                    IntStream
                            .rangeClosed(1, longest.get().posY + longest.get().height)
                            .forEach(j -> this.checkSquareInchForNumberOccupied(i, j));
                });

        System.out.println("Number of covered area in square inches of overlapping fabrics: " + counter);

        //Part 2

        long fabricsNum = desiredFarbicsFields.stream()
                .filter(this::checkforNonOverlappingFabric)
                .count();

        System.out.printf("Found %d fabrics non overlapping\n", fabricsNum);
    }

    private boolean checkforNonOverlappingFabric(final FabricField fabric) {

        long count = desiredFarbicsFields.stream()
                .filter(itFab -> fabric.isFabricOverlapping(itFab))
                .count();

        System.out.println(fabric.id + " " + count + " out of " + desiredFarbicsFields.size());
        return count == 0;

    }

    long counter = 0;

    private void checkSquareInchForNumberOccupied(final int i, final int j) {

        long numFabrics = desiredFarbicsFields.stream()
                .filter(fabric -> fabric.isFabriCoveringPoint(i, j))
                .count();
        if (numFabrics >= 2) {
//            System.out.printf("fabrics at point (%d, %d) = %d\n", i, j, numFabrics);
            counter++;
        }
    }

    private FabricField fillData(String s) {
        //System.out.println("Parsing string : " + s);
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(s);

        List<Integer> values = new ArrayList<>();
        while (matcher.find()) {
            values.add(Integer.parseInt(matcher.group()));
        }
        //System.out.println(values);

        return new FabricField(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
    }


    private class FabricField {
        private int posX, posY;
        private int width;
        private int height;
        private int id;

        FabricField() {
        }

        FabricField(int id, int posX, int posY, int width, int height) {
            this.width = width;
            this.height = height;
            this.posX = posX;
            this.posY = posY;
            this.id = id;
        }

        FabricField widerFabric(FabricField other) {
            return (this.posX + this.width > other.posX + other.width) ? this : other;
        }

        FabricField longestFabric(FabricField other) {
            return (this.posY + this.height > other.posY + other.height) ? this : other;
        }

        boolean isFabriCoveringPoint(int x, int y) {
            return x >= posX &&
                    x < posX + width &&
                    y >= posY &&
                    y < posY + height;
        }

        //check if one of the 4 corners is within boundary
        boolean isFabricOverlapping(FabricField other) {
            if (other == this) return false;
            return (other.posX >= posX &&
                    other.posX <= posX + width-1 &&
                    other.posY >= posY &&
                    other.posY <= posY + height-1)
                    || (
                    other.posX + other.width-1 >= posX &&
                    other.posX + other.width-1 <= posX + width-1 &&
                    other.posY >= posY &&
                    other.posY <= posY + height-1)
                    || (
                    other.posX + other.width-1 >= posX &&
                    other.posX + other.width-1 <= posX + width-1 &&
                    other.posY + other.height-1 >= posY &&
                    other.posY + other.height-1 <= posY + height-1)
                    || (
                    other.posX >= posX &&
                    other.posX <= posX + width-1 &&
                    other.posY + other.height-1 >= posY &&
                    other.posY + other.height-1 <= posY + height-1);

        }

        @Override
        public String toString() {
            return "FabricField{" +
                    "id=" + id +
                    ", posX=" + posX +
                    ", posY=" + posY +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }
}
