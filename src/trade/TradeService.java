package trade;

import service.CoinService;
import main.MainController;

public class TradeService {
    TradeDAO tradeDAO = new TradeDAO();
    CoinService coinService = new CoinService();

    // 매수 로직
    public String buyCoin(String symbol, int count) {
        // 1. 코인 코드 만들기 (BTC -> KRW-BTC)
        String assetId = "KRW-" + symbol.toUpperCase();

        // 2. 현재 가격 조회 (빗썸 API)
        int currentPrice = coinService.getPrice(assetId);
        if (currentPrice == 0) return "시세 조회를 실패했습니다.";

        // 3. 총비용 계산
        int totalCost = currentPrice * count;
        int myCash = MainController.loginUser.getCash();

        // 4. 잔액 부족 확인
        if (myCash < totalCost) return "잔액이 부족합니다! (필요: " + totalCost + "원, 보유: " + myCash + "원)";

        // 5. 거래 실행
        int result = tradeDAO.buy(MainController.loginUser.getUserId(), assetId, count, currentPrice, totalCost);

        if (result > 0) {
            return "🎉매수를 성공했습니다.[" + symbol + "] " + count + "개를 " + totalCost + "원에 샀습니다.";
        } else {
            return "매수를 실패했습니다.(시스템 오류)";
        }
    }

    // 매도 로직
    public String sellCoin(String symbol, int count) {
        // 1. 코인 코드 만들기 (BTC -> KRW-BTC)
        String assetId = "KRW-" + symbol.toUpperCase();

        // 2. 현재 가격 조회 (빗썸 API)
        int currentPrice = coinService.getPrice(assetId);
        if (currentPrice == 0) return "시세 조회를 실패했습니다.";

        // 3. 총 판매가격 계산
        int totalCost = currentPrice * count;

        // 4. 내 코인 수량 확인
        int myQuantity = tradeDAO.getQuantity(MainController.loginUser.getUserId(), assetId);

        // 5. 개수 초과 확인
        if (myQuantity < count) return "개수가 초과합니다! (매도하려는 개수: " + count + "원, 보유수량: " + myQuantity + "개)";

        // 6. 거래 실행
        int result = tradeDAO.sell(MainController.loginUser.getUserId(), assetId, count, currentPrice, totalCost);

        if (result > 0) {
            return "🎉매도를 성공했습니다.[" + symbol + "] " + count + "개를 " + totalCost + "원에 팔았습니다.";
        } else {
            return "매도를 실패했습니다.(시스템 오류)";
        }
    }

    public int getQuantity(String userId, String assetId) {
        return tradeDAO.getQuantity(userId, assetId);
    }
}