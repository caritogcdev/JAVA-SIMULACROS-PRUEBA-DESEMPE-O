import database.ConfigDB;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hola desde reservación de vuelos aerolínea");
        // Probamos la conexión a la DB
        //Podemos llamar a la clase ConfigDB directamente porque en este caso sus métodos y su atributo único es estático
        ConfigDB.openConnection();
        ConfigDB.closeConnection();

    }
}