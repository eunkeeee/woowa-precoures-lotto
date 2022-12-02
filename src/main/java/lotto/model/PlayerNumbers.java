package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerNumbers {

    private final List<PlayerNumber> playerNumbers;

    private PlayerNumbers(int ticketNumber) {
        List<PlayerNumber> playerNumbers = collectPlayerNumbers(ticketNumber);
        this.playerNumbers = playerNumbers;
    }

    public static PlayerNumbers issueLottoByTickets(int ticketNumber) {
        return new PlayerNumbers(ticketNumber);
    }

    private static List<PlayerNumber> collectPlayerNumbers(int ticketNumber) {
        List<PlayerNumber> playerNumbers = new ArrayList<>();
        for (int i = 0; i < ticketNumber; i++) {
            playerNumbers.add(PlayerNumber.createAutoLotto());
        }
        return playerNumbers;
    }

}
