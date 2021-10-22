
package Vista;

import Control.AlumnoData;
import Control.ConectarBD;
import Modelo.*;
import Control.*;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;


public class MenuUniversidad extends javax.swing.JFrame {

    //----ATRIBUTOS ------------------------------------------------------------
    
        ConectarBD conexion = new ConectarBD();
        
    //----CONSTRUCTOR-----------------------------------------------------------
    
    public MenuUniversidad() {
        initComponents();
        
        borraTablasBD();                                                        //<---Borramos y precargamos unos alumnos y unas materias para poder probar
        preCargaAlumnos();
        preCargaMaterias();   
        
        this.setLocationRelativeTo(null);                                       //<---Para que centre el formulario principal en la panatalla
    
        
        
    }

    //**************************** METODOS *************************************
    
    //--- METODO BORRAR TABLAS -------------------------------------------------
    
    private void borraTablasBD(){

        String sentenciaSql;
        PreparedStatement prepStatem;

        try {

            sentenciaSql = "DELETE FROM cursada ";
            prepStatem = conexion.getConexion().prepareStatement(sentenciaSql);
            prepStatem.executeUpdate();

            sentenciaSql = "DELETE FROM alumno ";
            prepStatem = conexion.getConexion().prepareStatement(sentenciaSql);
            prepStatem.executeUpdate();        
            
            sentenciaSql = "DELETE FROM materia ";
            prepStatem = conexion.getConexion().prepareStatement(sentenciaSql);
            prepStatem.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se boorarron las tablas de la BD Univeridad!\n\n"
                                             + "cursada, alumno y materia");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Hubo un Problema al borrar las tablas de la BD!");
        }     

    
    }
    
    
    //--- METODO PRE CARGA ALUMNOS ---------------------------------------------
    
    private void preCargaAlumnos(){    
        
        //---CREAMOS ALUMNOS ---------------------------------------------------
        
        AlumnoData alumnoData = new AlumnoData(conexion);
        
        Alumno diaszCarlos = new Alumno("Diaz","Carlos",LocalDate.of(1975,2,25),1003,true); 
        Alumno luceroEnrique = new Alumno("Lucero","Enrique",LocalDate.of(1985,1,5),1004,true); 
        Alumno sosaLuis = new Alumno("Sosa","Luis",LocalDate.of(1995,3,10),1005,true); 
        Alumno DonovanDaniel = new Alumno("Donovan","Daniel",LocalDate.of(1977,7,20),1006,true); 
    
        
        //---GUARDAMOS ALUMNOS -------------------------------------------------
       
        alumnoData.guardarAlumno(diaszCarlos);
        alumnoData.guardarAlumno(luceroEnrique);
        alumnoData.guardarAlumno(sosaLuis);
        
        if (alumnoData.guardarAlumno(DonovanDaniel)){
        
            JOptionPane.showMessageDialog(null," El Alumno fue guardado Satisfactorioamente!");

        }
        
    }
    
    
    //--- METODO PRE CARGA MATERIAS --------------------------------------------
    
    private void preCargaMaterias(){ 
        
        
        //---CREAMOS MATERIAS --------------------------------------------------
        
        MateriaData materiaData = new MateriaData(conexion);
        
        Materia laboratorio1 = new Materia("Laboratorio",1,true);
        Materia laboratorio2 = new Materia("Laboratorio",2,true);
        Materia estructura = new Materia("Estrucutra de Datos",1,true);
        Materia matematicas1 = new Materia("Matematicas I",1,true);
        Materia web1 = new Materia("Web I",1,true);
       
        //---GUARDAMOS MATERIAS ------------------------------------------------
                
        materiaData.guardarMateria(laboratorio1);
        materiaData.guardarMateria(laboratorio2);
        materiaData.guardarMateria(estructura);
        materiaData.guardarMateria(matematicas1);


        if (materiaData.guardarMateria(web1)){

            JOptionPane.showMessageDialog(null," La materia fue guardada Satisfactorioamente!");

        }
            
        
    }
    
    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
        
        
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDekPanEscritorio = new javax.swing.JDesktopPane();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItmSalir = new javax.swing.JMenuItem();
        jMenuMaterias = new javax.swing.JMenu();
        jMenuItmMaterias = new javax.swing.JMenuItem();
        jMenuAlumnos = new javax.swing.JMenu();
        jMenuItmAlumnos = new javax.swing.JMenuItem();
        jMenuInscripciones = new javax.swing.JMenu();
        jMenuItmManejoDeIncripciones = new javax.swing.JMenuItem();
        jMenuCargaDeNotas = new javax.swing.JMenu();
        jMenuItmManipulacionDeNotas = new javax.swing.JMenuItem();
        jMenuConsultas = new javax.swing.JMenu();
        jMenuItmListDeAlumnoXMateria = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDekPanEscritorioLayout = new javax.swing.GroupLayout(jDekPanEscritorio);
        jDekPanEscritorio.setLayout(jDekPanEscritorioLayout);
        jDekPanEscritorioLayout.setHorizontalGroup(
            jDekPanEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 858, Short.MAX_VALUE)
        );
        jDekPanEscritorioLayout.setVerticalGroup(
            jDekPanEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );

        jMenuArchivo.setText("Archivo");

        jMenuItmSalir.setText("Salir");
        jMenuItmSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItmSalirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItmSalir);

        jMenuBar.add(jMenuArchivo);

        jMenuMaterias.setText("Materias");
        jMenuMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMateriasActionPerformed(evt);
            }
        });

        jMenuItmMaterias.setText("Formulario de Materias");
        jMenuItmMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItmMateriasActionPerformed(evt);
            }
        });
        jMenuMaterias.add(jMenuItmMaterias);

        jMenuBar.add(jMenuMaterias);

        jMenuAlumnos.setText("Alumnos");

        jMenuItmAlumnos.setText("Formulario de Alumnos");
        jMenuItmAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItmAlumnosActionPerformed(evt);
            }
        });
        jMenuAlumnos.add(jMenuItmAlumnos);

        jMenuBar.add(jMenuAlumnos);

        jMenuInscripciones.setText("Inscripciones");

        jMenuItmManejoDeIncripciones.setText("Manejo de Inscripciones");
        jMenuItmManejoDeIncripciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItmManejoDeIncripcionesActionPerformed(evt);
            }
        });
        jMenuInscripciones.add(jMenuItmManejoDeIncripciones);

        jMenuBar.add(jMenuInscripciones);

        jMenuCargaDeNotas.setText("Carga de Notas");

        jMenuItmManipulacionDeNotas.setText("Manipulacion de Notas");
        jMenuItmManipulacionDeNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItmManipulacionDeNotasActionPerformed(evt);
            }
        });
        jMenuCargaDeNotas.add(jMenuItmManipulacionDeNotas);

        jMenuBar.add(jMenuCargaDeNotas);

        jMenuConsultas.setText("Consultas");

        jMenuItmListDeAlumnoXMateria.setText("Listado de Alumnos x Materia");
        jMenuItmListDeAlumnoXMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItmListDeAlumnoXMateriaActionPerformed(evt);
            }
        });
        jMenuConsultas.add(jMenuItmListDeAlumnoXMateria);

        jMenuBar.add(jMenuConsultas);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDekPanEscritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDekPanEscritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItmSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItmSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItmSalirActionPerformed

    private void jMenuItmManejoDeIncripcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItmManejoDeIncripcionesActionPerformed
        // Limpiamos el escritorio
        jDekPanEscritorio.removeAll();
        jDekPanEscritorio.repaint();
        
        VistaFormularioDeInscripcion form = new VistaFormularioDeInscripcion();
        
        // agregamos la vista al escritorio
        jDekPanEscritorio.add(form);
        // centramos la vista dentro del escritoio
        Dimension desktopSize = jDekPanEscritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        // Seteamos visible a la vista
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItmManejoDeIncripcionesActionPerformed

    private void jMenuMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMateriasActionPerformed

    }//GEN-LAST:event_jMenuMateriasActionPerformed

    private void jMenuItmMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItmMateriasActionPerformed
        // Limpiamos el escritorio
        jDekPanEscritorio.removeAll();
        jDekPanEscritorio.repaint();
        
        VistaMateria form = new VistaMateria();
        
        // agregamos la vista al escritorio
        jDekPanEscritorio.add(form);
        // centramos la vista dentro del escritoio
        Dimension desktopSize = jDekPanEscritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        // Seteamos visible a la vista
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItmMateriasActionPerformed

    private void jMenuItmAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItmAlumnosActionPerformed
        // Limpiamos el escritorio
        jDekPanEscritorio.removeAll();
        jDekPanEscritorio.repaint();
        
        VistaAlumno form = new VistaAlumno();
        
        // agregamos la vista al escritorio
        jDekPanEscritorio.add(form);
        // centramos la vista dentro del escritoio
        Dimension desktopSize = jDekPanEscritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        // Seteamos visible a la vista
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItmAlumnosActionPerformed

    private void jMenuItmManipulacionDeNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItmManipulacionDeNotasActionPerformed
        // Limpiamos el escritorio
        jDekPanEscritorio.removeAll();
        jDekPanEscritorio.repaint();
        
        VistaManipulacionDeNotas form = new VistaManipulacionDeNotas();
        
        // agregamos la vista al escritorio
        jDekPanEscritorio.add(form);
        // centramos la vista dentro del escritoio
        Dimension desktopSize = jDekPanEscritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        // Seteamos visible a la vista
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItmManipulacionDeNotasActionPerformed

    private void jMenuItmListDeAlumnoXMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItmListDeAlumnoXMateriaActionPerformed
        // Limpiamos el escritorio
        jDekPanEscritorio.removeAll();
        jDekPanEscritorio.repaint();
        
        VistaListadoAlumnoxMateria form = new VistaListadoAlumnoxMateria();
        
        // agregamos la vista al escritorio
        jDekPanEscritorio.add(form);
        // centramos la vista dentro del escritoio
        Dimension desktopSize = jDekPanEscritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        // Seteamos visible a la vista
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItmListDeAlumnoXMateriaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUniversidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUniversidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUniversidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUniversidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUniversidad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDekPanEscritorio;
    private javax.swing.JMenu jMenuAlumnos;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuCargaDeNotas;
    private javax.swing.JMenu jMenuConsultas;
    private javax.swing.JMenu jMenuInscripciones;
    private javax.swing.JMenuItem jMenuItmAlumnos;
    private javax.swing.JMenuItem jMenuItmListDeAlumnoXMateria;
    private javax.swing.JMenuItem jMenuItmManejoDeIncripciones;
    private javax.swing.JMenuItem jMenuItmManipulacionDeNotas;
    private javax.swing.JMenuItem jMenuItmMaterias;
    private javax.swing.JMenuItem jMenuItmSalir;
    private javax.swing.JMenu jMenuMaterias;
    // End of variables declaration//GEN-END:variables
}
