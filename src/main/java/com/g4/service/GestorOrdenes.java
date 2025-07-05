package com.g4.service;

import com.g4.model.Cliente;
import com.g4.model.Orden;

import java.util.ArrayList;
import java.util.List;

public class GestorOrdenes {

    private List<Orden> ordenes;

    public GestorOrdenes() {
        this.ordenes = new ArrayList<>();
    }

    public boolean registrarOrden(String nombreCliente, String producto) {
        if (!datosValidos(nombreCliente, producto)) {
            return false;
        }

        Cliente cliente = new Cliente(nombreCliente);
        Orden orden = new Orden(cliente, producto);
        ordenes.add(orden);
        return true;
    }

    public List<Orden> obtenerOrdenes() {
        return new ArrayList<>(ordenes);
    }

    public int totalOrdenes() {
        return ordenes.size();
    }

    private boolean datosValidos(String cliente, String producto) {
        return cliente != null && !cliente.isBlank() && producto != null && !producto.isBlank();
    }
}
