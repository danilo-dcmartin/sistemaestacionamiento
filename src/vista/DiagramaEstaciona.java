/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorEstaciona;
import funcionpanel.CambioJornada;
import funcionpanel.ElementosLimite;
import funcionpanel.EscuchaBDPlaza;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.ModeloEstaciona;

/**
 *
 * @author Danilo Martinez
 */
public class DiagramaEstaciona extends javax.swing.JInternalFrame {

    /**
     * Creates new form DiagramaEstaciona
     */
    public DiagramaEstaciona() {
        ControladorEstaciona ce = new ControladorEstaciona();
        initComponents();
        ocultarInfoCli();
        this.setTitle("Gestion estacionamiento - Usuario: " + ce.optenerNombreRol());
        EscuchaBDPlaza esc = new EscuchaBDPlaza(this);
        CambioJornada cj = new CambioJornada(this);
        Thread tesc = new Thread(esc);
        Thread tcj = new Thread(cj);
        tesc.start();
        tcj.start();
    }
    public void mostrarInfoCli()
    {
        lblId.setVisible(true);
        lblRut.setVisible(true);
        lblNombre.setVisible(true);
        lblRol.setVisible(true);
        lblPatente.setVisible(true);
        lblReserva.setVisible(true);
    }
    public void ocultarInfoCli()
    {
        lblId.setVisible(false);
        lblRut.setVisible(false);
        lblNombre.setVisible(false);
        lblRol.setVisible(false);
        lblPatente.setVisible(false);
        lblReserva.setVisible(false);
    }
    
    public void actualizarLabels(String id)
    {
        ControladorEstaciona ce = new ControladorEstaciona();
        ArrayList<String> datosP = new ArrayList<String>();
        datosP = ce.infoUsuario(id);
        lblId.setText("ID: " + datosP.get(0));
        lblRut.setText("RUT: " + datosP.get(1));
        lblNombre.setText("NOMBRE: " + datosP.get(2));
        lblRol.setText("ROL: " + datosP.get(3));
        lblPatente.setText(("PATENTE: " +  datosP.get(4)).toUpperCase());
        if(datosP.get(5).equals("0"))
        {
            lblReserva.setText("RESERVA: SIN RESERVA");
        }
        else
        {
            lblReserva.setText("RESERVA: RESERVA - PLAZA " + datosP.get(5));
        }
        txtIDRut.setText("");
    }
    
    public void reservaPlazas (int idP) throws StringIndexOutOfBoundsException
    {
        int respuesta, i;
        ControladorEstaciona ce = new ControladorEstaciona();
        ArrayList<String> plaza = new ArrayList<String>();
        plaza = ce.datosPlaza(idP); // datos de la plaza seleccionada
        if(lblId.isVisible())
        {
            if(lblReserva.getText().substring(9).equals("SIN RESERVA"))
            {
                if(plaza.get(1).equals("1") || (!plaza.get(2).equals("0"))) // ocupado - id-barra
                {
                    JOptionPane.showMessageDialog(null, "La plaza " + idP + " ya cuenta con una reserva y/o esta ocupado","Confirmar Reserva",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    respuesta = JOptionPane.showConfirmDialog(null, "¿Confirmar reserva?\n" + lblId.getText() 
                        + "\n" + lblNombre.getText() + "\n" + lblRol.getText() + "\n" + lblPatente.getText(), "Confirmar Reserva", JOptionPane.YES_NO_OPTION);
                    if(JOptionPane.YES_OPTION == respuesta)
                    {
                        i = ce.reservarPlaza(lblId.getText().substring(4), idP);
                        if(i == 1)
                        {
                            ce.registroHistorialPlaza(lblId.getText().substring(4), idP);
                            JOptionPane.showMessageDialog(null, "Reserva en la plaza " + idP + " completada","Confirmar Reserva",JOptionPane.INFORMATION_MESSAGE);
                            actualizarLabels(lblId.getText().substring(4));
                        }
                        else
                        {
                            // error
                        }
                    }
                }
            }
            else
            {
                if(lblReserva.getText().substring(25).equals(String.valueOf(idP))) // compara plaza del label con la seleccionada, verifica si se selecciono la plaza reservada por el cliente
                {
                    if(plaza.get(1).equals("0")) // ocupado
                    {
                        respuesta = JOptionPane.showConfirmDialog(null, "¿Eliminar reserva de usuario " + lblId.getText() + "?", "Eliminar Reserva", JOptionPane.YES_NO_OPTION);
                        if(JOptionPane.YES_OPTION == respuesta)
                        {
                            i = ce.reservarPlaza("0", idP);
                            if(i == 1)
                            {
                                JOptionPane.showMessageDialog(null, "Reserva eliminada","Confirmar Reserva",JOptionPane.INFORMATION_MESSAGE);
                                actualizarLabels(lblId.getText().substring(4));
                            }
                            else
                            {
                                // error
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar reserva, un vehiculo esta ocupando la \nplaza " + idP + ", debe estar desocupada la plaza para eliminar la reserva","Confirmar Reserva",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "El usuario ya cuenta con una reserva","Confirmar Reserva",JOptionPane.ERROR_MESSAGE);
                    // SE DEBERIA BORRAR POR PISTOLA
                }
            }
        }
        else
        {
            ArrayList<String> infoUsuario = new ArrayList<String>();
            if(plaza.get(1).equals("0") && plaza.get(2).equals("0")) // ocupado - id_plaza
            {
                // plaza disponible
                JOptionPane.showMessageDialog(null, "INFORMACION PLAZA " + idP + ":\nESTADO: DISPONIBLE","Informacion Plaza",JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                if(plaza.get(1).equals("1") && (!plaza.get(2).equals("0"))) // ocupado - id_plaza
                {
                    // plaza reservada y ocupada
                    infoUsuario = ce.infoUsuario(plaza.get(2));
                    JOptionPane.showMessageDialog(null, "INFORMACION PLAZA " + idP + ":\n"
                            + "ESTADO: OCUPADO CON RESERVA\n"
                            + "ID: "+ infoUsuario.get(0) +"\n"
                            + "RUT: "+ infoUsuario.get(1) +"\n"
                            + "NOMBRE: "+ infoUsuario.get(2) +"\n"
                            + "ROL: "+ infoUsuario.get(3) +"\n"
                            + "PATENTE: "+ infoUsuario.get(4) +"","Informacion Plaza",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    if(plaza.get(1).equals("1")) // ocupado
                    {
                        // plaza ocupada pero no reservada
                        JOptionPane.showMessageDialog(null, "INFORMACION PLAZA " + idP + ":\nESTADO: OCUPADO SIN RESERVA","Informacion Plaza",JOptionPane.INFORMATION_MESSAGE);
                    }
                    if(!(plaza.get(2).equals("0"))) // id_plaza
                    {
                        // plaza reservada pero no ocupada
                        infoUsuario = ce.infoUsuario(plaza.get(2));
                        int seleccion = JOptionPane.showOptionDialog(null, "INFORMACION PLAZA " + idP + ":\n"
                                + "ESTADO: RESERVA PLAZA\n"
                                + "ID: "+ infoUsuario.get(0) +"\n"
                                + "RUT: "+ infoUsuario.get(1) +"\n"
                                + "NOMBRE: "+ infoUsuario.get(2) +"\n"
                                + "ROL: "+ infoUsuario.get(3) +"\n"
                                + "PATENTE: "+ infoUsuario.get(4) +"", "Informacion Plaza", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"Aceptar","Cancelar reserva"}, "Aceptar");
                        if(seleccion == 1)
                        {
                            respuesta = JOptionPane.showConfirmDialog(null, "¿Eliminar reserva de usuario " + infoUsuario.get(0) + "?", "Eliminar Reserva", JOptionPane.YES_NO_OPTION);
                            if(JOptionPane.YES_OPTION == respuesta)
                            {
                                i = ce.reservarPlaza("0", idP);
                                if(i == 1)
                                {
                                    JOptionPane.showMessageDialog(null, "Reserva eliminada","Confirmar Reserva",JOptionPane.INFORMATION_MESSAGE);
                                    actualizarLabels(lblId.getText().substring(4));
                                }
                                else
                                {
                                    // error
                                }
                            }
                        }
                    }
                }
            }
        }
        buttonGroup1.clearSelection();
        rbtnId.setSelected(true);
        txtIDRut.setText("");
        txtIDRut.requestFocus();
    }
    
    public void bloqCambJor()
    {
        String rol, id;
        id = lblId.getText().substring(4);
        rol = lblRol.getText().substring(5);
        ControladorEstaciona ce = new ControladorEstaciona();
        CambioJornada cj = new CambioJornada();
        if(ce.esDisc(id)) // disc
        {
            if(cj.getJornadaBtn() == 1) // diurno
            {
                switch(rol)
                {
                    case "administrativo":
                        plaTotal(false);
                        plaAdminDiur(true);
                        plaDiscapacitados(true);
                        break;
                    case "alumno":
                        plaTotal(false);
                        plaAlumDiur(true);
                        plaDiscapacitados(true);
                        break;
                    case "docente":
                        plaTotal(false);
                        plaDocenDiur(true);
                        plaDiscapacitados(true);
                        break;
                    case "ejecutivo":
                        plaTotal(false);
                        plaEjecDiur(true);
                        plaDiscapacitados(true);
                        break;
                    case "visita":
                        plaTotal(false);
                        plaVisitDiur(true);
                        break;
                    case "deshabilitado":
                        plaTotal(false);
                        JOptionPane.showMessageDialog(null, "Usuario se encuentra deshabilitado para utilizar el estacionamiento","Reserva Plazas",JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
            else
            {
                switch(rol)
                {
                    case "administrativo":
                        plaTotal(false);
                        plaAdminVesp(true);
                        plaDiscapacitados(true);
                        break;
                    case "alumno":
                        plaTotal(false);
                        plaAlumVesp(true);
                        plaDiscapacitados(true);
                        break;
                    case "docente":
                        plaTotal(false);
                        plaDocenVesp(true);
                        plaDiscapacitados(true);
                        break;
                    case "ejecutivo":
                        plaTotal(false);
                        plaEjecVesp(true);
                        plaDiscapacitados(true);
                        break;
                    case "visita":
                        plaTotal(false);
                        JOptionPane.showMessageDialog(null, "Plazas para visitas no disponible en jornada vespertina","Reserva Plazas",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "deshabilitado":
                        plaTotal(false);
                        JOptionPane.showMessageDialog(null, "Usuario se encuentra deshabilitado para utilizar el estacionamiento","Reserva Plazas",JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
        }
        else
        {
            if(cj.getJornadaBtn() == 1) // diurno
            {
                switch(rol)
                {
                    case "administrativo":
                        plaTotal(false);
                        plaAdminDiur(true);
                        break;
                    case "alumno":
                        plaTotal(false);
                        plaAlumDiur(true);
                        break;
                    case "docente":
                        plaTotal(false);
                        plaDocenDiur(true);
                        break;
                    case "ejecutivo":
                        plaTotal(false);
                        plaEjecDiur(true);
                        break;
                    case "visita":
                        plaTotal(false);
                        plaVisitDiur(true);
                        break;
                    case "deshabilitado":
                        plaTotal(false);
                        JOptionPane.showMessageDialog(null, "Usuario se encuentra deshabilitado para utilizar el estacionamiento","Reserva Plazas",JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
            else
            {
                switch(rol)
                {
                    case "administrativo":
                        plaTotal(false);
                        plaAdminVesp(true);
                        break;
                    case "alumno":
                        plaTotal(false);
                        plaAlumVesp(true);
                        break;
                    case "docente":
                        plaTotal(false);
                        plaDocenVesp(true);
                        break;
                    case "ejecutivo":
                        plaTotal(false);
                        plaEjecVesp(true);
                        break;
                    case "visita":
                        plaTotal(false);
                        JOptionPane.showMessageDialog(null, "Plazas para visitas no disponible en jornada vespertina","Reserva Plazas",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "deshabilitado":
                        plaTotal(false);
                        JOptionPane.showMessageDialog(null, "Usuario se encuentra deshabilitado para utilizar el estacionamiento","Reserva Plazas",JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
        }
        
    }
    
    public void datosCliente()
    {
        String id, rut, idPlaza;
        String laIDUsuario = "";
        boolean acceso = false;
        ControladorEstaciona ce = new ControladorEstaciona();
        ArrayList<String> datosP = new ArrayList<String>();
        if(!lblId.isVisible())
        {
            if(rbtnId.isSelected())
            {
                id = txtIDRut.getText();
                if(id.length() > 0)
                {
                    laIDUsuario = ce.verificarIDRUT(id, "0");
                    if(laIDUsuario.equals("-1"))
                    {
                        JOptionPane.showMessageDialog(null, "La ID no existe!","Aviso",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        acceso = true;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ingrese los datos solicitados!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                rut = txtIDRut.getText();
                if(rut.length() > 0)
                {
                    laIDUsuario = ce.verificarIDRUT("0", rut);
                    if(laIDUsuario.equals("-1"))
                    {
                        JOptionPane.showMessageDialog(null, "El RUT no existe!","Aviso",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        acceso = true;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ingrese los datos solicitados!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(acceso)
            {
                mostrarInfoCli();
                datosP = ce.infoUsuario(laIDUsuario);
                lblId.setText(lblId.getText() + " " + datosP.get(0));
                lblRut.setText(lblRut.getText() + " " + datosP.get(1));
                lblNombre.setText(lblNombre.getText()+ " " + datosP.get(2));
                lblRol.setText(lblRol.getText() + " " + datosP.get(3));
                lblPatente.setText((lblPatente.getText() + " " +  datosP.get(4)).toUpperCase());
                if(datosP.get(5).equals("0"))
                {
                    lblReserva.setText(lblReserva.getText() + " SIN RESERVA");
                }
                else
                {
                    lblReserva.setText(lblReserva.getText() + " RESERVA - PLAZA " + datosP.get(5));
                }
                bloqCambJor();
            }
        }
        else
        {
            if(rbtnId.isSelected())
            {
                id = txtIDRut.getText();
                if(id.length() > 0)
                {
                    laIDUsuario = ce.verificarIDRUT(id, "0");
                    if(laIDUsuario.equals("-1"))
                    {
                        JOptionPane.showMessageDialog(null, "La ID no existe!","Aviso",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        acceso = true;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ingrese los datos solicitados!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                rut = txtIDRut.getText();
                if(rut.length() > 0)
                {
                    laIDUsuario = ce.verificarIDRUT("0", rut);
                    if(laIDUsuario.equals("-1"))
                    {
                        JOptionPane.showMessageDialog(null, "El RUT no existe!","Aviso",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        acceso = true;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ingrese los datos solicitados!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(acceso)
            {
                mostrarInfoCli();
                datosP = ce.infoUsuario(laIDUsuario);
                lblId.setText("ID: " + datosP.get(0));
                lblRut.setText("RUT: " + datosP.get(1));
                lblNombre.setText("NOMBRE: " + datosP.get(2));
                lblRol.setText("ROL: " + datosP.get(3));
                lblPatente.setText(("PATENTE: " +  datosP.get(4)).toUpperCase());
                if(datosP.get(5).equals("0"))
                {
                    lblReserva.setText("RESERVA: SIN RESERVA");
                }
                else
                {
                    lblReserva.setText("RESERVA: RESERVA - PLAZA " + datosP.get(5));
                }
                bloqCambJor();
            }
        }
        buttonGroup1.clearSelection();
        rbtnId.setSelected(true);
        txtIDRut.setText("");
        txtIDRut.requestFocus();
    }
    public void plaTotal(boolean estado)
    {
        btn1.setEnabled(estado);
        btn2.setEnabled(estado);
        btn3.setEnabled(estado);
        btn4.setEnabled(estado);
        btn5.setEnabled(estado);
        btn6.setEnabled(estado);
        btn7.setEnabled(estado);
        btn8.setEnabled(estado);
        btn9.setEnabled(estado);
        btn10.setEnabled(estado);
        btn11.setEnabled(estado);
        btn12.setEnabled(estado);
        btn13.setEnabled(estado);
        btn14.setEnabled(estado);
        btn15.setEnabled(estado);
        btn16.setEnabled(estado);
        btn17.setEnabled(estado);
        btn18.setEnabled(estado);
        btn19.setEnabled(estado);
        btn20.setEnabled(estado);
        btn21.setEnabled(estado);
        btn22.setEnabled(estado);
        btn23.setEnabled(estado);
        btn24.setEnabled(estado);
        btn25.setEnabled(estado);
        btn26.setEnabled(estado);
        btn27.setEnabled(estado);
        btn28.setEnabled(estado);
        btn29.setEnabled(estado);
        btn30.setEnabled(estado);
        btn31.setEnabled(estado);
        btn32.setEnabled(estado);
        btn33.setEnabled(estado);
        btn34.setEnabled(estado);
        btn35.setEnabled(estado);
        btn36.setEnabled(estado);
        btn37.setEnabled(estado);
        btn38.setEnabled(estado);
        btn39.setEnabled(estado);
        btn40.setEnabled(estado);
        btn41.setEnabled(estado);
        btn42.setEnabled(estado);
        btn43.setEnabled(estado);
        btn44.setEnabled(estado);
        btn45.setEnabled(estado);
        btn46.setEnabled(estado);
        btn47.setEnabled(estado);
        btn48.setEnabled(estado);
        btn49.setEnabled(estado);
        btn50.setEnabled(estado);
        btn51.setEnabled(estado);
        btn52.setEnabled(estado);
        btn53.setEnabled(estado);
        btn54.setEnabled(estado);
        btn55.setEnabled(estado);
        btn56.setEnabled(estado);
        btn57.setEnabled(estado);
        btn58.setEnabled(estado);
        btn59.setEnabled(estado);
        btn60.setEnabled(estado);
        btn61.setEnabled(estado);
        btn62.setEnabled(estado);
        btn63.setEnabled(estado);
        btn64.setEnabled(estado);
        btn65.setEnabled(estado);
        btn66.setEnabled(estado);
        btn67.setEnabled(estado);
        btn68.setEnabled(estado);
        btn69.setEnabled(estado);
        btn70.setEnabled(estado);
        btn71.setEnabled(estado);
        btn72.setEnabled(estado);
        btn73.setEnabled(estado);
        btn74.setEnabled(estado);
        btn75.setEnabled(estado);
        btn76.setEnabled(estado);
        btn77.setEnabled(estado);
        btn78.setEnabled(estado);
        btn79.setEnabled(estado);
        btn80.setEnabled(estado);
        btn81.setEnabled(estado);
        btn82.setEnabled(estado);
        btn83.setEnabled(estado);
        btn84.setEnabled(estado);
        btn85.setEnabled(estado);
        btn86.setEnabled(estado);
        btn87.setEnabled(estado);
        btn88.setEnabled(estado);
        btn89.setEnabled(estado);
        btn90.setEnabled(estado);
        btn91.setEnabled(estado);
        btn92.setEnabled(estado);
        btn93.setEnabled(estado);
        btn94.setEnabled(estado);
        btn95.setEnabled(estado);
        btn96.setEnabled(estado);
        btn97.setEnabled(estado);
        btn98.setEnabled(estado);
        btn99.setEnabled(estado);
        btn100.setEnabled(estado);
        btn101.setEnabled(estado);
        btn102.setEnabled(estado);
        btn103.setEnabled(estado);
        btn104.setEnabled(estado);
        btn105.setEnabled(estado);
        btn106.setEnabled(estado);
        btn107.setEnabled(estado);
        btn108.setEnabled(estado);
        btn109.setEnabled(estado);
        btn110.setEnabled(estado);
        btn111.setEnabled(estado);
        btn112.setEnabled(estado);
        btn113.setEnabled(estado);
        btn114.setEnabled(estado);
        btn115.setEnabled(estado);
        btn116.setEnabled(estado);
        btn117.setEnabled(estado);
        btn118.setEnabled(estado);
        btn119.setEnabled(estado);
        btn120.setEnabled(estado);
        btn121.setEnabled(estado);
        btn122.setEnabled(estado);
        btn123.setEnabled(estado);
        btn124.setEnabled(estado);
        btn125.setEnabled(estado);
        btn126.setEnabled(estado);
        btn127.setEnabled(estado);
        btn128.setEnabled(estado);
        btn129.setEnabled(estado);
        btn130.setEnabled(estado);
        btn131.setEnabled(estado);
        btn132.setEnabled(estado);
        btn133.setEnabled(estado);
        btn134.setEnabled(estado);
        btn135.setEnabled(estado);
        btn136.setEnabled(estado);
        btn137.setEnabled(estado);
        btn138.setEnabled(estado);
        btn139.setEnabled(estado);
        btn140.setEnabled(estado);
        btn141.setEnabled(estado);
        btn142.setEnabled(estado);
        btn143.setEnabled(estado);
        btn144.setEnabled(estado);
        btn145.setEnabled(estado);
        btn146.setEnabled(estado);
        btn147.setEnabled(estado);
        btn148.setEnabled(estado);
        btn149.setEnabled(estado);
        btn150.setEnabled(estado);
        btn151.setEnabled(estado);
        btn152.setEnabled(estado);
        btn153.setEnabled(estado);
        btn154.setEnabled(estado);
        btn155.setEnabled(estado);
        btn156.setEnabled(estado);
        btn157.setEnabled(estado);
        btn158.setEnabled(estado);
        btn159.setEnabled(estado);
        btn160.setEnabled(estado);
        btn161.setEnabled(estado);
        btn162.setEnabled(estado);
        btn163.setEnabled(estado);
        btn164.setEnabled(estado);
        btn165.setEnabled(estado);
        btn166.setEnabled(estado);
        btn167.setEnabled(estado);
        btn168.setEnabled(estado);
        btn169.setEnabled(estado);
        btn170.setEnabled(estado);
        btn171.setEnabled(estado);
        btn172.setEnabled(estado);
        btn173.setEnabled(estado);
        btn174.setEnabled(estado);
        btn175.setEnabled(estado);
        btn176.setEnabled(estado);
        btn177.setEnabled(estado);
        btn178.setEnabled(estado);
        btn179.setEnabled(estado);
        btn180.setEnabled(estado);
        btn181.setEnabled(estado);
        btn182.setEnabled(estado);
        btn183.setEnabled(estado);
        btn184.setEnabled(estado);
        btn185.setEnabled(estado);
        btn186.setEnabled(estado);
        btn187.setEnabled(estado);
        btn188.setEnabled(estado);
        btn189.setEnabled(estado);
        btn190.setEnabled(estado);
        btn191.setEnabled(estado);
        btn192.setEnabled(estado);
        btn193.setEnabled(estado);
        btn194.setEnabled(estado);
        btn195.setEnabled(estado);
        btn196.setEnabled(estado);
        btn197.setEnabled(estado);
    }
    /***************************/
    public void plaAdminDiur(boolean estado)
    {
        btn21.setEnabled(estado);
        btn22.setEnabled(estado);
        btn23.setEnabled(estado);
        btn24.setEnabled(estado);
        btn25.setEnabled(estado);
        btn26.setEnabled(estado);
        btn43.setEnabled(estado);
        btn44.setEnabled(estado);
        btn45.setEnabled(estado);
        btn46.setEnabled(estado);
        btn47.setEnabled(estado);
        btn48.setEnabled(estado);
        btn49.setEnabled(estado);
        btn50.setEnabled(estado);
        btn51.setEnabled(estado);
        btn52.setEnabled(estado);
        btn53.setEnabled(estado);
        btn54.setEnabled(estado);
        btn55.setEnabled(estado);
        btn56.setEnabled(estado);
        btn57.setEnabled(estado);
        btn58.setEnabled(estado);
        btn59.setEnabled(estado);
        btn60.setEnabled(estado);
        btn61.setEnabled(estado);
        btn62.setEnabled(estado);
        btn63.setEnabled(estado);
        btn64.setEnabled(estado);
        btn186.setEnabled(estado);
        btn187.setEnabled(estado);
        btn188.setEnabled(estado);
        btn189.setEnabled(estado);
        btn190.setEnabled(estado);
        btn191.setEnabled(estado);
        btn192.setEnabled(estado);

    }
    public void plaEjecDiur(boolean estado)
    {
        btn27.setEnabled(estado);
        btn28.setEnabled(estado);
        btn29.setEnabled(estado);
        btn30.setEnabled(estado);
        btn31.setEnabled(estado);
        btn32.setEnabled(estado);
        btn33.setEnabled(estado);
        btn34.setEnabled(estado);
        btn35.setEnabled(estado);
        btn36.setEnabled(estado);
        btn37.setEnabled(estado);
        btn38.setEnabled(estado);
        btn39.setEnabled(estado);
        btn40.setEnabled(estado);
        btn41.setEnabled(estado);
        btn42.setEnabled(estado);

    }
    public void plaAlumDiur(boolean estado)
    {
        btn1.setEnabled(estado);
        btn2.setEnabled(estado);
        btn3.setEnabled(estado);
        btn4.setEnabled(estado);
        btn5.setEnabled(estado);
        btn6.setEnabled(estado);
        btn7.setEnabled(estado);
        btn8.setEnabled(estado);
        btn9.setEnabled(estado);
        btn10.setEnabled(estado);
        btn11.setEnabled(estado);
        btn12.setEnabled(estado);
        btn13.setEnabled(estado);
        btn14.setEnabled(estado);
        btn15.setEnabled(estado);
        btn16.setEnabled(estado);
        btn17.setEnabled(estado);
        btn18.setEnabled(estado);
        btn19.setEnabled(estado);
        btn20.setEnabled(estado);
        btn109.setEnabled(estado);
        btn110.setEnabled(estado);
        btn111.setEnabled(estado);
        btn112.setEnabled(estado);
        btn113.setEnabled(estado);
        btn114.setEnabled(estado);
        btn115.setEnabled(estado);
        btn116.setEnabled(estado);
        btn117.setEnabled(estado);
        btn118.setEnabled(estado);
        btn131.setEnabled(estado);
        btn132.setEnabled(estado);
        btn133.setEnabled(estado);
        btn134.setEnabled(estado);
        btn135.setEnabled(estado);
        btn136.setEnabled(estado);
        btn137.setEnabled(estado);
        btn138.setEnabled(estado);
        btn139.setEnabled(estado);
        btn140.setEnabled(estado);
        btn141.setEnabled(estado);
        btn142.setEnabled(estado);
        btn143.setEnabled(estado);
        btn144.setEnabled(estado);
        btn145.setEnabled(estado);
        btn146.setEnabled(estado);
        btn147.setEnabled(estado);
        btn148.setEnabled(estado);
        btn149.setEnabled(estado);
        btn150.setEnabled(estado);
        btn151.setEnabled(estado);
        btn152.setEnabled(estado);
        btn153.setEnabled(estado);
        btn154.setEnabled(estado);
        btn155.setEnabled(estado);
        btn156.setEnabled(estado);
        btn157.setEnabled(estado);
        btn158.setEnabled(estado);
        btn159.setEnabled(estado);
        btn160.setEnabled(estado);
        btn161.setEnabled(estado);
        btn162.setEnabled(estado);
        btn163.setEnabled(estado);
        btn164.setEnabled(estado);
        btn165.setEnabled(estado);
        btn166.setEnabled(estado);
        btn167.setEnabled(estado);
        btn168.setEnabled(estado);
        btn169.setEnabled(estado);
        btn170.setEnabled(estado);
        btn171.setEnabled(estado);
        btn172.setEnabled(estado);
        btn173.setEnabled(estado);
        btn174.setEnabled(estado);
        btn175.setEnabled(estado);
        btn176.setEnabled(estado);
        btn177.setEnabled(estado);
        btn178.setEnabled(estado);
        btn179.setEnabled(estado);
        btn180.setEnabled(estado);
        btn181.setEnabled(estado);
        btn182.setEnabled(estado);
        btn183.setEnabled(estado);
        btn184.setEnabled(estado);
        btn185.setEnabled(estado);
    }
    public void plaDocenDiur(boolean estado)
    {
        btn65.setEnabled(estado);
        btn66.setEnabled(estado);
        btn67.setEnabled(estado);
        btn68.setEnabled(estado);
        btn69.setEnabled(estado);
        btn70.setEnabled(estado);
        btn71.setEnabled(estado);
        btn72.setEnabled(estado);
        btn73.setEnabled(estado);
        btn74.setEnabled(estado);
        btn75.setEnabled(estado);
        btn76.setEnabled(estado);
        btn77.setEnabled(estado);
        btn78.setEnabled(estado);
        btn79.setEnabled(estado);
        btn80.setEnabled(estado);
        btn81.setEnabled(estado);
        btn82.setEnabled(estado);
        btn83.setEnabled(estado);
        btn84.setEnabled(estado);
        btn85.setEnabled(estado);
        btn86.setEnabled(estado);
        btn87.setEnabled(estado);
        btn88.setEnabled(estado);
        btn89.setEnabled(estado);
        btn90.setEnabled(estado);
        btn91.setEnabled(estado);
        btn92.setEnabled(estado);
        btn93.setEnabled(estado);
        btn94.setEnabled(estado);
        btn95.setEnabled(estado);
        btn96.setEnabled(estado);
        btn97.setEnabled(estado);
        btn98.setEnabled(estado);
        btn99.setEnabled(estado);
        btn100.setEnabled(estado);
        btn101.setEnabled(estado);
        btn102.setEnabled(estado);
        btn103.setEnabled(estado);
        btn104.setEnabled(estado);
        btn105.setEnabled(estado);
        btn106.setEnabled(estado);
        btn107.setEnabled(estado);
        btn108.setEnabled(estado);
        btn119.setEnabled(estado);
        btn120.setEnabled(estado);
        btn121.setEnabled(estado);
        btn122.setEnabled(estado);
        btn123.setEnabled(estado);
        btn124.setEnabled(estado);
        btn125.setEnabled(estado);
        btn126.setEnabled(estado);
        btn127.setEnabled(estado);
        btn128.setEnabled(estado);
        btn129.setEnabled(estado);
        btn130.setEnabled(estado);
    }
    /**************************/
    public void plaAdminVesp(boolean estado)
    {
        btn21.setEnabled(estado);
        btn22.setEnabled(estado);
        btn23.setEnabled(estado);
        btn24.setEnabled(estado);
        btn25.setEnabled(estado);
        btn26.setEnabled(estado);
    }
    public void plaEjecVesp(boolean estado)
    {
        btn27.setEnabled(estado);
        btn28.setEnabled(estado);
        btn29.setEnabled(estado);
        btn30.setEnabled(estado);
        btn31.setEnabled(estado);
        btn32.setEnabled(estado);
        btn33.setEnabled(estado);
        btn34.setEnabled(estado);
        btn35.setEnabled(estado);
        btn36.setEnabled(estado);
        btn37.setEnabled(estado);
        btn38.setEnabled(estado);
        btn39.setEnabled(estado);
        btn40.setEnabled(estado);
        btn41.setEnabled(estado);
        btn42.setEnabled(estado);
    }
    public void plaAlumVesp(boolean estado)
    {
        btn1.setEnabled(estado);
        btn2.setEnabled(estado);
        btn3.setEnabled(estado);
        btn4.setEnabled(estado);
        btn5.setEnabled(estado);
        btn6.setEnabled(estado);
        btn7.setEnabled(estado);
        btn8.setEnabled(estado);
        btn9.setEnabled(estado);
        btn10.setEnabled(estado);
        btn11.setEnabled(estado);
        btn12.setEnabled(estado);
        btn13.setEnabled(estado);
        btn14.setEnabled(estado);
        btn15.setEnabled(estado);
        btn16.setEnabled(estado);
        btn17.setEnabled(estado);
        btn18.setEnabled(estado);
        btn19.setEnabled(estado);
        btn20.setEnabled(estado);
        btn43.setEnabled(estado);
        btn44.setEnabled(estado);
        btn45.setEnabled(estado);
        btn46.setEnabled(estado);
        btn47.setEnabled(estado);
        btn48.setEnabled(estado);
        btn49.setEnabled(estado);
        btn50.setEnabled(estado);
        btn51.setEnabled(estado);
        btn52.setEnabled(estado);
        btn53.setEnabled(estado);
        btn54.setEnabled(estado);
        btn55.setEnabled(estado);
        btn56.setEnabled(estado);
        btn57.setEnabled(estado);
        btn58.setEnabled(estado);
        btn59.setEnabled(estado);
        btn60.setEnabled(estado);
        btn61.setEnabled(estado);
        btn62.setEnabled(estado);
        btn63.setEnabled(estado);
        btn64.setEnabled(estado);
        btn65.setEnabled(estado);
        btn66.setEnabled(estado);
        btn67.setEnabled(estado);
        btn68.setEnabled(estado);
        btn69.setEnabled(estado);
        btn70.setEnabled(estado);
        btn71.setEnabled(estado);
        btn72.setEnabled(estado);
        btn73.setEnabled(estado);
        btn74.setEnabled(estado);
        btn75.setEnabled(estado);
        btn76.setEnabled(estado);
        btn77.setEnabled(estado);
        btn78.setEnabled(estado);
        btn79.setEnabled(estado);
        btn80.setEnabled(estado);
        btn81.setEnabled(estado);
        btn82.setEnabled(estado);
        btn83.setEnabled(estado);
        btn84.setEnabled(estado);
        btn85.setEnabled(estado);
        btn86.setEnabled(estado);
        btn131.setEnabled(estado);
        btn132.setEnabled(estado);
        btn133.setEnabled(estado);
        btn134.setEnabled(estado);
        btn135.setEnabled(estado);
        btn136.setEnabled(estado);
        btn137.setEnabled(estado);
        btn138.setEnabled(estado);
        btn139.setEnabled(estado);
        btn140.setEnabled(estado);
        btn141.setEnabled(estado);
        btn142.setEnabled(estado);
        btn143.setEnabled(estado);
        btn144.setEnabled(estado);
        btn145.setEnabled(estado);
        btn146.setEnabled(estado);
        btn147.setEnabled(estado);
        btn148.setEnabled(estado);
        btn149.setEnabled(estado);
        btn150.setEnabled(estado);
        btn151.setEnabled(estado);
        btn152.setEnabled(estado);
        btn153.setEnabled(estado);
        btn154.setEnabled(estado);
        btn155.setEnabled(estado);
        btn156.setEnabled(estado);
        btn157.setEnabled(estado);
        btn158.setEnabled(estado);
        btn159.setEnabled(estado);
        btn160.setEnabled(estado);
        btn161.setEnabled(estado);
        btn162.setEnabled(estado);
        btn163.setEnabled(estado);
        btn164.setEnabled(estado);
        btn165.setEnabled(estado);
        btn166.setEnabled(estado);
        btn167.setEnabled(estado);
        btn168.setEnabled(estado);
        btn169.setEnabled(estado);
        btn170.setEnabled(estado);
        btn171.setEnabled(estado);
        btn172.setEnabled(estado);
        btn173.setEnabled(estado);
        btn174.setEnabled(estado);
        btn175.setEnabled(estado);
        btn176.setEnabled(estado);
        btn177.setEnabled(estado);
        btn178.setEnabled(estado);
        btn179.setEnabled(estado);
        btn180.setEnabled(estado);
        btn181.setEnabled(estado);
        btn182.setEnabled(estado);
        btn183.setEnabled(estado);
        btn184.setEnabled(estado);
        btn185.setEnabled(estado);
        btn186.setEnabled(estado);
        btn187.setEnabled(estado);
        btn188.setEnabled(estado);
        btn189.setEnabled(estado);
        btn190.setEnabled(estado);
        btn191.setEnabled(estado);
        btn192.setEnabled(estado);
        btn193.setEnabled(estado);
        btn194.setEnabled(estado);
    }
    public void plaDocenVesp(boolean estado)
    {
        btn87.setEnabled(estado);
        btn88.setEnabled(estado);
        btn89.setEnabled(estado);
        btn90.setEnabled(estado);
        btn91.setEnabled(estado);
        btn92.setEnabled(estado);
        btn93.setEnabled(estado);
        btn94.setEnabled(estado);
        btn95.setEnabled(estado);
        btn96.setEnabled(estado);
        btn97.setEnabled(estado);
        btn98.setEnabled(estado);
        btn99.setEnabled(estado);
        btn100.setEnabled(estado);
        btn101.setEnabled(estado);
        btn102.setEnabled(estado);
        btn103.setEnabled(estado);
        btn104.setEnabled(estado);
        btn105.setEnabled(estado);
        btn106.setEnabled(estado);
        btn107.setEnabled(estado);
        btn108.setEnabled(estado);
        btn109.setEnabled(estado);
        btn110.setEnabled(estado);
        btn111.setEnabled(estado);
        btn112.setEnabled(estado);
        btn113.setEnabled(estado);
        btn114.setEnabled(estado);
        btn115.setEnabled(estado);
        btn116.setEnabled(estado);
        btn117.setEnabled(estado);
        btn118.setEnabled(estado);
        btn119.setEnabled(estado);
        btn120.setEnabled(estado);
        btn121.setEnabled(estado);
        btn122.setEnabled(estado);
        btn123.setEnabled(estado);
        btn124.setEnabled(estado);
        btn125.setEnabled(estado);
        btn126.setEnabled(estado);
        btn127.setEnabled(estado);
        btn128.setEnabled(estado);
        btn129.setEnabled(estado);
        btn130.setEnabled(estado);
    }
    /*********************************************/
    public void plaVisitDiur(boolean estado)
    {
        btn193.setEnabled(estado);
        btn194.setEnabled(estado);
    }
    public void plaDiscapacitados(boolean estado)
    {
        btn195.setEnabled(estado);
        btn196.setEnabled(estado);
        btn197.setEnabled(estado);
    }
    /******************************************/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn28 = new javax.swing.JButton();
        btn29 = new javax.swing.JButton();
        btn30 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn36 = new javax.swing.JButton();
        btn37 = new javax.swing.JButton();
        btn38 = new javax.swing.JButton();
        btn39 = new javax.swing.JButton();
        btn40 = new javax.swing.JButton();
        btn41 = new javax.swing.JButton();
        btn42 = new javax.swing.JButton();
        lbl21a26 = new javax.swing.JLabel();
        lbl27a42 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn43 = new javax.swing.JButton();
        btn44 = new javax.swing.JButton();
        btn45 = new javax.swing.JButton();
        btn46 = new javax.swing.JButton();
        btn47 = new javax.swing.JButton();
        btn48 = new javax.swing.JButton();
        btn49 = new javax.swing.JButton();
        btn50 = new javax.swing.JButton();
        btn51 = new javax.swing.JButton();
        btn52 = new javax.swing.JButton();
        btn53 = new javax.swing.JButton();
        btn54 = new javax.swing.JButton();
        btn55 = new javax.swing.JButton();
        btn56 = new javax.swing.JButton();
        btn57 = new javax.swing.JButton();
        btn58 = new javax.swing.JButton();
        btn59 = new javax.swing.JButton();
        btn60 = new javax.swing.JButton();
        btn61 = new javax.swing.JButton();
        btn62 = new javax.swing.JButton();
        btn63 = new javax.swing.JButton();
        btn64 = new javax.swing.JButton();
        lbl43a64 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn65 = new javax.swing.JButton();
        btn66 = new javax.swing.JButton();
        btn67 = new javax.swing.JButton();
        btn68 = new javax.swing.JButton();
        btn69 = new javax.swing.JButton();
        btn70 = new javax.swing.JButton();
        btn71 = new javax.swing.JButton();
        btn72 = new javax.swing.JButton();
        btn73 = new javax.swing.JButton();
        btn74 = new javax.swing.JButton();
        btn75 = new javax.swing.JButton();
        btn76 = new javax.swing.JButton();
        btn77 = new javax.swing.JButton();
        btn78 = new javax.swing.JButton();
        btn79 = new javax.swing.JButton();
        btn80 = new javax.swing.JButton();
        btn81 = new javax.swing.JButton();
        btn82 = new javax.swing.JButton();
        btn83 = new javax.swing.JButton();
        btn84 = new javax.swing.JButton();
        btn85 = new javax.swing.JButton();
        btn86 = new javax.swing.JButton();
        lbl65a86 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btn87 = new javax.swing.JButton();
        btn88 = new javax.swing.JButton();
        btn89 = new javax.swing.JButton();
        btn90 = new javax.swing.JButton();
        btn91 = new javax.swing.JButton();
        btn92 = new javax.swing.JButton();
        btn93 = new javax.swing.JButton();
        btn94 = new javax.swing.JButton();
        btn95 = new javax.swing.JButton();
        btn96 = new javax.swing.JButton();
        btn97 = new javax.swing.JButton();
        btn98 = new javax.swing.JButton();
        btn99 = new javax.swing.JButton();
        btn100 = new javax.swing.JButton();
        btn101 = new javax.swing.JButton();
        btn102 = new javax.swing.JButton();
        btn103 = new javax.swing.JButton();
        btn104 = new javax.swing.JButton();
        btn105 = new javax.swing.JButton();
        btn106 = new javax.swing.JButton();
        btn107 = new javax.swing.JButton();
        btn108 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btn109 = new javax.swing.JButton();
        btn110 = new javax.swing.JButton();
        btn111 = new javax.swing.JButton();
        btn112 = new javax.swing.JButton();
        btn113 = new javax.swing.JButton();
        btn114 = new javax.swing.JButton();
        btn115 = new javax.swing.JButton();
        btn116 = new javax.swing.JButton();
        btn117 = new javax.swing.JButton();
        btn118 = new javax.swing.JButton();
        btn119 = new javax.swing.JButton();
        btn120 = new javax.swing.JButton();
        btn121 = new javax.swing.JButton();
        btn122 = new javax.swing.JButton();
        btn123 = new javax.swing.JButton();
        btn124 = new javax.swing.JButton();
        btn125 = new javax.swing.JButton();
        btn126 = new javax.swing.JButton();
        btn127 = new javax.swing.JButton();
        btn128 = new javax.swing.JButton();
        btn129 = new javax.swing.JButton();
        btn130 = new javax.swing.JButton();
        lbl109a118 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btn131 = new javax.swing.JButton();
        btn132 = new javax.swing.JButton();
        btn133 = new javax.swing.JButton();
        btn134 = new javax.swing.JButton();
        btn135 = new javax.swing.JButton();
        btn136 = new javax.swing.JButton();
        btn137 = new javax.swing.JButton();
        btn138 = new javax.swing.JButton();
        btn139 = new javax.swing.JButton();
        btn140 = new javax.swing.JButton();
        btn141 = new javax.swing.JButton();
        btn142 = new javax.swing.JButton();
        btn143 = new javax.swing.JButton();
        btn144 = new javax.swing.JButton();
        btn145 = new javax.swing.JButton();
        btn146 = new javax.swing.JButton();
        btn147 = new javax.swing.JButton();
        btn148 = new javax.swing.JButton();
        btn149 = new javax.swing.JButton();
        btn150 = new javax.swing.JButton();
        btn151 = new javax.swing.JButton();
        btn152 = new javax.swing.JButton();
        lbl131a152 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btn153 = new javax.swing.JButton();
        btn154 = new javax.swing.JButton();
        btn155 = new javax.swing.JButton();
        btn156 = new javax.swing.JButton();
        btn157 = new javax.swing.JButton();
        btn158 = new javax.swing.JButton();
        btn159 = new javax.swing.JButton();
        btn160 = new javax.swing.JButton();
        btn161 = new javax.swing.JButton();
        btn162 = new javax.swing.JButton();
        btn163 = new javax.swing.JButton();
        btn164 = new javax.swing.JButton();
        btn165 = new javax.swing.JButton();
        btn166 = new javax.swing.JButton();
        btn167 = new javax.swing.JButton();
        btn168 = new javax.swing.JButton();
        btn169 = new javax.swing.JButton();
        btn170 = new javax.swing.JButton();
        btn171 = new javax.swing.JButton();
        btn172 = new javax.swing.JButton();
        btn173 = new javax.swing.JButton();
        btn174 = new javax.swing.JButton();
        btn175 = new javax.swing.JButton();
        btn176 = new javax.swing.JButton();
        lbl153a176 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        lbl1a20 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btn197 = new javax.swing.JButton();
        btn196 = new javax.swing.JButton();
        btn195 = new javax.swing.JButton();
        btn194 = new javax.swing.JButton();
        btn193 = new javax.swing.JButton();
        btn192 = new javax.swing.JButton();
        btn191 = new javax.swing.JButton();
        btn190 = new javax.swing.JButton();
        btn189 = new javax.swing.JButton();
        btn188 = new javax.swing.JButton();
        btn187 = new javax.swing.JButton();
        btn186 = new javax.swing.JButton();
        btn185 = new javax.swing.JButton();
        btn184 = new javax.swing.JButton();
        btn183 = new javax.swing.JButton();
        btn182 = new javax.swing.JButton();
        btn181 = new javax.swing.JButton();
        btn180 = new javax.swing.JButton();
        btn179 = new javax.swing.JButton();
        btn178 = new javax.swing.JButton();
        btn177 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lbl192a186 = new javax.swing.JLabel();
        lbl185a177 = new javax.swing.JLabel();
        lbl194a193 = new javax.swing.JLabel();
        txtIDRut = new javax.swing.JTextField();
        rbtnId = new javax.swing.JRadioButton();
        rbtnRut = new javax.swing.JRadioButton();
        btnBuscar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblRut = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        lblPatente = new javax.swing.JLabel();
        lblReserva = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblHora = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();

        setIconifiable(true);
        setTitle("Gestion estacionamiento");
        setToolTipText("");

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btn21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn21.setText("21");
        btn21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn21ActionPerformed(evt);
            }
        });
        jPanel1.add(btn21);

        btn22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn22.setText("22");
        btn22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn22ActionPerformed(evt);
            }
        });
        jPanel1.add(btn22);

        btn23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn23.setText("23");
        btn23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn23ActionPerformed(evt);
            }
        });
        jPanel1.add(btn23);

        btn24.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn24.setText("24");
        btn24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn24ActionPerformed(evt);
            }
        });
        jPanel1.add(btn24);

        btn25.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn25.setText("25");
        btn25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn25ActionPerformed(evt);
            }
        });
        jPanel1.add(btn25);

        btn26.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn26.setText("26");
        btn26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn26ActionPerformed(evt);
            }
        });
        jPanel1.add(btn26);

        btn27.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn27.setText("27");
        btn27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn27ActionPerformed(evt);
            }
        });
        jPanel1.add(btn27);

        btn28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn28.setText("28");
        btn28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn28ActionPerformed(evt);
            }
        });
        jPanel1.add(btn28);

        btn29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn29.setText("29");
        btn29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn29ActionPerformed(evt);
            }
        });
        jPanel1.add(btn29);

        btn30.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn30.setText("30");
        btn30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn30ActionPerformed(evt);
            }
        });
        jPanel1.add(btn30);

        btn31.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn31.setText("31");
        btn31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn31ActionPerformed(evt);
            }
        });
        jPanel1.add(btn31);

        btn32.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn32.setText("32");
        btn32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn32ActionPerformed(evt);
            }
        });
        jPanel1.add(btn32);

        btn33.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn33.setText("33");
        btn33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn33ActionPerformed(evt);
            }
        });
        jPanel1.add(btn33);

        btn34.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn34.setText("34");
        btn34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn34ActionPerformed(evt);
            }
        });
        jPanel1.add(btn34);

        btn35.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn35.setText("35");
        btn35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn35ActionPerformed(evt);
            }
        });
        jPanel1.add(btn35);

        btn36.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn36.setText("36");
        btn36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn36ActionPerformed(evt);
            }
        });
        jPanel1.add(btn36);

        btn37.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn37.setText("37");
        btn37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn37ActionPerformed(evt);
            }
        });
        jPanel1.add(btn37);

        btn38.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn38.setText("38");
        btn38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn38ActionPerformed(evt);
            }
        });
        jPanel1.add(btn38);

        btn39.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn39.setText("39");
        btn39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn39ActionPerformed(evt);
            }
        });
        jPanel1.add(btn39);

        btn40.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn40.setText("40");
        btn40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn40ActionPerformed(evt);
            }
        });
        jPanel1.add(btn40);

        btn41.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn41.setText("41");
        btn41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn41ActionPerformed(evt);
            }
        });
        jPanel1.add(btn41);

        btn42.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn42.setText("42");
        btn42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn42ActionPerformed(evt);
            }
        });
        jPanel1.add(btn42);

        lbl21a26.setBackground(new java.awt.Color(91, 255, 83));
        lbl21a26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl21a26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl21a26.setText("ADMINISTRATIVOS");
        lbl21a26.setOpaque(true);

        lbl27a42.setBackground(new java.awt.Color(77, 252, 229));
        lbl27a42.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl27a42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl27a42.setText("EJECUTIVOS");
        lbl27a42.setOpaque(true);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btn43.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn43.setText("43");
        btn43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn43ActionPerformed(evt);
            }
        });
        jPanel2.add(btn43);

        btn44.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn44.setText("44");
        btn44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn44ActionPerformed(evt);
            }
        });
        jPanel2.add(btn44);

        btn45.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn45.setText("45");
        btn45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn45ActionPerformed(evt);
            }
        });
        jPanel2.add(btn45);

        btn46.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn46.setText("46");
        btn46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn46ActionPerformed(evt);
            }
        });
        jPanel2.add(btn46);

        btn47.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn47.setText("47");
        btn47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn47ActionPerformed(evt);
            }
        });
        jPanel2.add(btn47);

        btn48.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn48.setText("48");
        btn48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn48ActionPerformed(evt);
            }
        });
        jPanel2.add(btn48);

        btn49.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn49.setText("49");
        btn49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn49ActionPerformed(evt);
            }
        });
        jPanel2.add(btn49);

        btn50.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn50.setText("50");
        btn50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn50ActionPerformed(evt);
            }
        });
        jPanel2.add(btn50);

        btn51.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn51.setText("51");
        btn51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn51ActionPerformed(evt);
            }
        });
        jPanel2.add(btn51);

        btn52.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn52.setText("52");
        btn52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn52ActionPerformed(evt);
            }
        });
        jPanel2.add(btn52);

        btn53.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn53.setText("53");
        btn53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn53ActionPerformed(evt);
            }
        });
        jPanel2.add(btn53);

        btn54.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn54.setText("54");
        btn54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn54ActionPerformed(evt);
            }
        });
        jPanel2.add(btn54);

        btn55.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn55.setText("55");
        btn55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn55ActionPerformed(evt);
            }
        });
        jPanel2.add(btn55);

        btn56.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn56.setText("56");
        btn56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn56ActionPerformed(evt);
            }
        });
        jPanel2.add(btn56);

        btn57.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn57.setText("57");
        btn57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn57ActionPerformed(evt);
            }
        });
        jPanel2.add(btn57);

        btn58.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn58.setText("58");
        btn58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn58ActionPerformed(evt);
            }
        });
        jPanel2.add(btn58);

        btn59.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn59.setText("59");
        btn59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn59ActionPerformed(evt);
            }
        });
        jPanel2.add(btn59);

        btn60.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn60.setText("60");
        btn60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn60ActionPerformed(evt);
            }
        });
        jPanel2.add(btn60);

        btn61.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn61.setText("61");
        btn61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn61ActionPerformed(evt);
            }
        });
        jPanel2.add(btn61);

        btn62.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn62.setText("62");
        btn62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn62ActionPerformed(evt);
            }
        });
        jPanel2.add(btn62);

        btn63.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn63.setText("63");
        btn63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn63ActionPerformed(evt);
            }
        });
        jPanel2.add(btn63);

        btn64.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn64.setText("64");
        btn64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn64ActionPerformed(evt);
            }
        });
        jPanel2.add(btn64);

        lbl43a64.setBackground(new java.awt.Color(91, 255, 83));
        lbl43a64.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl43a64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl43a64.setText("ADMINISTRATIVOS");
        lbl43a64.setOpaque(true);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btn65.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn65.setText("65");
        btn65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn65ActionPerformed(evt);
            }
        });
        jPanel3.add(btn65);

        btn66.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn66.setText("66");
        btn66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn66ActionPerformed(evt);
            }
        });
        jPanel3.add(btn66);

        btn67.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn67.setText("67");
        btn67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn67ActionPerformed(evt);
            }
        });
        jPanel3.add(btn67);

        btn68.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn68.setText("68");
        btn68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn68ActionPerformed(evt);
            }
        });
        jPanel3.add(btn68);

        btn69.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn69.setText("69");
        btn69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn69ActionPerformed(evt);
            }
        });
        jPanel3.add(btn69);

        btn70.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn70.setText("70");
        btn70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn70ActionPerformed(evt);
            }
        });
        jPanel3.add(btn70);

        btn71.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn71.setText("71");
        btn71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn71ActionPerformed(evt);
            }
        });
        jPanel3.add(btn71);

        btn72.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn72.setText("72");
        btn72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn72ActionPerformed(evt);
            }
        });
        jPanel3.add(btn72);

        btn73.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn73.setText("73");
        btn73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn73ActionPerformed(evt);
            }
        });
        jPanel3.add(btn73);

        btn74.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn74.setText("74");
        btn74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn74ActionPerformed(evt);
            }
        });
        jPanel3.add(btn74);

        btn75.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn75.setText("75");
        btn75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn75ActionPerformed(evt);
            }
        });
        jPanel3.add(btn75);

        btn76.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn76.setText("76");
        btn76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn76ActionPerformed(evt);
            }
        });
        jPanel3.add(btn76);

        btn77.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn77.setText("77");
        btn77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn77ActionPerformed(evt);
            }
        });
        jPanel3.add(btn77);

        btn78.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn78.setText("78");
        btn78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn78ActionPerformed(evt);
            }
        });
        jPanel3.add(btn78);

        btn79.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn79.setText("79");
        btn79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn79ActionPerformed(evt);
            }
        });
        jPanel3.add(btn79);

        btn80.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn80.setText("80");
        btn80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn80ActionPerformed(evt);
            }
        });
        jPanel3.add(btn80);

        btn81.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn81.setText("81");
        btn81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn81ActionPerformed(evt);
            }
        });
        jPanel3.add(btn81);

        btn82.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn82.setText("82");
        btn82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn82ActionPerformed(evt);
            }
        });
        jPanel3.add(btn82);

        btn83.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn83.setText("83");
        btn83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn83ActionPerformed(evt);
            }
        });
        jPanel3.add(btn83);

        btn84.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn84.setText("84");
        btn84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn84ActionPerformed(evt);
            }
        });
        jPanel3.add(btn84);

        btn85.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn85.setText("85");
        btn85.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn85ActionPerformed(evt);
            }
        });
        jPanel3.add(btn85);

        btn86.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn86.setText("86");
        btn86.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn86ActionPerformed(evt);
            }
        });
        jPanel3.add(btn86);

        lbl65a86.setBackground(new java.awt.Color(255, 61, 61));
        lbl65a86.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl65a86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl65a86.setText("DOCENTES");
        lbl65a86.setOpaque(true);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btn87.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn87.setText("87");
        btn87.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn87ActionPerformed(evt);
            }
        });
        jPanel4.add(btn87);

        btn88.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn88.setText("88");
        btn88.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn88ActionPerformed(evt);
            }
        });
        jPanel4.add(btn88);

        btn89.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn89.setText("89");
        btn89.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn89ActionPerformed(evt);
            }
        });
        jPanel4.add(btn89);

        btn90.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn90.setText("90");
        btn90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn90ActionPerformed(evt);
            }
        });
        jPanel4.add(btn90);

        btn91.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn91.setText("91");
        btn91.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn91ActionPerformed(evt);
            }
        });
        jPanel4.add(btn91);

        btn92.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn92.setText("92");
        btn92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn92ActionPerformed(evt);
            }
        });
        jPanel4.add(btn92);

        btn93.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn93.setText("93");
        btn93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn93ActionPerformed(evt);
            }
        });
        jPanel4.add(btn93);

        btn94.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn94.setText("94");
        btn94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn94ActionPerformed(evt);
            }
        });
        jPanel4.add(btn94);

        btn95.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn95.setText("95");
        btn95.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn95ActionPerformed(evt);
            }
        });
        jPanel4.add(btn95);

        btn96.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn96.setText("96");
        btn96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn96ActionPerformed(evt);
            }
        });
        jPanel4.add(btn96);

        btn97.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn97.setText("97");
        btn97.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn97ActionPerformed(evt);
            }
        });
        jPanel4.add(btn97);

        btn98.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn98.setText("98");
        btn98.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn98ActionPerformed(evt);
            }
        });
        jPanel4.add(btn98);

        btn99.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn99.setText("99");
        btn99.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn99ActionPerformed(evt);
            }
        });
        jPanel4.add(btn99);

        btn100.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn100.setText("100");
        btn100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn100ActionPerformed(evt);
            }
        });
        jPanel4.add(btn100);

        btn101.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn101.setText("101");
        btn101.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn101ActionPerformed(evt);
            }
        });
        jPanel4.add(btn101);

        btn102.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn102.setText("102");
        btn102.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn102ActionPerformed(evt);
            }
        });
        jPanel4.add(btn102);

        btn103.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn103.setText("103");
        btn103.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn103ActionPerformed(evt);
            }
        });
        jPanel4.add(btn103);

        btn104.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn104.setText("104");
        btn104.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn104ActionPerformed(evt);
            }
        });
        jPanel4.add(btn104);

        btn105.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn105.setText("105");
        btn105.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn105ActionPerformed(evt);
            }
        });
        jPanel4.add(btn105);

        btn106.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn106.setText("106");
        btn106.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn106ActionPerformed(evt);
            }
        });
        jPanel4.add(btn106);

        btn107.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn107.setText("107");
        btn107.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn107ActionPerformed(evt);
            }
        });
        jPanel4.add(btn107);

        btn108.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn108.setText("108");
        btn108.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn108ActionPerformed(evt);
            }
        });
        jPanel4.add(btn108);

        jLabel5.setBackground(new java.awt.Color(255, 61, 61));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DOCENTES");
        jLabel5.setOpaque(true);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        btn109.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn109.setText("109");
        btn109.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn109ActionPerformed(evt);
            }
        });
        jPanel5.add(btn109);

        btn110.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn110.setText("110");
        btn110.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn110ActionPerformed(evt);
            }
        });
        jPanel5.add(btn110);

        btn111.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn111.setText("111");
        btn111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn111ActionPerformed(evt);
            }
        });
        jPanel5.add(btn111);

        btn112.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn112.setText("112");
        btn112.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn112ActionPerformed(evt);
            }
        });
        jPanel5.add(btn112);

        btn113.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn113.setText("113");
        btn113.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn113ActionPerformed(evt);
            }
        });
        jPanel5.add(btn113);

        btn114.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn114.setText("114");
        btn114.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn114ActionPerformed(evt);
            }
        });
        jPanel5.add(btn114);

        btn115.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn115.setText("115");
        btn115.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn115ActionPerformed(evt);
            }
        });
        jPanel5.add(btn115);

        btn116.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn116.setText("116");
        btn116.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn116ActionPerformed(evt);
            }
        });
        jPanel5.add(btn116);

        btn117.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn117.setText("117");
        btn117.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn117ActionPerformed(evt);
            }
        });
        jPanel5.add(btn117);

        btn118.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn118.setText("118");
        btn118.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn118ActionPerformed(evt);
            }
        });
        jPanel5.add(btn118);

        btn119.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn119.setText("119");
        btn119.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn119ActionPerformed(evt);
            }
        });
        jPanel5.add(btn119);

        btn120.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn120.setText("120");
        btn120.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn120ActionPerformed(evt);
            }
        });
        jPanel5.add(btn120);

        btn121.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn121.setText("121");
        btn121.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn121ActionPerformed(evt);
            }
        });
        jPanel5.add(btn121);

        btn122.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn122.setText("122");
        btn122.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn122ActionPerformed(evt);
            }
        });
        jPanel5.add(btn122);

        btn123.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn123.setText("123");
        btn123.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn123ActionPerformed(evt);
            }
        });
        jPanel5.add(btn123);

        btn124.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn124.setText("124");
        btn124.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn124ActionPerformed(evt);
            }
        });
        jPanel5.add(btn124);

        btn125.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn125.setText("125");
        btn125.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn125ActionPerformed(evt);
            }
        });
        jPanel5.add(btn125);

        btn126.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn126.setText("126");
        btn126.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn126ActionPerformed(evt);
            }
        });
        jPanel5.add(btn126);

        btn127.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn127.setText("127");
        btn127.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn127ActionPerformed(evt);
            }
        });
        jPanel5.add(btn127);

        btn128.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn128.setText("128");
        btn128.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn128ActionPerformed(evt);
            }
        });
        jPanel5.add(btn128);

        btn129.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn129.setText("129");
        btn129.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn129ActionPerformed(evt);
            }
        });
        jPanel5.add(btn129);

        btn130.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn130.setText("130");
        btn130.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn130ActionPerformed(evt);
            }
        });
        jPanel5.add(btn130);

        lbl109a118.setBackground(new java.awt.Color(251, 217, 66));
        lbl109a118.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl109a118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl109a118.setText("ALUMNOS DIURNOS");
        lbl109a118.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(255, 61, 61));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DOCENTES");
        jLabel7.setOpaque(true);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        btn131.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn131.setText("131");
        btn131.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn131ActionPerformed(evt);
            }
        });
        jPanel6.add(btn131);

        btn132.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn132.setText("132");
        btn132.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn132ActionPerformed(evt);
            }
        });
        jPanel6.add(btn132);

        btn133.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn133.setText("133");
        btn133.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn133ActionPerformed(evt);
            }
        });
        jPanel6.add(btn133);

        btn134.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn134.setText("134");
        btn134.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn134ActionPerformed(evt);
            }
        });
        jPanel6.add(btn134);

        btn135.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn135.setText("135");
        btn135.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn135ActionPerformed(evt);
            }
        });
        jPanel6.add(btn135);

        btn136.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn136.setText("136");
        btn136.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn136ActionPerformed(evt);
            }
        });
        jPanel6.add(btn136);

        btn137.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn137.setText("137");
        btn137.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn137ActionPerformed(evt);
            }
        });
        jPanel6.add(btn137);

        btn138.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn138.setText("138");
        btn138.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn138ActionPerformed(evt);
            }
        });
        jPanel6.add(btn138);

        btn139.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn139.setText("139");
        btn139.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn139ActionPerformed(evt);
            }
        });
        jPanel6.add(btn139);

        btn140.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn140.setText("140");
        btn140.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn140ActionPerformed(evt);
            }
        });
        jPanel6.add(btn140);

        btn141.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn141.setText("141");
        btn141.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn141ActionPerformed(evt);
            }
        });
        jPanel6.add(btn141);

        btn142.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn142.setText("142");
        btn142.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn142ActionPerformed(evt);
            }
        });
        jPanel6.add(btn142);

        btn143.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn143.setText("143");
        btn143.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn143ActionPerformed(evt);
            }
        });
        jPanel6.add(btn143);

        btn144.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn144.setText("144");
        btn144.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn144ActionPerformed(evt);
            }
        });
        jPanel6.add(btn144);

        btn145.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn145.setText("145");
        btn145.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn145ActionPerformed(evt);
            }
        });
        jPanel6.add(btn145);

        btn146.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn146.setText("146");
        btn146.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn146ActionPerformed(evt);
            }
        });
        jPanel6.add(btn146);

        btn147.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn147.setText("147");
        btn147.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn147ActionPerformed(evt);
            }
        });
        jPanel6.add(btn147);

        btn148.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn148.setText("148");
        btn148.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn148ActionPerformed(evt);
            }
        });
        jPanel6.add(btn148);

        btn149.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn149.setText("149");
        btn149.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn149ActionPerformed(evt);
            }
        });
        jPanel6.add(btn149);

        btn150.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn150.setText("150");
        btn150.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn150ActionPerformed(evt);
            }
        });
        jPanel6.add(btn150);

        btn151.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn151.setText("151");
        btn151.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn151ActionPerformed(evt);
            }
        });
        jPanel6.add(btn151);

        btn152.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn152.setText("152");
        btn152.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn152ActionPerformed(evt);
            }
        });
        jPanel6.add(btn152);

        lbl131a152.setBackground(new java.awt.Color(251, 217, 66));
        lbl131a152.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl131a152.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl131a152.setText("ALUMNOS DIURNOS");
        lbl131a152.setOpaque(true);

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        btn153.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn153.setText("153");
        btn153.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn153ActionPerformed(evt);
            }
        });
        jPanel7.add(btn153);

        btn154.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn154.setText("154");
        btn154.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn154ActionPerformed(evt);
            }
        });
        jPanel7.add(btn154);

        btn155.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn155.setText("155");
        btn155.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn155ActionPerformed(evt);
            }
        });
        jPanel7.add(btn155);

        btn156.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn156.setText("156");
        btn156.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn156ActionPerformed(evt);
            }
        });
        jPanel7.add(btn156);

        btn157.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn157.setText("157");
        btn157.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn157ActionPerformed(evt);
            }
        });
        jPanel7.add(btn157);

        btn158.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn158.setText("158");
        btn158.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn158ActionPerformed(evt);
            }
        });
        jPanel7.add(btn158);

        btn159.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn159.setText("159");
        btn159.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn159ActionPerformed(evt);
            }
        });
        jPanel7.add(btn159);

        btn160.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn160.setText("160");
        btn160.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn160ActionPerformed(evt);
            }
        });
        jPanel7.add(btn160);

        btn161.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn161.setText("161");
        btn161.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn161ActionPerformed(evt);
            }
        });
        jPanel7.add(btn161);

        btn162.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn162.setText("162");
        btn162.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn162ActionPerformed(evt);
            }
        });
        jPanel7.add(btn162);

        btn163.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn163.setText("163");
        btn163.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn163ActionPerformed(evt);
            }
        });
        jPanel7.add(btn163);

        btn164.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn164.setText("164");
        btn164.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn164ActionPerformed(evt);
            }
        });
        jPanel7.add(btn164);

        btn165.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn165.setText("165");
        btn165.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn165ActionPerformed(evt);
            }
        });
        jPanel7.add(btn165);

        btn166.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn166.setText("166");
        btn166.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn166ActionPerformed(evt);
            }
        });
        jPanel7.add(btn166);

        btn167.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn167.setText("167");
        btn167.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn167ActionPerformed(evt);
            }
        });
        jPanel7.add(btn167);

        btn168.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn168.setText("168");
        btn168.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn168ActionPerformed(evt);
            }
        });
        jPanel7.add(btn168);

        btn169.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn169.setText("169");
        btn169.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn169ActionPerformed(evt);
            }
        });
        jPanel7.add(btn169);

        btn170.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn170.setText("170");
        btn170.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn170ActionPerformed(evt);
            }
        });
        jPanel7.add(btn170);

        btn171.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn171.setText("171");
        btn171.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn171ActionPerformed(evt);
            }
        });
        jPanel7.add(btn171);

        btn172.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn172.setText("172");
        btn172.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn172ActionPerformed(evt);
            }
        });
        jPanel7.add(btn172);

        btn173.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn173.setText("173");
        btn173.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn173ActionPerformed(evt);
            }
        });
        jPanel7.add(btn173);

        btn174.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn174.setText("174");
        btn174.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn174ActionPerformed(evt);
            }
        });
        jPanel7.add(btn174);

        btn175.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn175.setText("175");
        btn175.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn175ActionPerformed(evt);
            }
        });
        jPanel7.add(btn175);

        btn176.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn176.setText("176");
        btn176.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn176ActionPerformed(evt);
            }
        });
        jPanel7.add(btn176);

        lbl153a176.setBackground(new java.awt.Color(251, 217, 66));
        lbl153a176.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl153a176.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl153a176.setText("ALUMNOS DIURNOS");
        lbl153a176.setOpaque(true);

        jPanel8.setLayout(new java.awt.GridLayout(20, 1));

        btn1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        jPanel8.add(btn1);

        btn2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        jPanel8.add(btn2);

        btn3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        jPanel8.add(btn3);

        btn4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        jPanel8.add(btn4);

        btn5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel8.add(btn5);

        btn6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn6.setText("6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        jPanel8.add(btn6);

        btn7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        jPanel8.add(btn7);

        btn8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        jPanel8.add(btn8);

        btn9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        jPanel8.add(btn9);

        btn10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn10.setText("10");
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });
        jPanel8.add(btn10);

        btn11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn11.setText("11");
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });
        jPanel8.add(btn11);

        btn12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn12.setText("12");
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });
        jPanel8.add(btn12);

        btn13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn13.setText("13");
        btn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn13ActionPerformed(evt);
            }
        });
        jPanel8.add(btn13);

        btn14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn14.setText("14");
        btn14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn14ActionPerformed(evt);
            }
        });
        jPanel8.add(btn14);

        btn15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn15.setText("15");
        btn15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn15ActionPerformed(evt);
            }
        });
        jPanel8.add(btn15);

        btn16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn16.setText("16");
        btn16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn16ActionPerformed(evt);
            }
        });
        jPanel8.add(btn16);

        btn17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn17.setText("17");
        btn17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn17ActionPerformed(evt);
            }
        });
        jPanel8.add(btn17);

        btn18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn18.setText("18");
        btn18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn18ActionPerformed(evt);
            }
        });
        jPanel8.add(btn18);

        btn19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn19.setText("19");
        btn19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn19ActionPerformed(evt);
            }
        });
        jPanel8.add(btn19);

        btn20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn20.setText("20");
        btn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20ActionPerformed(evt);
            }
        });
        jPanel8.add(btn20);

        lbl1a20.setBackground(new java.awt.Color(251, 217, 66));
        lbl1a20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl1a20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1a20.setText("<html>ALUM.<br>DIURNO</html>");
        lbl1a20.setOpaque(true);

        jPanel9.setLayout(new java.awt.GridLayout(21, 1));

        btn197.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn197.setText("197");
        btn197.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn197ActionPerformed(evt);
            }
        });
        jPanel9.add(btn197);

        btn196.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn196.setText("196");
        btn196.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn196ActionPerformed(evt);
            }
        });
        jPanel9.add(btn196);

        btn195.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn195.setText("195");
        btn195.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn195ActionPerformed(evt);
            }
        });
        jPanel9.add(btn195);

        btn194.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn194.setText("194");
        btn194.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn194ActionPerformed(evt);
            }
        });
        jPanel9.add(btn194);

        btn193.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn193.setText("193");
        btn193.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn193ActionPerformed(evt);
            }
        });
        jPanel9.add(btn193);

        btn192.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn192.setText("192");
        btn192.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn192ActionPerformed(evt);
            }
        });
        jPanel9.add(btn192);

        btn191.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn191.setText("191");
        btn191.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn191ActionPerformed(evt);
            }
        });
        jPanel9.add(btn191);

        btn190.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn190.setText("190");
        btn190.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn190ActionPerformed(evt);
            }
        });
        jPanel9.add(btn190);

        btn189.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn189.setText("189");
        btn189.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn189ActionPerformed(evt);
            }
        });
        jPanel9.add(btn189);

        btn188.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn188.setText("188");
        btn188.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn188ActionPerformed(evt);
            }
        });
        jPanel9.add(btn188);

        btn187.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn187.setText("187");
        btn187.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn187ActionPerformed(evt);
            }
        });
        jPanel9.add(btn187);

        btn186.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn186.setText("186");
        btn186.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn186ActionPerformed(evt);
            }
        });
        jPanel9.add(btn186);

        btn185.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn185.setText("185");
        btn185.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn185ActionPerformed(evt);
            }
        });
        jPanel9.add(btn185);

        btn184.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn184.setText("184");
        btn184.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn184ActionPerformed(evt);
            }
        });
        jPanel9.add(btn184);

        btn183.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn183.setText("183");
        btn183.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn183ActionPerformed(evt);
            }
        });
        jPanel9.add(btn183);

        btn182.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn182.setText("182");
        btn182.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn182ActionPerformed(evt);
            }
        });
        jPanel9.add(btn182);

        btn181.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn181.setText("181");
        btn181.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn181ActionPerformed(evt);
            }
        });
        jPanel9.add(btn181);

        btn180.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn180.setText("180");
        btn180.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn180ActionPerformed(evt);
            }
        });
        jPanel9.add(btn180);

        btn179.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn179.setText("179");
        btn179.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn179ActionPerformed(evt);
            }
        });
        jPanel9.add(btn179);

        btn178.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn178.setText("178");
        btn178.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn178ActionPerformed(evt);
            }
        });
        jPanel9.add(btn178);

        btn177.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btn177.setText("177");
        btn177.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn177ActionPerformed(evt);
            }
        });
        jPanel9.add(btn177);

        jLabel11.setBackground(new java.awt.Color(102, 255, 255));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disc.png"))); // NOI18N
        jLabel11.setOpaque(true);

        lbl192a186.setBackground(new java.awt.Color(91, 255, 83));
        lbl192a186.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl192a186.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl192a186.setText("ADMIN.");
        lbl192a186.setOpaque(true);

        lbl185a177.setBackground(new java.awt.Color(251, 217, 66));
        lbl185a177.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl185a177.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl185a177.setText("<html>ALUM.<br>DIURNO</html>");
        lbl185a177.setOpaque(true);

        lbl194a193.setBackground(new java.awt.Color(153, 153, 153));
        lbl194a193.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbl194a193.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl194a193.setText("VISITA");
        lbl194a193.setOpaque(true);

        txtIDRut.setToolTipText("Ingrese una ID o RUT");
        txtIDRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDRutKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDRutKeyTyped(evt);
            }
        });

        buttonGroup1.add(rbtnId);
        rbtnId.setSelected(true);
        rbtnId.setText("ID");
        rbtnId.setToolTipText("");
        rbtnId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnIdActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnRut);
        rbtnRut.setText("RUT");
        rbtnRut.setToolTipText("");
        rbtnRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRutActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(255, 255, 0));
        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("GARITA");
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel17.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Leyenda:");

        jLabel4.setBackground(new java.awt.Color(255, 204, 0));
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel4.setOpaque(true);

        jLabel6.setBackground(new java.awt.Color(0, 204, 0));
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel6.setOpaque(true);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel8.setOpaque(true);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Disponible");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Ocupado C/Reserva");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Reserva plaza");

        jLabel13.setBackground(new java.awt.Color(255, 0, 0));
        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel13.setOpaque(true);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Ocupado S/Reserva");

        lblId.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblId.setForeground(new java.awt.Color(255, 0, 0));
        lblId.setText("ID:");

        lblRut.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblRut.setForeground(new java.awt.Color(255, 0, 0));
        lblRut.setText("RUT:");

        lblNombre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 0, 0));
        lblNombre.setText("NOMBRE:");

        lblRol.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblRol.setForeground(new java.awt.Color(255, 0, 0));
        lblRol.setText("ROL:");

        lblPatente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblPatente.setForeground(new java.awt.Color(255, 0, 0));
        lblPatente.setText("PATENTE:");

        lblReserva.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblReserva.setForeground(new java.awt.Color(255, 0, 0));
        lblReserva.setText("RESERVA:");

        lblHora.setBackground(new java.awt.Color(204, 204, 204));
        lblHora.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHora.setText("18:30:00");
        lblHora.setOpaque(true);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setToolTipText("");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl1a20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl131a152, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl109a118, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbl65a86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl43a64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl21a26, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl27a42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl185a177)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl192a186, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl194a193, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbl153a176, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtIDRut, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnRut)
                            .addComponent(rbtnId))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblId)
                            .addComponent(lblRut)
                            .addComponent(lblNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblReserva)
                            .addComponent(lblRol)
                            .addComponent(lblPatente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14))
                            .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtnId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLimpiar)
                                .addComponent(rbtnRut))
                            .addComponent(btnBuscar))
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl1a20, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14))
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(9, 9, 9)
                                                .addComponent(jLabel9))
                                            .addComponent(lblHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbl27a42, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl21a26, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl43a64, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)
                                        .addComponent(lbl65a86, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbl109a118, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl131a152, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbl194a193, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbl192a186, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbl185a177, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblId)
                                    .addGap(8, 8, 8)
                                    .addComponent(lblRut)
                                    .addGap(8, 8, 8)
                                    .addComponent(lblNombre))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(lblRol)
                                    .addGap(8, 8, 8)
                                    .addComponent(lblPatente)
                                    .addGap(8, 8, 8)
                                    .addComponent(lblReserva))))
                        .addGap(24, 24, 24)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl153a176, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        lbl21a26.getAccessibleContext().setAccessibleName("jLabel1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn48ActionPerformed
        reservaPlazas(48);
    }//GEN-LAST:event_btn48ActionPerformed

    private void btn59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn59ActionPerformed
        reservaPlazas(59);
    }//GEN-LAST:event_btn59ActionPerformed

    private void btn95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn95ActionPerformed
        reservaPlazas(95);
    }//GEN-LAST:event_btn95ActionPerformed

    private void btn98ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn98ActionPerformed
        reservaPlazas(98);
    }//GEN-LAST:event_btn98ActionPerformed

    private void btn99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn99ActionPerformed
        reservaPlazas(99);
    }//GEN-LAST:event_btn99ActionPerformed

    private void btn86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn86ActionPerformed
        reservaPlazas(86);
    }//GEN-LAST:event_btn86ActionPerformed

    private void btn123ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn123ActionPerformed
        reservaPlazas(123);
    }//GEN-LAST:event_btn123ActionPerformed

    private void btn125ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn125ActionPerformed
        reservaPlazas(125);
    }//GEN-LAST:event_btn125ActionPerformed

    private void btn152ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn152ActionPerformed
        reservaPlazas(152);
    }//GEN-LAST:event_btn152ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        reservaPlazas(4);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20ActionPerformed
        reservaPlazas(20);
    }//GEN-LAST:event_btn20ActionPerformed

    private void btn192ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn192ActionPerformed
        reservaPlazas(192);
    }//GEN-LAST:event_btn192ActionPerformed

    private void btn184ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn184ActionPerformed
        reservaPlazas(184);
    }//GEN-LAST:event_btn184ActionPerformed

    private void rbtnRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRutActionPerformed
        txtIDRut.setText("");
        txtIDRut.requestFocus();
    }//GEN-LAST:event_rbtnRutActionPerformed

    private void rbtnIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnIdActionPerformed
        txtIDRut.setText("");
        txtIDRut.requestFocus();
    }//GEN-LAST:event_rbtnIdActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        datosCliente();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtIDRutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDRutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            datosCliente();
        }
    }//GEN-LAST:event_txtIDRutKeyPressed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        reservaPlazas(2);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        reservaPlazas(1);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        reservaPlazas(3);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        reservaPlazas(5);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        reservaPlazas(6);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        reservaPlazas(7);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        reservaPlazas(8);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        reservaPlazas(9);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        reservaPlazas(10);
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        reservaPlazas(11);
    }//GEN-LAST:event_btn11ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        reservaPlazas(12);
    }//GEN-LAST:event_btn12ActionPerformed

    private void btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn13ActionPerformed
        reservaPlazas(13);
    }//GEN-LAST:event_btn13ActionPerformed

    private void btn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn14ActionPerformed
        reservaPlazas(14);
    }//GEN-LAST:event_btn14ActionPerformed

    private void btn15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn15ActionPerformed
        reservaPlazas(15);
    }//GEN-LAST:event_btn15ActionPerformed

    private void btn16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn16ActionPerformed
        reservaPlazas(16);
    }//GEN-LAST:event_btn16ActionPerformed

    private void btn17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn17ActionPerformed
        reservaPlazas(17);
    }//GEN-LAST:event_btn17ActionPerformed

    private void btn18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn18ActionPerformed
        reservaPlazas(18);
    }//GEN-LAST:event_btn18ActionPerformed

    private void btn19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn19ActionPerformed
        reservaPlazas(19);
    }//GEN-LAST:event_btn19ActionPerformed

    private void btn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn21ActionPerformed
        reservaPlazas(21);
    }//GEN-LAST:event_btn21ActionPerformed

    private void btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn22ActionPerformed
        reservaPlazas(22);
    }//GEN-LAST:event_btn22ActionPerformed

    private void btn23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn23ActionPerformed
        reservaPlazas(23);
    }//GEN-LAST:event_btn23ActionPerformed

    private void btn24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn24ActionPerformed
        reservaPlazas(24);
    }//GEN-LAST:event_btn24ActionPerformed

    private void btn25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn25ActionPerformed
        reservaPlazas(25);
    }//GEN-LAST:event_btn25ActionPerformed

    private void btn26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn26ActionPerformed
        reservaPlazas(26);
    }//GEN-LAST:event_btn26ActionPerformed

    private void btn27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn27ActionPerformed
        reservaPlazas(27);
    }//GEN-LAST:event_btn27ActionPerformed

    private void btn28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn28ActionPerformed
        reservaPlazas(28);
    }//GEN-LAST:event_btn28ActionPerformed

    private void btn29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn29ActionPerformed
        reservaPlazas(29);
    }//GEN-LAST:event_btn29ActionPerformed

    private void btn30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn30ActionPerformed
        reservaPlazas(30);
    }//GEN-LAST:event_btn30ActionPerformed

    private void btn31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn31ActionPerformed
        reservaPlazas(31);
    }//GEN-LAST:event_btn31ActionPerformed

    private void btn32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn32ActionPerformed
        reservaPlazas(32);
    }//GEN-LAST:event_btn32ActionPerformed

    private void btn33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn33ActionPerformed
        reservaPlazas(33);
    }//GEN-LAST:event_btn33ActionPerformed

    private void btn34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn34ActionPerformed
        reservaPlazas(34);
    }//GEN-LAST:event_btn34ActionPerformed

    private void btn35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn35ActionPerformed
        reservaPlazas(35);
    }//GEN-LAST:event_btn35ActionPerformed

    private void btn36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn36ActionPerformed
        reservaPlazas(36);
    }//GEN-LAST:event_btn36ActionPerformed

    private void btn37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn37ActionPerformed
        reservaPlazas(37);
    }//GEN-LAST:event_btn37ActionPerformed

    private void btn38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn38ActionPerformed
        reservaPlazas(38);
    }//GEN-LAST:event_btn38ActionPerformed

    private void btn39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn39ActionPerformed
        reservaPlazas(39);
    }//GEN-LAST:event_btn39ActionPerformed

    private void btn40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn40ActionPerformed
        reservaPlazas(40);
    }//GEN-LAST:event_btn40ActionPerformed

    private void btn41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn41ActionPerformed
        reservaPlazas(41);
    }//GEN-LAST:event_btn41ActionPerformed

    private void btn42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn42ActionPerformed
        reservaPlazas(42);
    }//GEN-LAST:event_btn42ActionPerformed

    private void btn43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn43ActionPerformed
        reservaPlazas(43);
    }//GEN-LAST:event_btn43ActionPerformed

    private void btn44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn44ActionPerformed
        reservaPlazas(44);
    }//GEN-LAST:event_btn44ActionPerformed

    private void btn45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn45ActionPerformed
        reservaPlazas(45);
    }//GEN-LAST:event_btn45ActionPerformed

    private void btn46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn46ActionPerformed
        reservaPlazas(46);
    }//GEN-LAST:event_btn46ActionPerformed

    private void btn47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn47ActionPerformed
        reservaPlazas(47);
    }//GEN-LAST:event_btn47ActionPerformed

    private void btn49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn49ActionPerformed
        reservaPlazas(49);
    }//GEN-LAST:event_btn49ActionPerformed

    private void btn50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn50ActionPerformed
        reservaPlazas(50);
    }//GEN-LAST:event_btn50ActionPerformed

    private void btn51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn51ActionPerformed
        reservaPlazas(51);
    }//GEN-LAST:event_btn51ActionPerformed

    private void btn52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn52ActionPerformed
        reservaPlazas(52);
    }//GEN-LAST:event_btn52ActionPerformed

    private void btn53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn53ActionPerformed
        reservaPlazas(53);
    }//GEN-LAST:event_btn53ActionPerformed

    private void btn54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn54ActionPerformed
        reservaPlazas(54);
    }//GEN-LAST:event_btn54ActionPerformed

    private void btn55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn55ActionPerformed
        reservaPlazas(55);
    }//GEN-LAST:event_btn55ActionPerformed

    private void btn56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn56ActionPerformed
        reservaPlazas(56);
    }//GEN-LAST:event_btn56ActionPerformed

    private void btn57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn57ActionPerformed
        reservaPlazas(57);
    }//GEN-LAST:event_btn57ActionPerformed

    private void btn58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn58ActionPerformed
        reservaPlazas(58);
    }//GEN-LAST:event_btn58ActionPerformed

    private void btn60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn60ActionPerformed
        reservaPlazas(60);
    }//GEN-LAST:event_btn60ActionPerformed

    private void btn61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn61ActionPerformed
        reservaPlazas(61);
    }//GEN-LAST:event_btn61ActionPerformed

    private void btn62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn62ActionPerformed
        reservaPlazas(62);
    }//GEN-LAST:event_btn62ActionPerformed

    private void btn63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn63ActionPerformed
        reservaPlazas(63);
    }//GEN-LAST:event_btn63ActionPerformed

    private void btn64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn64ActionPerformed
        reservaPlazas(64);
    }//GEN-LAST:event_btn64ActionPerformed

    private void btn65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn65ActionPerformed
        reservaPlazas(65);
    }//GEN-LAST:event_btn65ActionPerformed

    private void btn66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn66ActionPerformed
        reservaPlazas(66);
    }//GEN-LAST:event_btn66ActionPerformed

    private void btn67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn67ActionPerformed
        reservaPlazas(67);
    }//GEN-LAST:event_btn67ActionPerformed

    private void btn68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn68ActionPerformed
        reservaPlazas(68);
    }//GEN-LAST:event_btn68ActionPerformed

    private void btn69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn69ActionPerformed
        reservaPlazas(69);
    }//GEN-LAST:event_btn69ActionPerformed

    private void btn70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn70ActionPerformed
        reservaPlazas(70);
    }//GEN-LAST:event_btn70ActionPerformed

    private void btn71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn71ActionPerformed
        reservaPlazas(71);
    }//GEN-LAST:event_btn71ActionPerformed

    private void btn72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn72ActionPerformed
        reservaPlazas(72);
    }//GEN-LAST:event_btn72ActionPerformed

    private void btn73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn73ActionPerformed
        reservaPlazas(73);
    }//GEN-LAST:event_btn73ActionPerformed

    private void btn74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn74ActionPerformed
        reservaPlazas(74);
    }//GEN-LAST:event_btn74ActionPerformed

    private void btn75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn75ActionPerformed
        reservaPlazas(75);
    }//GEN-LAST:event_btn75ActionPerformed

    private void btn76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn76ActionPerformed
        reservaPlazas(76);
    }//GEN-LAST:event_btn76ActionPerformed

    private void btn77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn77ActionPerformed
        reservaPlazas(77);
    }//GEN-LAST:event_btn77ActionPerformed

    private void btn78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn78ActionPerformed
        reservaPlazas(78);
    }//GEN-LAST:event_btn78ActionPerformed

    private void btn79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn79ActionPerformed
        reservaPlazas(79);
    }//GEN-LAST:event_btn79ActionPerformed

    private void btn80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn80ActionPerformed
        reservaPlazas(80);
    }//GEN-LAST:event_btn80ActionPerformed

    private void btn81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn81ActionPerformed
        reservaPlazas(81);
    }//GEN-LAST:event_btn81ActionPerformed

    private void btn82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn82ActionPerformed
        reservaPlazas(82);
    }//GEN-LAST:event_btn82ActionPerformed

    private void btn83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn83ActionPerformed
        reservaPlazas(83);
    }//GEN-LAST:event_btn83ActionPerformed

    private void btn84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn84ActionPerformed
        reservaPlazas(84);
    }//GEN-LAST:event_btn84ActionPerformed

    private void btn85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn85ActionPerformed
        reservaPlazas(85);
    }//GEN-LAST:event_btn85ActionPerformed

    private void btn87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn87ActionPerformed
        reservaPlazas(87);
    }//GEN-LAST:event_btn87ActionPerformed

    private void btn88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn88ActionPerformed
        reservaPlazas(88);
    }//GEN-LAST:event_btn88ActionPerformed

    private void btn89ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn89ActionPerformed
        reservaPlazas(89);
    }//GEN-LAST:event_btn89ActionPerformed

    private void btn90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn90ActionPerformed
        reservaPlazas(90);
    }//GEN-LAST:event_btn90ActionPerformed

    private void btn91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn91ActionPerformed
        reservaPlazas(91);
    }//GEN-LAST:event_btn91ActionPerformed

    private void btn92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn92ActionPerformed
        reservaPlazas(92);
    }//GEN-LAST:event_btn92ActionPerformed

    private void btn93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn93ActionPerformed
        reservaPlazas(93);
    }//GEN-LAST:event_btn93ActionPerformed

    private void btn94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn94ActionPerformed
        reservaPlazas(94);
    }//GEN-LAST:event_btn94ActionPerformed

    private void btn96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn96ActionPerformed
        reservaPlazas(96);
    }//GEN-LAST:event_btn96ActionPerformed

    private void btn97ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn97ActionPerformed
        reservaPlazas(97);
    }//GEN-LAST:event_btn97ActionPerformed

    private void btn100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn100ActionPerformed
        reservaPlazas(100);
    }//GEN-LAST:event_btn100ActionPerformed

    private void btn101ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn101ActionPerformed
        reservaPlazas(101);
    }//GEN-LAST:event_btn101ActionPerformed

    private void btn102ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn102ActionPerformed
        reservaPlazas(102);
    }//GEN-LAST:event_btn102ActionPerformed

    private void btn103ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn103ActionPerformed
        reservaPlazas(103);
    }//GEN-LAST:event_btn103ActionPerformed

    private void btn104ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn104ActionPerformed
        reservaPlazas(104);
    }//GEN-LAST:event_btn104ActionPerformed

    private void btn105ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn105ActionPerformed
        reservaPlazas(105);
    }//GEN-LAST:event_btn105ActionPerformed

    private void btn106ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn106ActionPerformed
        reservaPlazas(106);
    }//GEN-LAST:event_btn106ActionPerformed

    private void btn107ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn107ActionPerformed
        reservaPlazas(107);
    }//GEN-LAST:event_btn107ActionPerformed

    private void btn108ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn108ActionPerformed
        reservaPlazas(108);
    }//GEN-LAST:event_btn108ActionPerformed

    private void btn109ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn109ActionPerformed
        reservaPlazas(109);
    }//GEN-LAST:event_btn109ActionPerformed

    private void btn110ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn110ActionPerformed
        reservaPlazas(110);
    }//GEN-LAST:event_btn110ActionPerformed

    private void btn111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn111ActionPerformed
        reservaPlazas(111);
    }//GEN-LAST:event_btn111ActionPerformed

    private void btn112ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn112ActionPerformed
        reservaPlazas(112);
    }//GEN-LAST:event_btn112ActionPerformed

    private void btn113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn113ActionPerformed
        reservaPlazas(113);
    }//GEN-LAST:event_btn113ActionPerformed

    private void btn114ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn114ActionPerformed
        reservaPlazas(114);
    }//GEN-LAST:event_btn114ActionPerformed

    private void btn115ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn115ActionPerformed
        reservaPlazas(115);
    }//GEN-LAST:event_btn115ActionPerformed

    private void btn116ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn116ActionPerformed
        reservaPlazas(116);
    }//GEN-LAST:event_btn116ActionPerformed

    private void btn117ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn117ActionPerformed
        reservaPlazas(117);
    }//GEN-LAST:event_btn117ActionPerformed

    private void btn118ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn118ActionPerformed
        reservaPlazas(118);
    }//GEN-LAST:event_btn118ActionPerformed

    private void btn119ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn119ActionPerformed
        reservaPlazas(119);
    }//GEN-LAST:event_btn119ActionPerformed

    private void btn120ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn120ActionPerformed
        reservaPlazas(120);
    }//GEN-LAST:event_btn120ActionPerformed

    private void btn121ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn121ActionPerformed
        reservaPlazas(121);
    }//GEN-LAST:event_btn121ActionPerformed

    private void btn122ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn122ActionPerformed
        reservaPlazas(122);
    }//GEN-LAST:event_btn122ActionPerformed

    private void btn124ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn124ActionPerformed
        reservaPlazas(124);
    }//GEN-LAST:event_btn124ActionPerformed

    private void btn126ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn126ActionPerformed
        reservaPlazas(126);
    }//GEN-LAST:event_btn126ActionPerformed

    private void btn127ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn127ActionPerformed
        reservaPlazas(127);
    }//GEN-LAST:event_btn127ActionPerformed

    private void btn128ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn128ActionPerformed
        reservaPlazas(128);
    }//GEN-LAST:event_btn128ActionPerformed

    private void btn129ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn129ActionPerformed
        reservaPlazas(129);
    }//GEN-LAST:event_btn129ActionPerformed

    private void btn130ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn130ActionPerformed
        reservaPlazas(130);
    }//GEN-LAST:event_btn130ActionPerformed

    private void btn131ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn131ActionPerformed
        reservaPlazas(131);
    }//GEN-LAST:event_btn131ActionPerformed

    private void btn132ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn132ActionPerformed
        reservaPlazas(132);
    }//GEN-LAST:event_btn132ActionPerformed

    private void btn133ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn133ActionPerformed
        reservaPlazas(133);
    }//GEN-LAST:event_btn133ActionPerformed

    private void btn134ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn134ActionPerformed
        reservaPlazas(134);
    }//GEN-LAST:event_btn134ActionPerformed

    private void btn135ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn135ActionPerformed
        reservaPlazas(135);
    }//GEN-LAST:event_btn135ActionPerformed

    private void btn136ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn136ActionPerformed
        reservaPlazas(136);
    }//GEN-LAST:event_btn136ActionPerformed

    private void btn137ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn137ActionPerformed
        reservaPlazas(137);
    }//GEN-LAST:event_btn137ActionPerformed

    private void btn138ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn138ActionPerformed
        reservaPlazas(138);
    }//GEN-LAST:event_btn138ActionPerformed

    private void btn139ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn139ActionPerformed
        reservaPlazas(139);
    }//GEN-LAST:event_btn139ActionPerformed

    private void btn140ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn140ActionPerformed
        reservaPlazas(140);
    }//GEN-LAST:event_btn140ActionPerformed

    private void btn141ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn141ActionPerformed
        reservaPlazas(141);
    }//GEN-LAST:event_btn141ActionPerformed

    private void btn142ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn142ActionPerformed
        reservaPlazas(142);
    }//GEN-LAST:event_btn142ActionPerformed

    private void btn143ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn143ActionPerformed
        reservaPlazas(143);
    }//GEN-LAST:event_btn143ActionPerformed

    private void btn144ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn144ActionPerformed
        reservaPlazas(144);
    }//GEN-LAST:event_btn144ActionPerformed

    private void btn145ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn145ActionPerformed
        reservaPlazas(145);
    }//GEN-LAST:event_btn145ActionPerformed

    private void btn146ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn146ActionPerformed
        reservaPlazas(146);
    }//GEN-LAST:event_btn146ActionPerformed

    private void btn147ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn147ActionPerformed
        reservaPlazas(147);
    }//GEN-LAST:event_btn147ActionPerformed

    private void btn148ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn148ActionPerformed
        reservaPlazas(148);
    }//GEN-LAST:event_btn148ActionPerformed

    private void btn149ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn149ActionPerformed
        reservaPlazas(149);
    }//GEN-LAST:event_btn149ActionPerformed

    private void btn150ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn150ActionPerformed
        reservaPlazas(150);
    }//GEN-LAST:event_btn150ActionPerformed

    private void btn151ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn151ActionPerformed
        reservaPlazas(151);
    }//GEN-LAST:event_btn151ActionPerformed

    private void btn153ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn153ActionPerformed
        reservaPlazas(153);
    }//GEN-LAST:event_btn153ActionPerformed

    private void btn154ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn154ActionPerformed
        reservaPlazas(154);
    }//GEN-LAST:event_btn154ActionPerformed

    private void btn155ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn155ActionPerformed
        reservaPlazas(155);
    }//GEN-LAST:event_btn155ActionPerformed

    private void btn156ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn156ActionPerformed
        reservaPlazas(156);
    }//GEN-LAST:event_btn156ActionPerformed

    private void btn157ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn157ActionPerformed
        reservaPlazas(157);
    }//GEN-LAST:event_btn157ActionPerformed

    private void btn158ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn158ActionPerformed
        reservaPlazas(158);
    }//GEN-LAST:event_btn158ActionPerformed

    private void btn159ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn159ActionPerformed
        reservaPlazas(159);
    }//GEN-LAST:event_btn159ActionPerformed

    private void btn160ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn160ActionPerformed
        reservaPlazas(160);
    }//GEN-LAST:event_btn160ActionPerformed

    private void btn161ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn161ActionPerformed
        reservaPlazas(161);
    }//GEN-LAST:event_btn161ActionPerformed

    private void btn162ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn162ActionPerformed
        reservaPlazas(162);
    }//GEN-LAST:event_btn162ActionPerformed

    private void btn163ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn163ActionPerformed
        reservaPlazas(163);
    }//GEN-LAST:event_btn163ActionPerformed

    private void btn164ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn164ActionPerformed
        reservaPlazas(164);
    }//GEN-LAST:event_btn164ActionPerformed

    private void btn165ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn165ActionPerformed
        reservaPlazas(165);
    }//GEN-LAST:event_btn165ActionPerformed

    private void btn166ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn166ActionPerformed
        reservaPlazas(166);
    }//GEN-LAST:event_btn166ActionPerformed

    private void btn167ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn167ActionPerformed
        reservaPlazas(167);
    }//GEN-LAST:event_btn167ActionPerformed

    private void btn168ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn168ActionPerformed
        reservaPlazas(168);
    }//GEN-LAST:event_btn168ActionPerformed

    private void btn169ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn169ActionPerformed
        reservaPlazas(169);
    }//GEN-LAST:event_btn169ActionPerformed

    private void btn170ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn170ActionPerformed
        reservaPlazas(170);
    }//GEN-LAST:event_btn170ActionPerformed

    private void btn171ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn171ActionPerformed
        reservaPlazas(171);
    }//GEN-LAST:event_btn171ActionPerformed

    private void btn172ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn172ActionPerformed
        reservaPlazas(172);
    }//GEN-LAST:event_btn172ActionPerformed

    private void btn173ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn173ActionPerformed
        reservaPlazas(173);
    }//GEN-LAST:event_btn173ActionPerformed

    private void btn174ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn174ActionPerformed
        reservaPlazas(174);
    }//GEN-LAST:event_btn174ActionPerformed

    private void btn175ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn175ActionPerformed
        reservaPlazas(175);
    }//GEN-LAST:event_btn175ActionPerformed

    private void btn176ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn176ActionPerformed
        reservaPlazas(176);
    }//GEN-LAST:event_btn176ActionPerformed

    private void btn177ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn177ActionPerformed
        reservaPlazas(177);
    }//GEN-LAST:event_btn177ActionPerformed

    private void btn178ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn178ActionPerformed
        reservaPlazas(178);
    }//GEN-LAST:event_btn178ActionPerformed

    private void btn179ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn179ActionPerformed
        reservaPlazas(179);
    }//GEN-LAST:event_btn179ActionPerformed

    private void btn180ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn180ActionPerformed
        reservaPlazas(180);
    }//GEN-LAST:event_btn180ActionPerformed

    private void btn181ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn181ActionPerformed
        reservaPlazas(181);
    }//GEN-LAST:event_btn181ActionPerformed

    private void btn182ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn182ActionPerformed
        reservaPlazas(182);
    }//GEN-LAST:event_btn182ActionPerformed

    private void btn183ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn183ActionPerformed
        reservaPlazas(183);
    }//GEN-LAST:event_btn183ActionPerformed

    private void btn185ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn185ActionPerformed
        reservaPlazas(185);
    }//GEN-LAST:event_btn185ActionPerformed

    private void btn186ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn186ActionPerformed
        reservaPlazas(186);
    }//GEN-LAST:event_btn186ActionPerformed

    private void btn187ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn187ActionPerformed
        reservaPlazas(187);
    }//GEN-LAST:event_btn187ActionPerformed

    private void btn188ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn188ActionPerformed
        reservaPlazas(188);
    }//GEN-LAST:event_btn188ActionPerformed

    private void btn189ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn189ActionPerformed
        reservaPlazas(189);
    }//GEN-LAST:event_btn189ActionPerformed

    private void btn190ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn190ActionPerformed
        reservaPlazas(190);
    }//GEN-LAST:event_btn190ActionPerformed

    private void btn191ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn191ActionPerformed
        reservaPlazas(191);
    }//GEN-LAST:event_btn191ActionPerformed

    private void btn193ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn193ActionPerformed
        reservaPlazas(193);
    }//GEN-LAST:event_btn193ActionPerformed

    private void btn194ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn194ActionPerformed
        reservaPlazas(194);
    }//GEN-LAST:event_btn194ActionPerformed

    private void btn195ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn195ActionPerformed
        reservaPlazas(195);
    }//GEN-LAST:event_btn195ActionPerformed

    private void btn196ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn196ActionPerformed
        reservaPlazas(196);
    }//GEN-LAST:event_btn196ActionPerformed

    private void btn197ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn197ActionPerformed
        reservaPlazas(197);
    }//GEN-LAST:event_btn197ActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        plaTotal(true);
        txtIDRut.setText("");
        buttonGroup1.clearSelection();
        rbtnId.setSelected(true);
        lblId.setText("ID:");
        lblRut.setText("RUT:");
        lblNombre.setText("NOMBRE:");
        lblRol.setText("ROL:");
        lblPatente.setText("PATENTE:");
        lblReserva.setText("RESERVA:");
        ocultarInfoCli();
        txtIDRut.requestFocus();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtIDRutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDRutKeyTyped
        if(rbtnId.isSelected())
        {
            ElementosLimite el = new ElementosLimite();
            el.soloNumeros(evt, txtIDRut, 11);
        }
        else
        {
            ElementosLimite el = new ElementosLimite();
            el.limiteCaracteres(evt, txtIDRut, 9);
        }
    }//GEN-LAST:event_txtIDRutKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn1;
    public javax.swing.JButton btn10;
    public javax.swing.JButton btn100;
    public javax.swing.JButton btn101;
    public javax.swing.JButton btn102;
    public javax.swing.JButton btn103;
    public javax.swing.JButton btn104;
    public javax.swing.JButton btn105;
    public javax.swing.JButton btn106;
    public javax.swing.JButton btn107;
    public javax.swing.JButton btn108;
    public javax.swing.JButton btn109;
    public javax.swing.JButton btn11;
    public javax.swing.JButton btn110;
    public javax.swing.JButton btn111;
    public javax.swing.JButton btn112;
    public javax.swing.JButton btn113;
    public javax.swing.JButton btn114;
    public javax.swing.JButton btn115;
    public javax.swing.JButton btn116;
    public javax.swing.JButton btn117;
    public javax.swing.JButton btn118;
    public javax.swing.JButton btn119;
    public javax.swing.JButton btn12;
    public javax.swing.JButton btn120;
    public javax.swing.JButton btn121;
    public javax.swing.JButton btn122;
    public javax.swing.JButton btn123;
    public javax.swing.JButton btn124;
    public javax.swing.JButton btn125;
    public javax.swing.JButton btn126;
    public javax.swing.JButton btn127;
    public javax.swing.JButton btn128;
    public javax.swing.JButton btn129;
    public javax.swing.JButton btn13;
    public javax.swing.JButton btn130;
    public javax.swing.JButton btn131;
    public javax.swing.JButton btn132;
    public javax.swing.JButton btn133;
    public javax.swing.JButton btn134;
    public javax.swing.JButton btn135;
    public javax.swing.JButton btn136;
    public javax.swing.JButton btn137;
    public javax.swing.JButton btn138;
    public javax.swing.JButton btn139;
    public javax.swing.JButton btn14;
    public javax.swing.JButton btn140;
    public javax.swing.JButton btn141;
    public javax.swing.JButton btn142;
    public javax.swing.JButton btn143;
    public javax.swing.JButton btn144;
    public javax.swing.JButton btn145;
    public javax.swing.JButton btn146;
    public javax.swing.JButton btn147;
    public javax.swing.JButton btn148;
    public javax.swing.JButton btn149;
    public javax.swing.JButton btn15;
    public javax.swing.JButton btn150;
    public javax.swing.JButton btn151;
    public javax.swing.JButton btn152;
    public javax.swing.JButton btn153;
    public javax.swing.JButton btn154;
    public javax.swing.JButton btn155;
    public javax.swing.JButton btn156;
    public javax.swing.JButton btn157;
    public javax.swing.JButton btn158;
    public javax.swing.JButton btn159;
    public javax.swing.JButton btn16;
    public javax.swing.JButton btn160;
    public javax.swing.JButton btn161;
    public javax.swing.JButton btn162;
    public javax.swing.JButton btn163;
    public javax.swing.JButton btn164;
    public javax.swing.JButton btn165;
    public javax.swing.JButton btn166;
    public javax.swing.JButton btn167;
    public javax.swing.JButton btn168;
    public javax.swing.JButton btn169;
    public javax.swing.JButton btn17;
    public javax.swing.JButton btn170;
    public javax.swing.JButton btn171;
    public javax.swing.JButton btn172;
    public javax.swing.JButton btn173;
    public javax.swing.JButton btn174;
    public javax.swing.JButton btn175;
    public javax.swing.JButton btn176;
    public javax.swing.JButton btn177;
    public javax.swing.JButton btn178;
    public javax.swing.JButton btn179;
    public javax.swing.JButton btn18;
    public javax.swing.JButton btn180;
    public javax.swing.JButton btn181;
    public javax.swing.JButton btn182;
    public javax.swing.JButton btn183;
    public javax.swing.JButton btn184;
    public javax.swing.JButton btn185;
    public javax.swing.JButton btn186;
    public javax.swing.JButton btn187;
    public javax.swing.JButton btn188;
    public javax.swing.JButton btn189;
    public javax.swing.JButton btn19;
    public javax.swing.JButton btn190;
    public javax.swing.JButton btn191;
    public javax.swing.JButton btn192;
    public javax.swing.JButton btn193;
    public javax.swing.JButton btn194;
    public javax.swing.JButton btn195;
    public javax.swing.JButton btn196;
    public javax.swing.JButton btn197;
    public javax.swing.JButton btn2;
    public javax.swing.JButton btn20;
    public javax.swing.JButton btn21;
    public javax.swing.JButton btn22;
    public javax.swing.JButton btn23;
    public javax.swing.JButton btn24;
    public javax.swing.JButton btn25;
    public javax.swing.JButton btn26;
    public javax.swing.JButton btn27;
    public javax.swing.JButton btn28;
    public javax.swing.JButton btn29;
    public javax.swing.JButton btn3;
    public javax.swing.JButton btn30;
    public javax.swing.JButton btn31;
    public javax.swing.JButton btn32;
    public javax.swing.JButton btn33;
    public javax.swing.JButton btn34;
    public javax.swing.JButton btn35;
    public javax.swing.JButton btn36;
    public javax.swing.JButton btn37;
    public javax.swing.JButton btn38;
    public javax.swing.JButton btn39;
    public javax.swing.JButton btn4;
    public javax.swing.JButton btn40;
    public javax.swing.JButton btn41;
    public javax.swing.JButton btn42;
    public javax.swing.JButton btn43;
    public javax.swing.JButton btn44;
    public javax.swing.JButton btn45;
    public javax.swing.JButton btn46;
    public javax.swing.JButton btn47;
    public javax.swing.JButton btn48;
    public javax.swing.JButton btn49;
    public javax.swing.JButton btn5;
    public javax.swing.JButton btn50;
    public javax.swing.JButton btn51;
    public javax.swing.JButton btn52;
    public javax.swing.JButton btn53;
    public javax.swing.JButton btn54;
    public javax.swing.JButton btn55;
    public javax.swing.JButton btn56;
    public javax.swing.JButton btn57;
    public javax.swing.JButton btn58;
    public javax.swing.JButton btn59;
    public javax.swing.JButton btn6;
    public javax.swing.JButton btn60;
    public javax.swing.JButton btn61;
    public javax.swing.JButton btn62;
    public javax.swing.JButton btn63;
    public javax.swing.JButton btn64;
    public javax.swing.JButton btn65;
    public javax.swing.JButton btn66;
    public javax.swing.JButton btn67;
    public javax.swing.JButton btn68;
    public javax.swing.JButton btn69;
    public javax.swing.JButton btn7;
    public javax.swing.JButton btn70;
    public javax.swing.JButton btn71;
    public javax.swing.JButton btn72;
    public javax.swing.JButton btn73;
    public javax.swing.JButton btn74;
    public javax.swing.JButton btn75;
    public javax.swing.JButton btn76;
    public javax.swing.JButton btn77;
    public javax.swing.JButton btn78;
    public javax.swing.JButton btn79;
    public javax.swing.JButton btn8;
    public javax.swing.JButton btn80;
    public javax.swing.JButton btn81;
    public javax.swing.JButton btn82;
    public javax.swing.JButton btn83;
    public javax.swing.JButton btn84;
    public javax.swing.JButton btn85;
    public javax.swing.JButton btn86;
    public javax.swing.JButton btn87;
    public javax.swing.JButton btn88;
    public javax.swing.JButton btn89;
    public javax.swing.JButton btn9;
    public javax.swing.JButton btn90;
    public javax.swing.JButton btn91;
    public javax.swing.JButton btn92;
    public javax.swing.JButton btn93;
    public javax.swing.JButton btn94;
    public javax.swing.JButton btn95;
    public javax.swing.JButton btn96;
    public javax.swing.JButton btn97;
    public javax.swing.JButton btn98;
    public javax.swing.JButton btn99;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel lbl109a118;
    public javax.swing.JLabel lbl131a152;
    public javax.swing.JLabel lbl153a176;
    public javax.swing.JLabel lbl185a177;
    public javax.swing.JLabel lbl192a186;
    public javax.swing.JLabel lbl194a193;
    public javax.swing.JLabel lbl1a20;
    public javax.swing.JLabel lbl21a26;
    public javax.swing.JLabel lbl27a42;
    public javax.swing.JLabel lbl43a64;
    public javax.swing.JLabel lbl65a86;
    public javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPatente;
    private javax.swing.JLabel lblReserva;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblRut;
    private javax.swing.JRadioButton rbtnId;
    private javax.swing.JRadioButton rbtnRut;
    private javax.swing.JTextField txtIDRut;
    // End of variables declaration//GEN-END:variables
}
