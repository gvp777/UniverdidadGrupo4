package Modelo;

import java.util.Objects;


public class Materia {
    
    //---ATRIBUTOS--------------------------------------------------------------
    private int id = -1;   
    private String nombre;
    private int anio;
    private boolean activo;

    //---CONSTRUTCTORES---------------------------------------------------------
    
    public Materia() {
    
    }
    
    public Materia(String nombre, int anio, boolean activo) {
        this.nombre = nombre;
        this.anio = anio;
        this.activo = activo;
    }

    public Materia(int id, String nombre, int anio, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.anio = anio;
        this.activo = activo;
    }
    
    
    //---GETTERS Y SETTERS------------------------------------------------------
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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    //---TOSTRING-HASHCODE-EQUALS-----------------------------------------------
    @Override
    public String toString() {
        
       return id + ", " + nombre + ", " + anio;
                
       /* 
        return "\n\tMateria{" + "id=" + id + 
                ", nombre=" + nombre + 
                ", anio=" + anio + 
                ", activo=" + activo + '}';
        */        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.nombre);
        hash = 71 * hash + this.anio;
        hash = 71 * hash + (this.activo ? 1 : 0);
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
        final Materia other = (Materia) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.anio != other.anio) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    //--------------------------------------------------------------------------
    
    
}
