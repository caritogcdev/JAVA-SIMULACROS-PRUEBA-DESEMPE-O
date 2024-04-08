package model;

import database.CRUD;
import database.ConfigDB;
import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Reservacion
        Reservacion objReservacion = (Reservacion) obj;

        try {
            //3. Escribimos la sentencia SQL para crear una reservacion
            String sql = "INSERT INTO reservacion (fecha_reservacion, asiento, id_pasajero, id_vuelo) VALUES (?,?,?,?);";

            //4. Preparamos el statement, y le pasamos el sql como parámetro y le decimos que nos retorne las llaves generadas por la misma base de datos
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Le damos valor a los ? ? ? ?
            objPrepare.setDate(1, Date.valueOf(objReservacion.getFechaReservacion()));
            objPrepare.setString(2, objReservacion.getAsiento());
            objPrepare.setInt(3, objReservacion.getIdPasajero());
            objPrepare.setInt(4, objReservacion.getIdVuelo());

            //6. Ejecutamos el query
            objPrepare.execute();

            //7. Resultado del query
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // Mientras haya un resultado
            while (objResult.next()){
                /*
                 * Vamos a guardar en el objReservacion el id que se generó
                 * en este caso lo sacamos del objResult.getInt con la columna o el índice 1
                 */

                objReservacion.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Reservación creada correctamente");

        }catch (SQLException error){
            System.out.println("ERROR >>> " + error.getMessage());
        }

        //8. Cerramos la conexión
        ConfigDB.closeConnection();

        return objReservacion;
    }

    @Override
    public List<Object> findAll() {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos una lista de objetos
        List<Object> listaReservaciones = new ArrayList<>();

        try {
            //3. Escribimos la sentencia SQL para seleccionar las reservaciones
            String sql = "SELECT * FROM reservacion\n" +
                    "INNER JOIN pasajero ON pasajero.id = reservacion.id_pasajero\n" +
                    "INNER JOIN vuelo ON vuelo.id = reservacion.id_vuelo;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Vamos a ejecutar el query con executeQuery() para que nos devuelva todos los registros que están en la base de datos
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                //6. Crear una nueva instancia de Reservacion
                Reservacion objReservacion = new Reservacion();

                //7. Creamos una instancia del objeto Pasajero
                Pasajero objPasajero = new Pasajero();

                //8.  Creamos una instancia del objeto Vuelo
                Vuelo objVuelo = new Vuelo();

                //9. Y lo vamos a llenar con los siguientes datos
                objReservacion.setId(objResult.getInt("reservacion.id"));
                objReservacion.setFechaReservacion(objResult.getString("reservacion.fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("reservacion.asiento"));
                objReservacion.setIdPasajero(objResult.getInt("reservacion.id_pasajero"));
                objReservacion.setIdVuelo(objResult.getInt("reservacion.id_vuelo"));

                //10. En el ToString yo solo quiero tanto el nombre del pasajero como el destino del vuelo
                objPasajero.setNombre(objResult.getString("pasajero.nombre"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));

                //11. Luego nos toca guardar tanto objPasajero y objVuelo
                objReservacion.setObjPasajero(objPasajero);
                objReservacion.setObjVuelo(objVuelo);

                //12. Luego guardamos en la lista en reservacion que ya tiene al pasajero y al vuelo
                listaReservaciones.add(objReservacion);
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //13. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return listaReservaciones;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Reservacion
        Reservacion objReservacion = (Reservacion) obj;

        //3. Declaramos la variable que nos va a indicar si una reservacion fue actualizada o no
        boolean isUpdated = false;

        try {
            //4. Escribimos la sentencia SQL para eliminar una reservacion
            String sql = "UPDATE reservacion SET fecha_reservacion = ?, asiento = ?, id_pasajero = ?, id_vuelo = ? WHERE id = ? ;";

            //5. Preparamos el statement que viene de la conexión
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor a los ? ? ? ? ? ?
            objPrepare.setDate(1, Date.valueOf(objReservacion.getFechaReservacion()));
            objPrepare.setString(2, objReservacion.getAsiento());
            objPrepare.setInt(3, objReservacion.getIdPasajero());
            objPrepare.setInt(4, objReservacion.getIdVuelo());
            objPrepare.setInt(5, objReservacion.getId());

            //7. Ejecutamos verificando cuantas filas fueron afectadas
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Reservación actualizada correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //8. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Reservacion
        Reservacion objReservacion = (Reservacion) obj;

        //3. Declaramos la variable que nos va a indicar si una reservacion fue eliminada o no
        boolean isDeleted = false;

        try {
            //4. Escribimos la sentencia SQL para eliminar una reservacion
            String sql = "DELETE FROM reservacion WHERE id = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor al ?
            objPrepare.setInt(1, objReservacion.getId());

            //7. Ejecutamos verificando cuantas filas fueron afectadas
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Reservación eliminada correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //8. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
