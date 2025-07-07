package com.g4.model;
import com.g4.model.Cliente;

public class Orden {
    private Cliente cliente;
    private String producto;

    public Orden(Cliente cliente, String producto) {
        this.cliente = cliente;
        this.producto = producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getProducto() {
        return producto;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNombre() + ", Producto: " + producto;
    }
}