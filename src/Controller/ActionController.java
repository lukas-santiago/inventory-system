package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;

import DAO.TextFileDAO;
import Model.InventoryItem;

public class ActionController {
    public ActionController() {
    }

    public List<InventoryItem> select() {
        TextFileDAO dao = new TextFileDAO();
        List<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
        try {
            inventoryItems = dao.getAllInventoryItem();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return inventoryItems;
    }

    public int proximoId() {
        return 0;
    }

    public void insert(InventoryItem inventoryItem) {
        TextFileDAO dao = new TextFileDAO();
        List<InventoryItem> inventoryItems;
        try {
            inventoryItems = dao.getAllInventoryItem();
            inventoryItems.add(inventoryItem);
            dao.saveAllInventoryItems(inventoryItems);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void update(InventoryItem inventoryItem, long id) {

    }

    public void delete(long id) {

    }
}
