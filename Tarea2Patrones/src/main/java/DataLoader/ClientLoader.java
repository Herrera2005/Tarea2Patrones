package DataLoader;

import java.util.ArrayList;
import java.util.List;

import Notificaciones.NotificacionEmail;
import Notificaciones.NotificacionSMS;
import clases.Cliente;

public class ClientLoader {

    private List<Cliente> clientes;

    public ClientLoader(){
    clientes = new ArrayList<>();
    Cliente cliente1 = new Cliente(1001, "Juan Pérez", "juan@example.com", "password123");
        // Añadir notificaciones
        cliente1.addNotificacion(new NotificacionSMS("1234567890")); // Notificación SMS para Juan
        cliente1.addNotificacion(new NotificacionEmail("juan@example.com")); // Notificación Email para Juan
        clientes.add(cliente1);

        Cliente cliente2 = new Cliente(1002, "María López", "maria@example.com", "securepass");
        // Añadir notificaciones
        cliente2.addNotificacion(new NotificacionSMS("0987654321")); // Notificación SMS para María
        cliente2.addNotificacion(new NotificacionEmail("maria@example.com")); // Notificación Email para María
        clientes.add(cliente2);

        Cliente cliente3 = new Cliente(1003, "Carlos Gómez", "carlos@example.com", "qwerty");
        // Añadir notificaciones
        cliente3.addNotificacion(new NotificacionSMS("1122334455")); // Notificación SMS para Carlos
        cliente3.addNotificacion(new NotificacionEmail("carlos@example.com")); // Notificación Email para Carlos
        clientes.add(cliente3);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}
