package eu.tjago;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    private List<FabricField> desiredFarbicsFields = new ArrayList<>();


    public static void main(String[] args) {
        new Day3().run();
    }

    private void run() {

        List<String> inputs = Common.getStringArraysOutOfFile("res/Day3input.txt");

        inputs.stream()
//                .limit(1)
                .forEach(s -> desiredFarbicsFields.add(this.fillData(s)));

//        System.out.println(desiredFarbicsFields);
    }

    private FabricField fillData(String s) {
        System.out.println("Parsing string : " + s);
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(s);

        List<Integer> values = new ArrayList<>();
        while (matcher.find()) {
            values.add(Integer.parseInt(matcher.group()));
        }
        System.out.println(values);

        return new FabricField(values.get(1), values.get(2), values.get(3), values.get(4));
    }


    private class FabricField {
        private int posX, posY;
        private int width;
        private int height;

        FabricField() {}

        public FabricField(int posX, int posY, int width, int height) {
            this.width = width;
            this.height = height;
            this.posX = posX;
            this.posY = posY;
        }

        @Override
        public String toString() {
            return "FabricField{" +
                    "posX=" + posX +
                    ", posY=" + posY +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }
}
