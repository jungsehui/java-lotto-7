package lotto.global;

public enum Rank {

    FIRST(6, 2_000_000_000, "6개 일치"),
    SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, 1_500_000, "5개 일치"),
    FOURTH(4, 50_000, "4개 일치"),
    FIFTH(3, 5_000, "3개 일치"),
    NONE(0, 0, "미당첨");

    private final int matchCount;
    private final int prizeMoney;
    private final String description;

    Rank(final int matchCount, final int prizeMoney, final String description) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.description = description;
    }

    public static Rank valueOf(final int matchCount, final boolean matchBonus) {
        return findByMatchCount(matchCount, matchBonus);
    }

    public static Rank findByMatchCount(final int matchCount, final boolean matchBonus) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return matchBonusNumber(matchBonus);
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return NONE;
    }

    public static Rank matchBonusNumber(final boolean matchBonus) {
        if (matchBonus) {
            return SECOND;
        }
        return FOURTH;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getDescription() {
        return description;
    }
}
