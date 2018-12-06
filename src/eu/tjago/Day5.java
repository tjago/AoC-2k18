package eu.tjago;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class Day5 {
    public static void main(String[] args) {
        new Day5().run();
    }

    private void run() {
        List<String> input = Common.getStringArraysOutOfFile("res/Day5input.txt");


        List<Integer> longPolymer = input.get(0).chars().boxed().collect(Collectors.toList());
        System.out.println("size of longPolymer before parsing: " + longPolymer.size());

        int result = processPolymer(longPolymer, 0);

        longPolymer.stream().forEach(integer -> System.out.print(Character.toChars(integer)));

        System.out.println();
        System.out.println("size of longPolymer after parsing: " + result);


        //Part 2


        IntStream
                .rangeClosed(65,90)
                .forEach(value -> {
                    List<Integer>  newLongPolymer = input.get(0).chars().boxed().collect(Collectors.toList());
                    int result2 = processPolymer(newLongPolymer, value);
                    System.out.println(result2);
        });


    }

    private int processPolymer(List<Integer> longPolymer, int preCleanPolymer) {

        System.out.println("processng for .... " + Integer.toString(preCleanPolymer));
        if (preCleanPolymer != 0) {
            for (int i = 0; i < longPolymer.size() -1; i++) {
                     if (       longPolymer.get(i) == preCleanPolymer
                             || longPolymer.get(i) == preCleanPolymer + 32)
                     {
                         longPolymer.remove(i);
                         if (i != 0) i--;
                     }
                    }
        }

        boolean foundReaction;
        do {
            foundReaction = false;
            for (int i = 1; i < longPolymer.size() -1; i++) {
                if (abs(longPolymer.get(i) - longPolymer.get(i-1)) == 32) {
                    longPolymer.remove((int)i);
                    longPolymer.remove(i-1);
                    foundReaction = true;
                }
            }
        }
        while(foundReaction);

        return longPolymer.size();
    }
}
