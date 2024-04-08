package entity;

public class Vuelo {
    private int id;
    private String destino;
    private String fechaSalida;
    private String horaSalida;
    private int idAvion;

    /*
     * Cuando nosotros utilizamos una relación, en este caso vuelo tiene una
     * relación en la DB, nosotros vamos a querer traer toda la información unida,
     * es decir, que cuando nosotros por ejemplo nos traigamos al vuelo, no vamos
     * a querer solamente la información del vuelo que es destino, fechaSalida y horaSalida, sino
     * que también queremos que nos traiga toda la información del avión,
     * entonces tenemos que hacer un inner join.
     * ¿Pero como guardamos toda la información que viene del inner join en Java?
     * Tenemos que hacer una INYECCIÓN DE DEPENDENCIAS: es guardar un objeto o una
     * clase de tipo objeto dentro de otra clase private.
     * En este caso vamos a guardar un objeto de tipo Avion
     * */

    // Inyectar un objeto dentro de otra clase
    private Avion objAvion;

    public Vuelo() {
    }

    public Vuelo(String destino, String fechaSalida, String horaSalida, int idAvion, Avion objAvion) {
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.idAvion = idAvion;
        this.objAvion = objAvion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public Avion getObjAvion() {
        return objAvion;
    }

    public void setObjAvion(Avion objAvion) {
        this.objAvion = objAvion;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "destino='" + destino + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", avion=" + this.objAvion.getModelo() +
                '}';
    }
}
