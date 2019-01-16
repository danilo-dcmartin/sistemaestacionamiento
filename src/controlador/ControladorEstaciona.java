/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ConexionBD;
import modelo.ModeloEstaciona;

/**
 *
 * @author Danilo Martinez
 */
public class ControladorEstaciona
{
    ModeloEstaciona me = new ModeloEstaciona();
    public String optenerNombreRol()
    {
        ResultSet rs;
        String identidad = "";
        rs = me.optenerNombreApRol();
        try {
            while(rs.next())
            {
                identidad = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " - " + rs.getString(4).toUpperCase();
            }
        }
        catch (SQLException ex)
        {
            
        }
        return identidad;
    }
    public boolean verificarConexionBD()
    {
        boolean conexion = false;
        Connection cn = null;
        ConexionBD bd = new ConexionBD();
        cn = bd.conexion();
        if(cn != null)
        {
            conexion = true;
        }
        return conexion;
    }
    public String login(String user, String pass)
    {
        String rol = "0";
        ResultSet rs = null;
        ModeloEstaciona me = new ModeloEstaciona();
        try
        {
            rs = me.inicioSesion();
            while(rs.next())
            {
                if(rs.getString(1).equals(user) && rs.getString(2).equals(pass))
                {
                    me.setRutOperador(user);
                    rol = rs.getString(3);
                }
            }
            return rol;
        }
        catch (SQLException ex)
        {
            return rol;
        }
        catch(NullPointerException ex)
        {
            return "-1";
        }
    }
    public byte cambioPass(String antPass, String nuevPass, String confNuevPass)
    {
        byte i, cp = -1;
        ModeloEstaciona me = new ModeloEstaciona();
        if(antPass.length() > 0 && nuevPass.length() > 0 && confNuevPass.length() > 0)
        {
            if(nuevPass.equals(confNuevPass))
            {
                i = me.cambioPass(nuevPass, antPass);
                if(i != 0)
                {
                    cp = 0; // cambio pass correcto
                }
                else
                {
                    cp = -2; // contraseña actual incorrecta
                }
            }
            else
            {
                cp = -3; // nuevas contraseñas no coinciden
            }
        }
        else
        {
            cp = -1; // TextBox vacios
        }
        return cp;
    }
    public String verificarIDRUT(String id, String rut)
    {
        boolean comprob;
        String laID;
        ModeloEstaciona ce = new ModeloEstaciona();
        if(!id.equals("0")) // id
        {
            laID = id;
            comprob = ce.comprobarIDex(laID);
            if(comprob)
            {
                return laID;
            }
            else
            {
                return "-1"; // no existe ID
            }
        }
        else // rut
        {
            laID = ce.obtenerID(rut);
            if(!laID.equals("-1"))
            {
                return laID;
            }
            else
            {
                return "-1"; // no exite rut, por ende, no existe ID
            }
        }
    }
    public ArrayList<String> infoUsuario(String id)
    {
        String plaza;
        ResultSet rs;
        ModeloEstaciona me = new ModeloEstaciona();
        ArrayList<String> datosPersonales = new ArrayList<String>();
        try
        {    
            rs = me.informacionCliente(id);
            while(rs.next())
            {
                for(byte i = 1; i <= 7; i++)
                {
                    if(i == 3)
                    {
                        datosPersonales.add(rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
                        i = 5;
                    }
                    else
                    {
                        datosPersonales.add(rs.getString(i));
                    }
                }
            }
            plaza = me.comprobarReservaPlaza(id);
            datosPersonales.add(plaza);
        }
        catch (SQLException ex)
        {
            System.out.println("Error controlador infousuario: " + ex.getMessage());
        }
        return datosPersonales;
    }
    public ResultSet inicioTablaUsuarios()       
    {
        ResultSet rs = null;
        ModeloEstaciona me = new ModeloEstaciona();
        rs = me.datosUsuarios();
        return rs;
    }
    public ArrayList<String> editarItemUsuario(String id)
    {
        ResultSet rs = null;
        ModeloEstaciona me = new ModeloEstaciona();
        rs = me.datosClienteEdicion(id);
        ArrayList<String> datos = new ArrayList<String>();
        try
        {
            while(rs.next())
            {
                for(byte i = 1; i <= 12; i++)
                {
                    datos.add(rs.getString(i));
                }
            }
        }
        catch (SQLException ex)
        {
            
        }
        return datos;
    }
    public ArrayList<String> cmbRol()
    {
        ResultSet rs;
        ModeloEstaciona me = new ModeloEstaciona();
        ArrayList<String> datos = new ArrayList<String>();
        rs = me.datosCMBRol();
        try
        {
            while(rs.next())
            {
                datos.add(rs.getString(1));
            }
        }
        catch (SQLException ex)
        {
            
        }
        return datos;
    }
    public boolean edicionInfoUsuario(String id_barra,String rut, String nombres, String app, String apm, String tel, String disc, String rol, String id_auto, String patente1, String patente2, String patente3, String modelo, String color)
    {
        ModeloEstaciona me = new ModeloEstaciona();
        boolean estado = false;
        estado = me.edicionDatosUsu(id_barra, rut, nombres, app, apm, tel, disc, rol, id_auto, patente1, patente2, patente3, modelo, color);
        if(estado)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean agregarUsuario(String id_barra,String rut, String nombres, String app, String apm, String tel, String disc, String rol, String patente, String modelo, String color)
    {
        ModeloEstaciona me = new ModeloEstaciona();
        boolean estado = false;
        estado = me.agregarUsu(id_barra, rut, nombres, app, apm, tel, disc, rol, patente, modelo, color);
        if(estado)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public ArrayList<String> datosPlaza(int plaza)
    {
        ResultSet rs = null;
        ModeloEstaciona me = new ModeloEstaciona();
        ArrayList<String> datos = new ArrayList<String>();
        rs = me.optenerReservaPlaza(plaza);
        try
        {
            while(rs.next())
            {
                datos.add(rs.getString(1));
                datos.add(rs.getString(2));
                datos.add(rs.getString(3));
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return datos;
    }
    public int reservarPlaza(String id, int plaza)
    {
        int res = 0;
        boolean exito = false;
        ModeloEstaciona me = new ModeloEstaciona();
        exito = me.reservaPlaza(id, plaza);
        if(exito)
        {
            res = 1;
        }
        return res;
    }
    public void registroHistorialPlaza(String id, int plaza)
    {
        boolean exito = false;
        ModeloEstaciona me = new ModeloEstaciona();
        exito = me.registroHistorial(id, plaza);
        if(!exito)
        {
            System.out.println("error");
        }
    }
    public boolean esDisc(String id)
    {
        boolean exito = false;
        ResultSet rs = null;
        ModeloEstaciona me = new ModeloEstaciona();
        rs = me.obtenerDisc(id);
        try
        {
            while(rs.next())
            {
                if(rs.getString(1).equals("si"))
                {
                    exito = true;
                }
                else
                {
                    exito = false;
                }
            }
        }
        catch (SQLException ex)
        {
            
        }
        return exito;
    }
    public void insertarPassAnd(String id, String pass)
    {
        int i;
        ModeloEstaciona me = new ModeloEstaciona();
        i = me.insertPassAndroid(id, pass);
        if(i == 0)
        {
            System.out.println("error controlador insertarPassAnd");
        }
    }
    public String buscarPassAnd(String id)
    {
        String pass;
        ModeloEstaciona me = new ModeloEstaciona();
        pass = me.obtenerPassAnd(id);
        return pass;
    }
    public void insertUpdateAnd(String id, String pass)
    {
        int i;
        ModeloEstaciona me = new ModeloEstaciona();
        i = me.inseUpdaPassAnd(id, pass);
        if(i == 0)
        {
            System.out.println("error controlador insertUpdateAnd");
        }
    }
    public void limpiarTabla(JTable t, DefaultTableModel dt)
    {
       for (int i = 0; i < t.getRowCount(); i++)
       {
           dt.removeRow(i);
           i-=1;
       }
    }
    public ResultSet busquedaInfo(String b, byte op)
    {
        ResultSet rs;
        ModeloEstaciona me = new ModeloEstaciona();
        rs = me.busqInf(b, op);
        return rs;
    }
}
