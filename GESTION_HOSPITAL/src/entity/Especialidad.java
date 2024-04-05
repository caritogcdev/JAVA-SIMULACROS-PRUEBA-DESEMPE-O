package entity;

public class Especialidad {
    private int id;
    private String nombre;
    private String descripcion;

    public Especialidad() {
    }
    public Especialidad(String nombre, String descripcion) {
        /*
        * eliminamos el id del constructor porque quiero que solo me permita llenar el nombre
        * y descripción, ya que el id es autoincrementable
        */
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return
                " nombre: '" + nombre + '\'' +
                " descripcion: '" + descripcion + '\'';
    }
}