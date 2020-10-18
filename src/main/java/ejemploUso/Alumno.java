package ejemploUso;

import java.io.Serializable;

public class Alumno implements Serializable{
    private int ID;
    private String nombre;
    private int edad;

    public Alumno() {
    }

    public Alumno(int ID, String nombre, int edad) {
        this.ID = ID;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Alumno{" + "ID=" + ID + ", nombre=" + nombre + ", edad=" + edad + '}';
    }
    
}
