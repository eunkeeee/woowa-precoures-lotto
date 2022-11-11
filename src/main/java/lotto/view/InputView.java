package lotto.view;

import static lotto.Util.removeSpace;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    public static List<Integer> inputWinningNumbers() {
        return formatInput(Console.readLine());
    }

    public static String inputBonusNumber() {
        return Console.readLine();
    }

    private static List<Integer> formatInput(String input) {
        return convertStringListToIntList(separateStringByComma(removeSpace(input)));
    }

    private static List<String> separateStringByComma(String input) {
        return Arrays.asList(input.split(","));
    }

    private static List<Integer> convertStringListToIntList(List<String> list) {
        return list.stream().map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
    }
}
