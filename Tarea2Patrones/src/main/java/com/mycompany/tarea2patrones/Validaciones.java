/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea2patrones;

import clases.Cliente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author RUCO HOUSE
 */
public class Validaciones {
    private static final String CLIENTE_TXT = "clientes.txt";
    private static final String ADMIN_TXT = "admin.txt";
    private static final String SOPORTE_TXT = "soporte.txt";
    private static final String RESERVAS_TXT = "reservas.txt";
    public static boolean validarCredenciales(String usuario, String contraseña, String archivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2 && partes[0].equals(usuario) && partes[1].equals(contraseña)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void crearArchivoSiNoExiste(String archivo) throws IOException {
        File file = new File(archivo);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                if (archivo.equals(CLIENTE_TXT)) {
                    bw.write("cliente:client\n");
                } else if (archivo.equals(ADMIN_TXT)) {
                    bw.write("admin:admin\n");
                } else if (archivo.equals(SOPORTE_TXT)) {
                    bw.write("soporte:support\n");
                }
            }
        }
    }
    private static void crearArchivoCliente(File archivoCliente, Cliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCliente))) {
            writer.write("ID: " + cliente.getIdCedula());
            writer.newLine();
            writer.write("Nombre: " + cliente.getNombre());
            writer.newLine();
            writer.write("Email: " + cliente.getEmail());
            writer.newLine();
            writer.write("Contrasena: " + cliente.getContrasenia());
            writer.newLine();
            writer.write("Reservas:{}");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo del cliente: " + e.getMessage());
        }
    }

}
