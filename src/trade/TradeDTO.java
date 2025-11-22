package trade;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TradeDTO {

    private int tradeLogId;
    private String tradeType;
    private int tradeQuantity;
    private int tradePrice;
    private Date tradeDate;
    private String userId;
    private String assetId;
}
