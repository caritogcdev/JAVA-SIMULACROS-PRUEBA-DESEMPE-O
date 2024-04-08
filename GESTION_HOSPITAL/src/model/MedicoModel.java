package model;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        //1. Abrimos la conexión a la DB
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos un objeto de tipo médico, es decir, convertimos el objeto que llegó
        Medico objMedico = (Medico) obj;

        try {
            //3. Escribimos el SQL
            String sql = "INSERT INTO medico (nombre, apellidos, id_especialidad) VALUES (?,?,?); ";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Le damos valor a los signos de interrogación
            objPrepare.setString(1, objMedico.getNombre());
            objPrepare.setString(2, objMedico.getApellidos());
            objPrepare.setInt(3, objMedico.getIdEspecialidad());

            //6. Ejecutamos el SQL
            objPrepare.execute();

            //7. Obtenemos los resultados generados por las llaves
            ResultSet objResult = objPrepare.getGeneratedKeys();

            //8. Iterar mientras haya un siguiente registro (por eso se coloca el next())

            while (objResult.next()){
                objMedico.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Registro insertado correctamente.");

        } catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        return objMedico;
    }

    // Método para pbtener todos los registros guardados en la base de datos
    @Override
    public List<Object> findAll() {

        // 1. Lista donde se van a almacenar todos los registros de los médicos
        List<Object> listaMedicos = new ArrayList<>();

        // 2. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Escribimos la sentencia SQL
            /* Pero necesitamos traer al médico UNIDO con la información de la especialidad,
             * entonces eso se hace con un INNER JOIN
            */

            String sql = "SELECT * FROM medico\n" +
                    "INNER JOIN especialidad ON especialidad.id = medico.id_especialidad;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Obtenemos los resultados
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Medico objMedico = new Medico();

                // Creamos un objeto de especialidad
                Especialidad objEspecialidad = new Especialidad();

                objMedico.setId(objResult.getInt("medico.id"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("medico.apellidos"));
                objMedico.setIdEspecialidad(objResult.getInt("medico.id_especialidad"));

                objEspecialidad.setId(objResult.getInt("especialidad.id"));
                objEspecialidad.setNombre(objResult.getString("especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("especialidad.descripcion"));

                // Hay que guardar la especialidad dentro del medico
                // Entonces utilizamos sus respectivos métodos get y set que fueron creados en la entidad medico

                objMedico.setObjEspecialidad(objEspecialidad);

                listaMedicos.add(objMedico);

            }

        } catch (SQLException e){
            // El getMessage() es para dar detalles del error
            System.out.println("ERROR >>> " + e.getMessage());
        }

        // Cerramos la conexión
        ConfigDB.closeConnection();


        return listaMedicos;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrimos la conexión, como no es una clase con métodos estáticos, no se coloca new
        Connection objConnection = ConfigDB.openConnection();

        //2. Casteamos el objeto que nos llega como parámetro, es decir, convertimos obj a un tipo de dato Medico
        Medico objMedico = (Medico) obj;

        //3.  Variable que obtiene el estado de la consulta en tiempo real, si se está actualizado o no el registro
        boolean isUpdated = false;

        try {
            //4. Escribimos la sentencia SQL para eliminar
            String sql = "UPDATE medico SET nombre=?, apellidos=?, id_especialidad=?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor a los ? ? ?
            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getIdEspecialidad());

            //7. Ejecutamos el query
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro del médico actualizado correctamente");
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

        //2. Casteamos el objeto que nos llega como parámetro, es decir, convertimos obj a un tipo de dato Medico
        Medico objMedico = (Medico) obj;

        //3. Variable que obtiene el estado de la consulta en tiempo real, si se está eliminando o no
        boolean isDeleted = false;

        try {
            //4. Escribimos la sentencia SQL para eliminar
            String sql = "DELETE FROM medico WHERE id = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor al ?
            objPrepare.setInt(1,objMedico.getId());

            // 7.
            /* Ejecutamos para saber cuantas filas afectó, por eso ejecutamos el método executeUpdate()
            * que nos devuelve cuantas filas se afectaron en la consulta
            */
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Registro del médico eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //8. Cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
