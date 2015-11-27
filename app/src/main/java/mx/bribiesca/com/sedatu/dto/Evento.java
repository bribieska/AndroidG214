package mx.bribiesca.com.sedatu.dto;

/**
 * Created by Bribiesca on 17/11/15.
 */

public class Evento {
    private int idEvento;
    private String fechaInicio;
    private String nombre;
    private int img;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", nombre='" + nombre + '\'' +
                ", img=" + img +
                '}';
    }
}
