package lotto;

public class Application {

    public static void main(String[] args) {
        LottoService lottoService = new LottoService(new InputView(), new OutputView());
        lottoService.start();
    }
}
