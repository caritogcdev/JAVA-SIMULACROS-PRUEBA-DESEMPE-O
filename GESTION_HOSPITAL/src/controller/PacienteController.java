package controller;

import entity.Paciente;
import model.PacienteModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class PacienteController {

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

        Object[] opcionesPaciente = Utils.listToArray(instanciaModel().findAll());

        Paciente pacienteSeleccionado = (Paciente) JOptionPane.showInputDialog(
                null,
                "Seleccione un paciente que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPaciente,
                opcionesPaciente[0]
        );

        //Le pedimos los nuevos datos al usuario
        pacienteSeleccionado.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre", pacienteSeleccionado.getNombre()));
        pacienteSeleccionado.setApellidos(JOptionPane.showInputDialog(null, "Ingrese los nuevos apellidos", pacienteSeleccionado.getApellidos()));
        pacienteSeleccionado.setFecha_nacimiento(JOptionPane.showInputDialog(null, "Ingrese la nueva fecha de nacimiento YYYY-MM-dd", pacienteSeleccionado.getFecha_nacimiento()));
        pacienteSeleccionado.setDocumento_identidad(JOptionPane.showInputDialog(null, "Ingrese el nuevo documento de identidad", pacienteSeleccionado.getDocumento_identidad()));

        //Acá lo actualizamos con el modelo instanciado
        instanciaModel().update(pacienteSeleccionado);
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

        Object[] opcionesPaciente = Utils.listToArray(instanciaModel().findAll());

        Paciente pacienteSeleccionado = (Paciente) JOptionPane.showInputDialog(
                null,
                "Seleccione un paciente que desea eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPaciente,
                opcionesPaciente[0]
        );

        instanciaModel().delete(pacienteSeleccionado);

    }


    public static void getAll(){
        /*
         * Guardar en una lista el método getAll pero le pasamos
         * el método instanciaModelo().findAll()
         * La lista que le vamos a pasar a getAll() la vamos a tomar
         * de instaciar el modelo, es decir, de instanciaModelo()
         * y obtener el método instanciaModelo()
        */

        String lista = getAll(instanciaModel().findAll());

        // Le pasamos la lista que recorrió getAll que aplica sobreescritura sobre métodos estáticos
        JOptionPane.showMessageDialog(null, lista);
    }

    /* Método que es sobreecritura de getAll
     * pero este devuelve un String y va a pedir como parámetro una
     * lista de Object
     */
    public static String getAll(List<Object> list){
        String listaString = "Lista de registros de Pacientes \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objMedico a Paciente ese temporal que se está recorriendo en cada iteracion
            Paciente objPaciente = (Paciente) temp;
            listaString += objPaciente.toString() + "\n";
        }

        return listaString;

    }


    public static void insert(){
        // Pedimos los datos al usuario
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del paciente");
        String apellidos = JOptionPane.showInputDialog("Ingresa los apellidos del paciente");
        // Acá le pedimos al usuario que ingrese la fecha de nacimiento con el formato YYYY-MM-dd
        String fechaNacimiento = JOptionPane.showInputDialog("Ingresa la fecha de nacimiento del paciente YYYY-MM-dd");
        String documentoIdentidad = JOptionPane.showInputDialog("Ingresa el documento de identidad del paciente");

        /* Llamamos al método instanciaModel() que creamos y le pasamos
         al insert un nuevo paciente que le pasamos los parámetros del constructor que creamos en la entidad
         */

        instanciaModel().insert(new Paciente(nombre, apellidos, fechaNacimiento, documentoIdentidad));

    }

    //Creamos el metodo para llamar a nuestra instancia de modelo de paciente
    public static PacienteModel instanciaModel(){
        return new PacienteModel();
    }
}
