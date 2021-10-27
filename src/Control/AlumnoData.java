
package Control;

import Modelo.Alumno;

import com.mysql.jdbc.Statement;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;


public class AlumnoData {
    
    //---ATRIBUTO---------------------------------------------------------------
    private Connection conexion = null;                                         

    //---CONSTRUCTOR------------------------------------------------------------
    public AlumnoData(ConectarBD conexionParam) {
        
        this.conexion = conexionParam.getConexion();
    }
    
    //****************************** METODOS ***********************************
    
    //----METODO - GUARDAR ALUMNO ----------------------------------------------
    public boolean guardarAlumno(Alumno alumnoParam){
       
        boolean confirmacion = false ;                                          //<---Varialble creada por nosotros para poder devolver la confirmacion del metodo        
        
        String sentenciaSql = "INSERT INTO alumno(apellido,nombre,fechaNac,legajo,activo)"
                                      + " VALUES (?,?,?,?,?)";                            
                     
                    
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS ); //<---Preparamos el Statement pasando la sentencia y 'el segundo parametro' es para que nos retorne el id que le dio despues de insertar un nuevo registro
            
            
            //---Reunimos la informacion para pasarle a cada singo de pregunta (?) de la sentenciaSql
            
            prepStatem.setString(1, alumnoParam.getApellido());                 //<--- El primer argumento, es la posicion 1(uno) de los signos de pregunta (?) en la sentenciaSql
            prepStatem.setString(2, alumnoParam.getNombre());                   
            prepStatem.setDate(3, Date.valueOf( alumnoParam.getFechaNac()));    
            prepStatem.setInt(4, alumnoParam.getLegajo()); 
            prepStatem.setBoolean(5, alumnoParam.isActivo()); 
      
            prepStatem.executeUpdate();                                         //<--- Por ultimo, ejecutamos el Statement  IMPORTANTE:No recibe nada por parametro, es una confusion comun en los alumnos (explica Saez)
            
            ResultSet resultSet = prepStatem.getGeneratedKeys();                //<--- Cuando termino de ejecutar en la BD la Sentencia,  le pido que me devuelva las llaves que genero  y se las asigno a una variable resultSet  (Osea, la respuesta del Set) ResulSet es UN CONJUNTO DE RESULTADOS, Esto lo quiero para refrescar la caja de texto (si lo necesitara)
            
            if (resultSet.next()){
                alumnoParam.setId(resultSet.getInt(1));                         
                confirmacion = true;
            }
         
            prepStatem.close();                                                 //<---Cerramos el Statement
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "El alumno no puedo guardar" + ex); //<--- adicionamos el error solo por el practico
   
        }
                    
       return confirmacion;                                                     //<---Retornamos el estado del metodo             
    }
    
    //----METODO - BUSCAR ALUMNO ----------------------------------------------
    public Alumno buscarAlumno(int idParam){
       
          Alumno alumnoObj = null;
          String sentenciaSql = "SELECT * "
                              + "FROM alumno "
                              + "WHERE idAlumno = ? "
                              + "AND activo = true ";
          
          try {
              
                PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); 
                prepStatem.setInt(1, idParam);

                ResultSet resultSet = prepStatem.executeQuery();  

                while(resultSet.next()){                                        //<--- Mientras haya algo ene l resulSet, se va seteando de a uno 

                    alumnoObj = new Alumno();

                    alumnoObj.setId(resultSet.getInt("idAlumno"));
                    alumnoObj.setNombre(resultSet.getString("nombre"));
                    alumnoObj.setApellido(resultSet.getString("apellido"));
                    alumnoObj.setFechaNac(resultSet.getDate("fechaNac").toLocalDate());   
                    alumnoObj.setLegajo(resultSet.getInt("legajo"));
                    alumnoObj.setActivo(resultSet.getBoolean("activo"));
                }    
                
                prepStatem.close();                                             //<---Cerramos el Statement 
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al buscar alumno!" + ex); //<--- adicionamos el error solo por el practico
            }

        return alumnoObj;
    }
     //----METODO - BUSCAR ALUMNO por legajo ----------------------------------------------
    public Alumno buscarAlumnoLegajo(int legajo){
       
          Alumno alumnoObj = null;
          String sentenciaSql = "SELECT * "
                              + "FROM alumno "
                              + "WHERE legajo = ? ";
          
          try {
              
                PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); 
                prepStatem.setInt(1, legajo);

                ResultSet resultSet = prepStatem.executeQuery();  

                while(resultSet.next()){                                        //<--- Mientras haya algo ene l resulSet, se va seteando de a uno 

                    alumnoObj = new Alumno();

                    alumnoObj.setId(resultSet.getInt("idAlumno"));
                    alumnoObj.setNombre(resultSet.getString("nombre"));
                    alumnoObj.setApellido(resultSet.getString("apellido"));
                    alumnoObj.setFechaNac(resultSet.getDate("fechaNac").toLocalDate());   
                    alumnoObj.setLegajo(resultSet.getInt("legajo"));
                    alumnoObj.setActivo(resultSet.getBoolean("activo"));
                }    
                
                prepStatem.close();                                             //<---Cerramos el Statement 
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al buscar alumno!" + ex); //<--- adicionamos el error solo por el practico
            }

        return alumnoObj;
    }
   
    
    //----METODO - LISTAR ALUMNOS ----------------------------------------------
    public List<Alumno> listarAlumnos(){                                        
        
          Alumno alumnoObj = null;
          List<Alumno> alumnoLista = new ArrayList<>();
          
          String sentenciaSql = "SELECT * "
                              + "FROM alumno "
                              + "WHERE activo = true";                          
          
          try {
              
                PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); 
         
                ResultSet resultSet = prepStatem.executeQuery();  

                while(resultSet.next()){                                        //<--- Mientras haya algo en el resulSet, se va seteando de a uno 

                    alumnoObj = new Alumno();

                    alumnoObj.setId(resultSet.getInt("idAlumno"));
                    alumnoObj.setNombre(resultSet.getString("nombre"));
                    alumnoObj.setApellido(resultSet.getString("apellido"));
                    alumnoObj.setFechaNac(resultSet.getDate("fechaNac").toLocalDate());       
                    alumnoObj.setLegajo(resultSet.getInt("legajo"));
                    alumnoObj.setActivo(resultSet.getBoolean("activo"));
                
                    alumnoLista.add(alumnoObj);                                 //<--- Aca va almacenando en la lista cada alumno obtenido de la BD
                }    
                
                prepStatem.close();                                             //<---Cerramos el Statement 
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al obtener lista de alumnos!" + ex); //<--- adicionamos el error solo por el practico
            }
   
        return alumnoLista;
    }
    
    //----METODO - ACTUALIZAR ALUMNO -------------------------------------------
    public boolean actualizarAlumno(Alumno alumnoParam){                        
        
        boolean confirmacion = false ;                                          //<---Varialble creada por nosotros para poder devolver la confirmacion del metodo        
        
        String sentenciaSql = "UPDATE alumno "
                            + "SET apellido = ?, nombre = ?, fechaNac = ?, legajo = ?, activo = ? "
                            + "WHERE idAlumno = ?";
                                                   
                    
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); //<---Preparamos el Statement pasando la sentencia 

            //---Reunimos la informacion para pasarle a cada singo de pregunta (?) de la sentenciaSql
            
            prepStatem.setString(1, alumnoParam.getApellido());                 //<--- El primer argumento, es la posicion 1(uno) de los signos de pregunta (?) en la sentenciaSql
            prepStatem.setString(2, alumnoParam.getNombre());                   
            prepStatem.setDate(3, Date.valueOf( alumnoParam.getFechaNac()));   
            prepStatem.setInt(4, alumnoParam.getLegajo()); 
            prepStatem.setBoolean(5, alumnoParam.isActivo()); 
            
            prepStatem.setInt(6, alumnoParam.getId()); 
                  
            if( prepStatem.executeUpdate() > 0){                                //<--- Por ultimo, ejecutamos el Statement  
                confirmacion = true;
            }        
            
            prepStatem.close();                                                 //<---Cerramos el Statement 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al actualizar alumno!" + ex); //<--- adicionamos el error solo por el practico
        }
        return confirmacion;                                                    //<---Retornamos la confirmacion del metodo
    }
    
    //----METODO - BORRAR ALUMNO -----------------------------------------------
    public boolean borrarAlumno(int idParam){                                   
        
        boolean confirmacion = false ;                                          //<---Varialble creada por nosotros para poder devolver la confirmacion del metodo               
        
        String sentenciaSql = "UPDATE alumno "
                            + "SET activo = false "
                            + "WHERE idAlumno = ?";
                                                 
                    
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); //<---Preparamos el Statement pasando la sentencia 
                  
            prepStatem.setInt(1, idParam ); 
                  
            if( prepStatem.executeUpdate() > 0){                                //<--- Por ultimo, ejecutamos el Statement 
               confirmacion = true;
            }     
            
            prepStatem.close();                                                 //<---Cerramos el Statement 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al borrar alumno!" + ex); //<--- adicionamos el error solo por el practico
        }
       return confirmacion;                                                     //<---Retornamos el estado del metodo
    }
    //--------------------------------------------------------------------------
    
}
