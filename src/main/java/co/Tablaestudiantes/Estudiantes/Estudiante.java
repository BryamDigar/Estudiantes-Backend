package co.Tablaestudiantes.Estudiantes;

import java.util.Objects;

public class Estudiante {

    private String nombre;
    private int id;
    private int semestre;
    private String facultad;
    private String programa;

    //Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }
    //Constructors

    public Estudiante(String nombre, int id, int semestre, String facultad, String programa) {
        this.nombre = nombre;
        this.id = id;
        this.semestre = semestre;
        this.facultad = facultad;
        this.programa = programa;
    }

    public Estudiante() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(facultad, that.facultad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultad);
    }
}
