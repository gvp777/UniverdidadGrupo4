package Modelo;


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;


public class Alumno {
    private int id = -1;                                                        //<--- Saez agrega este atributo y lo inicializa en -1 (corrsponde a la CLAVE PRIMARIA)
    private String nombre;                                                      //se parsea a VARCHAR
    private String apellido;                                                    //se parsea a VARCHAR
    private LocalDate fechaNac;                                                      // se parsea a DATETIME
    private int legajo;     
    private boolean activo;                                                     //se parsea a TINYINT   (ponemos boolean y la BD sola dice que es un TINYINT
    
   
    
    //---CONTRUCTOR-------------------------------------------------------------

    // 3 construcotres en total
    
    public Alumno(String apellido, String nombre, LocalDate fechaNac, int legajo, boolean activo) {
        this.id = -1;                                                           //<--- Saez agrega este atributo como es clave autoincrementan en la BD no la poasa por parametro
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.legajo = legajo;
        this.activo = activo;
    }

    


    
    public Alumno(){                                                            //<---2º contructor    
    
        this.id = -1;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    //--------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac + ", legajo=" + legajo + ", activo=" + activo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.nombre);
        hash = 89 * hash + Objects.hashCode(this.apellido);
        hash = 89 * hash + Objects.hashCode(this.fechaNac);
        hash = 89 * hash + this.legajo;
        hash = 89 * hash + (this.activo ? 1 : 0);
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
        final Alumno other = (Alumno) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.legajo != other.legajo) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.fechaNac, other.fechaNac)) {
            return false;
        }
        return true;
    }
    
    
}
