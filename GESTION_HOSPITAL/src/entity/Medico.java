package entity;

public class Medico {
    private int id;
    private String nombre;
    private String apellidos;
    private int idEspecialidad;

    /*
    * Cuando nosotros utilizamos una relación, en este caso medico tiene una
    * relación en la DB, nosotros vamos a querer traer toda la información unida,
    * es decir, que cuando nosotros por ejemplo nos traigamos al médico, no vamos
    * a querer solamente la informacion del medico que es nombre y apellido, sino
    * que también queremos que nos traiga toda la información de la especialización,
    * entonces tenemos que hacer un inner join.
    * ¿Pero como guardamos toda la información que viene del inner join en Java?
    * tenemos que hacer una INYECCIÓN DE DEPENDENCIAS: es guardar un objeto o una
    * clase de tipo objeto dentro de otra clase private.
    * En este caso vamos a guardar un objeto de tipo Especialidad
    * */

    // Inyectar un objeto dentro de otra clase
    private Especialidad objEspecialidad;

    public Medico() {
    }

    // Añadimos el objEspecialidad al constructor
    public Medico(String nombre, String apellidos, int idEspecialidad, Especialidad objEspecialidad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.idEspecialidad = idEspecialidad;
        this.objEspecialidad = objEspecialidad;
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

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    // Generamos también los get y el set del objEspecialidad

    public Especialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(Especialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

    @Override
    public String toString() {
        return " nombre:'" + nombre + '\'' +
               ", apellidos:'" + apellidos + '\'' +
               "especialidad: " + this.objEspecialidad.getNombre();
    }
}
