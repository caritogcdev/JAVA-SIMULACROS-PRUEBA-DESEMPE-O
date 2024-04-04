package entity;

import java.sql.Date;
import java.sql.Time;

public class Cita {
    private int id;
    private int id_paciente;
    private int id_medico;
    private Date fecha_cita;
    private Time hora_cita;
    private String motivo;

    public Cita() {
    }

    public Cita(int id, int id_paciente, int id_medico, Date fecha_cita, Time hora_cita, String motivo) {
        this.id = id;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.fecha_cita = fecha_cita;
        this.hora_cita = hora_cita;
        this.motivo = motivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public Time getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(Time hora_cita) {
        this.hora_cita = hora_cita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", id_paciente=" + id_paciente +
                ", id_medico=" + id_medico +
                ", fecha_cita=" + fecha_cita +
                ", hora_cita=" + hora_cita +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}

