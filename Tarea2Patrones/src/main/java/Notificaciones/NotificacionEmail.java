package Notificaciones;

import javax.management.Notification;

import clases.Cliente;

public class NotificacionEmail implements Notificacion {

    public NotificacionEmail(String email){
        this.correo = email;
    }

    private String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public void notificar(String mensaje, Cliente cliente) {
        System.out.println("Se notifica al usuario: "+cliente.getNombre()+ " al correo: "+correo+" ;con el mensaje: " +mensaje);
        
    }



}
