package com.miempresa.miproyecto.dao;

import com.miempresa.miproyecto.model.Sucursal;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAO {

    private List<Sucursal> sucursales = new ArrayList<>();

    public List<Sucursal> getAll() {
        return sucursales;
    }

    public void create(Sucursal sucursal) {
        sucursales.add(sucursal);
    }

    public void update(Sucursal sucursal) {
        for (int i = 0; i < sucursales.size(); i++) {
            if (sucursales.get(i).getId() == sucursal.getId()) {
                sucursales.set(i, sucursal);
                break;
            }
        }
    }

    public void delete(int id) {
        sucursales.removeIf(sucursal -> sucursal.getId() == id);
    }

    public Sucursal getById(int id) {
        for (Sucursal sucursal : sucursales) {
            if (sucursal.getId() == id) {
                return sucursal;
            }
        }
        return null;
    }
}
