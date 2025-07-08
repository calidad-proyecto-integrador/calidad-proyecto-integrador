package com.g4.app;

import com.g4.model.Cliente;
import com.g4.model.Orden;
import com.g4.service.GestorOrdenes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {


    private GestorOrdenes gestor;

    @BeforeEach
    public void setUp() {
        gestor = new GestorOrdenes();
    }

    @Test
    public void testInitialState() {
        // Al inicio no hay órdenes
        assertEquals(0, gestor.totalOrdenes(), "Debe empezar con 0 órdenes");
        assertTrue(gestor.obtenerOrdenes().isEmpty(), "La lista inicial debe estar vacía");
    }

    @Test
    public void testRegistrarOrdenValida() {
        boolean resultado = gestor.registrarOrden("Alice", "Laptop");
        assertTrue(resultado, "Registrar una orden válida debe devolver true");
        assertEquals(1, gestor.totalOrdenes(), "Total de órdenes debe ser 1 tras registrar una");
        List<Orden> lista = gestor.obtenerOrdenes();
        assertEquals(1, lista.size(), "La lista debe contener exactamente una orden");
        Orden orden = lista.get(0);
        assertEquals("Alice", orden.getCliente().getNombre(), "Nombre de cliente en la orden");
        assertEquals("Laptop", orden.getProducto(), "Producto en la orden");
        assertEquals("Cliente: Alice, Producto: Laptop", orden.toString(), "toString de Orden");
    }

    @Test
    public void testRegistrarOrdenMultiples() {
        assertTrue(gestor.registrarOrden("Bob", "Teléfono"));
        assertTrue(gestor.registrarOrden("Carol", "Tablet"));
        assertEquals(2, gestor.totalOrdenes(), "Debe acumular todas las órdenes registradas");
    }

    @Test
    public void testRegistrarOrdenInvalidaNombreNulo() {
        boolean resultado = gestor.registrarOrden(null, "Producto");
        assertFalse(resultado, "Nombre nulo debe invalidar el registro");
        assertEquals(0, gestor.totalOrdenes(), "No debe añadir orden con nombre nulo");
    }

    @Test
    public void testRegistrarOrdenInvalidaNombreVacio() {
        boolean resultado = gestor.registrarOrden("   ", "Producto");
        assertFalse(resultado, "Nombre en blanco debe invalidar el registro");
        assertEquals(0, gestor.totalOrdenes(), "No debe añadir orden con nombre en blanco");
    }

    @Test
    public void testRegistrarOrdenInvalidaProductoNulo() {
        boolean resultado = gestor.registrarOrden("Alice", null);
        assertFalse(resultado, "Producto nulo debe invalidar el registro");
        assertEquals(0, gestor.totalOrdenes(), "No debe añadir orden con producto nulo");
    }

    @Test
    public void testRegistrarOrdenInvalidaProductoVacio() {
        boolean resultado = gestor.registrarOrden("Alice", "   ");
        assertFalse(resultado, "Producto en blanco debe invalidar el registro");
        assertEquals(0, gestor.totalOrdenes(), "No debe añadir orden con producto en blanco");
    }

    @Test
    public void testObtenerOrdenesInmutable() {
        gestor.registrarOrden("Dave", "Monitor");
        List<Orden> lista1 = gestor.obtenerOrdenes();
        // Intentar modificar la lista retornada
        lista1.clear();
        // La lista interna no debe verse afectada
        assertEquals(1, gestor.totalOrdenes(), "La lista interna no cambia al modificar la copia");
        // Volver a obtener y comprobar que sigue presente
        List<Orden> lista2 = gestor.obtenerOrdenes();
        assertEquals(1, lista2.size(), "Siempre debe seguir habiendo una orden interna");
    }

    @Test
    public void testClienteModel() {
        Cliente cliente = new Cliente("Eve");
        assertEquals("Eve", cliente.getNombre(), "Getter de nombre en Cliente");
    }

    @Test
    public void testOrdenModel() {
        Cliente c = new Cliente("Frank");
        Orden orden = new Orden(c, "Impresora");
        assertSame(c, orden.getCliente(), "Orden conserva la referencia al Cliente");
        assertEquals("Impresora", orden.getProducto(), "Getter de producto en Orden");
        assertTrue(orden.toString().contains("Frank"), "toString debe incluir el nombre del cliente");
        assertTrue(orden.toString().contains("Impresora"), "toString debe incluir el producto");
    }
}
