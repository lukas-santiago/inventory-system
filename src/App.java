import java.util.List;

import DAO.TextFileDAO;
import Model.InventoryItem;
import java.text.SimpleDateFormat;

public class App {
    public static void main(String[] args) throws Exception {

        TextFileDAO dao = new TextFileDAO();
        List<InventoryItem> inventoryItems = dao.getAllInventoryItem();
        InventoryItem inventoryItem = new InventoryItem();

        inventoryItem.id = Long.parseLong("2");
        inventoryItem.entryDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021");
        inventoryItem.purchasePlace = "a";
        inventoryItem.tipo = "a";
        inventoryItem.brand = "a";
        inventoryItem.description = "a";
        inventoryItem.size = "a";
        inventoryItem.color = "a";
        inventoryItem.tagValueAtPurchase = Double.parseDouble("1");
        inventoryItem.amountPaidOnPurchase = Double.parseDouble("1");
        inventoryItem.suggestedPrice = Double.parseDouble("1");
        inventoryItem.valueWithMargin = Double.parseDouble("1");

        inventoryItems.add(inventoryItem);
        dao.writeFile(dao.createString(inventoryItems));
        System.out.println(dao.createString(inventoryItems));
    }
}
