package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Esta clase se encargará de abrir y cerrar la conexión con la base de datos
public class ConfigDB {

    //Este atributo tendrá el estado de la conexión
    public static Connection objConnection = null;

    //Método para conectar la DB
    public static Connection openConnection(){

        try {
            // importamos el driver que acabamos de llamar

            Class.forName("com.mysql.cj.jdbc.Driver");

            // CONEXIÓN A LA DB
            String url = "jdbc:mysql://bbkmfblau3z71vu1t5f7-mysql.services.clever-cloud.com/bbkmfblau3z71vu1t5f7";
            String user = "umws1c9atctpbqee";
            String password = "719Hj5v2WnBmTK9zx8cg";

            // Establecer la conexión
            // Lo casteamos porque queremos que lo que devuelva getConnection sea de tipo Connection y por eso lo ponemos entre paréntesis
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Me conecté perfectamente!!!");

        } catch (ClassNotFoundException error){
            System.out.println("ERROR >> Driver no Instalado " + error.getMessage());
        } catch (SQLException error) {
            System.out.println("ERROR >> error al conectar con la base de datos" + error.getMessage());
        }

        return objConnection;
    }

    //Método para finalizar (cerrar) una conexión para disminuir recursos de infraestructura y por tanto hace más seguro el pull de conexiones
    public static void closeConnection(){
        try {
            //Si hay una conexión activa entonces la cierro
            if (objConnection != null) objConnection.close();
            System.out.println("Se finalizó la conexión con éxito");
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
