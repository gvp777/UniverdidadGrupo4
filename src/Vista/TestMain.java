

/*

    Trabajo Practico 1º Parte
    
    Alumnos:
    Franco Maximiliano Ybañez
    Lucas Zarate
    Guillermo Visco Ponticelli

NOTA:

    -   El trabajabo lo realizamos los 3 integrantes del grupo 4 conjuntamente mediante 
        reuniones fijadas en el Zoom. 

        Tubimos un problema de pisada de clase en github entonces, por esta etapa, decidimos
        trabajarlo d eesta manera pero conocemos el github desde netbeans perfectamente.

        Es por esto que esta subida por uno de los integrantes y no comititiada por 
        los colaboradores. Haciamos cada cual su clase y la pasabamos por mail o chat
    
    -   Preguntamos y cambiamos los metodos void a boolean para porder disparar un cartel 
        desde el main a menos que se trate de algun error capturado.
    
    -   Borramos las tablas desde MySql para probar desde cero los metodos.
        Al cambiar las claves primarias que devuelve, pedimos el id de cada objeto
    
        Sentencias para Mysql: 
    
        DELETE FROM `cursada`;
        DELETE FROM `materia`;
        DELETE FROM `alumno`


*/


package Vista;

import Control.AlumnoData;
import Control.ConectarBD;
import Control.CursadaData;
import Control.MateriaData;
import Modelo.Alumno;
import Modelo.Cursada;
import Modelo.Materia;

import java.sql.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;



public class TestMain {

 
    public static void main(String[] args) {
        
        String sentenciaSql;
        
        ConectarBD conexion = new ConectarBD();
       
            
        //**********************************************************************
                
        //--- TESTEMOS ALUMNO DATA ---------------------------------------------
                
        AlumnoData alumnoData = new AlumnoData(conexion);
        
        Alumno diaszCarlos = new Alumno("Diaz","Carlos",LocalDate.of(1975,2,25),1003,true); 
        Alumno luceroEnrique = new Alumno("Lucero","Enrique",LocalDate.of(1985,1,5),1004,true); 
        Alumno sosaLuis = new Alumno("Sosa","Luis",LocalDate.of(1995,3,10),1005,true); 
        Alumno DonovanDaniel = new Alumno("Donovan","Daniel",LocalDate.of(1977,7,20),1006,true); 
    
        
        //---Guargar Alumno ----------------------------------------------------
       
        alumnoData.guardarAlumno(diaszCarlos);
        alumnoData.guardarAlumno(luceroEnrique);
        alumnoData.guardarAlumno(sosaLuis);
        
        if (alumnoData.guardarAlumno(DonovanDaniel)){
        
            JOptionPane.showMessageDialog(null," El Alumno fue guardado Satisfactorioamente!");
       
        }
     
        
        
        //---BUSCAR ALUMNO -----------------------------------------------------
        
            Alumno alumno = alumnoData.buscarAlumno(sosaLuis.getId());          //<--- Perdimos el id porque la BD coloca el id desde el ultimo que borro
        
            System.out.println("\nBuscar Alumno " + sosaLuis.getId() + ": "                              
                                + alumno.getApellido() + " " 
                                + alumno.getNombre()); 
            
        
        //---LISTAR ALUMNO -----------------------------------------------------
       
            System.out.println("\nListar Alumnos: " + alumnoData.listarAlumnos());  //<--- trea el toString de la clase
        
       
        //--- ACTUALIZAR ALUMNO ------------------------------------------------
        
            sosaLuis = alumnoData.buscarAlumno(DonovanDaniel.getId());

            sosaLuis.setNombre("Luis (EL Luigui)");

            if (alumnoData.actualizarAlumno(sosaLuis)){

                JOptionPane.showMessageDialog(null," El Alumno fue actualizado Satisfactorioamente!");

            }
            
        //---BORRA ALUMNO ------------------------------------------------------
               
            if ( alumnoData.borrarAlumno(DonovanDaniel.getId())){

                JOptionPane.showMessageDialog(null,"El Alumno fue dado de baja Satisfactorioamente!");

            }
           
        //**********************************************************************
        
        //--- TESTEAMOS MATERIA DATA -------------------------------------------
        
        MateriaData materiaData = new MateriaData(conexion);
        
        Materia laboratorio1 = new Materia("Laboratorio",1,true);
        Materia laboratorio2 = new Materia("Laboratorio",2,true);
        Materia estructura = new Materia("Estrucutra de Datos",1,true);
        Materia matematicas1 = new Materia("Matematicas I",1,true);
        Materia web1 = new Materia("Web I",1,true);
       
        //---GUARDAR MATERIA----------------------------------------------------
                
            materiaData.guardarMateria(laboratorio1);
            materiaData.guardarMateria(laboratorio2);
            materiaData.guardarMateria(estructura);
            materiaData.guardarMateria(matematicas1);
           
            
            if (materiaData.guardarMateria(web1)){
            
                JOptionPane.showMessageDialog(null," La materia fue guardada Satisfactorioamente!");
            
            }
            
        
       
        //---BUSCAR MATERIA-----------------------------------------------------
              
             System.out.println("\n\nBuscar materia " + matematicas1.getId() + ": "              //<--- Perdimos el id porque la BD coloca el id desde el ultimo que borro
                     + materiaData.buscarMateria(matematicas1.getId()).getNombre() 
                     + " de " + materiaData.buscarMateria(matematicas1.getId()).getAnio()+ " Año"); 
       
        
        //---LISTAR MATERIA-----------------------------------------------------
       
            System.out.println("\nMaterias Listadas: " + materiaData.listarMateria());   //<--- trea el toString de la clase
       
         
        //---Actualizar materia-------------------------------------------------
      
            Materia matematicas1Materia = materiaData.buscarMateria(matematicas1.getId());

            matematicas1Materia.setNombre("Matematic");

            materiaData.actualizarMateria(matematicas1Materia);
        
        
        //---BORRAR MATERIA-----------------------------------------------------
       
            
            
            if ( materiaData.borrarMateria(matematicas1.getId())){

                JOptionPane.showMessageDialog(null,"La materia fue dada de baja Satisfactorioamente!");

            }
        
           
        //**********************************************************************
                
        
            

        
        //--- TESTEAMOS CURDSADA DATA -------------------------------------------
        
        CursadaData cursadaData = new CursadaData(conexion);
        
        Cursada cursada1  = new Cursada(laboratorio2,DonovanDaniel,9,true);
        Cursada cursada2  = new Cursada(laboratorio2,diaszCarlos,7,true);
        Cursada cursada3  = new Cursada(estructura,luceroEnrique,8,true);
        Cursada cursada4  = new Cursada(matematicas1,sosaLuis,9,true);
        Cursada cursada5  = new Cursada(laboratorio1,diaszCarlos,9,true);
   
        //**********************************************************************
        
        //--- GUARDAR INCRIPCION -----------------------------------------------
            cursadaData.guardarIncripcion(cursada1);
            cursadaData.guardarIncripcion(cursada2);
            cursadaData.guardarIncripcion(cursada3);
            cursadaData.guardarIncripcion(cursada4);
         
  
           
            
            if (cursadaData.guardarIncripcion(cursada5)){
            
                JOptionPane.showMessageDialog(null," La Cuarsada fue guardada Satisfactorioamente!");
            
            }
        
        //--- BORRAR INCRIPCION ------------------------------------------------
            
            if ( cursadaData.borrarIncripcion(laboratorio2.getId(), diaszCarlos.getId() )){

                JOptionPane.showMessageDialog(null,"La Inscripcion fue dada de baja Satisfactorioamente!");

            }
       
        //--- OBTENER INCRIPCIONES ---------------------------------------------
        
        
           ArrayList<Cursada> a = cursadaData.obtenerIncripcion();
                    
          for (Cursada it :a){
          
              System.out.println("\nInscripcion: " + it.toString());
   
          }          
        
        //--- OBTENER MATERIAS INSCRIPTAS --------------------------------------
                
           ArrayList<Materia> m = cursadaData.obtenerMateriasIncriptas(sosaLuis.getId());
                    
          for (Materia it :m){
          
              System.out.println("\nMateria Inscripta: " + it.toString());
   
          }          
        
        //--- OBTENER MATERIAS NO INSCRIPTAS -----------------------------------
                
           ArrayList<Materia> mn = cursadaData.obtenerMateriasIncriptas(sosaLuis.getId());
                    
          for (Materia it :mn){
          
              System.out.println("\nMateria No Inscripta: " + it.toString());
   
          }  
        
        //--- ACTUALIZAR NOTAS -------------------------------------------------
    
        
        if (cursadaData.actualizarNota (luceroEnrique.getId(),estructura.getId(),6)){
        
            JOptionPane.showMessageDialog(null,"La nota fue actualizada Satisfactorioamente!");
        
        //----------------------------------------------------------------------
        }
    }
    
}
