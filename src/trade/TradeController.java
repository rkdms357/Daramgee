package trade;

import java.util.*;
import asset.*;
import main.*;

public class TradeController implements ControllerInterface {
    Scanner sc = new Scanner(System.in);
    TradeService tradeService = new TradeService();
    AssetService assetService = new AssetService();

    @Override
    public void execute(Scanner sc) {
        this.sc = sc;
        boolean isStop = false;
        if (MainController.loginUser == null) {
            TradeView.print("로그인이 필요한 서비스입니다.");
            return;
        }
        while (!isStop) {
            TradeView.menu();
            int job = sc.nextInt();
            switch (job) {
                case 1 -> f_buy();
                case 2 -> f_sell();
                case 99 -> isStop = true;
                default -> TradeView.print("잘못된 선택입니다.");
            }
        }
    }
    // 1. 매수하기
    private void f_buy() {
        System.out.println("===========매수(구매)하기===========");
        System.out.println("내 잔고: " + MainController.loginUser.getCash() + "원");
        pricePrint();

        System.out.print("매수(구매)할 코인 약어 입력 (예: BTC) >> ");
        String symbol = sc.next();

        System.out.print("매수(구매)할 개수 입력 >> ");
        int count = sc.nextInt();

        TradeView.print("거래 처리 중입니다...🐿");
        String msg = tradeService.buyCoin(symbol, count);
        TradeView.print(msg);
    }
    // 2. 매도하기
    private void f_sell() {
        System.out.println("===========매도(판매)하기===========");
        pricePrint();

        System.out.print("매도(판매)할 코인 약어 입력 (예: BTC) >> ");
        String symbol = sc.next();

        System.out.print("매도(판매)할 개수 입력 >> ");
        int count = sc.nextInt();

        TradeView.print("거래 처리 중입니다...🐿");
        String msg = tradeService.sellCoin(symbol, count);
        TradeView.print(msg);

        System.out.println("내 잔고: " + MainController.loginUser.getCash() + "원");
    }

    private void pricePrint() {
        AssetView.print("빗썸에서 실시간 시세를 가져오는 중입니다...🐿️");
        List<AssetDTO> list = assetService.getAllAssets();
        AssetView.printAssetList(list);
    }
}