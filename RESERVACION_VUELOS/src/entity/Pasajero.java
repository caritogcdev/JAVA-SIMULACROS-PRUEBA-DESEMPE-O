package entity;

public class Pasajero {
    private int id;
    private String nombre;
    private String apellido;
    private String documento_identidad;

    public Pasajero() {
    }

    public Pasajero(String nombre, String apellido, String documento_identidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento_identidad = documento_identidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(String documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documento_identidad='" + documento_identidad + '\'' +
                '}';
    }
}
