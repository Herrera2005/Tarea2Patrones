package com.mycompany.tarea2patrones;

import Interfaces.AccionMenu;
import Interfaces.EscalarIncidente;
import Interfaces.Salir;
import Interfaces.VerIncidentes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuSo {
    private Map<Integer, AccionMenu> acciones;

    public MenuSo() {
        acciones = new HashMap<>();
        acciones.put(1, new VerIncidentes());
        acciones.put(2, new EscalarIncidente());
        acciones.put(3, new Salir());
    }

    public void menuSoporte() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("=== Menú Soporte ===");
            System.out.println("1. Ver incidentes");
            System.out.println("2. Escalar incidente");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            AccionMenu accion = acciones.get(opcion);
            if (accion != null) {
                accion.ejecutar();
                if (accion instanceof Salir) {
                    salir = true;
                }
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
}