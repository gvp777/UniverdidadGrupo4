
package Control;


import Modelo.Alumno;
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



public class AlumnoData {
    
    private Connection conexion = null;                                         

    //---CONSTRUCTOR------------------------------------------------------------
    public AlumnoData(ConectarBD conexionParam) {
        
        this.conexion = conexionParam.getConexion();
    }
    
    //---METODOS----------------------------------------------------------------
    public void guardarAlumno(Alumno alumnoParam){
       
        String sentenciaSql = "INSERT INTO alumno(apellido,nombre,fechaNac,legajo,activo)"
                                      + " VALUES (?,?,?,?,?)";                              //<--- Ejempleo con prepareStatement 
                     
                    //     + " VALUES ('Perez','martin','19770927',10101,true)";            //<--- Ejmpleo con valores clasico
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS ); //<---Preparamos el Statement pasando la sentencia y 'el segundo parametro' es para que nos retorne el id que le dio despues de insertar un nuevo registro
            
            
            //---Reunimos la informacion para pasarle a cada singo de pregunta (?) de la sentenciaSql
            
            prepStatem.setString(1, alumnoParam.getApellido());                 //<--- El primer argumento, es la posicion 1(uno) de los signos de pregunta (?) en la sentenciaSql
            prepStatem.setString(2, alumnoParam.getNombre());                   
            prepStatem.setDate(3, Date.valueOf( alumnoParam.getFechaNac()));    //<---* Solo me dejo si casteo a (Date) y no con Date.valueOf
            prepStatem.setInt(4, alumnoParam.getLegajo()); 
            prepStatem.setBoolean(5, alumnoParam.isActivo()); 
      
            prepStatem.executeUpdate();                                         //<--- Por ultimo, ejecutamos el Statement  IMPORTANTE:No recibe nada por parametro, es una confusion comun en los alumnos (explica Saez)
            
            ResultSet resultSet = prepStatem.getGeneratedKeys();                //<--- Cuando termino de ejecutar en la BD la Sentencia,  le pido que me devuelva las llaves que genero  y se las asigno a una variable resultSet  (Osea, la respuesta del Set) ResulSet es UN CONJUNTO DE RESULTADOS, Esto lo quiero para refrescar la caja de texto (si lo necesitara)
            
            if (resultSet.next()){
                alumnoParam.setId(resultSet.getInt(1));                         //<--- Guarda el id almacenado en el resulSet, en el objeto Alumno que viene por paramametro
                JOptionPane.showMessageDialog(null, "El alumno se guardo con exito");
            }
         
            prepStatem.close();                                                 //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "El alumno no puedo guardar");
   
        }
                    
                    
    }
    
    //-------------------------------------------------
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
                    alumnoObj.setFechaNac(resultSet.getDate("fechaNac").toLocalDate());   //<--- Fataria el .toLocalDate de esta forma, da error ahora poreque no esta creado.   Ejemplo: (resultSet.getDate("fechaNac").toLocalDate());
                    alumnoObj.setLegajo(resultSet.getInt("legajo"));
                    alumnoObj.setActivo(resultSet.getBoolean("activo"));
                }    
                
                prepStatem.close();                                             //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al buscar alumno!" );
            }

        return alumnoObj;
    }
    
    //-------------------------------------------------
    public List<Alumno> listarAlumnos(){                                        //<--- Se le puede pasa por parametro un apellido para que no es devuelva un conjunto de alumnos con ese apellido
        
          Alumno alumnoObj = null;
          List<Alumno> alumnoLista = new ArrayList<>();
          
          String sentenciaSql = "SELECT * "
                              + "FROM alumno "
                              + "WHERE activo = true";                          //<--- La sentencia, de esta forma, devuelve todos los alumnos de la BD  (sin ningun filtro)    
          
          try {
              
                PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); 
          //    prepStatem.setInt(1, idParam);

                ResultSet resultSet = prepStatem.executeQuery();  

                while(resultSet.next()){                                        //<--- Mientras haya algo en el resulSet, se va seteando de a uno 

                    alumnoObj = new Alumno();

                    alumnoObj.setId(resultSet.getInt("idAlumno"));
                    alumnoObj.setNombre(resultSet.getString("nombre"));
                    alumnoObj.setApellido(resultSet.getString("apellido"));
                    alumnoObj.setFechaNac(resultSet.getDate("fechaNac").toLocalDate());       //<--- Faltaria el .toLocalDate de esta forma, da error ahora poreque no esta creado.   Ejemplo: (resultSet.getDate("fechaNac").toLocalDate());
                    alumnoObj.setLegajo(resultSet.getInt("legajo"));
                    alumnoObj.setActivo(resultSet.getBoolean("activo"));
                
                    alumnoLista.add(alumnoObj);                                 //<--- Aca va almacenando en la lista cada alumno obtenido de la BD
                }    
                
                prepStatem.close();                                             //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al obtener lista de alumnos!" );
            }
   
        return alumnoLista;
    }
    
    //-------------------------------------------------
    public boolean actualizarAlumno(Alumno alumnoParam){                           //---¿No tendria que retornar una confirmacion si la sentencia se pudo concretretar o no?
        
        boolean estado = false ;
        
        String sentenciaSql = "UPDATE alumno "
                            + "SET apellido = ?, nombre = ?, fechaNac = ?,legajo = ?,activo = ? "
                            + "WHERE idAlumno = ?"
                            + "AND activo = true";                         
                    
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); //<---Preparamos el Statement pasando la sentencia 

            //---Reunimos la informacion para pasarle a cada singo de pregunta (?) de la sentenciaSql
            
            prepStatem.setString(1, alumnoParam.getApellido());                 //<--- El primer argumento, es la posicion 1(uno) de los signos de pregunta (?) en la sentenciaSql
            prepStatem.setString(2, alumnoParam.getNombre());                   
            prepStatem.setDate(3, Date.valueOf( alumnoParam.getFechaNac()));            //<---* Solo me dejo si casteo a (Date) y no con Date.valueOf
            prepStatem.setInt(4, alumnoParam.getLegajo()); 
            prepStatem.setBoolean(5, alumnoParam.isActivo()); 
            
            prepStatem.setInt(6, alumnoParam.getId()); 
                  
            if( prepStatem.executeUpdate() > 0){                                         //<--- Por ultimo, ejecutamos el Statement  IMPORTANTE:No recibe nada por parametro, es una confusion comun en los alumnos (explica Saez)
                JOptionPane.showMessageDialog(null,"Se actulizo el alumno Exitosamente!" );
                estado = true;
            }else{
                JOptionPane.showMessageDialog(null,"Hubo un problema en actualizar alumno" );
                
            }        
            
            prepStatem.close();                                                 //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al actualizar alumno!" );
        }
        return estado;
    }
    
    //-------------------------------------------------
    public boolean borrarAlumno(int idParam){                                      //---¿No tendria que retornar una confirmacion si la sentencia se pudo concretretar o no?
        
        boolean estado = false ;
        
        String sentenciaSql = "UPDATE alumno "
                            + "SET activo = ? "
                            + "WHERE idAlumno = ?";
                                                 
                    
        try {
            
            PreparedStatement prepStatem = conexion.prepareStatement(sentenciaSql); //<---Preparamos el Statement pasando la sentencia 
                  
            prepStatem.setInt(1, idParam ); 
                  
            if( prepStatem.executeUpdate() > 0){                                         //<--- Por ultimo, ejecutamos el Statement  IMPORTANTE:No recibe nada por parametro, es una confusion comun en los alumnos (explica Saez)
                JOptionPane.showMessageDialog(null,"Se dio de baja el alumno Exitosamente!" );
                estado = true;
            }else{
                JOptionPane.showMessageDialog(null,"Hubo un problema en borrar alumno" );
            }     
            prepStatem.close();                                                 //<---Cerramos el Statement (faltaria, tal vez, cerrar la conexion)
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al borrar alumno!" );
        }
       return estado;
    }
    //--------------------------------------------------------------------------
    
}
