
package Control;

import Modelo.Materia;
import com.mysql.jdbc.Statement;


import java.util.List;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MateriaData {
    
    //---ATRIBUTO---------------------------------------------------------------
        private Connection conexion = null;                                         

    //---CONSTRUCTOR------------------------------------------------------------
    public MateriaData(ConectarBD conexionParam) {
        
        this.conexion = conexionParam.getConexion();
    }
        
    
    //****************************** METODOS ***********************************
    
    //----METODO - GUARDAR MATERIA ---------------------------------------------
    public boolean guardarMateria(Materia materiaParam){
        
        boolean confirmacion = false;                                           //<---Varialble creada por nosotros para poder devolver la confirmacion del metodo        
        
        String sentenciaSql = "INSERT INTO materia(nombreMateria,anio,activo)"
                                      + " VALUES (?,?,?)";                              

        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS ); //<---Preparamos el Statement pasando la sentencia y 'el segundo parametro' es para que nos retorne el id que le dio despues de insertar un nuevo registro
            
            //---Reunimos la informacion para pasarle a cada singo de pregunta (?) de la sentenciaSql
            
            prepStatem.setString(1, materiaParam.getNombre());                  //<--- El primer argumento, es la posicion 1(uno) de los signos de pregunta (?) en la sentenciaSql
            prepStatem.setInt(2, materiaParam.getAnio());                   
            prepStatem.setBoolean(3,materiaParam.isActivo());                    
                      
            prepStatem.executeUpdate();                                         //<--- Por ultimo, ejecutamos el Statement  
            
            ResultSet resultSet = prepStatem.getGeneratedKeys();                //<--- Cuando termino de ejecutar en la BD la Sentencia, le pedimos que me devuelva las llaves que genero y se la asignamos a una variable resultSet 
            
            if (resultSet.next()){
                materiaParam.setId(resultSet.getInt(1));                         //<--- Guarda el id almacenado en el resulSet, en el objeto Alumno que viene por paramametro
                confirmacion = true;
            }
         
            prepStatem.close();                                                 //<---Cerramos el Statement
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "La materia no puedo guardar" + ex); //<--- adicionamos el error solo por el practico
        }
             
        return confirmacion;                                                    //<---Retornamos la confirmacion del metodo        
    }

    
    //----METODO - BUSCAR MATERIA ----------------------------------------------
    public Materia buscarMateria(int idParam){
       
          Materia materiaObj = null;
          
          String sentenciaSql = "SELECT * "
                              + "FROM materia "
                              + "WHERE idMateria = ? "
                              + "AND activo = true ";
          
          try {
              
                PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); 
                
                prepStatem.setInt(1, idParam);

                ResultSet resultSet = prepStatem.executeQuery();  

                while(resultSet.next()){                                        //<--- Mientras haya algo ene l resulSet, se va seteando de a uno 

                    materiaObj = new Materia();

                    materiaObj.setId(resultSet.getInt("idMateria"));
                    materiaObj.setNombre(resultSet.getString("nombreMateria"));
                    materiaObj.setAnio(resultSet.getInt("anio"));
                    materiaObj.setActivo(resultSet.getBoolean("activo"));
                }    
                
                prepStatem.close();                                             //<---Cerramos el Statement 
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al buscar materia!" + ex); //<--- adicionamos el error solo por el practico
            }

        return materiaObj;
    }
    
    //----METODO - LISTAR MATERIA ----------------------------------------------
    public List<Materia> listarMateria(){                                        //<--- Se le puede pasa por parametro un apellido para que no es devuelva un conjunto de alumnos con ese apellido
        
          Materia materiaObj = null;
          List<Materia> materiaLista = new ArrayList<>();
          
          String sentenciaSql = "SELECT * "
                              + "FROM materia "
                              + "WHERE activo = true";                          //<--- La sentencia, de esta forma, devuelve todos los alumnos de la BD  (sin ningun filtro)    
          
          try {
              
                PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); 
  
                ResultSet resultSet = prepStatem.executeQuery();  

                while(resultSet.next()){                                        //<--- Mientras haya algo en el resulSet, se va seteando de a uno 

                    materiaObj = new Materia();

                    materiaObj.setId(resultSet.getInt("idMateria"));
                    materiaObj.setNombre(resultSet.getString("nombreMateria"));
                    materiaObj.setAnio(resultSet.getInt("anio"));
                    materiaObj.setActivo(resultSet.getBoolean("activo"));
                
                    materiaLista.add(materiaObj);                               //<--- Aca va almacenando en la lista cada alumno obtenido de la BD
                }    
                
                prepStatem.close();                                             //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al obtener lista de materias!" + ex); //<--- adicionamos el error solo por el practico
            }
   
        return materiaLista;                                                    //<--- Retornamos el confirmacion del metodo
    }
    
    //----METODO - ACTUALIZAR MATERIA ------------------------------------------
    public boolean actualizarMateria(Materia materiaParam){                     
       
        boolean confirmacion= false;                                            //<---Varialble creada por nosotros para poder devolver la confirmacion del metodo
        
        String sentenciaSql = "UPDATE materia "
                            + "SET nombreMateria = ?, anio = ?, activo = ? "
                            + "WHERE idMateria = ?";
                                                    
                    
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); //<---Preparamos el Statement pasando la sentencia 

            //---Reunimos la informacion para pasarle a cada singo de pregunta (?) de la sentenciaSql
            
            prepStatem.setString(1, materiaParam.getNombre());                  //<--- El primer argumento, es la posicion 1(uno) de los signos de pregunta (?) en la sentenciaSql
            prepStatem.setInt(2, materiaParam.getAnio());                   
            prepStatem.setBoolean(3,materiaParam.isActivo());  
            prepStatem.setInt(4, materiaParam.getId()); 
                  
            if( prepStatem.executeUpdate() > 0){                                //<---  ejecutamos el Statement, si se pudo concretar, confirmacion se setea a true  
                 confirmacion = true;
            }
         
            prepStatem.close();                                                 //<---Cerramos el Statement 
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al actualizar la materia!" + ex); //<--- adicionamos el error solo por el practico
        }
        
        return confirmacion;
    }
    
    //----METODO - BORRAR MATERIA ----------------------------------------------
    public boolean borrarMateria(int idParam){                                    
        
        boolean confirmacion= false;                                            //<---Varialble creada por nosotros para poder devolver la confirmacion del metodo
        
        String sentenciaSql = "UPDATE materia "
                            + "SET activo = false "
                            + "WHERE idMateria = ?";
                                                 
                    
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); //<---Preparamos el Statement pasando la sentencia 
                  
            prepStatem.setInt(1, idParam ); 
                  
            if( prepStatem.executeUpdate() > 0){                                //<---  ejecutamos el Statement, si se pudo concretar, confirmacion se setea a true                                    
               confirmacion = true;                                                   
            }
            
            prepStatem.close();                                                 //<---Cerramos el Statement 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al borrar la materia!" + ex); //<--- adicionamos el error solo por el practico
        }
       return confirmacion;                                                     //<--- Retornamos la confirmacion del metodo
    }
    //--------------------------------------------------------------------------
    
}

