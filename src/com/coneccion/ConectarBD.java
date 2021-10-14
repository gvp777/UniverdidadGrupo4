
package com.coneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConectarBD {
    
    private Connection conexion = null;                                         //atributo                    
    
    //---METODO-----------------------------------------------------------------
    public Connection getConexion(){
        
        try {
        
            Class.forName("com.mysql.jdbc.Driver");                             //<---En el ejemplo de Saez esta en un contructor
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/universidadg4","root","");
            System.out.println("La conexion fue establecida");  
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return conexion; 
    }
    //--------------------------------------------------------------------------
}
