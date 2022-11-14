package lotto.controller;

import static lotto.domain.Purchase.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Bonus;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Player;
import lotto.domain.Purchase;
import lotto.domain.Ranking;
import lotto.domain.Statistics;
import lotto.domain.Yield;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static int ticketNumber;
    private static List<List<Integer>> allPlayerNumbers = new ArrayList<>();
    private static List<Integer> winningNumbers;
    private static int bonusNumber;

    public static void run() {
        try {
            purchaseLotto();

            // 로또 발행
            Player player = new Player(ticketNumber);
            allPlayerNumbers = player.get();

            // 당첨 번호 생성
            Lotto lotto = new Lotto(InputView.inputWinningNumbers());
            Bonus bonus = new Bonus(InputView.inputBonusNumber());
            winningNumbers = lotto.get();
            bonusNumber = bonus.get();
            validateDuplicates();

            // 로또 결과 계산
            LottoResult result = new LottoResult(winningNumbers, allPlayerNumbers, bonusNumber);
            List<Integer> matches = result.getMatches(); // [3, 1, 5, 5, 3]
            List<Boolean> bonusMatches = result.getBonusMatches(); // [true, false, true, false, false]

            // 로또 순위 계산
            Ranking ranking = new Ranking(matches, bonusMatches); // [FIFTH, NONE, SECOND, THIRD, FIFTH]
            Statistics statistics = new Statistics(ranking.getRankings());

            OutputView.printStatistics();

            Yield yield = new Yield(ticketNumber * LOTTO_PRICE);
            OutputView.printYield(yield.getYield());

        } catch (Exception error) {
            OutputView.printErrorMessage(error);
        }

    }

    private static void purchaseLotto() {
        Purchase budget = new Purchase(InputView.inputTotalBudget());
        ticketNumber = budget.getTicketNumber();
        OutputView.printTicketNumber(ticketNumber);
    }

    private static void validateDuplicates() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호가 당첨 번호와 중복됩니다.");
        }
    }
}
