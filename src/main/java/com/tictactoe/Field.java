package com.tictactoe;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Field {
    private final Map<Integer, Sign> field;

    public Field() {
        field = new HashMap<>();
        field.put(0, Sign.EMPTY);
        field.put(1, Sign.EMPTY);
        field.put(2, Sign.EMPTY);
        field.put(3, Sign.EMPTY);
        field.put(4, Sign.EMPTY);
        field.put(5, Sign.EMPTY);
        field.put(6, Sign.EMPTY);
        field.put(7, Sign.EMPTY);
        field.put(8, Sign.EMPTY);
    }

    public Map<Integer, Sign> getField() {
        return field;
    }

    public int getEmptyFieldIndex() {
        List<Integer> ints = field.entrySet().stream()
                .filter(e -> e.getValue() == Sign.EMPTY)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        int index = (int) (Math.random() * ints.size());
        if (ints.size() == 0) return -1;
        return ints.get(index);

    }

    public List<Sign> getFieldData() {
        return field.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Sign checkWin() {
        List<List<Integer>> winPossibilities = List.of(
                List.of(0, 1, 2),
                List.of(3, 4, 5),
                List.of(6, 7, 8),
                List.of(0, 3, 6),
                List.of(1, 4, 7),
                List.of(2, 5, 8),
                List.of(0, 4, 8),
                List.of(2, 4, 6)
        );

        for (List<Integer> winPossibility : winPossibilities) {
            if (field.get(winPossibility.get(0)) == field.get(winPossibility.get(1))
                    && field.get(winPossibility.get(0)) == field.get(winPossibility.get(2))) {
                return field.get(winPossibility.get(0));
            }
        }
        return Sign.EMPTY;
    }


    public int checkPreWin() {
        List<List<Integer>> winPossibilities = List.of(
                List.of(0, 1, 2),
                List.of(3, 4, 5),
                List.of(6, 7, 8),
                List.of(0, 3, 6),
                List.of(1, 4, 7),
                List.of(2, 5, 8),
                List.of(0, 4, 8),
                List.of(2, 4, 6)
        );

        for (List<Integer> winPossibility : winPossibilities) {
            for (int i = 0; i < winPossibility.size(); i++) {
                if (field.get(winPossibility.get(i)) != Sign.EMPTY) {
                    if (field.get(winPossibility.get(1)) == field.get(winPossibility.get(2)) && field.get(winPossibility.get(0)) == Sign.EMPTY) {
                        return winPossibility.get(0);
                    } else if (field.get(winPossibility.get(0)) == field.get(winPossibility.get(1)) && field.get(winPossibility.get(2)) == Sign.EMPTY) {
                        return winPossibility.get(2);
                    } else if (field.get(winPossibility.get(0)) == field.get(winPossibility.get(2)) && field.get(winPossibility.get(1)) == Sign.EMPTY) {
                        return winPossibility.get(1);
                    }
                }
            }
        }
        return getEmptyFieldIndex();
    }
}