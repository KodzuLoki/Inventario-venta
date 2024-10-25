package com.miempresa.miproyecto.dao;

import com.miempresa.miproyecto.model.Trabajador;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO {

    private List<Trabajador> trabajadores = new ArrayList<>();

    public List<Trabajador> getAll() {
        return trabajadores;
    }

    public void create(Trabajador trabajador) {
        trabajadores.add(trabajador);
    }

    public void update(Trabajador trabajador) {
        for (int i = 0; i < trabajadores.size(); i++) {
            if (trabajadores.get(i).getId() == trabajador.getId()) {
                trabajadores.set(i, trabajador);
                break;
            }
        }
    }

    public void delete(int id) {
        trabajadores.removeIf(trabajador -> trabajador.getId() == id);
    }
}
