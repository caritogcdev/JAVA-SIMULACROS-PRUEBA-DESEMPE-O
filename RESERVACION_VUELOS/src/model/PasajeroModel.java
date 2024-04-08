package model;

import database.CRUD;
import database.ConfigDB;
import entity.Pasajero;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1. Abrimos la conexión a la DB
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos un objeto de tipo Pasajero, es decir, convertimos el objeto que llegó
        Pasajero objPasajero = (Pasajero) obj;

        try {
            //3. Insertamos el objeto en la BD (escribimos el SQL)
            String sql = "INSERT INTO pasajero (nombre, apellido, documento_identidad) VALUES (?,?,?);";

            //4. Preparamos la sentencia SQL (preparamos el statement)
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valor a los ? ? ?
            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2, objPasajero.getApellido());
            objPrepare.setString(3, objPasajero.getDocumento_identidad());

            //6. Ejecutamos la sentencia SQL (ejecutamos el statement) el query
            objPrepare.execute();

            //7. Obtenemos el ID generado por la BD (Llaves generadas)
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objResult.next()){
                //Podemos obtener el valor también con índices
                objPasajero.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Se creó el pasajero correctamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el pasajero");
            e.getMessage();
        }

        //9. Cerramos la conexión a la BD
        ConfigDB.closeConnection();

        //10. Retornar el objeto
        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        //1. Crear lista para ir guardando lo que nos devuelva la base de datos
        List<Object> listaPasajero = new ArrayList<>();

        //2. Abrimos la conexión a la BD
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Escribimos el query en SQL
            String sql = "SELECT * FROM pasajero;";

            // 4. Preparamos el statement o la sentencia SQL
            /*
             * No es necesario pasarle el genérico para que nos retorne las llaves ya que el SELECT * FROM
             * nos va a devolver todas las llaves y todos los registros
             * */
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutamos la sentencia SQL (Ejecutar el ResultSet que es donde va a venir el resultado de esa consulta)
            ResultSet objResultSet = objPrepare.executeQuery(); // executeQuery() me devuelve todos los registros de la DB

            // Iterar mientras haya un registro
            while (objResultSet.next()){
                //Podemos obtener el valor también con indices

                // 6. Crear un Pasajero (creamos un objeto pasajero), es decir crear una nueva instancia de Pasajero
                Pasajero objPasajero = new Pasajero();

                //7. Llenar el objeto con la información de la base de datos del objeto que está iterando
                // se tiene que llamar igual a como se llaman en la DB
                objPasajero.setId(objResultSet.getInt("id"));
                objPasajero.setNombre(objResultSet.getString("nombre"));
                objPasajero.setApellido(objResultSet.getString("apellido"));
                objPasajero.setDocumento_identidad(objResultSet.getString("documento_identidad"));

                //8. Agregamos el Pasajero a la lista. Cuando el objeto está lleno, lo agregamos a la lista
                listaPasajero.add(objPasajero);
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //9. Cerrar la conexión
        ConfigDB.closeConnection();

        //10. Retornar la lista
        return listaPasajero;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Pasajero
        Pasajero objPasajero = (Pasajero) obj;

        //3. Declaramos la variable que nos va a indicar si un pasajero fue actualizado o no
        boolean isUpdated = false;

        try {
            // 4. Escribimos la sentencia SQL
            String sql = "UPDATE pasajero SET nombre = ?, apellido = ?, documento_identidad = ? WHERE id = ?;";

            //5. Preparamos el statement el cual siempre proviene de la conexión y siempre hay que pasarle la consulta sql como parámetro
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor a los ? ? ? ?
            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2, objPasajero.getApellido());
            objPrepare.setString(3, objPasajero.getDocumento_identidad());
            objPrepare.setInt(4, objPasajero.getId());

            //7. Ejecutamos el sql para saber cuantas filas fueron afectadas con executeUpdate()
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "El pasajero ha sido actualizado correctamente");
            }

        } catch (SQLException error){
            System.out.println("ERROR >>> " + error.getMessage());
        }

        //8. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Pasajero
        Pasajero objPasajero = (Pasajero) obj;

        //3. Declaramos la variable que nos va a indicar si un pasajero fue eliminado o no
        boolean isDeleted = false;

        try {
            //4. Escribimos la sentencia sql
            String sql = "DELETE FROM pasajero WHERE id = ?;";

            //5. Preparamos el statement, el cual siempre proviene de la conexión
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Damos valor al ?, se coloca la posición y le pasamos el id del objeto pasajero
            objPrepare.setInt(1, objPasajero.getId());

            //7. Ejecutamos el sql para saber cuantas filas fueron afectadas con executeUpdate()
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "El pasajero ha sido eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR >>>" + e.getMessage());
        }

        //8. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
