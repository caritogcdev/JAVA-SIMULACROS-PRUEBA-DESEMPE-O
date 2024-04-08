package controller;

import entity.Pasajero;
import model.PasajeroModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class PasajeroController {
    //Creamos el método para llamar a nuestra instancia de modelo de pasajero
    public static PasajeroModel instanciaModel(){
        return new PasajeroModel();
    }

    //Método para crear un pasajero
    public static void insert(){
        // Pedimos los datos al usuario
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del pasajero");
        String apellido = JOptionPane.showInputDialog("Ingresa los apellidos del pasajero");
        String documentoIdentidad = JOptionPane.showInputDialog("Ingresa el documento de identidad del pasajero");

        /* Llamamos al método instanciaModel() que creamos y le pasamos
         al insert un nuevo pasajero que le pasamos los parámetros del constructor que creamos en la entidad
         */

        instanciaModel().insert(new Pasajero(nombre, apellido, documentoIdentidad));
    }

    // Método para obtener todos los registros (pasajeros)
    public static void getAll(){
        /*
         * Guardar en una lista el método getAll pero le pasamos
         * el método instanciaModelo().findAll()
         * La lista que le vamos a pasar a getAll() la vamos a tomar
         * de instanciar el modelo, es decir, de instanciaModelo()
         * y obtener el método instanciaModelo()
         */

        String lista = getAll(instanciaModel().findAll());

        // Le pasamos la lista que recorrió getAll que aplica sobreescritura sobre métodos estáticos
        JOptionPane.showMessageDialog(null, lista);
    }

    /* Método que es sobreecritura de getAll
     * Pero este devuelve un String y va a pedir como parámetro una lista de Object
     */
    public static String getAll(List<Object> list){
        String listaString = "Lista de registros de los Pasajeros \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objPasajero a Pasajero ese temporal que se está recorriendo en cada iteracion
            Pasajero objPasajero = (Pasajero) temp;
            listaString += objPasajero.toString() + "\n";
        }

        return listaString;
    }

    //Método para actualizar a los pasajeros
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

        Object[] opcionesPasajero = Utils.listToArray(instanciaModel().findAll());

        Pasajero pasajeroSeleccionado = (Pasajero) JOptionPane.showInputDialog(
                null,
                "Seleccione al pasajero que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPasajero,
                opcionesPasajero[0]
        );

        //Le pedimos los nuevos datos al usuario
        pasajeroSeleccionado.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del pasajero", pasajeroSeleccionado.getNombre()));
        pasajeroSeleccionado.setApellido(JOptionPane.showInputDialog(null, "Ingrese los nuevos apellidos del pasajero", pasajeroSeleccionado.getApellido()));
        pasajeroSeleccionado.setDocumento_identidad(JOptionPane.showInputDialog(null, "Ingrese el nuevo número de identificación del pasajero", pasajeroSeleccionado.getDocumento_identidad()));

        //Acá lo actualizamos con el modelo instanciado
        instanciaModel().update(pasajeroSeleccionado);
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

        Object[] opcionesPasajero = Utils.listToArray(instanciaModel().findAll());

        Pasajero pasajeroSeleccionado = (Pasajero) JOptionPane.showInputDialog(
                null,
                "Seleccione el avión que desea eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPasajero,
                opcionesPasajero[0]
        );
        instanciaModel().delete(pasajeroSeleccionado);
    }

}
