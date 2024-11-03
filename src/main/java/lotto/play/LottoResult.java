package lotto.play;

import lotto.domain.Lotto;
import lotto.global.Message;
import lotto.global.Rank;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Rank, Integer> rankCounts;

    public LottoResult(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.rankCounts = new HashMap<>();
        initializeRankCounts();
    }

    private void initializeRankCounts() {
        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }
    }

    public void start() {
        int inputPurchasePrice = inputView.getInputPurchasePrice();
        List<Lotto> purchasedLottos = setLottos(inputPurchasePrice);
        Lotto userLotto = inputView.getUserLotto();
        int inputBonusNumber = inputView.getInputBonusNumber();
        calculateResult(purchasedLottos, userLotto, inputBonusNumber);
        outputView.printWinningStatistics(rankCounts, calculateReturnRate(inputPurchasePrice));
    }

    // 당첨 내역 계산 메서드
    private void calculateResult(final List<Lotto> purchasedLottos,
                                 final Lotto userLotto,
                                 final int inputBonusNumber) {
        for (Lotto lotto : purchasedLottos) {
            int matchCount = countMatches(lotto, userLotto);
            boolean matchBonus = lotto.getNumbers().contains(inputBonusNumber);
            Rank rank = Rank.valueOf(matchCount, matchBonus);
            addWinningResult(rank);
        }
    }

    // 당첨 시 Map에 당첨 결과 반영 메서드
    public void addWinningResult(final Rank rank) {
        rankCounts.put(rank, rankCounts.get(rank) + 1);
    }

    // 수익률 계산 메서드
    private double calculateReturnRate(final int inputPurchasePrice) {
        long totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : rankCounts.entrySet()) {
            totalPrize += (long) entry.getKey().getPrizeMoney() * entry.getValue();
        }
        return (double) totalPrize / inputPurchasePrice * 100;
    }

    public List<Lotto> setLottos(final int inputPurchasePrice) {
        int purchaseQuantity = inputPurchasePrice / 1000;
        System.out.println("\n" + purchaseQuantity + Message.PURCHASE_QUANTITY.getMessage());

        List<Lotto> purchasedLottos = new ArrayList<>();
        for (int i = 0; i < purchaseQuantity; i++) {
            Lotto lotto = new Lotto(Lotto.create());
            System.out.println(lotto.getNumbers());
            purchasedLottos.add(lotto);
        }
        return purchasedLottos;
    }

    private int countMatches(final Lotto purchasedLotto, final Lotto userLotto) {
        return (int) purchasedLotto.getNumbers().stream()
                .filter(userLotto.getNumbers()::contains)
                .count();
    }
}
