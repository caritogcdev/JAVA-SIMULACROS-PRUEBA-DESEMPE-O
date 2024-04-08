import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;
import database.ConfigDB;

import javax.swing.*;

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
                                3. Actualizar Especialidad.
                                4. Eliminar Especialidad.
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

                case "2":
                    do {
                        option2 =  JOptionPane.showInputDialog("""
                                1. Crear Médico.
                                2. Listar Médicos.
                                3. Actualizar Médico.
                                4. Eliminar Médico.
                                5. Salir.
                                
                                Ingrese una opción:
                                """);
                        switch (option2){
                            case "1":
                                MedicoController.insert();
                                break;
                            case "2":
                                MedicoController.getAll();
                                break;
                            case "3":
                                MedicoController.update();
                                break;
                            case "4":
                                MedicoController.delete();
                                break;
                        }
                    } while (!option2.equals("5"));
                    break;

                case "3":
                    do {
                        option2 =  JOptionPane.showInputDialog("""
                                1. Crear Paciente.
                                2. Listar Pacientes.
                                3. Actualizar Paciente.
                                4. Eliminar Paciente.
                                5. Salir.
                                
                                Ingrese una opción:
                                """);
                        switch (option2){
                            case "1":
                                PacienteController.insert();
                                break;
                            case "2":
                                PacienteController.getAll();
                                break;
                            case "3":
                                PacienteController.update();
                                break;
                            case "4":
                                PacienteController.delete();
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;

                case "4":
                    do {
                        option2 =  JOptionPane.showInputDialog("""
                                1. Crear Cita.
                                2. Listar Citas.
                                3. Actualizar Cita.
                                4. Eliminar Cita.
                                5. Salir.
                                
                                Ingrese una opción:
                                """);
                        switch (option2){
                            case "1":
                                CitaController.insert();
                                break;
                            case "2":
                                CitaController.getAll();
                                break;
                            case "3":
                                CitaController.update();
                                break;
                            case "4":
                                CitaController.delete();
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;
            }

        } while (!option.equals("5"));
    }
}