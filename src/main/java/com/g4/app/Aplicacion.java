package com.g4.app;

import com.g4.service.GestorOrdenes;
import com.g4.model.Orden;

public class Aplicacion {
    public static void main(String[] args) {
        GestorOrdenes gestor = new GestorOrdenes();

        if (gestor.registrarOrden("Alice", "Laptop")) {
            System.out.println("Orden registrada correctamente.");
        } else {
            System.out.println("Error al registrar la orden.");
        }

        if (gestor.registrarOrden("Bob", "Teléfono")) {
            System.out.println("Orden registrada correctamente.");
        }

        System.out.println("Listado de órdenes:");
        for (Orden orden : gestor.obtenerOrdenes()) {
            System.out.println(orden);
        }

        System.out.println("Total de órdenes: " + gestor.totalOrdenes());
    }
}
