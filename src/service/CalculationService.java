package service;

import java.util.*;
import java.util.stream.Collectors;

public class CalculationService {
    private int solutionMaxSteps = 30;
    private final List<Boolean> startPosition;
    private static final List<Integer> BUTTON_1_BUTTONS_SWITCHED_INDEXES = List.of(1, 2, 4);
    private static final List<Integer> BUTTON_2_BUTTONS_SWITCHED_INDEXES = List.of(1, 2, 3, 5);
    private static final List<Integer> BUTTON_3_BUTTONS_SWITCHED_INDEXES = List.of(2, 3, 6);
    private static final List<Integer> BUTTON_4_BUTTONS_SWITCHED_INDEXES = List.of(1, 4, 5, 7);
    private static final List<Integer> BUTTON_5_BUTTONS_SWITCHED_INDEXES = List.of(2, 4, 5, 6, 8);
    private static final List<Integer> BUTTON_6_BUTTONS_SWITCHED_INDEXES = List.of(3, 5, 6, 9);
    private static final List<Integer> BUTTON_7_BUTTONS_SWITCHED_INDEXES = List.of(4, 7, 8);
    private static final List<Integer> BUTTON_8_BUTTONS_SWITCHED_INDEXES = List.of(5, 7, 8, 9);
    private static final List<Integer> BUTTON_9_BUTTONS_SWITCHED_INDEXES = List.of(6, 8, 9);
//
//    static {
//        final List<Integer> BUTTON_1_BUTTONS_SWITCHED_INDEXES = List.of(0, 1, 3);
//        final List<Integer> BUTTON_2_BUTTONS_SWITCHED_INDEXES = List.of(0, 1, 2, 4);
//        final List<Integer> BUTTON_3_BUTTONS_SWITCHED_INDEXES = List.of(1, 2, 5);
//        final List<Integer> BUTTON_4_BUTTONS_SWITCHED_INDEXES = List.of(0, 3, 4, 6);
//        final List<Integer> BUTTON_5_BUTTONS_SWITCHED_INDEXES = List.of(1, 3, 4, 5, 7);
//        final List<Integer> BUTTON_6_BUTTONS_SWITCHED_INDEXES = List.of(2, 4, 5, 8);
//        final List<Integer> BUTTON_7_BUTTONS_SWITCHED_INDEXES = List.of(3, 6, 7);
//        final List<Integer> BUTTON_8_BUTTONS_SWITCHED_INDEXES = List.of(4, 6, 7, 8);
//        final List<Integer> BUTTON_9_BUTTONS_SWITCHED_INDEXES = List.of(5, 7, 8);
//    }

    private final Random random;
    private final Integer min = 1;
    private final Integer max = 9;
    private int maxSolutionsRequired = 50;
    private Integer tries = 1;
    //    private List<Boolean> listCopy = new ArrayList<>(30);
    private Map<Integer, Boolean> booleanMap = new HashMap<>();

    public CalculationService(List<Boolean> startPosition) {
        this.startPosition = startPosition;
//        this.BUTTON_1_BUTTONS_SWITCHED_INDEXES = List.of(0, 1, 3, 4, 7);
//        this.BUTTON_2_BUTTONS_SWITCHED_INDEXES = List.of(0, 1, 2, 4, 6, 8);
//        this.BUTTON_3_BUTTONS_SWITCHED_INDEXES = List.of(0, 2, 6);
//        this.BUTTON_4_BUTTONS_SWITCHED_INDEXES = List.of(0, 1, 2, 3);
//        this.BUTTON_5_BUTTONS_SWITCHED_INDEXES = List.of(3, 4, 5);
//        this.BUTTON_6_BUTTONS_SWITCHED_INDEXES = List.of(2, 5, 8);
//        this.BUTTON_7_BUTTONS_SWITCHED_INDEXES = List.of(2, 4, 6);
//        this.BUTTON_8_BUTTONS_SWITCHED_INDEXES = List.of(1, 4, 7);
//        this.BUTTON_9_BUTTONS_SWITCHED_INDEXES = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8);
//        this.BUTTON_1_BUTTONS_SWITCHED_INDEXES = List.of(0, 1, 3);
//        this.BUTTON_2_BUTTONS_SWITCHED_INDEXES = List.of(0, 1, 2, 4);
//        this.BUTTON_3_BUTTONS_SWITCHED_INDEXES = List.of(1, 2, 5);
//        this.BUTTON_4_BUTTONS_SWITCHED_INDEXES = List.of(0, 3, 4, 6);
//        this.BUTTON_5_BUTTONS_SWITCHED_INDEXES = List.of(1, 3, 4, 5, 7);
//        this.BUTTON_6_BUTTONS_SWITCHED_INDEXES = List.of(2, 4, 5, 8);
//        this.BUTTON_7_BUTTONS_SWITCHED_INDEXES = List.of(3, 6, 7);
//        this.BUTTON_8_BUTTONS_SWITCHED_INDEXES = List.of(4, 6, 7, 8);
//        this.BUTTON_9_BUTTONS_SWITCHED_INDEXES = List.of(5, 7, 8);
        this.random = new Random();
    }

    public void calculateSolution() {
        initMap();
//        listCopy.clear();
//        listCopy.addAll(startPosition);
        List<List<Integer>> solutions = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();

        System.out.println("Calculation started.");
        for (int i = 0; i < maxSolutionsRequired; i++) {
            do {
                initMap();
//            listCopy = List.copyOf(startPosition);
                solution = new ArrayList<>();
                for (int j = 0; j < solutionMaxSteps; j++) {

                    Integer buttonPressed = getButtonPressed();
                    makeStep(buttonPressed, booleanMap);
                    solution.add(buttonPressed);
                    if (isSolved(booleanMap)) {
                        break;
                    }
                }
            } while (!isSolved(booleanMap));
            solutions.add(solution);
        }


        System.out.println("Calculation ended");
        System.out.println("Solutions: ");
        final Integer minStepsCount = solutions.stream()
                .map(List::size)
                .min(Integer::compare)
                .orElse(5);
        final List<List<Integer>> minStepsSolutions = solutions.stream().filter(e -> minStepsCount.equals(e.size())).collect(Collectors.toList());
        for (List<Integer> s : solutions) {
            System.out.println(s.toString());
        }
    }

    private void initMap() {
        booleanMap.clear();
        for (int i = 1; i < 10; i++) {
            booleanMap.put(i, startPosition.get(i - 1));
        }
    }

    private void makeStep(int buttonPressed, Map<Integer, Boolean> map) {
        switch (buttonPressed) {
            case 1: {
                press1(map);
                break;
            }
            case 2: {
                press2(map);
                break;
            }
            case 3: {
                press3(map);
                break;
            }
            case 4: {
                press4(map);
                break;
            }
            case 5: {
                press5(map);
                break;
            }
            case 6: {
                press6(map);
                break;
            }
            case 7: {
                press7(map);
                break;
            }
            case 8: {
                press8(map);
                break;
            }
            case 9: {
                press9(map);
                break;
            }
            default:break;
        }
    }

    private Integer getButtonPressed() {
        return random.nextInt(max - min) + min;
    }

    private boolean isSolved(Map<Integer, Boolean> map) {
        return map.values().stream().allMatch(Boolean.TRUE::equals)
//                || map.values().stream().allMatch(Boolean.FALSE::equals)
                ;
    }

    private void press1(Map<Integer, Boolean> booleanMap) {
        for (Integer index : BUTTON_1_BUTTONS_SWITCHED_INDEXES) {
            booleanMap.put(index, !booleanMap.get(index));
        }

//        BUTTON_1_BUTTONS_SWITCHED_INDEXES.forEach(integer ->
//                booleanList.set(integer, !booleanList.get(integer))
//        );
    }

    private void press2(Map<Integer, Boolean> booleanMap) {
        for (Integer index : BUTTON_2_BUTTONS_SWITCHED_INDEXES) {
            booleanMap.put(index, !booleanMap.get(index));
        }
//        BUTTON_2_BUTTONS_SWITCHED_INDEXES.forEach(integer ->
//                booleanList.set(integer, !booleanList.get(integer))
//        );
    }

    private void press3(Map<Integer, Boolean> booleanMap) {
        for (Integer index : BUTTON_3_BUTTONS_SWITCHED_INDEXES) {
            booleanMap.put(index, !booleanMap.get(index));
        }
//        BUTTON_3_BUTTONS_SWITCHED_INDEXES.forEach(integer ->
//                booleanList.set(integer, !booleanList.get(integer))
//        );
    }

    private void press4(Map<Integer, Boolean> booleanMap) {
        for (Integer index : BUTTON_4_BUTTONS_SWITCHED_INDEXES) {
            booleanMap.put(index, !booleanMap.get(index));
        }
//        BUTTON_4_BUTTONS_SWITCHED_INDEXES.forEach(integer ->
//                booleanList.set(integer, !booleanList.get(integer))
//        );
    }

    private void press5(Map<Integer, Boolean> booleanMap) {
        for (Integer index : BUTTON_5_BUTTONS_SWITCHED_INDEXES) {
            booleanMap.put(index, !booleanMap.get(index));
        }
//        BUTTON_5_BUTTONS_SWITCHED_INDEXES.forEach(integer ->
//                booleanList.set(integer, !booleanList.get(integer))
//        );
    }

    private void press6(Map<Integer, Boolean> booleanMap) {
        for (Integer index : BUTTON_6_BUTTONS_SWITCHED_INDEXES) {
            booleanMap.put(index, !booleanMap.get(index));
        }
//        BUTTON_6_BUTTONS_SWITCHED_INDEXES.forEach(integer ->
//                booleanList.set(integer, !booleanList.get(integer))
//        );
    }

    private void press7(Map<Integer, Boolean> booleanMap) {
        for (Integer index : BUTTON_7_BUTTONS_SWITCHED_INDEXES) {
            booleanMap.put(index, !booleanMap.get(index));
        }
//        BUTTON_7_BUTTONS_SWITCHED_INDEXES.forEach(integer ->
//                booleanList.set(integer, !booleanList.get(integer))
//        );
    }

    private void press8(Map<Integer, Boolean> booleanMap) {
        for (Integer index : BUTTON_8_BUTTONS_SWITCHED_INDEXES) {
            booleanMap.put(index, !booleanMap.get(index));
        }
//        BUTTON_8_BUTTONS_SWITCHED_INDEXES.forEach(integer ->
//                booleanList.set(integer, !booleanList.get(integer))
//        );
    }

    private void press9(Map<Integer, Boolean> booleanMap) {
        for (Integer index : BUTTON_9_BUTTONS_SWITCHED_INDEXES) {
            booleanMap.put(index, !booleanMap.get(index));
        }
//        BUTTON_9_BUTTONS_SWITCHED_INDEXES.forEach(integer ->
//                booleanList.set(integer, !booleanList.get(integer))
//        );
    }
}
