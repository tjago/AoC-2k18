package eu.tjago;

import java.util.*;

public class Day1 {

    private Deque<Long> intermidiateResults;

    public static void main(String[] args) {
        new Day1().run();
    }

    private void run() {
        intermidiateResults = new ArrayDeque<>();

        intermidiateResults.add(0L);//set initial freq to Zero

        List<String> inputs = Common.getStringArraysOutOfFile("res/Day1input.txt");

        //Part 1: find final resulting frequency
        inputs.stream()
                .map(Long::parseLong)
                .reduce(Long::sum)
                .ifPresent((System.out::println));

        //Part 2: Find first freq occur twice
        Optional<Long> firstFreqObservedTwice = Optional.empty();
        while(!firstFreqObservedTwice.isPresent()) {
            int prevSize = intermidiateResults.size();

            inputs.stream()
                    .mapToLong(Long::parseLong)
                    .forEach(this::incrementalIntermediateResultQueue);

            System.out.println("Size of increased Intermediate list: " + intermidiateResults.size());
            firstFreqObservedTwice =
                    intermidiateResults.parallelStream()
                            .skip(intermidiateResults.size() - prevSize)
                            .filter(i -> Collections.frequency(intermidiateResults, i) > 1)
                            .findFirst();
        }
        System.out.println("Found: " + firstFreqObservedTwice.get());
    }

    private void incrementalIntermediateResultQueue(Long val) {
        intermidiateResults.addLast(intermidiateResults.getLast() + val);
    }
}
