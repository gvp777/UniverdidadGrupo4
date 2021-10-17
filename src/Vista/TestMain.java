package Vista;

import Control.AlumnoData;
import Control.ConectarBD;
import Control.MateriaData;
import Modelo.Alumno;
import Modelo.Materia;

import java.sql.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;



public class TestMain {

 
    public static void main(String[] args) {
        
        String sentenciaSql;
        
        ConectarBD conexion = new ConectarBD();
        
        
        //**********************************************************************
     
        //--- BORRAMOS LOS ALUMNOS ---------------------------------------------
        
        sentenciaSql = "DELETE "                                                //<---Borramos los datos de la tabla para poder cargar prbar los metodos 
                     + "FROM alumno";

        try {
            
            PreparedStatement prepStatem = conexion.getConexion().prepareStatement(sentenciaSql);
            prepStatem.executeUpdate();
            prepStatem.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }

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
     
        
        
        //---Buscar Alumno -----------------------------------------------------
        
            Alumno alumno = alumnoData.buscarAlumno(sosaLuis.getId());          //<--- Perdimos el id porque la BD coloca el id desde el ultimo que borro
        
            System.out.println("\nBuscar Alumno " + sosaLuis.getId() + ": "                              
                                + alumno.getApellido() + " " 
                                + alumno.getNombre()); 
            
        
        //---Listar Alumno -----------------------------------------------------
       
            System.out.println("\nListar Alumnos: " + alumnoData.listarAlumnos());  //<--- trea el toString de la clase
        
       
        //--- Actualizar Alumno ------------------------------------------------
        
            sosaLuis = alumnoData.buscarAlumno(DonovanDaniel.getId());

            sosaLuis.setNombre("Luis (EL Luigui)");

            if (alumnoData.actualizarAlumno(sosaLuis)){

                JOptionPane.showMessageDialog(null," El Alumno fue actualizado Satisfactorioamente!");

            }
            
        //---Borrar Alumno -----------------------------------------------------
      
         
            if ( alumnoData.borrarAlumno(DonovanDaniel.getId())){

                JOptionPane.showMessageDialog(null,"El Alumno fue dado de baja Satisfactorioamente!");

            }
           
        
        //**********************************************************************
        
            
        //--- BORRAMOS LAS MATERIAS --------------------------------------------
        
        sentenciaSql = "DELETE "                                                //<---Borramos los datos de la tabla para poder cargar prbar los metodos 
                     + "FROM materia";
        
        try {
            
            PreparedStatement prepStatem = conexion.getConexionActiva().prepareStatement(sentenciaSql);
            prepStatem.executeUpdate();
            prepStatem.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //--- TESTEAMOS MATERIA DATA -------------------------------------------
        
        MateriaData materiaData = new MateriaData(conexion);
        
        Materia laboratorio1 = new Materia("Laboratorio",1,true);
        Materia laboratorio2 = new Materia("Laboratorio",2,true);
        Materia estructura = new Materia("Estrucutra de Datos",1,true);
        Materia matematicas1 = new Materia("Matematicas I",1,true);
        Materia web1 = new Materia("Web I",1,true);
       
        //---Guardar Materia----------------------------------------------------
                
            materiaData.guardarMateria(laboratorio1);
            materiaData.guardarMateria(laboratorio2);
            materiaData.guardarMateria(estructura);
            materiaData.guardarMateria(matematicas1);
           
            
            if (materiaData.guardarMateria(web1)){
            
                JOptionPane.showMessageDialog(null," La materia fue guardada Satisfactorioamente!");
            
            }
            
        
       
        //---Buscar materia-----------------------------------------------------
              
             System.out.println("\n\nBuscar materia " + matematicas1.getId() + ": "              //<--- Perdimos el id porque la BD coloca el id desde el ultimo que borro
                     + materiaData.buscarMateria(matematicas1.getId()).getNombre() 
                     + " de " + materiaData.buscarMateria(matematicas1.getId()).getAnio()+ " Año"); 
       
        
        //---Listar materia-----------------------------------------------------
       
            System.out.println("\nMaterias Listadas: " + materiaData.listarMateria());   //<--- trea el toString de la clase
       
         
        //---Actualizar materia-------------------------------------------------
      
            Materia matematicas1Materia = materiaData.buscarMateria(matematicas1.getId());

            matematicas1Materia.setNombre("Matematic");

            materiaData.actualizarMateria(matematicas1Materia);
        
        
        //---Borrar materia-----------------------------------------------------
       
            
            
            if ( materiaData.borrarMateria(matematicas1.getId())){

                JOptionPane.showMessageDialog(null,"La materia fue dada de baja Satisfactorioamente!");

            }
        
        //----------------------------------------------------------------------    
    
    }
    
}
