package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
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

    public Long proximoId() {
        TextFileDAO dao = new TextFileDAO();
        try {
            List<InventoryItem> inventoryItems = dao.getAllInventoryItem();
            Long maxValue = inventoryItems.stream().mapToLong(item -> item.id).max()
                    .orElseThrow(NoSuchElementException::new);

            return maxValue + 1;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return (long) 0;
    }

    public Boolean validateId(Long id) {
        TextFileDAO dao = new TextFileDAO();
        try {
            List<InventoryItem> inventoryItems = dao.getAllInventoryItem();

            for (InventoryItem inventoryItem : inventoryItems) {
                if (inventoryItem.id == id) {
                    return true;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(InventoryItem inventoryItem) {
        TextFileDAO dao = new TextFileDAO();
        List<InventoryItem> inventoryItems;
        try {
            inventoryItems = dao.getAllInventoryItem();
            inventoryItem.id = proximoId();
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

    public InventoryItem update(InventoryItem inventoryItem, long id) {
        TextFileDAO dao = new TextFileDAO();
        List<InventoryItem> inventoryItems;
        try {
            inventoryItems = dao.getAllInventoryItem();

            InventoryItem old = dao.getInventoryItem(id);

            if (old != null) {
                Integer oldIndex = -1;

                for (int i = 0; i < inventoryItems.size(); i++) {
                    if (inventoryItems.get(i).id == old.id) {
                        oldIndex = i;
                        i = inventoryItems.size();
                    }
                }

                if (oldIndex == -1) {
                    return null;
                }

                inventoryItem.id = old.id;
                inventoryItems.set(oldIndex, inventoryItem);

                dao.saveAllInventoryItems(inventoryItems);
                return inventoryItem;
            }
            return null;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InventoryItem delete(long id) {
        TextFileDAO dao = new TextFileDAO();
        List<InventoryItem> inventoryItems;
        try {
            inventoryItems = dao.getAllInventoryItem();

            InventoryItem old = dao.getInventoryItem(id);

            if (old != null) {
                int oldIndex = -1;

                for (int i = 0; i < inventoryItems.size(); i++) {
                    if (inventoryItems.get(i).id == old.id) {
                        oldIndex = i;
                        i = inventoryItems.size();
                    }
                }

                if (oldIndex == -1) {
                    return null;
                }

                inventoryItems.remove(oldIndex);

                dao.saveAllInventoryItems(inventoryItems);
                return old;
            }
            return null;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
