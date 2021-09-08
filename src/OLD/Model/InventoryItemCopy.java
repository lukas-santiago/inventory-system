package OLD.Model;

import java.math.BigDecimal;
import java.sql.Date;

public class InventoryItemCopy {
    long id;
    Date entryDate;
    PurchasePlace purchasePlace;
    String type;
    String brand;
    String description;
    EnumSize size;
    EnumColor color;
    BigDecimal tagValueAtPurchase;
    BigDecimal amountPaidOnPurchase;
    BigDecimal suggestedPrice;

    Short marginValue = 100;

    BigDecimal valueWithMargin() {
        return amountPaidOnPurchase.multiply(new BigDecimal(marginValue));
    };
}
