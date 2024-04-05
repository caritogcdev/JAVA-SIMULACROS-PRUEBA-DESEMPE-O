package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD {

    // El modelo es el que interactúa con la conexión y a su vez, con la base de datos

    // Acá hay que implementar todos los métodos de la interfaz que creamos
    // En el bombillo seleccionar la opción de Implement Methods
    @Override
    public Object insert(Object obj) {

        //1. Abrimos la conexión a la DB
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos un objeto de tipo especialidad, es decir, convertimos el objeto que llegó
        Especialidad objEspecialidad = (Especialidad) obj;

        try {
            //3. Insertamos el objeto en la BD (escribimos el SQL)
            String sql = "INSERT INTO especialidad (nombre, descripcion) VALUES (?,?);";

            //4. Preparamos la sentencia SQL (preparamos el statement)
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valor a los ? ?
            objPrepare.setString(1, objEspecialidad.getNombre());
            objPrepare.setString(2, objEspecialidad.getDescripcion());

            //6. Ejecutamos la sentencia SQL (ejecutamos el statement) el query
            objPrepare.execute();

            //7. Obtenemos el ID generado por la BD (Llaves generadas)
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objResult.next()){
                //Podemos obtener el valor también con índices
                objEspecialidad.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Se insertó la especialidad correctamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar la especialidad");
            e.getMessage();
        }

        //9. Cerramos la conexión a la BD
        ConfigDB.closeConnection();

        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {
        //1. Crear lista para ir guardando lo que nos devuelva la base de datos
        List<Object> listaEspecialidad = new ArrayList<>();

        //2. Abrimos la conexión a la BD
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Escribimos el query en SQL
            String sql = "SELECT * FROM especialidad;";

            // 4. Preparamos la sentencia SQL
            PreparedStatement objPrepare = objConnection.prepareStatement(sql); // no es necesario pasarle el genérico para que nos retorne las llaves ya que el SELECT * FROM nos va a devolver todas las llaves y todos los registros

            // 5. Ejecutamos la sentencia SQL (Ejecutar el ResultSet que es donde va a venir el resultado de esa consulta)
            ResultSet objResultSet = objPrepare.executeQuery(); // executeQuery() me devuelve todos los registros de la DB

            // 6. Iterar mientras haya un registro
            while (objResultSet.next()){
                //Podemos obtener el valor también con indices

                // 6.1 Crear una especialidad (creamos un objeto especialidad)
                Especialidad objEspecialidad = new Especialidad();

                //6.2 Llenar el objeto con la información de la base de datos del objeto que está iterando
                // se tiene que llamar igual a como se llaman en la DB
                objEspecialidad.setId(objResultSet.getInt("id"));
                objEspecialidad.setNombre(objResultSet.getString("nombre"));
                objEspecialidad.setDescripcion(objResultSet.getString("descripcion"));

                //6.3 Agregamos la Especialidad a la lista. Cuando el objeto está lleno, lo agregamos a la lista
                listaEspecialidad.add(objEspecialidad);
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7.  Cerrar la conexión
        ConfigDB.closeConnection();

        return listaEspecialidad;
    }

    // Método para encontrar una especialidad por ID, este lo necesitamos porque para actualizar necesitamos el id
    public Especialidad findById(int id){
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Crear la especialidad que vamos retornar
        Especialidad objEspecialidad = null;

        try {
            //3,Sentencia SQL
            String sql = "SELECT * FROM especialidad WHERE id = ?";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al parámetro del query
            objPrepare.setInt(1, id);

            // Execute devuelve un booleano
            // Execute query devuelve un resultado

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) { //si viene algo
                objEspecialidad = new Especialidad(); //reasignando el valor de la variable
                objEspecialidad.setId(objResult.getInt("id"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objEspecialidad;
    }

    // Método para actualizar una especialidad

    @Override
    public boolean update(Object obj) {

        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertirlo a una Especialidad
        Especialidad objEspecialidad = (Especialidad) obj;

        // 3. Variable para conocer el estado de la acción
        boolean isUpdated = false;

        try {
            // 4. Crear la sentencia SQL
            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ? WHERE id = ?;";

            // 5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Asignar valor a los parámetros del Query
            objPrepare.setString(1, objEspecialidad.getNombre());
            objPrepare.setString(2, objEspecialidad.getDescripcion());
            objPrepare.setInt(3, objEspecialidad.getId());

            //7. Ejecutar el query
            // Utilizamos executeUpdate() para que nos devuelva el número de filas afectadas
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Se actualizó la especialidad correctamente");
            }

        } catch ( Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // 8. Cerramos la conexión
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    // Método para eliminar una especialidad
    @Override
    public boolean delete(Object obj) {

        // 1. Convertir el objeto a la entidad
        Especialidad objEspecialidad = (Especialidad)  obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear la variable de estado
        boolean isDeleted = false;

        try {
            // 4. Crear la sentencia SQL
            String sql = "DELETE FROM especialidad WHERE id = ? ;";

            // 5. Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor al ?
            objPrepare.setInt(1, objEspecialidad.getId());

            // 7. Ejecutamos el Query (executeUpdate) que devuelve el número de registros (filas) afectadas
            int totalAffectedRows = objPrepare.executeUpdate();

            //Si las filas afectadas fueron mayor a cero quiere decir que si eliminó algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Se eliminó la especialidad correctamente");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 8. Cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
