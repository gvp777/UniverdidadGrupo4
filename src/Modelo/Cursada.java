package Modelo;


public class Cursada {


    private int idCursada;

    private Materia materia;
    private Alumno alumno;
    private float nota;
    private boolean activo;
    
    //contructores 
    public Cursada() {
    }

    public Cursada(Materia materia, Alumno alumno, float nota, boolean activo) {
        this.materia = materia;
        this.alumno = alumno;
        this.nota = nota;
        this.activo = activo;
    }

    public Cursada(int idCursada, Materia materia, Alumno alumno, float nota, boolean activo) {
        this.idCursada = idCursada;
        this.materia = materia;
        this.alumno = alumno;
        this.nota = nota;
        this.activo = activo;
    }
    //----------------------------------------------------------------------------
    //Get y Set.
    public int getIdCursada() {
        return idCursada;
    }

    public void setIdCursada(int idCursada) {
        this.idCursada = idCursada;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public float getNota() {
        return nota;
    }
    public void setNota(float nota) {
        this.nota = nota;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}