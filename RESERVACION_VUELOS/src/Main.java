import controller.AvionController;
import controller.PasajeroController;
import controller.ReservacionController;
import controller.VueloController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hola desde reservación de vuelos aerolínea");
        // Probamos la conexión a la DB
        //Podemos llamar a la clase ConfigDB directamente porque en este caso sus métodos y su atributo único es estático
        ConfigDB.openConnection();
        ConfigDB.closeConnection();

        String option = "", option2 = "";

        do {
            option = JOptionPane.showInputDialog("""
                    1. Administrar Aviones.
                    2. Administrar Vuelos.
                    3. Administrar Pasajeros.
                    4. Administrar Reservaciones.
                    5. Salir.
                    
                    Ingrese una opción:
                    """);
            switch (option) {
                case "1":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Crear Avión.
                                2. Listar los Aviones.
                                3. Actualizar Avión.
                                4. Eliminar Avión.
                                5. Salir.
                                                                
                                Ingrese una opción:
                                """);
                        switch (option2) {
                            case "1":
                                AvionController.insert();
                                break;
                            case "2":
                                AvionController.getAll();
                                break;
                            case "3":
                                AvionController.update();
                                break;
                            case "4":
                                AvionController.delete();
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;

                case "2":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Crear Vuelo.
                                2. Listar los Vuelos.
                                3. Actualizar Vuelo.
                                4. Eliminar Vuelo.
                                5. Salir.
                                                                
                                Ingrese una opción:
                                """);
                        switch (option2) {
                            case "1":
                                VueloController.insert();
                                break;
                            case "2":
                                VueloController.getAll();
                                break;
                            case "3":
                                VueloController.update();
                                break;
                            case "4":
                                VueloController.delete();
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;

                case "3":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Crear Pasajero.
                                2. Listar los Pasajeros.
                                3. Actualizar Pasajero.
                                4. Eliminar Pasajero.
                                5. Salir.
                                                                
                                Ingrese una opción:
                                """);
                        switch (option2) {
                            case "1":
                                PasajeroController.insert();
                                break;
                            case "2":
                                PasajeroController.getAll();
                                break;
                            case "3":
                                PasajeroController.update();
                                break;
                            case "4":
                                PasajeroController.delete();
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;

                case "4":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Crear Reservación.
                                2. Listar las Reservaciones.
                                3. Actualizar Reservación.
                                4. Eliminar Reservación.
                                5. Salir.
                                                                
                                Ingrese una opción:
                                """);
                        switch (option2) {
                            case "1":
                                ReservacionController.insert();
                                break;
                            case "2":
                                ReservacionController.getAll();
                                break;
                            case "3":
                                ReservacionController.update();
                                break;
                            case "4":
                                ReservacionController.delete();
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;
            }

        } while (!option.equals("5"));
    }
}