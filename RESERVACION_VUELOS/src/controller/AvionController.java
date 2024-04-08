package controller;

import entity.Avion;
import model.AvionModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class AvionController {
    //Creamos el metodo para llamar a nuestra instancia de modelo de avion
    public static AvionModel instanciaModel(){
        return new AvionModel();
    }

    //Método para crear un avion
    public static void insert(){
        // Pedimos los datos al usuario
        String modelo = JOptionPane.showInputDialog("Ingresa el modelo del avión");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la capacidad del avión"));

        /* Llamamos al método instanciaModel() que creamos y le pasamos
         al insert un nuevo avion que le pasamos los parámetros del constructor que creamos en la entidad
         */

        instanciaModel().insert(new Avion(modelo, capacidad));
    }

    // Método para obtener todos los registros
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
     * Pero este devuelve un String y va a pedir como parámetro una lista de Object
     */
    public static String getAll(List<Object> list){
        String listaString = "Lista de registros de Aviones \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objAvion a Avion ese temporal que se está recorriendo en cada iteracion
            Avion objAvion = (Avion) temp;
            listaString += objAvion.toString() + "\n";
        }

        return listaString;
    }

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

        Object[] opcionesAvion = Utils.listToArray(instanciaModel().findAll());

        Avion avionSeleccionado = (Avion) JOptionPane.showInputDialog(
                null,
                "Seleccione el avión que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesAvion,
                opcionesAvion[0]
        );

        //Le pedimos los nuevos datos al usuario
        avionSeleccionado.setModelo(JOptionPane.showInputDialog(null, "Ingrese el nuevo modelo de avión", avionSeleccionado.getModelo()));
        avionSeleccionado.setCapacidad(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la nueva capacidad del avión", avionSeleccionado.getCapacidad())));

        //Acá lo actualizamos con el modelo instanciado
        instanciaModel().update(avionSeleccionado);
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

        Object[] opcionesAvion = Utils.listToArray(instanciaModel().findAll());

        Avion avionSeleccionado = (Avion) JOptionPane.showInputDialog(
                null,
                "Seleccione el avión que desea eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesAvion,
                opcionesAvion[0]
        );

        instanciaModel().delete(avionSeleccionado);
    }
}
