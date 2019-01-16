/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorEstaciona;
import funcionpanel.ElementosLimite;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static vista.AdminPortal.jdpVentana;

/**
 *
 * @author Danilo Martinez
 */
public class VerEditarUsuarios extends javax.swing.JInternalFrame {
    DefaultTableModel modeloTabla;
    public VerEditarUsuarios() {
        celedit();
        compInicioTabla();
        initComponents();
    }

    public DefaultTableModel getDefaultTable()
    {
        return modeloTabla;
    }
    
    public void celedit()
    {
        modeloTabla = new DefaultTableModel(null,getColumnas())
        {
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
    }
    
    public void compInicioTabla()
    {
        ResultSet rs;
        ControladorEstaciona ce = new ControladorEstaciona();
        rs = ce.inicioTablaUsuarios();
        inicioTabla(rs);
    }
    
    private void inicioTabla(ResultSet rs)
    {
        try
        {
            Object datos[] = new Object[11];
            while(rs.next())
            {
                for(byte i = 0; i < 9; i++)
                {
                    datos[i] = rs.getString(i + 1);
                }
                modeloTabla.addRow(datos);
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("Error null: " +ex.getMessage());
        }
        catch (SQLException ex)
        {
            System.out.println("Error sql: " +ex.getMessage());
        }
    }
    
    private String[] getColumnas()
    {
        String columnas[] = new String[]{"ID Barra","RUT","Nombres","Apellido Paterno","Apellido Materno","Telefono","Discapacidad","Rol","Patente Automovil"};
        return columnas;
    }
    
    public void busq()
    {
        String busq;
        byte opBusq = 0;
        ResultSet rs;
        ControladorEstaciona ce = new ControladorEstaciona();
        if(txtBuscar.getText().length() > 0)
        {
            busq = txtBuscar.getText();
            if(rbtnCodigo.isSelected())
            {
                opBusq = 1;
            }
            if(rbtnRut.isSelected())
            {
                opBusq = 2;
            }
            if(rbtnApellido.isSelected())
            {
                opBusq = 3;
            }
            if(rbtnPatente.isSelected())
            {
                opBusq = 4;
            }
            rs = ce.busquedaInfo(busq, opBusq);
            ce.limpiarTabla(jTablaUsuarios, modeloTabla);
            inicioTabla(rs);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Ingrese el valor a buscar!","Ver/Editar Usuarios",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpBuscar = new javax.swing.ButtonGroup();
        grpDisc = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaUsuarios = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        rbtnCodigo = new javax.swing.JRadioButton();
        rbtnRut = new javax.swing.JRadioButton();
        rbtnApellido = new javax.swing.JRadioButton();
        rbtnPatente = new javax.swing.JRadioButton();
        btnAgregar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Ver/Editar usuarios");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jTablaUsuarios.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTablaUsuarios.setModel(modeloTabla);
        jTablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaUsuarios);

        txtBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Buscar");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        grpBuscar.add(rbtnCodigo);
        rbtnCodigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtnCodigo.setSelected(true);
        rbtnCodigo.setText("Codigo Barra");
        rbtnCodigo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnCodigoStateChanged(evt);
            }
        });
        rbtnCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCodigoActionPerformed(evt);
            }
        });

        grpBuscar.add(rbtnRut);
        rbtnRut.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtnRut.setText("RUT");
        rbtnRut.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnRutStateChanged(evt);
            }
        });
        rbtnRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRutActionPerformed(evt);
            }
        });

        grpBuscar.add(rbtnApellido);
        rbtnApellido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtnApellido.setText("Apellido Paterno");
        rbtnApellido.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnApellidoStateChanged(evt);
            }
        });
        rbtnApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnApellidoActionPerformed(evt);
            }
        });

        grpBuscar.add(rbtnPatente);
        rbtnPatente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtnPatente.setText("Patente");
        rbtnPatente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnPatenteStateChanged(evt);
            }
        });
        rbtnPatente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnPatenteActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnCodigo)
                            .addComponent(rbtnRut))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnApellido)
                            .addComponent(rbtnPatente))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimpiar)
                            .addComponent(btnBuscar)
                            .addComponent(btnAgregar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbtnApellido)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtnPatente)
                            .addComponent(rbtnRut)))
                    .addComponent(rbtnCodigo)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaUsuariosMouseClicked
        String id;
        int index;
        ControladorEstaciona ce = new ControladorEstaciona();
        AgregarEditarUsuario aeu = new AgregarEditarUsuario();
        ArrayList<String> datos = new ArrayList<String>();
        jdpVentana.add(aeu);
        aeu.show();
        index = jTablaUsuarios.getSelectedRow();
        id = (String)modeloTabla.getValueAt(index, 0);
        aeu.botonesInicioEditar();
        aeu.editarEliminarDatos(id);
    }//GEN-LAST:event_jTablaUsuariosMouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
     
    }//GEN-LAST:event_formInternalFrameClosed

    private void rbtnPatenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPatenteActionPerformed
        if(rbtnPatente.isSelected())
        {
            txtBuscar.setText("");
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_rbtnPatenteActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        AgregarEditarUsuario aeu = new AgregarEditarUsuario();
        jdpVentana.add(aeu);
        aeu.show();
        aeu.botonesAgregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        ResultSet rs;
        ControladorEstaciona ce = new ControladorEstaciona();
        ce.limpiarTabla(jTablaUsuarios, modeloTabla);
        rs = ce.inicioTablaUsuarios();
        inicioTabla(rs);
        txtBuscar.setText("");
        grpBuscar.clearSelection();
        rbtnCodigo.setSelected(true);
        txtBuscar.requestFocus();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        busq();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void rbtnCodigoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnCodigoStateChanged
        
    }//GEN-LAST:event_rbtnCodigoStateChanged

    private void rbtnRutStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnRutStateChanged
        
    }//GEN-LAST:event_rbtnRutStateChanged

    private void rbtnApellidoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnApellidoStateChanged
        
    }//GEN-LAST:event_rbtnApellidoStateChanged

    private void rbtnPatenteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnPatenteStateChanged
        
    }//GEN-LAST:event_rbtnPatenteStateChanged

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            busq();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        if(rbtnCodigo.isSelected())
        {
            ElementosLimite el = new ElementosLimite();
            el.soloNumeros(evt, txtBuscar, 11);
        }
        if(rbtnRut.isSelected())
        {
            ElementosLimite el = new ElementosLimite();
            el.limiteCaracteres(evt, txtBuscar, 9);
        }
        if(rbtnApellido.isSelected())
        {
            ElementosLimite el = new ElementosLimite();
            el.limiteCaracteres(evt, txtBuscar, 20);
        }
        if(rbtnPatente.isSelected())
        {
            ElementosLimite el = new ElementosLimite();
            el.limiteCaracteres(evt, txtBuscar, 8);
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void rbtnCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCodigoActionPerformed
        if(rbtnCodigo.isSelected())
        {
            txtBuscar.setText("");
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_rbtnCodigoActionPerformed

    private void rbtnRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRutActionPerformed
        if(rbtnRut.isSelected())
        {
            txtBuscar.setText("");
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_rbtnRutActionPerformed

    private void rbtnApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnApellidoActionPerformed
        if(rbtnApellido.isSelected())
        {
            txtBuscar.setText("");
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_rbtnApellidoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup grpBuscar;
    private javax.swing.ButtonGroup grpDisc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTablaUsuarios;
    private javax.swing.JRadioButton rbtnApellido;
    private javax.swing.JRadioButton rbtnCodigo;
    private javax.swing.JRadioButton rbtnPatente;
    private javax.swing.JRadioButton rbtnRut;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
