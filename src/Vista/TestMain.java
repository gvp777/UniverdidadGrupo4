package Vista;

import Control.AlumnoData;
import Control.ConectarBD;
import Modelo.Alumno;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;



public class TestMain {

 
    public static void main(String[] args) {
        
        ConectarBD conexion = new ConectarBD();
        Connection con = conexion.getConexion();
   /*     
        
        try {
            
           Statement st = con.createStatement(); 
            
            String sentenciaSql = "INSERT INTO alumno( apellido,nombre,fechaNac,legajo,activo)"
                  + " VALUES ('Perez','martin','19301927',10101,true)";
            
            st.executeUpdate(sentenciaSql);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
                
   */
   
   
   //--- GUILLE Pruebo los metodos del AlumnoData ---
   
        
   
        String apellido = "Lucero";
        String nombre = "Jose";
      
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
   
        String fecha = formato.format("07102021");                              //<--- Hacemos de cuenta que tomamos la fecha de la vista
        
        LocalDate fechaNac = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
        int legajo = 1775;
        
        boolean activo = true;
        
        Alumno alumno = new Alumno(apellido,nombre,fechaNac,legajo,activo);     // ERROR AL PASAR, EN EL CONTRUCTOR, fechaNac de LocalDate
        
        AlumnoData alumnoData = new AlumnoData(conexion);
        
        
        alumnoData.guardarAlumno(alumno);
       
        
    }
    
}
