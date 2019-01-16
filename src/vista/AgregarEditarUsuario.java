/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import controlador.ControladorEstaciona;
import funcionpanel.ElementosLimite;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Danilo Martinez
 */
public class AgregarEditarUsuario extends javax.swing.JInternalFrame {
    List<Component> list = Arrays.asList(this.txtCodigoBarra,this.txtRut);
    /** Creates new form AgregarEditarUsuario3 */
    public AgregarEditarUsuario() {
        initComponents();
        datosCMBRol();
        datosDiscap();
    }
    
    public void botonesAgregar()
    {
        btnAgregar.setEnabled(true);
        btnEditar.setEnabled(false);
        txtCodigoBarra.setEditable(true);
        txtRut.setEditable(true);
        txtNombres.setEditable(true);
        txtApPaterno.setEditable(true);
        txtApMaterno.setEditable(true);
        txtTelefono.setEditable(true);
        cmbDiscap.setEnabled(true);
        cmbRol.setEnabled(true);
        txtPatente1.setEditable(true);
        txtPatente2.setEditable(true);
        txtPatente3.setEditable(true);
        txtModelo.setEditable(true);
        txtColor.setEditable(true);
        txtCodigoBarra.requestFocus();
    }
    
    public void botonesInicioEditar()
    {
        btnEditar.setEnabled(true);
        txtRut.setEditable(false);
        txtNombres.setEditable(false);
        txtApPaterno.setEditable(false);
        txtApMaterno.setEditable(false);
        txtTelefono.setEditable(false);
        cmbDiscap.setEnabled(false);
        cmbRol.setEnabled(false);
        txtPatente1.setEditable(false);
        txtPatente2.setEditable(false);
        txtPatente3.setEditable(false);
        txtModelo.setEditable(false);
        txtColor.setEditable(false);
    }
    
    public void botonEditar()
    {
        btnEditar.setEnabled(false);
        btnGuardarCambios.setEnabled(true);
        txtNombres.setEditable(true);
        txtApPaterno.setEditable(true);
        txtApMaterno.setEditable(true);
        txtTelefono.setEditable(true);
        cmbDiscap.setEnabled(true);
        cmbRol.setEnabled(true);
        txtPatente1.setEditable(true);
        txtPatente2.setEditable(true);
        txtPatente3.setEditable(true);
        txtModelo.setEditable(true);
        txtColor.setEditable(true);
        txtPass.setEditable(true);
        txtPass2.setEditable(true);
        chkPass.setEnabled(true);
        if(chkPass.isSelected())
        {
            txtPass.setEnabled(true);
            txtPass2.setEnabled(true);
        }
    }
    
    public void editarEliminarDatos(String id)
    {
        String pass = "";
        ArrayList<String> datos = new ArrayList<String>();
        ControladorEstaciona ce = new ControladorEstaciona();
        datos = ce.editarItemUsuario(id);
        txtCodigoBarra.setText(datos.get(0));
        txtRut.setText(datos.get(1));
        txtNombres.setText(datos.get(2));
        txtApPaterno.setText(datos.get(3));
        txtApMaterno.setText(datos.get(4));
        txtTelefono.setText(datos.get(5));
        cmbDiscap.setSelectedItem(datos.get(6));
        cmbRol.setSelectedItem(datos.get(7));
        txtIDAuto.setText(datos.get(8));
//        System.out.println(datos.get(9).substring(0,2) + datos.get(9).substring(3,5) + datos.get(9).substring(6,8));
        txtPatente1.setText(datos.get(9).substring(0,2));
        txtPatente2.setText(datos.get(9).substring(3,5));
        txtPatente3.setText(datos.get(9).substring(6,8));
        txtModelo.setText(datos.get(10));
        txtColor.setText(datos.get(11));
        pass = ce.buscarPassAnd(id);
        if(!pass.equals(""))
        {
            chkPass.setSelected(true);
            txtPass.setText(pass);
            txtPass2.setText(pass);
            txtPass.setEditable(false);
            txtPass2.setEditable(false);
        }
        chkPass.setEnabled(false);
    }
    public void datosCMBRol()
    {
        ArrayList<String> datos = new ArrayList<String>();
        ControladorEstaciona ce = new ControladorEstaciona();
        datos = ce.cmbRol();
        for(byte i = 0; i < datos.size(); i++)
        {
            cmbRol.addItem(datos.get(i));
        }
    }
    public void datosDiscap()
    {
        cmbDiscap.addItem("no");
        cmbDiscap.addItem("si");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoBarra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtApPaterno = new javax.swing.JTextField();
        txtApMaterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbDiscap = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtIDAuto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPatente1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPatente2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPatente3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        chkPass = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        txtPass2 = new javax.swing.JPasswordField();
        btnAgregar = new javax.swing.JButton();
        btnGuardarCambios = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setTitle("Agregar/Editar Usuarios");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("ID Codigo barra");

        txtCodigoBarra.setEditable(false);
        txtCodigoBarra.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCodigoBarra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoBarraKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarraKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("RUT");

        txtRut.setEditable(false);
        txtRut.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRutKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Nombres");

        txtNombres.setEditable(false);
        txtNombres.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombresKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Apellido Paterno");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Apellido Materno");

        txtApPaterno.setEditable(false);
        txtApPaterno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtApPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApPaternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApPaternoKeyTyped(evt);
            }
        });

        txtApMaterno.setEditable(false);
        txtApMaterno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtApMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApMaternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApMaternoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Telefono");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Discapacidad");

        cmbDiscap.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Rol");

        cmbRol.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("ID Automovil");

        txtIDAuto.setEditable(false);
        txtIDAuto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtIDAuto.setText("-");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Patente");

        txtPatente1.setEditable(false);
        txtPatente1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPatente1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPatente1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPatente1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPatente1KeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("*");

        txtPatente2.setEditable(false);
        txtPatente2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPatente2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPatente2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPatente2KeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("*");

        txtPatente3.setEditable(false);
        txtPatente3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPatente3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPatente3KeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Modelo");

        txtModelo.setEditable(false);
        txtModelo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModeloKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Color");

        txtColor.setEditable(false);
        txtColor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColorKeyTyped(evt);
            }
        });

        chkPass.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        chkPass.setText("Aplicacion Android");
        chkPass.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkPassStateChanged(evt);
            }
        });
        chkPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPassActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Contraseña");

        txtPass.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPass.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Repetir Contraseña");

        txtPass2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPass2.setEnabled(false);

        btnAgregar.setText("Agregar");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnGuardarCambios.setText("Guardar cambios");
        btnGuardarCambios.setEnabled(false);
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarCambios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel2))
                                        .addGap(7, 7, 7)
                                        .addComponent(txtCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cmbDiscap, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkPass)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPass2))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtColor)
                                        .addComponent(txtModelo)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtIDAuto))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPatente1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPatente2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPatente3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(62, 62, 62)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(41, 41, 41)
                                    .addComponent(jLabel2))
                                .addComponent(jLabel12))
                            .addGap(19, 19, 19)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(cmbDiscap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jSeparator2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtPatente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPatente2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtPatente3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkPass)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnGuardarCambios)
                    .addComponent(btnEditar))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        botonEditar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        boolean estado = false;
        String pass1, pass2;
        ControladorEstaciona ce = new ControladorEstaciona();
        if(txtNombres.getText().length() > 0 && txtApPaterno.getText().length() > 0 && txtApMaterno.getText().length() > 0 &&
            txtTelefono.getText().length() > 0 && txtPatente1.getText().length() >0 && txtPatente2.getText().length() >0 && txtPatente3.getText().length() >0 && txtModelo.getText().length() > 0 &&
            txtColor.getText().length() > 0)
        {
            if(chkPass.isSelected())
            {
                pass1 = String.valueOf(txtPass.getPassword());
                pass2 = String.valueOf(txtPass2.getPassword());
                if(pass1.length() > 0 && pass2.length() > 0)
                {
                    if(pass1.equals(pass2))
                    {
                        estado = ce.edicionInfoUsuario(txtCodigoBarra.getText(), txtRut.getText(), txtNombres.getText(),
                            txtApPaterno.getText(), txtApMaterno.getText(), txtTelefono.getText(), cmbDiscap.getSelectedItem().toString(),
                            cmbRol.getSelectedItem().toString(), txtIDAuto.getText(), txtPatente1.getText(),txtPatente2.getText(),txtPatente3.getText(), txtModelo.getText(),
                            txtColor.getText());
                        ce.insertUpdateAnd(txtCodigoBarra.getText(), pass1);
                        if(estado)
                        {
                            JOptionPane.showMessageDialog(null, "Edicion realizada correctamente","Edicion Usuarios",JOptionPane.INFORMATION_MESSAGE);
                            this.dispose();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Hubo un error al editar","Edicion Usuarios",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Contraseñas no coinciden!","Edicion Usuarios",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Favor ingrese los datos solicitados","Edicion Usuarios",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                estado = ce.edicionInfoUsuario(txtCodigoBarra.getText(), txtRut.getText(), txtNombres.getText(),
                    txtApPaterno.getText(), txtApMaterno.getText(), txtTelefono.getText(), cmbDiscap.getSelectedItem().toString(),
                    cmbRol.getSelectedItem().toString(), txtIDAuto.getText(), txtPatente1.getText(),txtPatente2.getText(),txtPatente3.getText(), txtModelo.getText(),
                    txtColor.getText());
                if(estado)
                {
                    JOptionPane.showMessageDialog(null, "Edicion realizada correctamente","Edicion Usuarios",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Hubo un error al editar","Edicion Usuarios",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Favor ingrese los datos solicitados","Editar Usuarios",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        int i;
        String pass1, pass2;
        boolean estado = false;
        String patente = "";
        ControladorEstaciona ce = new ControladorEstaciona();
        if(txtCodigoBarra.getText().length() > 0 && txtNombres.getText().length() > 0 && txtApPaterno.getText().length() > 0 && txtApMaterno.getText().length() > 0 &&
            txtTelefono.getText().length() > 0 && txtPatente1.getText().length() >0 && txtPatente2.getText().length() >0 && txtPatente3.getText().length() >0 && txtModelo.getText().length() > 0 &&
            txtColor.getText().length() > 0)
        {
            if(ce.verificarIDRUT(txtCodigoBarra.getText(), "0").equals("-1") && ce.verificarIDRUT("0", txtRut.getText()).equals("-1"))
            {
                if(chkPass.isSelected())
                {
                    pass1 = String.valueOf(txtPass.getPassword());
                    pass2 = String.valueOf(txtPass2.getPassword());
                    if(pass1.length() > 0 && pass2.length() > 0)
                    {
                        if(pass1.equals(pass2))
                        {
                            patente = txtPatente1.getText() + "*" + txtPatente2.getText() + "*" + txtPatente3.getText();
                            estado = ce.agregarUsuario(txtCodigoBarra.getText(), txtRut.getText(), txtNombres.getText(),
                                txtApPaterno.getText(), txtApMaterno.getText(), txtTelefono.getText(), cmbDiscap.getSelectedItem().toString(),
                                cmbRol.getSelectedItem().toString(), patente, txtModelo.getText(),
                                txtColor.getText());
                            ce.insertarPassAnd(txtCodigoBarra.getText(), pass1);
                            if(estado)
                            {
                                JOptionPane.showMessageDialog(null, "Ingreso realizado correctamente","Agregar Usuario",JOptionPane.INFORMATION_MESSAGE);
                                this.dispose();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Hubo un error al ingresar","Agregar Usuario",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Contraseñas no coinciden!","Agregar Usuario",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Favor ingrese los datos solicitados","Agregar Usuario",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else
                {
                    patente = txtPatente1.getText() + "*" + txtPatente2.getText() + "*" + txtPatente3.getText();
                    estado = ce.agregarUsuario(txtCodigoBarra.getText(), txtRut.getText(), txtNombres.getText(),
                        txtApPaterno.getText(), txtApMaterno.getText(), txtTelefono.getText(), cmbDiscap.getSelectedItem().toString(),
                        cmbRol.getSelectedItem().toString(), patente, txtModelo.getText(),
                        txtColor.getText());
                    if(estado)
                    {
                        JOptionPane.showMessageDialog(null, "Ingreso realizado correctamente","Agregar Usuario",JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Hubo un error al ingresar","Agregar Usuario",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else
            {
                if(!ce.verificarIDRUT(txtCodigoBarra.getText(), "0").equals("-1"))
                {
                    JOptionPane.showMessageDialog(null, "Codigo de barra ya existe!","Agregar Usuario",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "RUT ya existe","Agregar Usuario",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Favor ingrese los datos solicitados","Agregar Usuario",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void chkPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPassActionPerformed
        if(chkPass.isSelected())
        {
            txtPass.setEnabled(true);
            txtPass2.setEnabled(true);
        }
        else
        {
            txtPass.setEnabled(false);
            txtPass2.setEnabled(false);
        }
    }//GEN-LAST:event_chkPassActionPerformed

    private void chkPassStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkPassStateChanged

    }//GEN-LAST:event_chkPassStateChanged

    private void txtColorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorKeyTyped
        ElementosLimite el = new ElementosLimite();
        el.limiteCaracteres(evt, txtColor, 20);
    }//GEN-LAST:event_txtColorKeyTyped

    private void txtModeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyTyped
        ElementosLimite el = new ElementosLimite();
        el.limiteCaracteres(evt, txtModelo, 20);
    }//GEN-LAST:event_txtModeloKeyTyped

    private void txtPatente3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPatente3KeyTyped
        ElementosLimite el = new ElementosLimite();
        el.limiteCaracteres(evt, txtPatente3, 2);
    }//GEN-LAST:event_txtPatente3KeyTyped

    private void txtPatente2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPatente2KeyTyped
        ElementosLimite el = new ElementosLimite();
        el.limiteCaracteres(evt, txtPatente2, 2);
    }//GEN-LAST:event_txtPatente2KeyTyped

    private void txtPatente2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPatente2KeyReleased
        String may;
        may = txtPatente2.getText().toUpperCase();
        txtPatente2.setText(may);
    }//GEN-LAST:event_txtPatente2KeyReleased

    private void txtPatente1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPatente1KeyTyped
        ElementosLimite el = new ElementosLimite();
        el.limiteCaracteres(evt, txtPatente1, 2);
    }//GEN-LAST:event_txtPatente1KeyTyped

    private void txtPatente1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPatente1KeyReleased
        String may;
        may = txtPatente1.getText().toUpperCase();
        txtPatente1.setText(may);
    }//GEN-LAST:event_txtPatente1KeyReleased

    private void txtPatente1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPatente1KeyPressed

    }//GEN-LAST:event_txtPatente1KeyPressed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        ElementosLimite el = new ElementosLimite();
        el.soloNumeros(evt, txtTelefono, 12);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtApMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApMaternoKeyTyped
        ElementosLimite el = new ElementosLimite();
        el.limiteCaracteres(evt, txtApMaterno, 20);
    }//GEN-LAST:event_txtApMaternoKeyTyped

    private void txtApMaternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApMaternoKeyPressed

    }//GEN-LAST:event_txtApMaternoKeyPressed

    private void txtApPaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApPaternoKeyTyped
        ElementosLimite el = new ElementosLimite();
        el.limiteCaracteres(evt, txtApPaterno, 20);
    }//GEN-LAST:event_txtApPaternoKeyTyped

    private void txtApPaternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApPaternoKeyPressed

    }//GEN-LAST:event_txtApPaternoKeyPressed

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        ElementosLimite el = new ElementosLimite();
        el.limiteCaracteres(evt, txtNombres, 40);
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtNombresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyPressed

    }//GEN-LAST:event_txtNombresKeyPressed

    private void txtRutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutKeyTyped
        ElementosLimite el = new ElementosLimite();
        el.limiteCaracteres(evt, txtRut, 9);
    }//GEN-LAST:event_txtRutKeyTyped

    private void txtRutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutKeyPressed

    }//GEN-LAST:event_txtRutKeyPressed

    private void txtCodigoBarraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarraKeyTyped
        ElementosLimite el = new ElementosLimite();
        el.soloNumeros(evt, txtCodigoBarra, 10);
    }//GEN-LAST:event_txtCodigoBarraKeyTyped

    private void txtCodigoBarraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB)
        {
            txtRut.requestFocus();
        }
    }//GEN-LAST:event_txtCodigoBarraKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JCheckBox chkPass;
    private javax.swing.JComboBox<String> cmbDiscap;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtApMaterno;
    private javax.swing.JTextField txtApPaterno;
    private javax.swing.JTextField txtCodigoBarra;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtIDAuto;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtPass2;
    private javax.swing.JTextField txtPatente1;
    private javax.swing.JTextField txtPatente2;
    private javax.swing.JTextField txtPatente3;
    private javax.swing.JTextField txtRut;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
