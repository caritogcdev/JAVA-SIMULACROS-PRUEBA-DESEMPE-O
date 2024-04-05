package model;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import database.CRUD;
import database.ConfigDB;
import entity.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            }


        } catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }


        return null;
    }

    @Override
    public List<Object> findAll() {
        return null;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
