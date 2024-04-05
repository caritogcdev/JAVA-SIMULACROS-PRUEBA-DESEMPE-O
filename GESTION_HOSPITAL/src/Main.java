import controller.EspecialidadController;
import database.ConfigDB;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // Probamos la conexión a la DB
        //Podemos llamar a la clase ConfigDB directamente porque en  este caso sus metodos y su atributo unico es estático
        ConfigDB.openConnection();
        ConfigDB.closeConnection();

        String option = "", option2 = "";

        do {
            option = JOptionPane.showInputDialog("""
                    1. Administrar Espacialidades.
                    2. Administrar Médicos.
                    3. Administrar Pacientes.
                    4. Administrar Citas.
                    5. Salir.
                    
                    Ingrese una opción:
                    """);
            switch (option){
                case "1":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Crear Espacialidad.
                                2. Listar las Especialidades.
                                3. Actualizar las Especialidades.
                                4. Eliminar las Especialidades.
                                5. Salir.
                                
                                Ingrese una opción:
                                """);
                        switch (option2){
                            case "1":
                                EspecialidadController.create();
                                break;
                            case "2":
                                EspecialidadController.getAll();
                                break;
                            case "3":
                                //EspecialidadController.update();
                                EspecialidadController.updateWithSelect();
                                break;
                            case "4":
                                //EspecialidadController.delete();
                                EspecialidadController.deleteWithSelect();
                                break;
                        }

                    } while (!option2.equals("5"));

                    break;

            }

        } while (!option.equals("5"));





    }
}