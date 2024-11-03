package lotto;

import lotto.play.LottoResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoResult lottoResult = new LottoResult(new InputView(), new OutputView());
        lottoResult.start();
    }
}
