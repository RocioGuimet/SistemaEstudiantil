package archivo.presentacion;

import archivo.DAO.EstudianteDAO;
import archivo.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantilApp {
    static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        // Crear instancia clase servicio
        var estudianteDao = new EstudianteDAO();
        while(!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(consola, estudianteDao);
            } catch (Exception e){
                System.out.println("Ocurrió un error al ejecutar operación: " + e.getMessage());
            }
            System.out.println();
        } //fin while
    }

    //Implementamos metodos
    private static void mostrarMenu(){
        System.out.print("""
                * Sistema Estudiantil *
                1. Listar Estudiantes
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elige una opción: 
                """);
    }

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion) {
            case 1 -> {
                System.out.println("Listado de Estudiantes: ");
                var estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Introduce el ID del estudiante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiante(estudiante);
                if (encontrado)
                    System.out.println("Estudiante encontrado: " + estudiante);
                else
                    System.out.println("No se encontró el estudiante: " + estudiante);
            }
            case 3 -> {
                System.out.println("Agregar Estudiante:");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = Integer.parseInt(consola.nextLine());
                System.out.print("Email: ");
                var email = consola.nextLine();
                // Crear objeto (sin id)
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if(agregado)
                    System.out.println("Estudiante agregado: " + estudiante);
                else
                    System.out.println("No se agregó el estudiante: " + estudiante);
            }
            case 4 -> {
                System.out.println("Modificar Estudiante:");
                System.out.println("Introduce el ID del Estudiante que deseas modificar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = Integer.parseInt(consola.nextLine());
                System.out.print("Email: ");
                var email = consola.nextLine();
                // Crear objeto a modificar
                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if (modificado)
                    System.out.println("Estudiante modificado: " + estudiante);
                else
                    System.out.println("No se modificó el estudiante: " + estudiante);
            }
            case 5 -> {
                System.out.println("Eliminar Estudiante:");
                System.out.println("Introduce el ID del Estudiante que deseas eliminar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if (eliminado)
                    System.out.println("Estudiante eliminado: " + estudiante);
                else
                    System.out.println("No se eliminó el estudiante: " + estudiante);
            }
            case 6 -> {
                System.out.println("¡Hasta pronto!");
                salir = true;
            }
            default -> System.out.println("Opción incorrecta: " + opcion);
        } // fin switch
        return salir;
    }
}