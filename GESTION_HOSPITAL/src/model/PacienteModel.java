package model;

import database.CRUD;
import database.ConfigDB;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Paciente
        Paciente objPaciente = (Paciente) obj;

        try {
            //3. Escribimos la sentencia SQL para crear un paciente
            String sql = "INSERT INTO paciente (nombre, apellidos, fecha_nacimiento, documento_identidad) VALUES (?,?,?,?);";

            //4. Preparamos el statement, y le pasamos el sql como parámetro y le decimos que nos retorne las llaves generadas por la misma base de datos
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Le damos valor a los ? ? ? ?
            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            //Hay que enviarlo como DATE porque así está en MySQL, entonces hay que convertir el string a tipo date
            objPrepare.setDate(3, Date.valueOf(objPaciente.getFecha_nacimiento()));
            objPrepare.setString(4, objPaciente.getDocumento_identidad());

            //6. Ejecutamos el query
            objPrepare.execute();

            //7. Resultado del query
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // Mientras haya un resultado
            while (objResult.next()){
                /*
                * Vamos a guardar en el objPaciente el id que se generó
                * en este caso lo sacamos del objResult.getInt con la columna o el índice 1
                */

                objPaciente.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Paciente creado correctamente");

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //8. Cerramos la conexión
        ConfigDB.closeConnection();

        //9. Retornamos el paciente
        return objPaciente;
    }

    @Override
    public List<Object> findAll() {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos una lista de objetos
        List<Object> listaPacientes = new ArrayList<>();

        try {
            //3. Escribimos la sentencia SQL para seleccionar los pacientes
            String sql = "SELECT * FROM paciente;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Vamos a ejecutar el query con executeQuery() para que nos devuelva todos los registros que están en la base de datos
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                //6. Crear una nueva instancia de Paciente
                Paciente objPaciente = new Paciente();

                //7. Y lo vamos a llenar con los siguientes datos
                objPaciente.setId(objResult.getInt("id"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                // Acá la fecha si la podemos traer como un String
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                //8. Agregamos el paciente a la lista
                listaPacientes.add(objPaciente);
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //9. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return listaPacientes;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Paciente
        Paciente objPaciente = (Paciente) obj;

        //3. Declaramos la variable que nos va a indicar si un paciente fue eliminado o no
        boolean isUpdated = false;

        try {
            // 4. Escribirmos la sentencia SQL
            String sql = "UPDATE paciente SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, documento_identidad = ? WHERE id = ?;";

            //5. Preparamos el statement el cual siempre proviene de la conexión y siempre hay que pasarle la consulta sql como parámetro
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor a los ? ? ? ? ?
            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            objPrepare.setDate(3, Date.valueOf(objPaciente.getFecha_nacimiento()));
            objPrepare.setString(4, objPaciente.getDocumento_identidad());
            objPrepare.setInt(5, objPaciente.getId());

            //7. Ejecutamos el sql para saber cuantas filas fueron afectadas con executeUpdate()
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Paciente actualizado correctamente");
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

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Paciente
        Paciente objPaciente = (Paciente) obj;

        //3. Declaramos la variable que nos va a indicar si un paciente fue eliminado o no
        boolean isDeleted = false;

        try {
            //4. Escribimos la sentencia sql
            String sql = "DELETE FROM paciente WHERE id = ?;";

            //5. Preparamos el statement, el cual siempre proviene de la conexión
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Damos valor al ?, se coloca la posición y le pasamos el id del objeto paciente
            objPrepare.setInt(1, objPaciente.getId());

            //7. Ejecutamos el sql para saber cuantas filas fueron afectadas con executeUpdate()
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR >>>" + e.getMessage());
        }

        //8. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
