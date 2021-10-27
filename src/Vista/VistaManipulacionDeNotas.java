
package Vista;

import Control.AlumnoData;
import Control.ConectarBD;
import Control.CursadaData;
import Control.MateriaData;
import Modelo.Alumno;
import Modelo.Cursada;
import Modelo.Materia;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class VistaManipulacionDeNotas extends javax.swing.JInternalFrame {
    
    // Atributos
    private DefaultTableModel modelo;
   
    private Alumno alumnoSeleccionado;
    private CursadaData cursadaData;
    private MateriaData materiaData;
    private boolean actualizoNota = false;
    
    private float notaSeleccionada= -2;
    private float notaActualizada = -2;
    private int idMateriaSeleccionada = -2;
    private int idAlumnoSeleccionado = -2;
    private int filaSeleccionada = -2;
        
    ConectarBD conexion = new ConectarBD();
    
    // Constructor
    public VistaManipulacionDeNotas() {
        initComponents();
        modelo = new DefaultTableModel(){                                       
            @Override
            public boolean isCellEditable (int filas, int columnas){            //<---Metodo sobreescrito para que la talbla no sea editable
            
                    if(columnas == 4){
                        return true;
                    }else{
                        return false;
                    }
            }
        };
        
        llenarCombo();
        colocarTitulosTabla();

        jcbxAlumnos.setSelectedIndex(-1);
        
        jlbNuevaNota.setVisible(false);
        jtxtNuevaNota.setVisible(false);
        jbtGuardarNota.setVisible(false);
        jbtCancelar.setVisible(false);
        
    }
    
    //**************************** METODOS *************************************
    //--------------------------------------------------------------------------
    
        
 
     
    
     //-------------------------------------------------------------------------  
    
    
    private void borrarFilas(){
    
        //---CADA FILA EN LA EN EL MODELO, ES  UN AREGLO DE OBJETOS---
        
        int fila = (modelo.getRowCount() -1 ); //---tengo la pocision d ela ultima fila  
        
        // borramnos de atras para adelante 
        
        for (int i =  fila ; i >= 0 ; i-- ){
            
            modelo.removeRow(i);
        }
        
        
    }
    
    //--------------------------------------------------------------------------
    
    public void colocarTitulosTabla(){    
        
        ArrayList <Object> columnasTabla = new ArrayList<>();
        
        columnasTabla.add("ID");
        columnasTabla.add("Materia");
        columnasTabla.add("Año");
        columnasTabla.add("Nota");
      
        
        for(Object columnaIt: columnasTabla){
            
            modelo.addColumn(columnaIt);
        }
        
        jtbleMateriasDelAlumno.setModel(modelo);
        
        
    }
        
    private void llenarCombo(){

        AlumnoData alumnoData = new AlumnoData(conexion);
        
        ArrayList <Alumno> alumnos = (ArrayList) alumnoData.listarAlumnos();
        
        for(Alumno aluIt: alumnos){
        
            jcbxAlumnos.addItem(aluIt);
        }
  
    }    
    

    
    private void cargarTablaConMateriasInscriptas(){
        String nota;
        borrarFilas();
        
        alumnoSeleccionado = (Alumno) jcbxAlumnos.getSelectedItem();               
        
        cursadaData = new CursadaData(conexion);
         
        if(alumnoSeleccionado != null ){
                   
        ArrayList<Cursada> listaMateriasQueSonCusadas = cursadaData.obtenerIncripcion();
    
            for( Cursada listMatCursIt : listaMateriasQueSonCusadas){

                if( listMatCursIt.getAlumno().getId() == alumnoSeleccionado.getId() ){
                    
                    if(listMatCursIt.getNota() != -1){                         
                       nota = listMatCursIt.getNota() + ""; 
                    }else{
                        nota = "(sin nota)";
                    }
                                     
                    modelo.addRow(new Object[]{listMatCursIt.getMateria().getId(), listMatCursIt.getMateria().getNombre(),listMatCursIt.getMateria().getAnio(), nota });

                }
            }
        }    
    }
    
 
    
    private boolean guardarCambios(){
               
        materiaData = new MateriaData(conexion);
        cursadaData = new CursadaData(conexion);
            
        try{
                    notaActualizada = Float.parseFloat(jtxtNuevaNota.getText());
 
                    if (notaActualizada >= 0 && notaActualizada <= 10){
               
                        ArrayList<Cursada> listaMateriasQueSonCusadas = cursadaData.obtenerIncripcion();

                        for( Cursada listMatCursIt : listaMateriasQueSonCusadas){

                            if(( listMatCursIt.getAlumno().getId() == alumnoSeleccionado.getId() )){

                                cursadaData.actualizarNota(idAlumnoSeleccionado, idMateriaSeleccionada, notaActualizada);
                                actualizoNota = true;
                            }
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Rango de nota admitido es de 0(cero) a 10(diez)");
                        jtxtNuevaNota.requestFocus();
                        jtxtNuevaNota.selectAll();
                    }    
             
            }catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Puede ingresar solo numeros");
                    jtxtNuevaNota.requestFocus();
                    jtxtNuevaNota.selectAll();
            }
                   
            return actualizoNota;
    }
    
    

    /**
     * This mrom within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbFormularioDeInscripcion = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jcbxAlumnos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbleMateriasDelAlumno = new javax.swing.JTable();
        jbtGuardarNota = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jtxtNuevaNota = new javax.swing.JTextField();
        jlbNuevaNota = new javax.swing.JLabel();

        setClosable(true);

        jlbFormularioDeInscripcion.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jlbFormularioDeInscripcion.setText("CARGA DE NOTAS");

        jLabel1.setText("Alumno");

        jcbxAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbxAlumnosActionPerformed(evt);
            }
        });

        jtbleMateriasDelAlumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbleMateriasDelAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbleMateriasDelAlumnoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jtbleMateriasDelAlumnoMouseExited(evt);
            }
        });
        jtbleMateriasDelAlumno.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jtbleMateriasDelAlumnoPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jtbleMateriasDelAlumno);

        jbtGuardarNota.setText("Actualizar Nota");
        jbtGuardarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGuardarNotaActionPerformed(evt);
            }
        });

        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jlbNuevaNota.setText("Nueva nota :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbFormularioDeInscripcion)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jcbxAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbNuevaNota, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtNuevaNota, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtGuardarNota)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtCancelar)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbFormularioDeInscripcion)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbxAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtGuardarNota)
                    .addComponent(jbtCancelar)
                    .addComponent(jtxtNuevaNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbNuevaNota))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtGuardarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGuardarNotaActionPerformed
       if ( guardarCambios()){
            JOptionPane.showMessageDialog(null, "Nota Actualizada!");
            actualizoNota = false;
            cargarTablaConMateriasInscriptas();
            jtxtNuevaNota.setText("");
            jlbNuevaNota.setVisible(false);
            jtxtNuevaNota.setVisible(false);
            jbtGuardarNota.setVisible(false);
            jbtCancelar.setVisible(false);

           
       }
    }//GEN-LAST:event_jbtGuardarNotaActionPerformed

    private void jcbxAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxAlumnosActionPerformed
        cargarTablaConMateriasInscriptas();
    }//GEN-LAST:event_jcbxAlumnosActionPerformed

    private void jtbleMateriasDelAlumnoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jtbleMateriasDelAlumnoPropertyChange
        
    }//GEN-LAST:event_jtbleMateriasDelAlumnoPropertyChange

    private void jtbleMateriasDelAlumnoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbleMateriasDelAlumnoMouseExited
     
    }//GEN-LAST:event_jtbleMateriasDelAlumnoMouseExited

    private void jtbleMateriasDelAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbleMateriasDelAlumnoMouseClicked
                
        if (evt.getClickCount() == 2){
        
               filaSeleccionada = jtbleMateriasDelAlumno.getSelectedRow() ;

                if ( filaSeleccionada != -1){                                        
                    
                    String n = (jtbleMateriasDelAlumno.getValueAt(filaSeleccionada, 3)+"");
                                      
                    if (n.equals("(sin nota)")){
                        
                        jtxtNuevaNota.setText("0.0");
               
                        
                    }else{    
                        notaSeleccionada  = Float.parseFloat( jtbleMateriasDelAlumno.getValueAt(filaSeleccionada, 3)+"");
                        
                        jtxtNuevaNota.setText(notaSeleccionada + "");
  
                    }    
                        
                    jlbNuevaNota.setVisible(true);
                    jtxtNuevaNota.setVisible(true);
                    jbtGuardarNota.setVisible(true);
                    jbtCancelar.setVisible(true);
                    jtxtNuevaNota.requestFocus();
                    jtxtNuevaNota.selectAll(); 
                    
                    idMateriaSeleccionada = (Integer)jtbleMateriasDelAlumno.getValueAt(filaSeleccionada, 0);

                    alumnoSeleccionado = (Alumno)jcbxAlumnos.getSelectedItem(); 
                    idAlumnoSeleccionado = alumnoSeleccionado.getId();


                    
                    
                }
        }
    }//GEN-LAST:event_jtbleMateriasDelAlumnoMouseClicked

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
       
        jtxtNuevaNota.setText("");
        jlbNuevaNota.setVisible(false);
        jtxtNuevaNota.setVisible(false);
        jbtGuardarNota.setVisible(false);
        jbtCancelar.setVisible(false);
        
        borrarFilas();
        jcbxAlumnos.setSelectedIndex(-1);
        jcbxAlumnos.requestFocus();
    }//GEN-LAST:event_jbtCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtGuardarNota;
    private javax.swing.JComboBox<Alumno> jcbxAlumnos;
    private javax.swing.JLabel jlbFormularioDeInscripcion;
    private javax.swing.JLabel jlbNuevaNota;
    private javax.swing.JTable jtbleMateriasDelAlumno;
    private javax.swing.JTextField jtxtNuevaNota;
    // End of variables declaration//GEN-END:variables
}
