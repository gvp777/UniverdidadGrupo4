package com.clases;


import java.util.Date;


public class Alumno {
    private int id = -1;                                                         //<--- Saez agrega este atributo y lo inicializa en -1 (corrsponde a la CLAVE PRIMARIA)
    private String nombre;                                                      //se parsea a VARCHAR
    private String apellido;                                                    //se parsea a VARCHAR
    private Date fechaNac;                                                      // se parsea a DATETIME
    private int legajo;     
    private boolean activo;                                                     //se parsea a TINYINT   (ponemos boolean y la BD sola dice que es un TINYINT
    
    //Atributo? coleccion de materias que cursa/o curso?
    
    //---CONTRUCTOR-------------------------------------------------------------

    public Alumno(String apellido, String nombre, Date fechaNac, int legajo, boolean activo) {
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

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
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
    
    //agregar el toString                                                       //<---Saez agrega un toString en la clase
    
}
