package utils;

import java.util.List;

public class Utils {
    /* T puede ser cualquier cosa, en este caso T va a tomar el valor de la lista que yo le esté
     * introduciendo como parámetro y va a retornar un array de T
     * T puede ser cualquier cosa, cualquier parámetro, cualquier objeto, puede ser un String
     * un Integer, un Double, puede ser una Especialidad o un Medico, T puede ser cualquier cosa
     * Todo esto con ayuda de los genéricos, la letra puede ser cualquiera, no tiene que ser la T
     *  obligatoriamente
     */

    public static<T> T[] listToArray(List<T> list){

        // Crear un arreglo de Object a partir del tamaño de la lista

        // Creamos el arreglo de tipo T
        //convertimos esta lista de object en un arreglo de tipo T
        T[] miArray = (T[]) new Object[list.size()];

        /*
         * Vamos a crear un for que va a tener un iterador y va a recorrer
         * sobre la lista que nos llega como parámetro.
         * El iterador va a ser de tipo T
         */

        int i = 0;
        for (T iterador : list) {
            // Contador que va air sumando de 1 en 1 y va air agregando el item, es decir, en este caso, el iterador
            miArray[i++] = iterador;
        }
        return miArray;
    }
}
