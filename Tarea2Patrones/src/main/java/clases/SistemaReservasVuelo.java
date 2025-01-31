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

        // Crear fechas futuras
        Date fechaVuelo1 = crearFechaFutura(2);  // 2 días después de hoy
        Date fechaVuelo2 = crearFechaFutura(7);  // 1 semana después de hoy

        // Agregar vuelos
        agregarVuelo(101, "Aerolinea A", fechaVuelo1, fechaVuelo2, 100);
        agregarVuelo(102, "Aerolinea B", fechaVuelo1, fechaVuelo2, 50);
    }

    // Método para crear una fecha futura basada en un número de días
    private Date crearFechaFutura(int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());  // Fecha actual
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // Sumar días
        return calendar.getTime();
    }

    // Método para agregar un vuelo a la lista
    private void agregarVuelo(int id, String aerolinea, Date fechaSalida, Date fechaLlegada, int capacidad) {
        vuelos.add(new Vuelo(id, aerolinea, fechaSalida, fechaLlegada, capacidad, new ArrayList<>()));
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

    // Getters y setters
    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }
}