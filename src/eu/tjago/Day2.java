package eu.tjago;

import java.util.*;
import java.util.stream.IntStream;

public class Day2 {
    public static void main(String[] args) {
        new Day2().run();
    }

    int occuranceOf2 = 0;
    int occuranceOf3 = 0;

    private void run() {

        List<String> inputs = Common.getStringArraysOutOfFile("res/Day2input.txt");

        //Part 1 - Checksum of boxes
        inputs.stream()
                .forEach(this::countLetterOccurances);
        System.out.printf("Checksum is %d (%d x %d)\n", occuranceOf2 * occuranceOf3, occuranceOf2, occuranceOf3);

        //Part 2 - find similar boxids
        inputs.forEach(
                pattern -> inputs.forEach(
                        iterableString -> this.compareToRestOfBoxes(pattern, iterableString)
                ));
    }

    private void compareToRestOfBoxes(String a, String b) {
        if(a.equals(b)) return;
        IntStream
                .range(0, a.length())
                .forEach( index -> {
                    String newA = a.substring(0, index) + a.substring(index+1);
                    String newB = b.substring(0, index) + b.substring(index+1);
                    if (newA.equals(newB)) {
                        System.out.println("Matching strings:");
                        System.out.println(a);
                        System.out.println(b);
                        System.out.println("Puzzle result: " + newA);
                    }
                });
    }

    private void countLetterOccurances(String boxid) {

        long multiply2, multiply3;

        multiply2 = boxid.chars()
                .filter(val -> countRepeats(val, boxid) == 2)
                .count();

        multiply3 = boxid.chars()
                .filter(val -> countRepeats(val, boxid) == 3)
                .count();

        if (multiply2 > 0) occuranceOf2++;
        if (multiply3 > 0) occuranceOf3++;
    }

    private long countRepeats(Integer i, String s) {
        return s.chars().filter(val -> val == i).count();
    }
}
