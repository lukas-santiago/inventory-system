package DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.InventoryItem;

public class TextFileDAO {
    private static final String FILENAME = "STORAGE.txt";
    private static final String CHARSET = "UTF-8";

    public TextFileDAO() {
    }

    public InventoryItem getInventoryItem(long id) throws FileNotFoundException, IOException, ParseException {
        List<InventoryItem> inventoryItems = readFile();
        return inventoryItems.stream().filter(item -> item.id == id).collect(Collectors.toList()).get(0);
    }

    public List<InventoryItem> getAllInventoryItem() throws FileNotFoundException, IOException, ParseException {
        List<InventoryItem> inventoryItems = readFile();
        return inventoryItems;
    }

    private List<InventoryItem> readFile() throws FileNotFoundException, IOException, ParseException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();

            List<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();

            while (line != null) {
                InventoryItem inventoryItem = parseLine(line);
                inventoryItems.add(inventoryItem);
                line = br.readLine();
            }
            return inventoryItems;
        }
    }

    private InventoryItem parseLine(String line) throws ParseException {
        String[] splittedLine = line.split("\\|");

        InventoryItem inventoryItem = new InventoryItem();

        inventoryItem.id = Long.parseLong(splittedLine[0]);
        inventoryItem.entryDate = new SimpleDateFormat("dd/MM/yyyy").parse(splittedLine[1]);
        inventoryItem.purchasePlace = splittedLine[2];
        inventoryItem.tipo = splittedLine[3];
        inventoryItem.brand = splittedLine[4];
        inventoryItem.description = splittedLine[5];
        inventoryItem.size = splittedLine[6];
        inventoryItem.color = splittedLine[7];
        inventoryItem.tagValueAtPurchase = Double.parseDouble(splittedLine[8]);
        inventoryItem.amountPaidOnPurchase = Double.parseDouble(splittedLine[9]);
        inventoryItem.suggestedPrice = Double.parseDouble(splittedLine[10]);
        inventoryItem.valueWithMargin = Double.parseDouble(splittedLine[11]);

        return inventoryItem;
    }

    public void writeFile(String content) throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter writer = new PrintWriter(FILENAME, CHARSET)) {
            writer.print(content);
        }
    }

    public String createString(List<InventoryItem> inventoryItems) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <= inventoryItems.size() - 1; i++) {
            stringBuilder.append(inventoryItems.get(i).id);
            stringBuilder.append("|");
            stringBuilder.append(new SimpleDateFormat("dd/MM/yyyy").format(inventoryItems.get(i).entryDate));
            stringBuilder.append("|");
            stringBuilder.append(inventoryItems.get(i).purchasePlace);
            stringBuilder.append("|");
            stringBuilder.append(inventoryItems.get(i).tipo);
            stringBuilder.append("|");
            stringBuilder.append(inventoryItems.get(i).brand);
            stringBuilder.append("|");
            stringBuilder.append(inventoryItems.get(i).description);
            stringBuilder.append("|");
            stringBuilder.append(inventoryItems.get(i).size);
            stringBuilder.append("|");
            stringBuilder.append(inventoryItems.get(i).color);
            stringBuilder.append("|");
            stringBuilder.append(inventoryItems.get(i).tagValueAtPurchase);
            stringBuilder.append("|");
            stringBuilder.append(inventoryItems.get(i).amountPaidOnPurchase);
            stringBuilder.append("|");
            stringBuilder.append(inventoryItems.get(i).suggestedPrice);
            stringBuilder.append("|");
            stringBuilder.append(inventoryItems.get(i).valueWithMargin);

            if (i != inventoryItems.size() - 1) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
