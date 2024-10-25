package com.miempresa.miproyecto.dao;

import com.miempresa.miproyecto.model.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    private List<Item> items = new ArrayList<>();

    public List<Item> getAll() {
        return items;
    }

    public void create(Item item) {
        items.add(item);
    }

    public void update(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                items.set(i, item);
                break;
            }
        }
    }

    public void delete(int id) {
        items.removeIf(item -> item.getId() == id);
    }

    public Item getById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // Retorna null si no se encuentra ningún item con ese id.
    }
}
