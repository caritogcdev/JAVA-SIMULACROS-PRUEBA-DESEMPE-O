package controller;

import entity.Cita;
import entity.Medico;
import entity.Paciente;
import model.CitaModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class CitaController {

    public static void update(){
        /*
         * Listamos todos los atributos que queremos actualizar, pero en un
         * select, para que mejore la experiencia de usuario
         * */

        /*
         * Utilizamos la clase Utils y el método que creamos en esa clase
         * llamado listToArray() y le mandamos la lista que queremos
         * convertir. Pero esa lista la tenemos que sacar del modelo y
         * del findAll()
         *  */

        Object[] opcionesCita = Utils.listToArray(instanceModel().findAll());

        Cita citaSeleccionada = (Cita) JOptionPane.showInputDialog(
                null,
                "Seleccione la cita que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesCita,
                opcionesCita[0]
        );

        citaSeleccionada.setFechaCita(JOptionPane.showInputDialog(null, "Ingresa la fecha de la cita: YYYY-MM-dd", citaSeleccionada.getFechaCita()));
        citaSeleccionada.setHoraCita(JOptionPane.showInputDialog(null, "Ingresa la hora de la cita: HH:mm:ss", citaSeleccionada.getHoraCita()));
        citaSeleccionada.setMotivo(JOptionPane.showInputDialog(null, "Ingresa el motivo de la cita", citaSeleccionada.getMotivo()));

        /* Utilizamos la clase utils, y en este caso vamos a instanciar una instancia de lo
         * que queremos llamar instanciar
         */
        Object[] opcionesPacientes = Utils.listToArray(PacienteController.instanciaModel().findAll());

        Object[] opcionesMedicos = Utils.listToArray(MedicoController.instanciaModelo().findAll());

        Paciente pacienteSeleccionado = (Paciente) JOptionPane.showInputDialog(
                null,
                "Seleccione un paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPacientes,
                opcionesPacientes[0]
        );

        citaSeleccionada.setIdPaciente(pacienteSeleccionado.getId());
        citaSeleccionada.setObjPaciente(pacienteSeleccionado);

        Medico medicoSeleccionado = (Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el médico asignado a la cita",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesMedicos,
                opcionesMedicos[0]
        );

        citaSeleccionada.setIdMedico(medicoSeleccionado.getId());
        citaSeleccionada.setObjMedico(medicoSeleccionado);

        /* Una vez que el usuario escoja la cita que quiere actualizar, llamamos al metodo
        instanceModel() y al metodo delete() y le enviamos todas las citas seleccionadas
        * */
        instanceModel().update(citaSeleccionada);
    }

    public static void delete(){
        /*
         * Listamos todos los atributos que queremos eliminar, pero en un
         * select, para que mejore la experiencia de usuario
         * */

        /*
         * Utilizamos la clase Utils y el método que creamos en esa clase
         * llamado listToArray() y le mandamos la lista que queremos
         * convertir. Pero esa lista la tenemos que sacar del modelo y
         * del findAll()
         *  */

        Object[] opcionesCita = Utils.listToArray(instanceModel().findAll());

        Cita citaSeleccionada = (Cita) JOptionPane.showInputDialog(
                null,
                "Seleccione la cita a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesCita,
                opcionesCita[0]
        );

        /* Una vez que el usuario escoja la cita que quiere eliminar, llamamos al metodo
        instanceModel() y al metodo delete() y le enviamos todas las citas seleccionadas
        * */

        instanceModel().delete(citaSeleccionada);

    }

    public static void getAll(){

        /*
         * Guardar en una lista el método getAll pero le pasamos
         * el método instanciaModelo().findAll()
         * La lista que le vamos a pasar a getAll() la vamos a tomar
         * de instaciar el modelo, es decir, de instanciaModelo()
         * y obtener el método instanciaModelo()
         */

        String listaString = getAll(instanceModel().findAll());

        // Le pasamos la lista que recorrió getAll que aplica sobreescritura sobre métodos estáticos
        JOptionPane.showMessageDialog(null, listaString);

    }

    public static String getAll(List<Object> list){
        String listaString = "Lista de registros de Citas \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objCita a Cita ese temporal que se está recorriendo en cada iteracion
            Cita objCita= (Cita) temp;
            listaString += objCita.toString() + "\n";
        }

        return listaString;
    }

    public static void insert() {
        String fechaCita = JOptionPane.showInputDialog(null, "Ingresa la fecha de la cita: YYYY-MM-dd");
        String horaCita = JOptionPane.showInputDialog(null, "Ingresa la hora de la cita: HH:mm:ss");
        String motivo = JOptionPane.showInputDialog(null, "Ingresa el motivo de la cita");


        /* Utilizamos la clase utils, y en este caso vamos a instanciar una instancia de lo
        * que queremos llamar instanciar
        */
        Object[] opcionesPacientes = Utils.listToArray(PacienteController.instanciaModel().findAll());

        Object[] opcionesMedicos = Utils.listToArray(MedicoController.instanciaModelo().findAll());

        Paciente pacienteSeleccionado = (Paciente) JOptionPane.showInputDialog(
                null,
                "Seleccione un paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPacientes,
                opcionesPacientes[0]
        );

        Medico medicoSeleccionado = (Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el médico asignado a la cita",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesMedicos,
                opcionesMedicos[0]
        );

        instanceModel().insert(new Cita(fechaCita, horaCita, motivo, pacienteSeleccionado.getId(), medicoSeleccionado.getId(), pacienteSeleccionado, medicoSeleccionado));
    }

    // Método para instanciar el modelo de cita

    public static CitaModel instanceModel(){
        return new CitaModel();
    }
}
