package DataLoader;

import java.util.ArrayList;
import java.util.List;

import clases.Cliente;

public class ClientLoader {

    private List<Cliente> clientes;

    public ClientLoader(){
    clientes = new ArrayList<>();
    Cliente cliente1 = new Cliente(1001, "Juan Pérez", "juan@example.com", "password123");
    clientes.add(cliente1);
    Cliente cliente2 = new Cliente(1002, "María López", "maria@example.com", "securepass");
    clientes.add(cliente2);
    Cliente cliente3 = new Cliente(1003, "Carlos Gómez", "carlos@example.com", "qwerty");
    clientes.add(cliente3);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}
