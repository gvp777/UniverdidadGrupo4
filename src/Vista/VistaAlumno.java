/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.AlumnoData;
import Control.ConectarBD;
import Modelo.Alumno;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;


public class VistaAlumno extends javax.swing.JInternalFrame {
    ConectarBD conexion = new ConectarBD();
    private AlumnoData alumnodata;
    private Alumno alumno;
    
    public VistaAlumno() { // constructor de la vista, inicializa un alumno y alumnodata, ademas de ocultar botones para su funcionamiento
        initComponents();
        alumnodata= new AlumnoData(conexion);
        alumno = new Alumno();
        jbBorrar.setEnabled(false);
        jbActualizar.setEnabled(false);
        jbLimpiar.setEnabled(false);
        
    }
    
     public void limpiar(){     // Este metedo limpia los campos Jtext, JdateChooser, jRadioboton.
        jtApeliido.setText("");
        jtLegajo.setText("");
        jtNombre.setText("");
        jDCFechaNac.setDate(null);
        jtEstado.setText("");
        jRBEstadoActivo.setSelected(false);
        
    }
    public String getfecha(JDateChooser jd){    // este metodo lo que nos permite es obtener la fecha del jDATECHOOSER pasandolo como parametro y te lo devuelve como un string
        
        SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
        if(jd.getDate()!= null){
            return formato.format(jd.getDate());
        }else{
            return null;
        }
     }
    
    public java.util.Date StringADate(String fecha){    // Este metodo lo que nos permite es convertir una fecha de tipo string a date.
        SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
        Date fechaE= null;
        try{
            fechaE= (Date) formato.parse(fecha);
            return fechaE;
        }catch(ParseException ex){
            return null;
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbActualizar = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();
        jbBorrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jbLimpiar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jRBEstadoActivo = new javax.swing.JRadioButton();
        jtEstado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDCFechaNac = new com.toedter.calendar.JDateChooser();
        jtNombre = new javax.swing.JTextField();
        jtApeliido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtLegajo = new javax.swing.JTextField();
        jbBuscar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);

        jbActualizar.setText("Actualizar");
        jbActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbActualizarMouseClicked(evt);
            }
        });

        jbGuardar.setText("Guardar");
        jbGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });

        jbBorrar.setText("Borrar");
        jbBorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbBorrarMouseClicked(evt);
            }
        });
        jbBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBorrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("FORMULARIO DE ALUMNO");

        jbLimpiar.setText("Limpiar");
        jbLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbLimpiarMouseClicked(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jRBEstadoActivo.setText(" Activo");
        jRBEstadoActivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRBEstadoActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBEstadoActivoActionPerformed(evt);
            }
        });

        jtEstado.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jtEstado.setEnabled(false);
        jtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtEstadoActionPerformed(evt);
            }
        });

        jLabel6.setText("Estado del alumno");

        jDCFechaNac.setDateFormatString("dd-MM-yyyy");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel2.setText("Legajo:");

        jtLegajo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtLegajo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtLegajoFocusLost(evt);
            }
        });
        jtLegajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtLegajoActionPerformed(evt);
            }
        });

        jbBuscar.setText("Buscar");
        jbBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setText("Seleccion de activo");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel8.setText("Apellido:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel9.setText("Nombre:");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel10.setText("Fecha de Nac:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel8)
                                .addComponent(jLabel2)))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDCFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jRBEstadoActivo))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(33, 33, 33)
                                    .addComponent(jbBuscar))
                                .addComponent(jtApeliido, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscar))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtApeliido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDCFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRBEstadoActivo))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jbGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(jbLimpiar)
                        .addGap(30, 30, 30)
                        .addComponent(jbBorrar)
                        .addGap(34, 34, 34)
                        .addComponent(jbActualizar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbGuardar)
                    .addComponent(jbLimpiar)
                    .addComponent(jbBorrar)
                    .addComponent(jbActualizar))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        // TODO add your handling code here:
        Alumno alumnoObj = new Alumno();
            alumnoObj.setLegajo(Integer.parseInt(jtLegajo.getText()));
            alumnoObj.setNombre(jtNombre.getText());
            alumnoObj.setApellido(jtApeliido.getText());
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(getfecha(jDCFechaNac), f );
            alumnoObj.setFechaNac(date);
            alumnoObj.setActivo(jRBEstadoActivo.isSelected());
             //alumnoObj.setActivo(jcbActivo.isSelected());
            if(alumnodata.guardarAlumno(alumnoObj)){
                JOptionPane.showMessageDialog(this,"Se agrego al alumno saticfatoriamente" );
                jbLimpiar.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(this,"No se pudo ingresar al alumno correctamente" );
            }
              
        
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        // TODO add your handling code here:
       try{ 
        int legajo= Integer.parseInt(jtLegajo.getText());
        alumno= alumnodata.buscarAlumnoLegajo(legajo);
        if(alumno != null){
            //recupera y muestra los datos del alumno.
            jtApeliido.setText(alumno.getApellido());
            jtNombre.setText(alumno.getNombre());
            jDCFechaNac.setDate(Date.valueOf(alumno.getFechaNac()));
            if(alumno.isActivo()){
                  jtEstado.setText("ACTIVO");
                  jRBEstadoActivo.setSelected(alumno.isActivo());
                  jRBEstadoActivo.setEnabled(alumno.isActivo());
             }else{
                jtEstado.setText("INACTIVO");
                jRBEstadoActivo.setSelected(alumno.isActivo());
               
                  }
                 //bloquea el boton de cargar si esta en la BD, y habilita los demas botones.
                 jbGuardar.setEnabled(false);
                 jbBorrar.setEnabled(true);
                 jbActualizar.setEnabled(true);
                 jbLimpiar.setEnabled(true);
             }else{
                JOptionPane.showMessageDialog(this,"El legajo que ingreso no esta registrado" );
                jbGuardar.setEnabled(true);
             }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this,"Error, No ingreso numero de legajo" );
        }
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jtLegajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtLegajoActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jtLegajoActionPerformed

    private void jbBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBorrarActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jbBorrarActionPerformed

    private void jbLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbLimpiarMouseClicked
        // TODO add your handling code here:
        limpiar();
       jbBorrar.setEnabled(false);
        jbActualizar.setEnabled(false);
        jbGuardar.setEnabled(true);
        
    }//GEN-LAST:event_jbLimpiarMouseClicked

    private void jbBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbBorrarMouseClicked
        // TODO add your handling code here:
        int legajo= Integer.parseInt(jtLegajo.getText());
        Alumno alumnoborrar = alumnodata.buscarAlumnoLegajo(legajo);
        int id=alumnoborrar.getId();
        if(alumnoborrar.isActivo() && alumnodata.borrarAlumno(id) ){
            JOptionPane.showMessageDialog(this,"Se pudo dar baja al alumno saticfatoriamente" );
        }else{
            JOptionPane.showMessageDialog(this,"Error, No se pudo borrar al alumno" );
        }
        
    }//GEN-LAST:event_jbBorrarMouseClicked

    private void jbActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbActualizarMouseClicked
        // TODO add your handling code here:
        int legajo= Integer.parseInt(jtLegajo.getText());
        Alumno alumnoObj = alumnodata.buscarAlumnoLegajo(legajo) ;
            alumnoObj.setLegajo(Integer.parseInt(jtLegajo.getText()));
            alumnoObj.setNombre(jtNombre.getText());
            alumnoObj.setApellido(jtApeliido.getText());
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(getfecha(jDCFechaNac), f );
            alumnoObj.setFechaNac(date);
            alumnoObj.setActivo(jRBEstadoActivo.isSelected());
        if(alumnodata.actualizarAlumno(alumnoObj)){
            JOptionPane.showMessageDialog(this,"Se actualizo los datos del alumno saticfatoriamente" );
        }else{
            JOptionPane.showMessageDialog(this,"No se actualizo los datos del alumno saticfatoriamente" );
        }       
    }//GEN-LAST:event_jbActualizarMouseClicked

    private void jRBEstadoActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBEstadoActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBEstadoActivoActionPerformed

    private void jtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtEstadoActionPerformed

    private void jtLegajoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtLegajoFocusLost
        // TODO add your handling code here:
          try{
            int validacion=Integer.parseInt(jtLegajo.getText());
        }catch(NumberFormatException jt){
            JOptionPane.showMessageDialog(this,"El legajo ingresado no es correcto, cheque que sean solo numeros" );
            jtLegajo.requestFocus();
        }
    }//GEN-LAST:event_jtLegajoFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDCFechaNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRBEstadoActivo;
    private javax.swing.JButton jbActualizar;
    private javax.swing.JButton jbBorrar;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbLimpiar;
    private javax.swing.JTextField jtApeliido;
    private javax.swing.JTextField jtEstado;
    private javax.swing.JTextField jtLegajo;
    private javax.swing.JTextField jtNombre;
    // End of variables declaration//GEN-END:variables
}
