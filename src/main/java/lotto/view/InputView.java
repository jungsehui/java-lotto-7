package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.global.Message;

import java.util.Arrays;
import java.util.List;

public class InputView {

    public int getInputPurchasePrice() {
        String inputPurchasePrice = "";
        while (true) {
            try {
                System.out.println(Message.PURCHASE_PRICE.getMessage());
                inputPurchasePrice = Console.readLine();
                validateNumberModulo(inputPurchasePrice);
                return Integer.parseInt(inputPurchasePrice);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 입력 형식은 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 잘못된 값을 입력하였습니다. 구입 금액은 1,000원 단위로 입력해 주세요");
            }
        }
    }

    private static void validateNumberModulo(String inputPurchasePrice) {
        if (Integer.parseInt(inputPurchasePrice) % 1000 != 0) {
            throw new IllegalArgumentException();
        }
    }

    public Lotto getUserLotto() {
        System.out.println(Message.WINNING_NUMBERS.getMessage());
        String inputWinningNumber = Console.readLine();
        List<Integer> winningNumberTokens = Arrays.stream(inputWinningNumber.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
        return new Lotto(winningNumberTokens);
    }

    public int getInputBonusNumber() {
        System.out.println(Message.BONUS_NUMBER.getMessage());
        String inputBonusNumber = Console.readLine();
        return Integer.parseInt(inputBonusNumber);
    }
}
