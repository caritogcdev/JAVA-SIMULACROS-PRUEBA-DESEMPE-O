package controller;

import entity.Especialidad;
import model.EspecialidadModel;
import utils.Utils;

import javax.swing.*;

public class EspecialidadController {
    // El controlador se caracteriza por utilizar el modelo

    /*
    * Como vamos a estar utilizando de manera implícita o concurrente el modelo,
    * vamos a crear un méto que se encargue de instanciarlo para no estarlo
    * instanciando todas las veces
    */

    // En este caso, este lo estamos usando en otro método para eliminar
    public static EspecialidadModel instanciaModel(){
        return new EspecialidadModel();
    }


    // Método para crear una especialidad
    public static void create(){
        // Usamos el modelo
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        // Pedimos los datos al usuario
        String nombre = JOptionPane.showInputDialog("Inserte el nombre de la especialidad");
        String descripcion = JOptionPane.showInputDialog("Inserte la descripción de la especialidad");

        // Creamos la instancia de Especialidad
        Especialidad objEspecialidad = new Especialidad();

       // instanciaModel().insert(new Especialidad(nombre, descripcion));

        objEspecialidad.setNombre(nombre);
        objEspecialidad.setDescripcion(descripcion);

        // Llamamos al método de inserción y guardamos el objeto que retorna en Especialidad previamente instanciado, debemos castearlo.

        objEspecialidad = (Especialidad) objEspecialidadModel.insert(objEspecialidad);

        JOptionPane.showMessageDialog(null, objEspecialidad.toString());
    }

    // Método para obtener todas las especialidades
    public static void getAll(){
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidad = " LISTA DE REGISTROS DE LAS ESPECIALIDADES \n";

        //Vamos a utilizar la lista de find all
        for (Object iterador: objModel.findAll()) {

            // Convertimos del Object a Especialidad

            // Convertir el iterador de tipo Object a un iterador de tipo Especialidad para poder acceder a los métodos id, nombre, etc.
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listEspecialidad);
    }

    // Como el método getAll (el de arriba) no se puede sobreescribir, hay que crear el mismo pero retornando la lista de especialidades en String para poderlo listar en el Delete
    // Método para listar todas las especialidades
    public static String getAllString(){
        EspecialidadModel objModel = new EspecialidadModel();
        String listEspecialidad = " LISTA DE REGISTROS DE LAS ESPECIALIDADES \n";

        //Vamos a utilizar la lista de find all
        for (Object iterador: objModel.findAll()) {

            // Convertimos del Object a Especialidad

            // Convertir el iterador de tipo Object a un iterador de tipo Especialidad para poder acceder a los métodos id, nombre, etc.
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }

        // Aquí queremos que nos devuelva la lista en String para poderlo listar en el Delete y se le añade el otro String para que pueda escoger

        return listEspecialidad;
    }

    // Método para actualizar una especialidad

    public static void update(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        String litaEspecialidad = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(litaEspecialidad + "\n Ingrese el ID de la especialidad a editar: "));

        // Obteniendo una especialidad por el id ingresado
        Especialidad objEspecialidad = objEspecialidadModel.findById(idUpdate);

        // Validamos que exista una especialidad
        if (objEspecialidad == null) {
            JOptionPane.showMessageDialog(null, "Especialidad no encontrada");
        } else {
            String nombre = JOptionPane.showInputDialog(null, "Ingresa un nuevo nombre para la especialidad: ", objEspecialidad.getNombre());
            String descripcion = JOptionPane.showInputDialog(null, "Ingresa una nueva descripción para la especialidad", objEspecialidad.getDescripcion());

            objEspecialidad.setNombre(nombre);
            objEspecialidad.setDescripcion(descripcion);

            // Actualizamos
            objEspecialidadModel.update(objEspecialidad);
        }
    }

    // Método para eliminar una especialidad
    public static void delete(){
        // Siempre debemos utilizar el modelo porque es el que tiene todos los modelos para interactuar con la DB
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        String listaEspecialidades = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listaEspecialidades + "\n Ingresa el ID de la especialidad a eliminar"));

        Especialidad objEspecialidad = objEspecialidadModel.findById(idDelete); // Aquí no lo casteamos porque ya findById devuelve una Especialidad y no un objeto

        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null, "Especialidad no encontrada");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que quiere eliminar la especialidad? \n" + objEspecialidad.toString());
            // Eliminar
            if (confirm == 0) objEspecialidadModel.delete(objEspecialidad);
        }
    }

    // Método para eliminar una especialidad utilizando un select
    public static void deleteWithSelect(){
        /*
        Vamos a habilitar el JOptionpane como un select no recibe listas o arraylists, solo recibe arrays
        entonces vamos a crearnos una utilidad que sea reutilizable y que nos permita cambiar y nos
        permita convertir una lista a un array.
         */

        /* Utilizamos la clase utils y el método que creamos que es listToArray()
         * y el array que le vamos a enviar
         */
        Object[] options = Utils.listToArray(instanciaModel().findAll());

        Especialidad objSeleccionado = (Especialidad) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad para eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options, // opciones que va a tener
                options[0] // opción por defecto que va a tener
        );
        instanciaModel().delete(objSeleccionado);
    }

    // Método para actualizar una especialidad utilizando un select
    public static void updateWithSelect(){
        /*
        * Reutilizamos lo que ya hicimos en el método deleteWithSelect(),
        * vamos a convertir la lista total de especialidades en un arrayList
        * */
        Object[] options = Utils.listToArray(instanciaModel().findAll());
        Especialidad objSeleccionado = (Especialidad) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options, // opciones que va a tener
                options[0] // opción por defecto que va a tener
        );

        // Pedirle los datos al usuario
        objSeleccionado.setNombre(JOptionPane.showInputDialog(null,"Ingresa el nuevo nombre de la especialidad:", objSeleccionado.getNombre())); // Después del message va el nombre por defecto
        objSeleccionado.setDescripcion(JOptionPane.showInputDialog(null,"Ingresa la nueva descripción de la especialidad", objSeleccionado.getDescripcion()));

        // Utilizamos el método que ua tenemos que nos instancia el modelo
        instanciaModel().update(objSeleccionado);
    }

}
