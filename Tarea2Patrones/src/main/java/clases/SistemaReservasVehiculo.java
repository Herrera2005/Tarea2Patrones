package clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import claseVehiculo.Vehiculo;
import claseVehiculo.VehiculoDeLujo;
import claseVehiculo.VehiculoEconomico;
import claseVehiculo.VehiculoEjecutivo;

public class SistemaReservasVehiculo {
    private List<Vehiculo> vehiculos = new ArrayList<>();



    public SistemaReservasVehiculo() {
        this.vehiculos = new ArrayList<>();
        // Se podrían agregar vehículos manualmente aquí
        vehiculos.add(new VehiculoDeLujo(1, "DeLujo", "Proveedor A", true));
        vehiculos.add(new VehiculoEconomico(2, "Economico", "Proveedor B", true));
        vehiculos.add(new VehiculoEjecutivo(3, "Ejecutivo", "Proveedor C", true));

    }


    private void mostrarVehiculosDisponibles(){
        for (Vehiculo vehiculo : vehiculos) {
            if(vehiculo.verificarDisponibilidad())
            System.out.println(vehiculo);
        }
    }



public static Vehiculo buscarVehiculoPorIDConLoop(List<Vehiculo> vehiculos) {
    Scanner scanner = new Scanner(System.in);
    Vehiculo encontrado = null;

    while (encontrado == null) {
        System.out.print("🔍 Ingrese el ID del vehículo que desea buscar: ");

        // Verificar que el usuario ingrese un número válido
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();

            // Buscar el vehículo en la lista
            for (Vehiculo vehiculo : vehiculos) {
                if (vehiculo.getIdVehiculo() == id) {
                    encontrado = vehiculo;
                    break; // Salir del bucle una vez encontrado
                }
            }

            if (encontrado == null) {
                System.out.println("❌ No se encontró ningún vehículo con ese ID. Intente de nuevo.");
            }
        } else {
            System.out.println("⚠️ Entrada inválida. Por favor ingrese un número.");
            scanner.next(); // Consumir la entrada inválida para evitar un bucle infinito
        }
    }

    return encontrado;
}

public void menuReservas() {
    Scanner scanner = new Scanner(System.in);
    boolean salir = false;

    while (!salir) {
        System.out.println("\n===== 📋 MENÚ DE RESERVAS =====");
        System.out.println("1️⃣ Mostrar vehículos disponibles");
        System.out.println("2️⃣ Reservar un vehículo");
        System.out.println("3️⃣ Salir");
        System.out.print("Seleccione una opción: ");

        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    mostrarVehiculosDisponibles();
                    break;
                case 2:
                    mostrarVehiculosDisponibles();
                    Vehiculo vehiculo = buscarVehiculoPorIDConLoop(vehiculos);
                    System.out.println("\n✅ Reservando vehículo: " + vehiculo);
                    vehiculo.confirmarVehiculo(); // Llama al método abstracto para confirmar la reserva
                    break;
                case 3:
                    System.out.println("👋 Saliendo del sistema...");
                    salir = true;
                    break;
                default:
                    System.out.println("⚠️ Opción inválida. Intente nuevamente.");
            }
        } else {
            System.out.println("⚠️ Entrada inválida. Por favor ingrese un número.");
            scanner.next();
        }
    }
}


public List<Vehiculo> getVehiculos() {
    return vehiculos;
}


public void setVehiculos(List<Vehiculo> vehiculos) {
    this.vehiculos = vehiculos;
}

}
