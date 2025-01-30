package clases;

import java.util.List;

public class GestorClientes {
    private List<Cliente> clientes;

    public GestorClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente obtenerClientePorIndice(int indice) {
        if (indice < 0 || indice >= clientes.size()) {
            throw new IllegalArgumentException("Índice no válido");
        }
        return clientes.get(indice);
    }

    public void mostrarClientes() {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.println((i + 1) + ". " + cliente.getNombre() + " (ID: " + cliente.getIdCedula() + ")");
        }
    }
}
