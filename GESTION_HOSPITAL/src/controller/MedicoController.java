package controller;

import entity.Especialidad;
import entity.Medico;
import model.MedicoModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class MedicoController {

    // Método para obtener a todos los médicos
    public static void getAll(){

        /*
        * Guardar en una lista el método getAll pero le pasamos
        * el método instanciaModelo().findAll()
        */

        String lista = getAll(instanciaModelo().findAll());

        // Le pasamos la lista que recorrió getAll que aplica sobreescritura sobre métodos estáticos
        JOptionPane.showMessageDialog(null, lista);

    }

    /* Método que es sobreecritura de getAll
    * pero este devuelve un String y va a pedir como parámetro una
    * lista de Object
     */
    public static String getAll(List<Object> list){
        String listaString = "Lista de registros de Médicos \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objMedico a Medico ese temporal que se está recorriendo en cada iteracion
            Medico objMedico = (Medico) temp;
            listaString += objMedico.toString() + "\n";
        }

        return listaString;
    }

    // Método para crear un medico
    public static void insert(){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del médico: ");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del médico: ");

        // Le vamos a pedir la especialidad del médico

        // listar la especialidad
        /*
        * Utilizamos el object de array guardando en unas opciones de especialidades
        * y utilizamos nuestra utilidad que creamos, que era convertir una lista
        * a array
        *
        * vamos a utilizar el modelo pero de especialidad
        * */

        Object[] opcionesEspecialidades = Utils.listToArray(EspecialidadController.instanciaModel().findAll());

        Especialidad objEspecialidad = (Especialidad) JOptionPane.showInputDialog(
                null,
                "Seleccione una especialidad: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesEspecialidades, // Las opciones son las de opcionesEspecialidades
                opcionesEspecialidades[0] // Y la opción por defecto va a ser la posición cero
        );

        // esta instancia tiene que llevar un objeto, y le pasamos los parámetros del constructor de Medico y todo el objeto de la especialidad
        instanciaModelo().insert( new Medico(nombre, apellidos, objEspecialidad.getId(), objEspecialidad));

    }

    // Método para devolver una instancia del modelo de Medico
    public static MedicoModel instanciaModelo(){
        return new MedicoModel();
    }

    //Método para actualizar a un médico
    public static void update(){
        /*
         * Listamos todos los atributos que queremos actualizar, pero en un
         * select, para que mejore la experiencia de usuario
         * */

        // Listar todos los medicos
        Object[] opcionesMedico = Utils.listToArray(instanciaModelo().findAll());

        Medico objMedico = (Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el médico a actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesMedico, // Las opciones son las de opcionesMedico
                opcionesMedico[0] // Y la opción por defecto va a ser la posición cero
        );

        // Pedir el nuevo nombre y el nuevo apellido
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del médico: ", objMedico.getNombre());
        String apellidos = JOptionPane.showInputDialog(null, "Ingrese los apellidos del médico: ", objMedico.getApellidos());

        // Listamos las especialidades
        Object[] opcionesEspecialidades = Utils.listToArray(EspecialidadController.instanciaModel().findAll());

        // Mostramos las especialidades en un select
        Especialidad objEspecialidad = (Especialidad) JOptionPane.showInputDialog(
                null,
                "Seleccione una especialidad: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesEspecialidades, // Las opciones son las de opcionesEspecialidades
                opcionesEspecialidades[0] // Y la opción por defecto va a ser la posición cero
        );

        // Y lo mandamos a actualizar
        // esta instancia tiene que llevar un objeto, y le pasamos los parámetros del constructor de Medico y todo el objeto de la especialidad
        instanciaModelo().update( new Medico(nombre, apellidos, objEspecialidad.getId(), objEspecialidad));

    }

    //Método para eliminar a un médico
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
        Object[] opcionesMedico = Utils.listToArray(instanciaModelo().findAll());

        Medico objMedico = (Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el médico a eliminar: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesMedico, // Las opciones son las de opcionesMedico
                opcionesMedico[0] // Y la opción por defecto va a ser la posición cero
        );

        // Llamamos al modelo .delete y le pasamos al médico que queremos eliminar
        instanciaModelo().delete(objMedico);
    }

}
