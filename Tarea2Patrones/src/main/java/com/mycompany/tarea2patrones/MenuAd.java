/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea2patrones;

import java.util.Scanner;

/**
 *
 * @author RUCO HOUSE
 */
public class MenuAd {
    public static void menuAdministracion() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Administración ===");
            System.out.println("1. Gestionar usuarios");
            System.out.println("2. Configuración");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> System.out.println("Gestionando usuarios...");
                case 2 -> System.out.println("Accediendo a configuración...");
                case 3 -> {
                    System.out.println("Saliendo del menú administración...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
}
