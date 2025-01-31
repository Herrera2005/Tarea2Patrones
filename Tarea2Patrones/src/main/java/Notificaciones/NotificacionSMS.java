package Notificaciones;

import clases.Cliente;

public class NotificacionSMS implements Notificacion{

    private String numero;

    public NotificacionSMS(String numero){
        this.numero = numero;
    }

    @Override
    public void notificar(String mensaje, Cliente cliente) {
        System.out.println("Se notifico al usuario: " + cliente.getNombre()+" enviando un mensaje al numero: " + numero+" con el mensaje: "+mensaje);
    }

    @Override
    public String getMensaje() {
        return numero;
    }
    
}
