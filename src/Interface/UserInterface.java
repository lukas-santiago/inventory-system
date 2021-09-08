package Interface;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Controller.ActionController;
import Model.InventoryItem;

public class UserInterface {

    public UserInterface() {
        Console csnl = System.console();

        Integer option = 0;
        System.out.println("Clothing Inventory!\n");
        do {
            System.out.println("Sistem - Options(Choose a number that it fits your wish):\n"
                    + "1-Insert an item into inventory:\n" + "2-Update data on available items of inventory:\n"
                    + "3-Delete items from inventory:\n" + "4-List data of items from inventory:\n" + "5-Exit...\n");
            option = Integer.parseInt(csnl.readLine());

            switch (option) {
                case 1:
                    try {
                        InventoryItem inventoryItem = insert();
                        ActionController controller = new ActionController();
                        controller.insert(inventoryItem);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;

                default:
                    System.out.println("Type it one of the possible options...");

                    break;
            }
        } while (option != 5);

    }

    public InventoryItem insert() throws ParseException {
        InventoryItem item = new InventoryItem();
        Console csnl = System.console();

        System.out.println("Type it the entry Date:\n ");
        item.entryDate = new SimpleDateFormat("dd/MM/yyyy").parse(csnl.readLine());

        System.out.println("Type it the clothing type:\n ");
        item.type = csnl.readLine();

        System.out.println("Type it the Postal Code (e.g.: CEP, ZIP Code, etc.):\n ");
        item.postalCode = csnl.readLine();

        System.out.println("Type it the clothing brand:\n ");
        item.brand = csnl.readLine();

        System.out.println("Describe the product:\n ");
        item.description = csnl.readLine();

        System.out.println("Type it the clothing size:\n ");
        item.size = csnl.readLine();

        System.out.println("Type it the clothing color:\n ");
        item.color = csnl.readLine();

        System.out.println("Type it the Tag Value At Purchase:\n ");
        item.tagValueAtPurchase = Double.parseDouble(csnl.readLine());

        System.out.println("Describe the amount Paid On Purchase:\n ");
        item.amountPaidOnPurchase = Double.parseDouble(csnl.readLine());

        System.out.println("Type it the suggested Price:\n ");
        item.suggestedPrice = Double.parseDouble(csnl.readLine());

        item.valueWithMargin = item.amountPaidOnPurchase * 2;
        return item;
    }
}
