
package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ConectarBD {
    
    
    private String direccion = "jdbc:mysql://localhost/universidadg4";
    private Connection conexion = null;                                         //atributo                    
    
    //---METODO-----------------------------------------------------------------
    public Connection getConexion(){
        
        try {
        
            Class.forName("com.mysql.jdbc.Driver");                             //<---En el ejemplo de Saez esta en un contructor
            
            conexion = DriverManager.getConnection(direccion + "?useLegacyDatetimeCode=false&serverTimezone=UTC"
                    + "&user=" + "root" + "&password=" + "");
            
            System.out.println("La conexion fue establecida");  
            
        } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Hubo un problema en la conexion");
        }

        return conexion; 
    }
    
    
    public Connection getConexionActiva() {
        return conexion;
    }

}


