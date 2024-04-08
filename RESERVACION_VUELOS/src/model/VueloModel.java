package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {

    // Método para crear un vuelo
    @Override
    public Object insert(Object obj) {

        //1. Abrimos la conexión a la DB
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos un objeto de tipo vuelo, es decir, convertimos el objeto que llegó
        Vuelo objVuelo = (Vuelo) obj;

        try {
            //3. Escribimos el SQL
            String sql = "INSERT INTO vuelo (destino, fecha_salida, hora_salida, id_avion) VALUES (?,?,?,?); ";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Le damos valor a los signos de interrogación
            objPrepare.setString(1, objVuelo.getDestino());
            objPrepare.setDate(2, Date.valueOf(objVuelo.getFechaSalida()));
            objPrepare.setTime(3, Time.valueOf(objVuelo.getHoraSalida()));
            objPrepare.setInt(4, objVuelo.getIdAvion());

            //6. Ejecutamos el SQL
            objPrepare.execute();

            //7. Obtenemos los resultados generados por las llaves
            ResultSet objResult = objPrepare.getGeneratedKeys();

            //8. Iterar mientras haya un siguiente registro (por eso se coloca el next())

            while (objResult.next()){
                objVuelo.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Vuelo creado correctamente.");

        } catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        return objVuelo;
    }

    // Método para obtener todos los registros de los vuelos guardados en la base de datos
    @Override
    public List<Object> findAll() {
        // 1. Lista donde se van a almacenar todos los registros de los vuelos
        List<Object> listaVuelos = new ArrayList<>();

        // 2. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Escribimos la sentencia SQL
            /* Pero necesitamos traer al vuelo UNIDO con la información del avion,
             * entonces eso se hace con un INNER JOIN
             */

            String sql = "SELECT * FROM vuelo\n" +
                    "INNER JOIN avion ON avion.id = vuelo.id_avion;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Obtenemos los resultados
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vuelo objVuelo= new Vuelo();

                // Creamos un objeto de avion
                Avion objAvion = new Avion();

                objVuelo.setId(objResult.getInt("vuelo.id"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                // Acá la fecha sí la podemos traer como un String
                objVuelo.setFechaSalida(objResult.getString("vuelo.fecha_salida"));
                // Acá la hora sí la podemos traer como un String
                objVuelo.setHoraSalida(objResult.getString("vuelo.hora_salida"));
                objVuelo.setIdAvion(objResult.getInt("vuelo.id_avion"));

                objAvion.setId(objResult.getInt("avion.id"));
                objAvion.setModelo(objResult.getString("avion.modelo"));
                objAvion.setCapacidad(objResult.getInt("avion.capacidad"));

                // Hay que guardar el avion dentro del vuelo
                // Entonces utilizamos sus respectivos métodos get y set que fueron creados en la entidad vuelo

                objVuelo.setObjAvion(objAvion);

                listaVuelos.add(objVuelo);
            }

        } catch (SQLException e){
            // El getMessage() es para dar detalles del error
            System.out.println("ERROR >>> " + e.getMessage());
        }

        // Cerramos la conexión
        ConfigDB.closeConnection();

        return listaVuelos;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrimos la conexión, como no es una clase con métodos estáticos, no se coloca new
        Connection objConnection = ConfigDB.openConnection();

        //2. Casteamos el objeto que nos llega como parámetro, es decir, convertimos obj a un tipo de dato Vuelo
        Vuelo objVuelo = (Vuelo) obj;

        //3.  Variable que obtiene el estado de la consulta en tiempo real, si se está actualizado o no el registro
        boolean isUpdated = false;

        try {
            //4. Escribimos la sentencia SQL para eliminar
            String sql = "UPDATE vuelo SET destino=?, fecha_salida=?, hora_salida=?, id_avion=?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor a los ? ? ? ?
            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setDate(2, Date.valueOf(objVuelo.getFechaSalida()));
            objPrepare.setTime(3, Time.valueOf(objVuelo.getHoraSalida()));
            objPrepare.setInt(4,objVuelo.getIdAvion());

            //7. Ejecutamos el query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro del vuelo actualizado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        // 8. Cerramos la conexión
        ConfigDB.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Casteamos el objeto que nos llega como parámetro, es decir, convertimos obj a un tipo de dato Vuelo
        Vuelo objVuelo = (Vuelo) obj;

        //3. Variable que obtiene el estado de la consulta en tiempo real, si se está eliminando o no
        boolean isDeleted = false;

        try {
            //4. Escribimos la sentencia SQL para eliminar
            String sql = "DELETE FROM vuelo WHERE id = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor al ?
            objPrepare.setInt(1,objVuelo.getId());

            // 7.
            /* Ejecutamos para saber cuantas filas afectó, por eso ejecutamos el método executeUpdate()
             * que nos devuelve cuantas filas se afectaron en la consulta
             */
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Registro del vuelo eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //8. Cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
