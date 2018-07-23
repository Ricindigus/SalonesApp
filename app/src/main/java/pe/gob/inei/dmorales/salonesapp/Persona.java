package pe.gob.inei.dmorales.salonesapp;

public class Persona {
    private String dni;
    private String nombre;
    private int salon;

    public Persona(String dni, String nombre, int salon) {
        this.dni = dni;
        this.nombre = nombre;
        this.salon = salon;
    }

    public Persona() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalon() {
        return salon;
    }

    public void setSalon(int salon) {
        this.salon = salon;
    }
}
