package eu.tjago;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    public static void main(String[] args) {
        new Day7().run();
    }


    private void run() {

        Queue<Character> solutionSequence = new ArrayDeque();

        //Part1
        List<String> input = Common.getStringArraysOutOfFile("res/Day7input.txt");

        Map<Character, Set<Character>> steps = new HashMap<>(); //step char + set of req;

        //Steps with no requirements
        Set<Character> mutableAvailableSteps = new HashSet<>();
        //Manually checked which Steps don't need other requirements
        mutableAvailableSteps.add('F');
        mutableAvailableSteps.add('H');
        mutableAvailableSteps.add('I');
        solutionSequence.add('F');
        solutionSequence.add('H');
        solutionSequence.add('I');

        input.forEach(s -> this.fillData(s, steps));
        System.out.println(steps);

        do {
            Optional<Character> newAvailableStepToComplete =
                    steps.entrySet().stream()
                            .filter(setEntry -> mutableAvailableSteps.containsAll(setEntry.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst();
            if (newAvailableStepToComplete.isPresent()) {
                solutionSequence.add(newAvailableStepToComplete.get());
                mutableAvailableSteps.add(newAvailableStepToComplete.get());
                steps.remove(newAvailableStepToComplete.get());
            } else {
                break;
            }
        } while (true);

        System.out.println("Solution Sequence: " + solutionSequence);
        //FHICMRTXYDBOAJNPWQGVZUEKLS

        //Part 2
        int timer = 0;
        do {
            //to be implemented
        } while (false);
        /**
         * Create a pool of available Steps
         * in Loop, evey second check available pool and assign free Step to worker by adding him time to finish
         */
    }

    private void fillData(String s, Map<Character, Set<Character>> steps) {

        Pattern pattern = Pattern.compile(" [A-Z] ");
        Matcher matcher = pattern.matcher(s);

        matcher.find();
        Character requirement = matcher.group().trim().toCharArray()[0];
        matcher.find();
        Character stepName = matcher.group().trim().toCharArray()[0];

        if (steps.containsKey(stepName)) {
            steps.get(stepName).add(requirement);
        } else {
            Set rr = new HashSet<>();
            rr.add(requirement);
            steps.putIfAbsent(stepName, rr);
        }
    }

}
