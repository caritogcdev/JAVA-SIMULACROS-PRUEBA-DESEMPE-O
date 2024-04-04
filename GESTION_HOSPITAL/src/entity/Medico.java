package entity;

public class Medico {
    private int id;
    private String nombre;
    private String apellidos;
    private int id_esp;

    public Medico() {
    }

    public Medico(int id, String nombre, String apellidos, int id_esp) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id_esp = id_esp;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId_esp() {
        return id_esp;
    }

    public void setId_esp(int id_esp) {
        this.id_esp = id_esp;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", id_esp=" + id_esp +
                '}';
    }
}
