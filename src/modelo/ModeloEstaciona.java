/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Danilo Martinez
 */
public class ModeloEstaciona
{
    private Connection cn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private static String rutOperador;
    /***********************/
    private static int idEst; // ID de apartado estacionamiento
    /***********************/
    public ResultSet inicioSesion()
    {
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select login_app.rut,login_app.password,rol_app.rol "
                    + "from login_app join rol_app on login_app.rol_app_id_rol=rol_app.id_rol");    
        }
        catch(SQLException ex)
        {
            System.out.println("Error sqlexception conexionBD 1: " + ex.getMessage());
        }
        catch(NullPointerException ex)
        {
            // err
        }
        return rs;
    }

    public static String getRutOperador() {
        return rutOperador;
    }

    public static void setRutOperador(String rutOperador) {
        ModeloEstaciona.rutOperador = rutOperador;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public static int getIdEst() {
        return idEst;
    }

    public static void setIdEst(int idEst) {
        ModeloEstaciona.idEst = idEst;
    }

    public ResultSet optenerNombreApRol()
    {
        ConexionBD cbd = new ConexionBD();
        String identidad = "";
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select login_app.nombres,login_app.ap_paterno,login_app.ap_materno,rol_app.rol "
                    + "from login_app join rol_app on login_app.rol_app_id_rol=rol_app.id_rol where login_app.rut='"+ rutOperador +"'");
            return rs;
        }
        catch(SQLException ex)
        {
            System.out.println("Error sqlexception conexionBD 2: " + ex.getMessage());
            return null;
        }
    }
    public byte cambioPass(String nPass, String pass)
    {
        int i;
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            i = st.executeUpdate("update login_app set password='"+ nPass +"' where rut='"+ this.rutOperador +"' and password='"+ pass +"'");
            return (byte)(i);
        }
        catch(SQLException ex)
        {
            System.out.println("Error sqlexception conexionBD 3: " + ex.getMessage());
            return -1;
        }
    }
    public String obtenerID(String rut)
    {
        String id = "-1";
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select * from datos_personales where rut='"+ rut +"'");
            while(rs.next())
            {
                id = rs.getString(1);
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error sqlexception conexionBD 5: " + ex.getMessage());
        }
        return id;
    }
    public boolean comprobarIDex(String id)
    {
        boolean exi = false;
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select * from datos_personales where id_barra="+ id +"");
            while(rs.next())
            {
                exi = true;
            }
            return exi;
        }
        catch(SQLException ex)
        {
            System.out.println("Error sqlexception conexionBD 5: " + ex.getMessage());
            return false;
        }
    }
    public ResultSet informacionCliente(String id)
    {
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select datos_personales.id_barra,datos_personales.rut,datos_personales.nombres,datos_personales.ap_paterno,"
                    + "datos_personales.ap_materno,usuario.rol,automovil.patente from datos_personales"
                    + " JOIN usuario ON datos_personales.usuario_id_usuario=usuario.id_usuario JOIN automovil"
                    + " on datos_personales.automovil_id_automovil=automovil.id_automovil where datos_personales.id_barra='"+ id +"'");
            return rs;
        }
        catch (SQLException ex)
        {
            System.out.println("Error modelo infocliente:" + ex.getMessage());
            return null;
        }
    }
    public ResultSet datosUsuarios()
    {
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select datos_personales.id_barra,datos_personales.rut,datos_personales.nombres,datos_personales.ap_paterno,"
                    + "datos_personales.ap_materno,datos_personales.telefono,datos_personales.discapacidad,usuario.rol,automovil.patente from datos_personales"
                    + " JOIN usuario ON datos_personales.usuario_id_usuario=usuario.id_usuario JOIN automovil"
                    + " on datos_personales.automovil_id_automovil=automovil.id_automovil");
            return rs;
        }
        catch (SQLException ex)
        {
            System.out.println("Error modelo datosusuario:" + ex.getMessage());
            return null;
        }
    }
    public ResultSet datosClienteEdicion(String id)
    {
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select datos_personales.id_barra,datos_personales.rut,datos_personales.nombres,datos_personales.ap_paterno,"
                    + "datos_personales.ap_materno,datos_personales.telefono,datos_personales.discapacidad,usuario.rol,automovil.id_automovil,automovil.patente,automovil.modelo,automovil.color from datos_personales"
                    + " JOIN usuario ON datos_personales.usuario_id_usuario=usuario.id_usuario JOIN automovil"
                    + " on datos_personales.automovil_id_automovil=automovil.id_automovil where datos_personales.id_barra='"+ id +"'");
            return rs;
        }
        catch (SQLException ex)
        {
            System.out.println("Error modelo datosusuario:" + ex.getMessage());
            return null;
        }
    }
    public ResultSet datosCMBRol()
    {
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select rol from usuario");
            return rs;
        }
        catch (SQLException ex)
        {
            return null;
        }
    }
    public boolean edicionDatosUsu(String id_barra,String rut, String nombres, String app, String apm, String tel, String disc, String rol, String id_auto, String patente1, String patente2, String patente3, String modelo, String color)
    {
        String pat;
        int i = 0, id_rol = 0;
        ConexionBD cbd = new ConexionBD();
        try
        {
            pat = patente1 + "*" + patente2 + "*" + patente3;
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select id_usuario from usuario where rol='"+ rol +"'");
            while(rs.next())
            {
                id_rol = rs.getInt(1);
            }
            i = st.executeUpdate("update automovil set patente='"+ pat +"',modelo='"+ modelo +"',color='"+ color +"' where id_automovil="+ id_auto +"");
            i = st.executeUpdate("update datos_personales set rut='"+ rut +"',nombres='"+ nombres +"',ap_paterno='"+ app +"',"
                    + "ap_materno='"+ apm +"',telefono='"+ tel +"',discapacidad='"+ disc +"',usuario_id_usuario=" + id_rol + " where id_barra="+ id_barra +"");
            return true;
        }
        catch (SQLException ex)
        {
            return false;
        }
    }
    public boolean agregarUsu(String id_barra,String rut, String nombres, String app, String apm, String tel, String disc, String rol, String patente, String modelo, String color)
    {
        int i = 0, id_rol = 0, idAutomovil = 0;
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select id_usuario from usuario where rol='"+ rol +"'");
            while(rs.next())
            {
                id_rol = rs.getInt(1);
            }
            i = st.executeUpdate("insert into automovil (patente,modelo,color) values('"+ patente +"','"+ modelo +"','"+ color +"')");
            rs = st.executeQuery("select id_automovil from automovil where patente='"+ patente +"'");
            while(rs.next())
            {
                idAutomovil = rs.getInt(1);
            }
            i = st.executeUpdate("insert into datos_personales values('"+ id_barra +"','"+ rut +"','"+ nombres +"','"+ app +"'"
                    + ",'"+ apm +"','"+ tel +"','"+ disc +"',"+ id_rol +","+ idAutomovil +")");
            return true;
        }
        catch (SQLException ex)
        {
            return false;
        }
    }
    public String comprobarReservaPlaza(String id)
    {
        String datos = "0";
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select * from plazas where datos_personales_id_barra='"+ id +"'");
            while(rs.next())
            {
                datos = rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            
        }
        return datos;
    }
    public ResultSet optenerReservaPlaza(int plaza)
    {
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select * from plazas where id_plaza="+ plaza +"");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    public boolean reservaPlaza(String id, int plaza)
    {
        int i;
        boolean mess = false;
        ConexionBD cbd = new ConexionBD();
        cn = cbd.conexion();
        try
        {
            st = cn.createStatement();
            i = st.executeUpdate("update plazas set datos_personales_id_barra='"+ id +"' where id_plaza="+ plaza +"");
            if(i != 0)
            {
                mess = true;
            }
        }
        catch (SQLException ex)
        {
            
        }
        return mess;
    }
    public boolean registroHistorial(String id, int plaza)
    {
        int i;
        Date ahora = new Date();
        boolean mess = false;
        ConexionBD cbd = new ConexionBD();
        cn = cbd.conexion();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            st = cn.createStatement();
            i = st.executeUpdate("insert into historial_plazas (fecha,plazas_id_plaza,datos_personales_id_barra) values('"+ dateFormat.format(ahora) +"',"+ plaza +",'"+ id +"')");
            if(i != 0)
            {
                mess = true;
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return mess;
    }
    public ResultSet obtenerDisc(String id)
    {
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select discapacidad from datos_personales where id_barra='"+ id +"'");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    public int insertPassAndroid(String id, String pass)
    {
        int i = 0;
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            i = st.executeUpdate("insert into pass_android (password,datos_personales_id_barra) values ('"+ pass +"','"+ id +"')");
        }
        catch (SQLException ex)
        {
            
        }
        return i;
    }
    public String obtenerPassAnd(String id)
    {
        String pass = "";
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery("select password from pass_android where datos_personales_id_barra='"+ id +"'");
            while(rs.next())
            {
                pass = rs.getString(1);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return pass;
    }
    public int inseUpdaPassAnd(String id, String pass)
    {
        int i = 0;
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            i = st.executeUpdate("update pass_android set password='"+ pass +"' where datos_personales_id_barra='"+ id +"'");
            if(i == 0)
            {
                i = st.executeUpdate("insert into pass_android (password,datos_personales_id_barra) values ('"+ pass +"','"+ id +"')");
            }
        }
        catch (SQLException ex)
        {
            
        }
        return i;
    }
    public ResultSet busqInf(String b, byte op)
    {
        String busq = "", sql;
        ConexionBD cbd = new ConexionBD();
        try
        {
            if(op == 4)
            {
                sql = "select datos_personales.id_barra,datos_personales.rut,datos_personales.nombres,datos_personales.ap_paterno,"
                    + "datos_personales.ap_materno,datos_personales.telefono,datos_personales.discapacidad,usuario.rol,automovil.patente from datos_personales"
                    + " JOIN usuario ON datos_personales.usuario_id_usuario=usuario.id_usuario JOIN automovil"
                    + " on datos_personales.automovil_id_automovil=automovil.id_automovil where automovil.patente like '%"+ b +"%'";
            }
            else
            {
                if(op == 1)
                {
                    busq = "datos_personales.id_barra";
                }
                if(op == 2)
                {
                    busq = "datos_personales.rut";
                }
                if(op == 3)
                {
                    busq = "datos_personales.ap_paterno";
                }
                sql = "select datos_personales.id_barra,datos_personales.rut,datos_personales.nombres,datos_personales.ap_paterno,"
                    + "datos_personales.ap_materno,datos_personales.telefono,datos_personales.discapacidad,usuario.rol,automovil.patente from datos_personales"
                    + " JOIN usuario ON datos_personales.usuario_id_usuario=usuario.id_usuario JOIN automovil"
                    + " on datos_personales.automovil_id_automovil=automovil.id_automovil where "+ busq +" like '%"+ b +"%'";
            }
            cn = cbd.conexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
        }
        catch (SQLException ex)
        {
            
        }
        return rs;
    }
//    public void llenadoSQL()
//    {
//        int i;
//        String sql = "insert into plazas values";
//        ConexionBD cbd = new ConexionBD();
//        try
//        {
//            cn = cbd.conexion();
//            st = cn.createStatement();
//            for(int j = 1; j <= 197; j++)
//            {
//                if(j == 197)
//                {
//                    sql += "("+ j +",0,'0')";
//                }
//                else
//                {
//                    sql += "("+ j +",0,'0'),";
//                }
//            }
//            i = st.executeUpdate(sql);
//            System.out.println(i);
//        }
//        catch (SQLException ex)
//        {
//            System.out.println(ex.getMessage());
//        }
//    }
}
