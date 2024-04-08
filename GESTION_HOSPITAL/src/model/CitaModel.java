package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cita;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Cita
        Cita objCita = (Cita) obj;

        try {
            //3. Escribimos la sentencia SQL para crear una cita
            String sql = "INSERT INTO cita (fecha_cita, hora_cita, motivo, id_paciente, id_medico) VALUES (?,?,?,?,?);";

            //4. Preparamos el statement, y le pasamos el sql como parámetro y le decimos que nos retorne las llaves generadas por la misma base de datos
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Le damos valor a los ? ? ? ? ?
            objPrepare.setDate(1, Date.valueOf(objCita.getFechaCita()));
            objPrepare.setTime(2, Time.valueOf(objCita.getHoraCita()));
            objPrepare.setString(3, objCita.getMotivo());
            objPrepare.setInt(4, objCita.getIdPaciente());
            objPrepare.setInt(5, objCita.getIdMedico());

            //6. Ejecutamos el query
            objPrepare.execute();

            //7. Resultado del query
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // Mientras haya un resultado
            while (objResult.next()){
                /*
                 * Vamos a guardar en el objCita el id que se generó
                 * en este caso lo sacamos del objResult.getInt con la columna o el índice 1
                 */

                objCita.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Cita creada correctamente");

        }catch (SQLException error){
            System.out.println("ERROR >>> " + error.getMessage());
        }

        //8. Cerramos la conexión
        ConfigDB.closeConnection();

        return objCita;
    }

    @Override
    public List<Object> findAll() {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos una lista de objetos
        List<Object> listaCitas = new ArrayList<>();

        try {
            //3. Escribimos la sentencia SQL para seleccionar las citas
            String sql = "SELECT * FROM cita\n" +
                    "INNER JOIN paciente ON paciente.id = cita.id_paciente\n" +
                    "INNER JOIN medico ON medico.id = cita.id_medico;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Vamos a ejecutar el query con executeQuery() para que nos devuelva todos los registros que están en la base de datos
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                //6. Crear una nueva instancia de Cita
                Cita objCita = new Cita();

                //7. Creamos una instancia del objeto Paciente
                Paciente objPaciente = new Paciente();

                //8.  Creamos una instancia del objeto Medico
                Medico objMedico = new Medico();

                //9. Y lo vamos a llenar con los siguientes datos
                objCita.setId(objResult.getInt("cita.id"));
                objCita.setFechaCita(objResult.getString("cita.fecha_cita"));
                objCita.setHoraCita(objResult.getString("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));
                objCita.setIdPaciente(objResult.getInt("cita.id_paciente"));
                objCita.setIdMedico(objResult.getInt("cita.id_medico"));


                //10. En el ToString yo solo quiero tanto el nombre del médico como el nombre del paciente
                objPaciente.setNombre(objResult.getString("paciente.nombre"));
                objMedico.setNombre(objResult.getString("medico.nombre"));

                //11. Luego nos toca guardar tanto objPaciente y objMedico
                objCita.setObjPaciente(objPaciente);
                objCita.setObjMedico(objMedico);

                //12. Luego guardamos en la lista a cita que ya tiene al paciente y al médico
                listaCitas.add(objCita);
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //13. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return listaCitas;
    }

    @Override
    public boolean update(Object obj) {
         //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Cita
        Cita objCita = (Cita) obj;

        //3. Declaramos la variable que nos va a indicar si una cita fue actualizada o no
        boolean isUpdated = false;

       try {
           //4. Escribimos la sentencia SQL para eliminar una cita
           String sql = "UPDATE cita SET fecha_cita = ?, hora_cita = ?, motivo = ?, id_paciente = ?, id_medico = ? WHERE id = ? ;";

           //5. Preparamos el statement que viene de la conexión
           PreparedStatement objPrepare = objConnection.prepareStatement(sql);

           //6. Le damos valor a los ? ? ? ? ? ?
           objPrepare.setDate(1, Date.valueOf(objCita.getFechaCita()));
           objPrepare.setTime(2, Time.valueOf(objCita.getHoraCita()));
           objPrepare.setString(3, objCita.getMotivo());
           objPrepare.setInt(4, objCita.getIdPaciente());
           objPrepare.setInt(5, objCita.getIdMedico());
           objPrepare.setInt(6, objCita.getId());

           //7. Ejecutamos verificando cuantas filas fueron afectadas
           int totalRowAffected = objPrepare.executeUpdate();

           if (totalRowAffected > 0){
               isUpdated = true;
               JOptionPane.showMessageDialog(null, "Cita actualizada correctamente");
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

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Cita
        Cita objCita = (Cita) obj;

        //3. Declaramos la variable que nos va a indicar si una cita fue eliminada o no
        boolean isDeleted = false;

        try {
            //4. Escribimos la sentencia SQL para eliminar una cita
            String sql = "DELETE FROM cita WHERE id = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor al ?
            objPrepare.setInt(1, objCita.getId());

            //7. Ejecutamos verificando cuantas filas fueron afectadas
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Cita eliminada correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //8. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
