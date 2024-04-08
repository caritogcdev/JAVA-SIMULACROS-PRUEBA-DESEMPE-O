package controller;

import entity.Avion;
import entity.Vuelo;
import model.VueloModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class VueloController {
    // Método para devolver una instancia del modelo de Vuelo
    public static VueloModel instanciaModelo() {
        return new VueloModel();
    }

    // Método para crear un vuelo
    public static void insert() {
        String destino = JOptionPane.showInputDialog("Ingrese el destino del vuelo: ");
        String fechaSalida = JOptionPane.showInputDialog("Ingrese la fecha de salida del vuelo: YYYY-MM-dd: ");
        String horaSalida = JOptionPane.showInputDialog("Ingrese la hora de salida del vuelo: HH:mm:ss: ");

        // Le vamos a pedir el avion del vuelo

        // listar el avion
        /*
         * Utilizamos el object de array guardando en unas opciones de aviones
         * y utilizamos nuestra utilidad que creamos, que era convertir una lista
         * a array
         *
         * vamos a utilizar el modelo pero de avion
         * */

        Object[] opcionesAviones = Utils.listToArray(AvionController.instanciaModel().findAll());

        Avion objAvion = (Avion) JOptionPane.showInputDialog(
                null,
                "Seleccione un avión: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesAviones, // Las opciones son las de opcionesAviones
                opcionesAviones[0] // Y la opción por defecto va a ser la posición cero
        );

        //Esta instancia tiene que llevar un objeto, y le pasamos los parámetros del constructor de Vuelo y todo el objeto del avion
        instanciaModelo().insert(new Vuelo(destino, fechaSalida, horaSalida, objAvion.getId(), objAvion));

    }

    // Método para obtener a todos los vuelos
    public static void getAll() {

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
    public static String getAll(List<Object> list) {
        String listaString = "Lista de registros de Vuelos \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objVuelo a Vuelo ese temporal que se está recorriendo en cada iteracion
            Vuelo objVuelo = (Vuelo) temp;
            listaString += objVuelo.toString() + "\n";
        }

        return listaString;
    }

    //Método para actualizar un vuelo
    public static void update(){
        /*
         * Listamos todos los atributos que queremos actualizar, pero en un
         * select, para que mejore la experiencia de usuario
         * */

        // Listar todos los vuelos
        Object[] opcionesVuelo = Utils.listToArray(instanciaModelo().findAll());

        Vuelo objVuelo = (Vuelo) JOptionPane.showInputDialog(
                null,
                "Seleccione el médico a actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesVuelo, // Las opciones son las de opcionesVuelo
                opcionesVuelo[0] // Y la opción por defecto va a ser la posición cero
        );

        // Pedir los nuevos datos al usuario
        String destino = JOptionPane.showInputDialog(null, "Ingrese el destino del vuelo: ", objVuelo.getDestino());
        String fechaSalida = JOptionPane.showInputDialog(null, "Ingrese la fecha de salida del vuelo: YYYY-MM-dd: ", objVuelo.getFechaSalida());
        String horaSalida = JOptionPane.showInputDialog(null, "Ingrese la hora de salida del vuelo: HH:mm:ss: ", objVuelo.getHoraSalida());


        // Listamos los aviones
        Object[] opcionesAviones = Utils.listToArray(AvionController.instanciaModel().findAll());

        // Mostramos los aviones en un select
        Avion objAvion = (Avion) JOptionPane.showInputDialog(
                null,
                "Seleccione un avion: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesAviones, // Las opciones son las de opcionesAviones
                opcionesAviones[0] // Y la opción por defecto va a ser la posición cero
        );

        // Y lo mandamos a actualizar
        // Esta instancia tiene que llevar un objeto, y le pasamos los parámetros del constructor de Vuelo y todo el objeto del avion
        instanciaModelo().update( new Vuelo(destino, fechaSalida, horaSalida, objAvion.getId(), objAvion));

    }

    //Método para eliminar a un vuelo
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
        Object[] opcionesVuelo = Utils.listToArray(instanciaModelo().findAll());

        Vuelo objVuelo = (Vuelo) JOptionPane.showInputDialog(
                null,
                "Seleccione el vuelo a eliminar: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesVuelo, // Las opciones son las de opcionesVuelo
                opcionesVuelo[0] // Y la opción por defecto va a ser la posición cero
        );

        // Llamamos al modelo .delete y le pasamos al vuelo que queremos eliminar
        instanciaModelo().delete(objVuelo);
    }

}