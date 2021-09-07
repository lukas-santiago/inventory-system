package Model;

import java.util.Date;

public class InventoryItem {
    public long id;
    public Date entryDate;
    public String purchasePlace;
    public String tipo;
    public String brand;
    public String description;
    public String size;
    public String color;
    public Double tagValueAtPurchase;
    public Double amountPaidOnPurchase;
    public Double suggestedPrice;
    public Double valueWithMargin;

    Short marginValue = 100;
}
