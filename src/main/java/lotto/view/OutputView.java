package lotto.view;

import lotto.global.Message;
import lotto.global.Rank;

import java.util.Map;

public class OutputView {

    // 당첨 결과 출력 메서드
    public void printWinningStatistics(final Map<Rank, Integer> rankCounts, final double returnRate) {
        System.out.println(Message.WINNING_STATISTICS.getMessage());
        printRankStatistics(Rank.FIFTH, rankCounts);
        printRankStatistics(Rank.FOURTH, rankCounts);
        printRankStatistics(Rank.THIRD, rankCounts);
        printRankStatistics(Rank.SECOND, rankCounts);
        printRankStatistics(Rank.FIRST, rankCounts);
        System.out.printf("총 수익률은 %.1f%%입니다.\n", returnRate);
    }

    // 당첨 순위 출력 메서드
    private void printRankStatistics(final Rank rank, final Map<Rank, Integer> rankCounts) {
        System.out.printf("%s (%,d원) - %d개\n",
                rank.getDescription(),
                rank.getPrizeMoney(),
                rankCounts.get(rank));
    }
}
