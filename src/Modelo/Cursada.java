package Modelo;

import java.util.Objects;


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

    @Override
    public String toString() {
        return "Cursada{" + "idCursada=" + idCursada + ", materia=" + materia + ", alumno=" + alumno + ", nota=" + nota + ", activo=" + activo + '}';
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.idCursada;
        hash = 97 * hash + Objects.hashCode(this.materia);
        hash = 97 * hash + Objects.hashCode(this.alumno);
        hash = 97 * hash + Float.floatToIntBits(this.nota);
        hash = 97 * hash + (this.activo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cursada other = (Cursada) obj;
        if (this.idCursada != other.idCursada) {
            return false;
        }
        if (Float.floatToIntBits(this.nota) != Float.floatToIntBits(other.nota)) {
            return false;
        }
        if (!Objects.equals(this.materia, other.materia)) {
            return false;
        }
        if (!Objects.equals(this.alumno, other.alumno)) {
            return false;
        }
        return true;
    }
    
    
    
}