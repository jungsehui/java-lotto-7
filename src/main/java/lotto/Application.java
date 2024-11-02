package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        System.out.println(Message.PURCHASE_PRICE.getMessage());
        String inputPurchasePrice = Console.readLine();

        if (Integer.parseInt(inputPurchasePrice) % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 잘못된 값을 입력하였습니다. 구입 금액은 1,000원 단위로 입력해 주세요");
        }

        int purchaseQuantity = Integer.parseInt(inputPurchasePrice) / 1000;
        System.out.println("\n" + purchaseQuantity + Message.PURCHASE_QUANTITY.getMessage());

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseQuantity; i++) {
            Lotto lotto = new Lotto(Lotto.create());
            System.out.println(lotto.getNumbers());
            lottos.add(lotto);
        }
    }
}
