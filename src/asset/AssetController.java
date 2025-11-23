package asset;

import java.util.List;
import java.util.Scanner;
import main.ControllerInterface;

public class AssetController implements ControllerInterface {
    AssetService assetService = new AssetService();

    @Override
    public void execute(Scanner sc) {
        AssetView.menu();
        printAllAssets();
    }

    private void printAllAssets() {
        AssetView.print("빗썸에서 실시간 시세를 가져오는 중입니다...🐿️");
        List<AssetDTO> list = assetService.getAllAssets();
        AssetView.printAssetList(list);
    }
}