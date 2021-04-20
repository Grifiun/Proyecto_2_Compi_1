/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.Utilities;

/**
 *
 * @author grifiun
 */
public class Ventana extends javax.swing.JFrame {

    String path = "";
    File archivo;
    
    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();   
        txtNumeration.setText("1");
        //sincronizamos los 2 vertical scroll
        new ScrollBarSynchronizer(scrollEntrada.getVerticalScrollBar(), scrollNumeration.getVerticalScrollBar());
        panelEditorTexto.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        panelEditorTexto = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        scrollEntrada = new javax.swing.JScrollPane();
        txtEntrada = new javax.swing.JTextPane();
        scrollNumeration = new javax.swing.JScrollPane();
        txtNumeration = new javax.swing.JTextPane();
        lblPosition = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        btnNuevo = new java.awt.Button();
        btnGuardarComo = new java.awt.Button();
        btnCargar = new java.awt.Button();
        btnGuardar = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(35);

        txtEntrada.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtEntradaCaretUpdate(evt);
            }
        });
        txtEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEntradaMouseClicked(evt);
            }
        });
        txtEntrada.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtEntradaInputMethodTextChanged(evt);
            }
        });
        txtEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntradaKeyPressed(evt);
            }
        });
        txtEntrada.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                //System.out.println("REMOVE UPTADTE");
                actualizarNumeracionLinea();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                //System.out.println("INSERT UPTADTE");
                actualizarNumeracionLinea();
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                //System.out.println("CHANGE UPTADTE");
                actualizarNumeracionLinea();
            }
        });
        scrollEntrada.setViewportView(txtEntrada);

        jSplitPane1.setRightComponent(scrollEntrada);

        txtNumeration.setEditable(false);
        txtNumeration.setBackground(new java.awt.Color(255, 255, 255));
        txtNumeration.setForeground(new java.awt.Color(0, 0, 0));
        txtNumeration.setToolTipText("");
        txtNumeration.setAutoscrolls(false);
        scrollNumeration.setViewportView(txtNumeration);
        //jScrollPane2.setVisible(false);//ocultamos
        scrollNumeration.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollNumeration.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
        txtNumeration.setParagraphAttributes(attribs, true);

        jSplitPane1.setLeftComponent(scrollNumeration);

        jScrollPane1.setViewportView(jTextPane1);

        jLabel1.setText("SALIDA:");

        javax.swing.GroupLayout panelEditorTextoLayout = new javax.swing.GroupLayout(panelEditorTexto);
        panelEditorTexto.setLayout(panelEditorTextoLayout);
        panelEditorTextoLayout.setHorizontalGroup(
            panelEditorTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditorTextoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEditorTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane1)
                    .addGroup(panelEditorTextoLayout.createSequentialGroup()
                        .addGroup(panelEditorTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 380, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelEditorTextoLayout.setVerticalGroup(
            panelEditorTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditorTextoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        btnNuevo.setActionCommand("Nuevo");
        btnNuevo.setLabel("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardarComo.setActionCommand("Nuevo");
        btnGuardarComo.setLabel("Guardar como");
        btnGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarComoActionPerformed(evt);
            }
        });

        btnCargar.setActionCommand("Nuevo");
        btnCargar.setLabel("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        btnGuardar.setActionCommand("Nuevo");
        btnGuardar.setLabel("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardarComo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(panelEditorTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 17, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarComo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEditorTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Funcion que controla la enumeracion de lineas
     * @param evt 
     */
    private void txtEntradaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtEntradaInputMethodTextChanged
            
    }//GEN-LAST:event_txtEntradaInputMethodTextChanged

    /**
     * Funcion que controla la enumeracion de lineas
     * @param evt 
     */
    private void txtEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaKeyPressed

    }//GEN-LAST:event_txtEntradaKeyPressed

    private void txtEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEntradaMouseClicked
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_txtEntradaMouseClicked

    /**
     * Actualizamos la posicon del cursor
     * @param evt 
     */
    private void txtEntradaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEntradaCaretUpdate
        // Posicion de seleccion
        String auxPosition = "FILA: "+getLineAtCaret(txtEntrada)+" COLUMNA: "+getColumnAtCaret(txtEntrada);
        lblPosition.setText(auxPosition);

        
    }//GEN-LAST:event_txtEntradaCaretUpdate

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try{
            seleccionarRuta();  
            panelEditorTexto.setVisible(true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al seleccionar la ruta");
        }
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarComoActionPerformed
        // TODO add your handling code here:
        guardarComo();
    }//GEN-LAST:event_btnGuardarComoActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // Abrimos un JOptionPane
        try{
            path = elegirArchivo();
            this.setTitle(path);            
            panelEditorTexto.setVisible(true);
            txtEntrada.setText(leerArchivo(path));//cargamos los datos de la entrada
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo");
        }
        
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //guardar
        try{
            guardarFichero(txtEntrada.getText(), archivo);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al seleccionar la ruta");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * Seleccionamos un archivo
     * @return 
     */
    private String elegirArchivo(){
        JFileChooser buscador = new JFileChooser();    
        //Agregamos un filtro al file Choser
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("TEXT FILES", "txt");
        buscador.setFileFilter(filtro);
        buscador.showOpenDialog(this);
        File file;
        String archivo;
        file = buscador.getSelectedFile();
        if(file == null){
            archivo="";        
        }else{
            archivo = buscador.getSelectedFile().getAbsolutePath();  
        }
        return archivo;
    }
    
    public String leerArchivo(String direccion){
        String aux = "";
        String contenidoArchivo = "";        
        try{///se lee el archivo
            FileReader fr = new FileReader(direccion);
            BufferedReader br = new BufferedReader(fr);

            //Leemos y analizamos todaas las lineas de texto del archivo (linea por linea)
            while(aux != null){//cuando el auxiliar no sea nulo el while sigue, sera nulo cuando se termine de leer el archivo
                contenidoArchivo += aux + "\n";//agregamos la linea a una var
                aux = br.readLine();//con el ReadLine procedemos a leer la siguiente linea            
                                
            }
            //substring(0, auxMat.length() - 1) hace que se remueva el ultimo caracter agregado, que seria un "*"
           
            
        }catch(Exception e){            
            JOptionPane.showMessageDialog(null, "Archivo inexistente");
            
        } 
    
        return contenidoArchivo;
    }
    
    public void guardarComo(){
        
        try{
            seleccionarRuta();            
            guardarFichero(txtEntrada.getText(), archivo);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al seleccionar la ruta");
        }
    }
    
    public void seleccionarRuta(){
        JFileChooser guardar = new JFileChooser();
        guardar.showSaveDialog(null);
        guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        this.archivo = guardar.getSelectedFile();
        this.setTitle(archivo.getAbsolutePath());
    }
    
    public void guardarFichero(String cadena, File archivo){

        FileWriter escribir;
        try {

            escribir = new FileWriter(archivo, true);
            escribir.write(cadena);
            escribir.close();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar, ponga nombre al archivo");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar, en la salida");
        }
    }
    
    private void actualizarNumeracionLinea(){
        String txtEntAux = txtEntrada.getText();
        int longitud = txtEntrada.getDocument().getDefaultRootElement().getElementCount();
        //cambiamos el texto de las numeraciones
        txtNumeration.setText("");
        String auxNum = "";
        for(int i = 1; i <= longitud; i++){
            auxNum += ""+i+"\n";
        }
        txtNumeration.setText(auxNum);
    }
    
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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    /**
     * Clase interna que ajusta el scroll vertical de los 2 scrollpane, de entrada y numeracion
     */
    public class ScrollBarSynchronizer implements AdjustmentListener
    {
        JScrollBar[] scrollBars;
        public ScrollBarSynchronizer(JScrollBar... scrollBars)
        {
            this.scrollBars = scrollBars;
            for (JScrollBar scrollBar: scrollBars)
                scrollBar.addAdjustmentListener( this );
        }

        @Override
        public void adjustmentValueChanged(AdjustmentEvent e)
        {
            JScrollBar source = (JScrollBar)e.getSource();
            int value = e.getValue();
            for (JScrollBar scrollBar: scrollBars)
            {
                if (scrollBar != source)
                {
                    scrollBar.removeAdjustmentListener( this );
                    scrollBar.setValue( value );
                    scrollBar.addAdjustmentListener( this );
                }
            }
        }
    }

    /**
     * Obtener la posicion de la columna
     * @param comp
     * @return 
     */
    public int getColumnAtCaret(JTextComponent comp) {
        int offset = comp.getCaretPosition();
        int column;
        try {
                column = offset - Utilities.getRowStart(comp, offset);
        } catch (BadLocationException e) {
                column = -1;
        }
        return column + 1;
    }
    
    /*
    **  Return the current line number at the Caret position.
    */
    public int getLineAtCaret(JTextComponent component)
    {
            int caretPosition = component.getCaretPosition();
            Element root = component.getDocument().getDefaultRootElement();
            return root.getElementIndex( caretPosition ) + 1;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnCargar;
    private java.awt.Button btnGuardar;
    private java.awt.Button btnGuardarComo;
    private java.awt.Button btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JPanel panelEditorTexto;
    private javax.swing.JScrollPane scrollEntrada;
    private javax.swing.JScrollPane scrollNumeration;
    private javax.swing.JTextPane txtEntrada;
    private javax.swing.JTextPane txtNumeration;
    // End of variables declaration//GEN-END:variables
}
