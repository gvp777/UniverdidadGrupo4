package com.clases;

import com.coneccion.ConectarBD;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class TestMain {

 
    public static void main(String[] args) {
        
        ConectarBD conexion = new ConectarBD();
        Connection con = conexion.getConexion();
        
        
        try {
            
           Statement st = con.createStatement(); 
            
            String sql = "INSERT INTO alumno( apellido,nombre,fechaNac,legajo,activo)"
                  + " VALUES ('Perez','martin','19770927',10101,true)";
            
            st.executeUpdate(sql);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
                
       
        
        
        
    }
    
}
