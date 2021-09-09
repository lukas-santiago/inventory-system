package Interface;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
                    Boolean checkUpdate = false;
                    Long idUpdate = (long) -1;
                    do {
                        System.out.println("\nType the Id of Item that you will update (\"0\" to exit): ");

                        try {
                            idUpdate = Long.parseLong(csnl.readLine());
                            if (idUpdate == (long) 0) {
                                System.out.println("Exitting...");
                                break;
                            }
                            checkUpdate = true;
                        } catch (Exception e) {
                            System.out.println("Type a valid number!");
                            continue;
                        }

                        ActionController controller = new ActionController();
                        if (controller.validateId(idUpdate) == true) {
                            checkUpdate = true;
                        } else {
                            System.out.println("ID Not Found! Try it again.");
                            checkUpdate = false;
                        }

                    } while (checkUpdate == false);

                    if (checkUpdate == true) {
                        try {
                            InventoryItem inventoryItem = insert();
                            ActionController controller = new ActionController();
                            controller.update(inventoryItem, idUpdate);
                            System.out.println("Updated successfully!");
                        } catch (Exception e) {
                            e.getStackTrace();
                            System.out.print(e.getMessage());
                        }
                    } else {
                        option = 0;
                    }
                    break;
                case 3:
                    Boolean checkDelete = false;
                    Long idDelete = (long) -1;
                    do {
                        System.out.println("\nType the Id of Item that you will delete (\"0\" to exit): ");

                        try {
                            idDelete = Long.parseLong(csnl.readLine());
                            if (idDelete == (long) 0) {
                                System.out.println("Exitting...");
                                break;
                            }
                            checkDelete = true;
                        } catch (Exception e) {
                            System.out.println("Type a valid number!");
                            continue;
                        }

                        ActionController controller = new ActionController();
                        if (controller.validateId(idDelete) == true) {
                            checkDelete = true;
                        } else {
                            System.out.println("ID Not Found! Try it again.");
                            checkDelete = false;
                        }

                    } while (checkDelete == false);

                    if (checkDelete == true) {
                        try {
                            ActionController controller = new ActionController();
                            controller.delete(idDelete);
                            System.out.println("Item deleted successfully!");
                        } catch (Exception e) {
                            e.getStackTrace();
                            System.out.print(e.getMessage());
                        }
                    } else {
                        option = 0;
                    }
                    break;
                case 4:
                    try {
                        ActionController controller = new ActionController();
                        List<InventoryItem> inventoryItems = controller.select();
                        this.select(inventoryItems);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                    break;
                default:
                    System.out.println("Type it one of the possible options...");

                    break;
            }
        } while (option != 5);

    }

    public void update(List<InventoryItem> Inventoryitems) {

    }

    public void select(List<InventoryItem> inventoryItems) {
        System.out.println("\n\nALL REGISTRIES ON DATABASE");
        System.out.println("+------------------------+");

        for (InventoryItem inventoryItem : inventoryItems) {
            toStringHelper("\nId", Long.toString(inventoryItem.id));
            toStringHelper("Entry Date", new SimpleDateFormat("dd/MM/yyyy").format(inventoryItem.entryDate));
            toStringHelper("Postal Code", inventoryItem.postalCode);
            toStringHelper("Type", inventoryItem.type);
            toStringHelper("Brand", inventoryItem.brand);
            toStringHelper("Description", inventoryItem.description);
            toStringHelper("Size", inventoryItem.size);
            toStringHelper("Color", inventoryItem.color);
            toStringHelper("Tag Value At Purchase", inventoryItem.tagValueAtPurchase.toString());
            toStringHelper("Amount Paid On Purchase", inventoryItem.amountPaidOnPurchase.toString());
            toStringHelper("Suggested Price", inventoryItem.suggestedPrice.toString());
            toStringHelper("Value With Margin", inventoryItem.valueWithMargin.toString());
            System.out.println("\n+------------------------+");
        }
        System.out.println("       END OF LIST");
        System.out.println("+------------------------+\n\n");
    }

    protected void toStringHelper(String type, String value) {
        System.out.print(type + ": " + value + "\n");
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
