package entity;

/*
 * Esta entidad tiene doble inner join
 * */
public class Reservacion {
    private int id;
    private String fechaReservacion;
    private String asiento;
    private int idPasajero;
    private int idVuelo;

    // Inyectar un objeto dentro de otra clase
    // es decir, hacemos inyección de dependencias
    private Pasajero objPasajero;
    private Vuelo objVuelo;

    public Reservacion() {
    }

    public Reservacion(String fechaReservacion, String asiento, int idPasajero, int idVuelo, Pasajero objPasajero, Vuelo objVuelo) {
        this.fechaReservacion = fechaReservacion;
        this.asiento = asiento;
        this.idPasajero = idPasajero;
        this.idVuelo = idVuelo;
        this.objPasajero = objPasajero;
        this.objVuelo = objVuelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(String fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Pasajero getObjPasajero() {
        return objPasajero;
    }

    public void setObjPasajero(Pasajero objPasajero) {
        this.objPasajero = objPasajero;
    }

    public Vuelo getObjVuelo() {
        return objVuelo;
    }

    public void setObjVuelo(Vuelo objVuelo) {
        this.objVuelo = objVuelo;
    }

    @Override
    public String toString() {
        return "Reservacion{" +
                "fechaReservacion='" + fechaReservacion + '\'' +
                ", asiento='" + asiento + '\'' +
                ", Pasajero=" + objPasajero.getNombre() +
                ", Vuelo=" + objVuelo.getDestino() +
                '}';
    }
}
