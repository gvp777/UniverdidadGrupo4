
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
    
    
        private Connection conexion = null;                                         // atributo

    //---CONSTRUCTOR------------------------------------------------------------
    public MateriaData(ConectarBD conexionParam) {
        
        this.conexion = conexionParam.getConexion();
    }
        
    
    
    public void guardarMateria(Materia materiaParam){
       
        String sentenciaSql = "INSERT INTO materia(nombreMateria,anio,activo)"
                                      + " VALUES (?,?,?)";                              //<--- Ejempleo con prepareStatement 
                    
                   
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS ); //<---Preparamos el Statement pasando la sentencia y 'el segundo parametro' es para que nos retorne el id que le dio despues de insertar un nuevo registro
            
            
            //---Reunimos la informacion para pasarle a cada singo de pregunta (?) de la sentenciaSql
            
            prepStatem.setString(1, materiaParam.getNombre());                 //<--- El primer argumento, es la posicion 1(uno) de los signos de pregunta (?) en la sentenciaSql
            prepStatem.setInt(2, materiaParam.getAnio());                   
            prepStatem.setBoolean(3,materiaParam.isActivo());                    //<---* Solo me dejo si casteo a (Date) y no con Date.valueOf
                      
            prepStatem.executeUpdate();                                         //<--- Por ultimo, ejecutamos el Statement  IMPORTANTE:No recibe nada por parametro, es una confusion comun en los alumnos (explica Saez)
            
            ResultSet resultSet = prepStatem.getGeneratedKeys();                //<--- Cuando termino de ejecutar en la BD la Sentencia,  le pido que me devuelva las llaves que genero  y se las asigno a una variable resultSet  (Osea, la respuesta del Set) ResulSet es UN CONJUNTO DE RESULTADOS, Esto lo quiero para refrescar la caja de texto (si lo necesitara)
            
            if (resultSet.next()){
                materiaParam.setId(resultSet.getInt(1));                         //<--- Guarda el id almacenado en el resulSet, en el objeto Alumno que viene por paramametro
                JOptionPane.showMessageDialog(null, "La materia se guardo con exito");
            }
         
            prepStatem.close();                                                 //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "La materia no puedo guardar");
   
        }
             
    }

    
    //-------------------------------------------------
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
                    materiaObj.setActivo(resultSet.getBoolean("activo"));
                }    
                
                prepStatem.close();                                             //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al buscar materia!" );
            }

        return materiaObj;
    }
    
    //-------------------------------------------------
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
                    materiaObj.setActivo(resultSet.getBoolean("activo"));
                
                    materiaLista.add(materiaObj);                                 //<--- Aca va almacenando en la lista cada alumno obtenido de la BD
                }    
                
                prepStatem.close();                                             //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al obtener lista de materia!" );
            }
   
        return materiaLista;
    }
    
    
    public boolean actualizarMateria(Materia materiaParam){                           //---¿No tendria que retornar una confirmacion si la sentencia se pudo concretretar o no?
       
        boolean estado= false;
        
        String sentenciaSql = "UPDATE materia "
                            + "SET nombreMateria = ?, anio = ?,activo = ? "
                            + "WHERE idMateria = ?"
                            + "AND activo = true";                         
                    
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); //<---Preparamos el Statement pasando la sentencia 

            //---Reunimos la informacion para pasarle a cada singo de pregunta (?) de la sentenciaSql
            
            prepStatem.setString(1, materiaParam.getNombre());                 //<--- El primer argumento, es la posicion 1(uno) de los signos de pregunta (?) en la sentenciaSql
            prepStatem.setInt(2, materiaParam.getAnio());                   
            prepStatem.setBoolean(3,materiaParam.isActivo());  
            
            prepStatem.setInt(4, materiaParam.getId()); 
                  
            if( prepStatem.executeUpdate() > 0){                                         //<--- Por ultimo, ejecutamos el Statement  IMPORTANTE:No recibe nada por parametro, es una confusion comun en los alumnos (explica Saez)
                JOptionPane.showMessageDialog(null,"Se actulizo la materia Exitosamente!" );
                estado = true;
            }else{
                JOptionPane.showMessageDialog(null,"Hubo un problema en actualizar la materia" );
            }        
            
            prepStatem.close();                                                 //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al actualizar la materia!" );
        }
        
        return estado;
    }
    
    //-------------------------------------------------
    public boolean borrarMateria(int idParam){                                      //---¿No tendria que retornar una confirmacion si la sentencia se pudo concretretar o no?
        
        boolean estado= false;
        
        String sentenciaSql = "UPDATE materia "
                            + "SET activo = ? "
                            + "WHERE idMateria = ?";
                                                 
                    
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); //<---Preparamos el Statement pasando la sentencia 
                  
            prepStatem.setInt(1, idParam ); 
                  
            if( prepStatem.executeUpdate() > 0){                                         //<--- Por ultimo, ejecutamos el Statement  IMPORTANTE:No recibe nada por parametro, es una confusion comun en los alumnos (explica Saez)
                JOptionPane.showMessageDialog(null,"Se dio de baja la materia Exitosamente!" );
                estado = true;
            }else{
                JOptionPane.showMessageDialog(null,"Hubo un problema en borrar la materia" );
            }     
            prepStatem.close();                                                 //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al borrar la materia!" );
        }
       return estado;
    }
    //--------------------------------------------------------------------------
    
}

