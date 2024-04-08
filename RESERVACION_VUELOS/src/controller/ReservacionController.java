package controller;

import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;
import model.ReservacionModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ReservacionController {

    // Método para instanciar el modelo de reservacion
    public static ReservacionModel instanceModel(){
        return new ReservacionModel();
    }

    //Método para crear una reservación
    public static void insert() {
        String fechaReservacion = JOptionPane.showInputDialog(null, "Ingresa la fecha para la reservación: YYYY-MM-dd");
        String asiento = JOptionPane.showInputDialog(null, "Ingresa el asiento para la reservación");

        /* Utilizamos la clase utils, y en este caso vamos a instanciar una instancia de lo
         * que queremos llamar instanciar
         */
        Object[] opcionesPasajeros = Utils.listToArray(PasajeroController.instanciaModel().findAll());

        Object[] opcionesVuelos= Utils.listToArray(VueloController.instanciaModelo().findAll());

        Pasajero pasajeroSeleccionado = (Pasajero) JOptionPane.showInputDialog(
                null,
                "Seleccione un pasajero",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPasajeros,
                opcionesPasajeros[0]
        );

        Vuelo vueloSeleccionado = (Vuelo) JOptionPane.showInputDialog(
                null,
                "Seleccione el vuelo asignado a la reservación",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesVuelos,
                opcionesVuelos[0]
        );

        instanceModel().insert(new Reservacion(fechaReservacion, asiento, pasajeroSeleccionado.getId(), vueloSeleccionado.getId(), pasajeroSeleccionado, vueloSeleccionado));
    }

    // Método para obtener todas las reservaciones
    public static void getAll(){
        /*
         * Guardar en una lista el método getAll pero le pasamos
         * el método instanciaModelo().findAll()
         * La lista que le vamos a pasar a getAll() la vamos a tomar
         * de instanciar el modelo, es decir, de instanciaModelo()
         * y obtener el método instanciaModelo()
         */

        String listaString = getAll(instanceModel().findAll());

        // Le pasamos la lista que recorrió getAll que aplica sobreescritura sobre métodos estáticos
        JOptionPane.showMessageDialog(null, listaString);

    }

    //Método getAll sobreescrito
    public static String getAll(List<Object> list){
        String listaString = "Lista de registros de Reservas \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objReservacion a Reservacion ese temporal que se está recorriendo en cada iteracion
            Reservacion objReservacion = (Reservacion) temp;
            listaString += objReservacion.toString() + "\n";
        }

        return listaString;
    }

    //  Método para actualizar una reservacion

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

        Object[] opcionesReservacion = Utils.listToArray(instanceModel().findAll());

        Reservacion reservacionSeleccionada = (Reservacion) JOptionPane.showInputDialog(
                null,
                "Seleccione la reservación que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesReservacion,
                opcionesReservacion[0]
        );

        reservacionSeleccionada.setFechaReservacion(JOptionPane.showInputDialog(null, "Ingresa la fecha de la reservación: YYYY-MM-dd", reservacionSeleccionada.getFechaReservacion()));
        reservacionSeleccionada.setAsiento(JOptionPane.showInputDialog(null, "Ingresa el asiento de la reservación", reservacionSeleccionada.getAsiento()));

        /*
         * Utilizamos la clase utils, y en este caso vamos a instanciar una
         * instancia de lo que queremos llamar instanciar
         */

        Object[] opcionesPasajeros = Utils.listToArray(PasajeroController.instanciaModel().findAll());

        Object[] opcionesVuelos = Utils.listToArray(VueloController.instanciaModelo().findAll());

        Pasajero pasajeroSeleccionado = (Pasajero) JOptionPane.showInputDialog(
                null,
                "Seleccione un pasajero",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPasajeros,
                opcionesPasajeros[0]
        );

        reservacionSeleccionada.setIdPasajero(pasajeroSeleccionado.getId());
        reservacionSeleccionada.setObjPasajero(pasajeroSeleccionado);

        Vuelo vueloSeleccionado = (Vuelo) JOptionPane.showInputDialog(
                null,
                "Seleccione el vuelo asignado a la reservación",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesVuelos,
                opcionesVuelos[0]
        );

        reservacionSeleccionada.setIdVuelo(vueloSeleccionado.getId());
        reservacionSeleccionada.setObjVuelo(vueloSeleccionado);

        /* Una vez que el usuario escoja la reservacion que quiere actualizar, llamamos al método
        instanceModel() y al método delete() y le enviamos todas las reservaciones seleccionadas
        * */
        instanceModel().update(reservacionSeleccionada);
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

        Object[] opcionesReservacion = Utils.listToArray(instanceModel().findAll());

        Reservacion reservacionSeleccionada = (Reservacion) JOptionPane.showInputDialog(
                null,
                "Seleccione la reservación a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesReservacion,
                opcionesReservacion[0]
        );

        /* Una vez que el usuario escoja la reservacion que quiere eliminar, llamamos al método
        instanceModel() y al método delete() y le enviamos todas las reservaciones seleccionadas
        * */

        instanceModel().delete(reservacionSeleccionada);

    }



}
