/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import com.mysql.jdbc.Statement;
import Control.ConectarBD;
import Modelo.Alumno;
import Modelo.Cursada;
import Modelo.Materia;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxi Ybañez
 */
public class CursadaData {
  
     private Connection conexion= null;   
     private AlumnoData alumData=null;
     private MateriaData mateData=null;

    public CursadaData(ConectarBD conexionParam) {
        this.conexion = conexionParam.getConexion();
        this.alumData= new AlumnoData(conexionParam);
        this.mateData= new MateriaData(conexionParam);
    }
    
    public boolean guardarIncripcion( Cursada cursada){
       boolean confirmacion=false; //
       String sentenciaSql = "INSERT INTO cursada`(`idMateria`,`idAlumno`, `nota`, `activo`)" + " VALUES (?,?,?,?)";                          
         
        try {
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS ); 
           
            prepStatem.setInt(1, cursada.getMateria().getId());                
            prepStatem.setInt(2, cursada.getAlumno().getId());                   
            prepStatem.setFloat(3, cursada.getNota()); 
            prepStatem.setBoolean(4, cursada.isActivo()); 
            
            prepStatem.executeUpdate();                                         
              ResultSet resultSet = prepStatem.getGeneratedKeys();                  
            
            if (resultSet.next()){
                cursada.setIdCursada(resultSet.getInt(1));  
                confirmacion=true;
               
            }
            prepStatem.close();                                                 
            
          } catch (SQLException ex) {
              confirmacion=false;
            JOptionPane.showMessageDialog(null," Error, no se pudo inscribir a la materia");
        }
      return confirmacion;
   } 
    
    
      public boolean borrarCursada( int idAlumno, int idMateria){
       boolean confirmacion=false; //
       
       String sentenciaSql = "UPDATE Cursada SET activo = false  WHERE Cursada.idAlumno = ? and Cursada.idMateria = ? " ;   
         
        try {
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql);
                     
            prepStatem.setInt(1, idAlumno);                   
            prepStatem.setInt(2, idMateria);               
            
            if(prepStatem.executeUpdate()>0){
               confirmacion=true;
            }  
                prepStatem.close();                                  
            
          } catch (SQLException ex) {
             confirmacion=false;
            JOptionPane.showMessageDialog(null," Error, en borrar cursada"+ ex);
        }
      return confirmacion;
   }  
    
     public List<Cursada> obtenerCursada(){
        ArrayList<Cursada> incriptos = new ArrayList<>(); 
        String sentenciaSql = "SELECT * FROM Cursada WHERE cursada.activo = true "; 
        
        try {
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql);
            ResultSet resultSet = prepStatem.executeQuery(); 
             while(resultSet.next()){                                       
                    Cursada cursadaobj = new Cursada();
                    cursadaobj.setIdCursada(resultSet.getInt("idCursada"));
                    Alumno a= alumData.buscarAlumno(resultSet.getInt("idAlumno"));
                    cursadaobj.setAlumno(a);
                    Materia m= mateData.buscarMateria(resultSet.getInt("idMateria"));
                    cursadaobj.setMateria(m);
                    cursadaobj.setNota(resultSet.getFloat("nota"));
                    cursadaobj.setActivo(resultSet.getBoolean("activo"));
                   
                    incriptos.add(cursadaobj);
             }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Error, en obtener cursadas"+ ex);
        }
        return incriptos;
   } 
     
      public List<Materia> obtenerMateriasIncriptas( int idAlumno){ //alumnos activos que hacen esa materia
        ArrayList<Materia> materiasIncriptas = new ArrayList<>(); // Lista para retornar con alumnos
        String sentenciaSql ="SELECT materia.idMateria, nombreMateria, anio, materia.activo\n"
                    + "FROM materia, cursada\n"
                    + "WHERE materia.idMateria = cursada.idMateria\n"
                    + "AND cursada.activo = true \n"
                    + "AND cursada.idAlumno = ?";  
        
        try {
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); 
           
            prepStatem.setInt(1, idAlumno);

            ResultSet rs = prepStatem.executeQuery();

            
            prepStatem.executeUpdate();                                         
            ResultSet resultSet = prepStatem.getGeneratedKeys();             
            while(resultSet.next()){
                Materia mate= new Materia();
                mate.setId(resultSet.getInt("idMateria"));
                mate.setNombre(resultSet.getString("nombreMateria"));
                mate.setAnio(resultSet.getInt("anio"));
                mate.setActivo(resultSet.getBoolean("materia.activo"));
                materiasIncriptas.add(mate);
                                           
            }
            
            prepStatem.close(); //<---Cerramos la conexion
            
          } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Error, obtener materias incriptas"+ ex);
        }
      return materiasIncriptas;
      }
      
      
      public List<Materia> obtenerMateriasNoIncriptas( int idAlumno){ 
        ArrayList<Materia> materiasNoIncriptas = new ArrayList<>(); 
        String sentenciaSql ="SELECT * \n"              
                    + "FROM materia"
                    + "WHERE idMateria NO IN(SELECT materia.idMateria, nombreMateria, anio, materia.activo\n"
                    + "FROM materia, cursada\n"
                    + "WHERE materia.idMateria = cursada.idMateria\n"
                    + "AND cursada.activo = true \n"
                    + "AND cursada.idAlumno = ?)";  
        
        try {
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); 
           
            prepStatem.setInt(1, idAlumno);

            ResultSet rs = prepStatem.executeQuery();

            
            prepStatem.executeUpdate();                                         
            ResultSet resultSet = prepStatem.getGeneratedKeys();             
            while(resultSet.next()){
                Materia mate= new Materia();
                mate.setId(resultSet.getInt("idMateria"));
                mate.setNombre(resultSet.getString("nombreMateria"));
                mate.setAnio(resultSet.getInt("anio"));
                mate.setActivo(resultSet.getBoolean("materia.activo"));
                materiasNoIncriptas.add(mate);
                                           
            }
            
            prepStatem.close();
          } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Error, en buscar materias no cursada"+ ex);
        }
      return materiasNoIncriptas;
      }
      
   
    public List<Alumno> listarAlumnos( int idMateria){
        ArrayList<Alumno> alumnosIncriptos = new ArrayList<>(); 
        String sentenciaSql = "SELECT alumno.idAlumno, alumno.apellido,alumno.nombre,alumno.fechaNac,alumno.legajo,alumno.activo \n" +
                                "FROM alumno, cursada\n" +
                                "WHERE alumno.idAlumno = cursada.idAlumno AND\n" +
                                "cursada.idMateria = ? AND alumno.activo = true  AND cursada.activo = true";                           
         try {
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql);
            prepStatem.setInt(1,idMateria);
            prepStatem.executeUpdate();                                         
            ResultSet resultSet = prepStatem.getGeneratedKeys();              
            while(resultSet.next()){
                Alumno alumnoObj= new Alumno();
                 alumnoObj.setId(resultSet.getInt("idAlumno"));
                    alumnoObj.setNombre(resultSet.getString("nombre"));
                    alumnoObj.setApellido(resultSet.getString("apellido"));
                    alumnoObj.setFechaNac(resultSet.getDate("fechaNac").toLocalDate());      
                    alumnoObj.setLegajo(resultSet.getInt("legajo"));
                    alumnoObj.setActivo(resultSet.getBoolean("activo"));
                
                    alumnosIncriptos.add(alumnoObj);            
                              
                }

            prepStatem.close();                                                 
            
          } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Error, en buscar alumno "+ ex);
        }
      return alumnosIncriptos;
   } 
   
    public boolean actualizarNota(int idAlumno, int idMateria, float nota){
         boolean confirmacion=false;
         String sentenciaSql = "UPDATE `cursada` SET nota = ? WHERE cursada.idAlumno = ? AND cursada.idMateria = ? AND cursada.activo = true";                           
         try {
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql);
            prepStatem.setFloat(1,nota);
            prepStatem.setInt(2,idAlumno);
            prepStatem.setInt(3,idMateria);
            
            if(prepStatem.executeUpdate()>0){
                confirmacion= true;
            } 
            prepStatem.close();                                                 
            
          } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Error, en actualizar nota "+ ex);
        }
         return confirmacion;
    }
   
}
