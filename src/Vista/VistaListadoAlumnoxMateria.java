
package Vista;

import Control.AlumnoData;
import Control.ConectarBD;
import Control.CursadaData;
import Control.MateriaData;
import Modelo.Alumno;
import Modelo.Cursada;
import Modelo.Materia;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class VistaListadoAlumnoxMateria extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo;
    ConectarBD conexion = new ConectarBD();
    private Materia materiaObtenida;
    private CursadaData cursadaData;
    
   
    public VistaListadoAlumnoxMateria() {
        initComponents();
        modelo = new DefaultTableModel(){                                       
            @Override
            public boolean isCellEditable (int filas, int columnas){            // Sobreescribimos el metedo isCellEditable para que la tabla no sea editable
            
                    if(columnas == 5){
                        return true;
                    }else{
                        return false;
                    }
            }
        };
        llenarCombo();
        colocarTitulosTabla();
        jcbxMaterias.setSelectedIndex(-1);
        
        
    }
  
    private void borrarFilas(){             //Este metodo borra los elementos de la filas
    
        int fila = (modelo.getRowCount() -1 );
        for (int i =  fila ; i >= 0 ; i-- ){
            modelo.removeRow(i);
        }
    }
        
    public void colocarTitulosTabla(){    //Este metodo coloca los titulos de la tabla
        
        ArrayList <Object> columnasTabla = new ArrayList<>();
        columnasTabla.add("Nº de Legajo");
        columnasTabla.add("Nombre");
        columnasTabla.add("Apellido");
        columnasTabla.add("Nota");
        for(Object columnaIt: columnasTabla){
             modelo.addColumn(columnaIt);
        }
        
        jtbleAlumnosxMateria.setModel(modelo);
       
    }
       
    private void llenarCombo(){ //Este metodo llena los combos recorriendo la lista de materias

        MateriaData materiaData = new MateriaData(conexion);
        
        ArrayList <Materia> materias = (ArrayList) materiaData.listarMateria();
        
        for(Materia aluIt: materias){
        
            jcbxMaterias.addItem(aluIt);
        }
  
    }
    
    private void cargarTablaConAlumnosInscriptos(){ // metodo para cargar la tabla con los alumnos inscriptos.
        String nota;
        borrarFilas();
        materiaObtenida = (Materia) jcbxMaterias.getSelectedItem();               
        cursadaData = new CursadaData(conexion);
                                 
        ArrayList<Cursada> materiasCursadas = cursadaData.obtenerIncripcion();
    
        for( Cursada listMatCursIt : materiasCursadas){
            if(materiaObtenida != null ){
                if( listMatCursIt.getMateria().getId() == materiaObtenida.getId() ){
                    if(listMatCursIt.getNota() != -1){                          //si algun alumno, todavia no tiene nota, en vez de un numero pone una leyenda (sin nota)
                      nota = listMatCursIt.getNota() + ""; 
                    }else{
                      nota = "(sin nota)";
                    }
                    modelo.addRow(new Object[]{listMatCursIt.getAlumno().getLegajo(),listMatCursIt.getAlumno().getNombre() ,listMatCursIt.getAlumno().getApellido() , nota  });
                }
            }
        }
    }
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jcbxMaterias = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbleAlumnosxMateria = new javax.swing.JTable();

        setClosable(true);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("LISTADO DE ALUMNOS POR MATERIA");

        jLabel1.setText("Materia");

        jcbxMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbxMateriasActionPerformed(evt);
            }
        });

        jtbleAlumnosxMateria.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbleAlumnosxMateria);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jcbxMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbxMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbxMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxMateriasActionPerformed
            cargarTablaConAlumnosInscriptos();
    }//GEN-LAST:event_jcbxMateriasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<Materia> jcbxMaterias;
    private javax.swing.JTable jtbleAlumnosxMateria;
    // End of variables declaration//GEN-END:variables

}
