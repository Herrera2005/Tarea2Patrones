package clases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import claseVuelo.Vuelo;

public class SistemaReservasVuelo {
        private List<Vuelo> vuelos;

    // Constructor que inicializa la lista de vuelos
    public SistemaReservasVuelo() {
        this.vuelos = new ArrayList<>();
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        // Fecha actual (para la reserva)
        Date fechaActual = new Date();  // Fecha de hoy
        Calendar calendar = Calendar.getInstance();
        
        // Fecha de vuelo: 2 días después de la fecha actual
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date fechaVuelo1 = calendar.getTime();

        // Otra fecha de vuelo: 1 semana después de la fecha actual
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date fechaVuelo2 = calendar.getTime();
        // Se pueden agregar vuelos manualmente
        vuelos.add(new Vuelo(101, "Aerolinea A", fechaVuelo1, fechaVuelo2, 100, new ArrayList<>()));
        vuelos.add(new Vuelo(102, "Aerolinea B", fechaVuelo1, fechaVuelo2, 50, new ArrayList<>()));
    }

    // Mostrar vuelos disponibles
    public void mostrarVuelosDisponibles() {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.verificarDisponibilidad()) {
                System.out.println(vuelo);
            }
        }
    }

    // Buscar un vuelo por ID
    public Vuelo buscarVueloPorID(int id) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getIdVuelo() == id) {
                return vuelo;
            }
        }
        return null;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

}
