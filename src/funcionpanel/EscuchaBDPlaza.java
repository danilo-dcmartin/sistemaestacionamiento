/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionpanel;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBD;
import vista.DiagramaEstaciona;

/**
 *
 * @author Danilo Martinez
 */
public class EscuchaBDPlaza implements Runnable
{
    private Connection cn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private DiagramaEstaciona diag = null;
    
    public EscuchaBDPlaza(DiagramaEstaciona d)
    {
        this.diag = d;
    }
    
    @Override
    public void run()
    {
        ConexionBD cbd = new ConexionBD();
        try
        {
            cn = cbd.conexion();
            st = cn.createStatement();
            while(true)
            {
                rs = st.executeQuery("select * from plazas");
                while(rs.next())
                {
                    switch(rs.getInt(1))
                    {
                        case 1:
                            if(rs.getInt(2) == 1)
                            {
                                if(rs.getString(3).equals("0")){diag.btn1.setBackground(new Color(255,0,0));}else{diag.btn1.setBackground(new Color(0,204,0));}
                            }
                            else
                            {
                                if(rs.getString(3).equals("0")){diag.btn1.setBackground(new Color(240,240,240));}else{diag.btn1.setBackground(new Color(255,204,0));}
                            }
                            break;
                        case 2:
                            if(rs.getInt(2) == 1)
                            {
                                    if(rs.getString(3).equals("0")){diag.btn2.setBackground(new Color(255,0,0));}else{diag.btn2.setBackground(new Color(0,204,0));}
                            }
                            else
                            {
                                    if(rs.getString(3).equals("0")){diag.btn2.setBackground(new Color(240,240,240));}else{diag.btn2.setBackground(new Color(255,204,0));}
                            }
                            break;
                        case 3:
                            if(rs.getInt(2) == 1)
                            {
                                    if(rs.getString(3).equals("0")){diag.btn3.setBackground(new Color(255,0,0));}else{diag.btn3.setBackground(new Color(0,204,0));}
                            }
                            else
                            {
                                    if(rs.getString(3).equals("0")){diag.btn3.setBackground(new Color(240,240,240));}else{diag.btn3.setBackground(new Color(255,204,0));}
                            }
                            break;
                        case 4:
                            if(rs.getInt(2) == 1)
                            {
                                    if(rs.getString(3).equals("0")){diag.btn4.setBackground(new Color(255,0,0));}else{diag.btn4.setBackground(new Color(0,204,0));}
                            }
                            else
                            {
                                    if(rs.getString(3).equals("0")){diag.btn4.setBackground(new Color(240,240,240));}else{diag.btn4.setBackground(new Color(255,204,0));}
                            }
                            break;
                        case 5:
                            if(rs.getInt(2) == 1)
                            {
                                    if(rs.getString(3).equals("0")){diag.btn5.setBackground(new Color(255,0,0));}else{diag.btn5.setBackground(new Color(0,204,0));}
                            }
                            else
                            {
                                    if(rs.getString(3).equals("0")){diag.btn5.setBackground(new Color(240,240,240));}else{diag.btn5.setBackground(new Color(255,204,0));}
                            }
                            break;
                        case 6:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn6.setBackground(new Color(255,0,0));}else{diag.btn6.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn6.setBackground(new Color(240,240,240));}else{diag.btn6.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 7:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn7.setBackground(new Color(255,0,0));}else{diag.btn7.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn7.setBackground(new Color(240,240,240));}else{diag.btn7.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 8:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn8.setBackground(new Color(255,0,0));}else{diag.btn8.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn8.setBackground(new Color(240,240,240));}else{diag.btn8.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 9:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn9.setBackground(new Color(255,0,0));}else{diag.btn9.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn9.setBackground(new Color(240,240,240));}else{diag.btn9.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 10:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn10.setBackground(new Color(255,0,0));}else{diag.btn10.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn10.setBackground(new Color(240,240,240));}else{diag.btn10.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 11:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn11.setBackground(new Color(255,0,0));}else{diag.btn11.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn11.setBackground(new Color(240,240,240));}else{diag.btn11.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 12:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn12.setBackground(new Color(255,0,0));}else{diag.btn12.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn12.setBackground(new Color(240,240,240));}else{diag.btn12.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 13:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn13.setBackground(new Color(255,0,0));}else{diag.btn13.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn13.setBackground(new Color(240,240,240));}else{diag.btn13.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 14:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn14.setBackground(new Color(255,0,0));}else{diag.btn14.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn14.setBackground(new Color(240,240,240));}else{diag.btn14.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 15:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn15.setBackground(new Color(255,0,0));}else{diag.btn15.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn15.setBackground(new Color(240,240,240));}else{diag.btn15.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 16:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn16.setBackground(new Color(255,0,0));}else{diag.btn16.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn16.setBackground(new Color(240,240,240));}else{diag.btn16.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 17:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn17.setBackground(new Color(255,0,0));}else{diag.btn17.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn17.setBackground(new Color(240,240,240));}else{diag.btn17.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 18:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn18.setBackground(new Color(255,0,0));}else{diag.btn18.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn18.setBackground(new Color(240,240,240));}else{diag.btn18.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 19:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn19.setBackground(new Color(255,0,0));}else{diag.btn19.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn19.setBackground(new Color(240,240,240));}else{diag.btn19.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 20:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn20.setBackground(new Color(255,0,0));}else{diag.btn20.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn20.setBackground(new Color(240,240,240));}else{diag.btn20.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 21:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn21.setBackground(new Color(255,0,0));}else{diag.btn21.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn21.setBackground(new Color(240,240,240));}else{diag.btn21.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 22:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn22.setBackground(new Color(255,0,0));}else{diag.btn22.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn22.setBackground(new Color(240,240,240));}else{diag.btn22.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 23:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn23.setBackground(new Color(255,0,0));}else{diag.btn23.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn23.setBackground(new Color(240,240,240));}else{diag.btn23.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 24:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn24.setBackground(new Color(255,0,0));}else{diag.btn24.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn24.setBackground(new Color(240,240,240));}else{diag.btn24.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 25:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn25.setBackground(new Color(255,0,0));}else{diag.btn25.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn25.setBackground(new Color(240,240,240));}else{diag.btn25.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 26:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn26.setBackground(new Color(255,0,0));}else{diag.btn26.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn26.setBackground(new Color(240,240,240));}else{diag.btn26.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 27:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn27.setBackground(new Color(255,0,0));}else{diag.btn27.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn27.setBackground(new Color(240,240,240));}else{diag.btn27.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 28:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn28.setBackground(new Color(255,0,0));}else{diag.btn28.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn28.setBackground(new Color(240,240,240));}else{diag.btn28.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 29:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn29.setBackground(new Color(255,0,0));}else{diag.btn29.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn29.setBackground(new Color(240,240,240));}else{diag.btn29.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 30:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn30.setBackground(new Color(255,0,0));}else{diag.btn30.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn30.setBackground(new Color(240,240,240));}else{diag.btn30.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 31:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn31.setBackground(new Color(255,0,0));}else{diag.btn31.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn31.setBackground(new Color(240,240,240));}else{diag.btn31.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 32:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn32.setBackground(new Color(255,0,0));}else{diag.btn32.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn32.setBackground(new Color(240,240,240));}else{diag.btn32.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 33:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn33.setBackground(new Color(255,0,0));}else{diag.btn33.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn33.setBackground(new Color(240,240,240));}else{diag.btn33.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 34:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn34.setBackground(new Color(255,0,0));}else{diag.btn34.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn34.setBackground(new Color(240,240,240));}else{diag.btn34.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 35:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn35.setBackground(new Color(255,0,0));}else{diag.btn35.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn35.setBackground(new Color(240,240,240));}else{diag.btn35.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 36:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn36.setBackground(new Color(255,0,0));}else{diag.btn36.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn36.setBackground(new Color(240,240,240));}else{diag.btn36.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 37:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn37.setBackground(new Color(255,0,0));}else{diag.btn37.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn37.setBackground(new Color(240,240,240));}else{diag.btn37.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 38:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn38.setBackground(new Color(255,0,0));}else{diag.btn38.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn38.setBackground(new Color(240,240,240));}else{diag.btn38.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 39:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn39.setBackground(new Color(255,0,0));}else{diag.btn39.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn39.setBackground(new Color(240,240,240));}else{diag.btn39.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 40:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn40.setBackground(new Color(255,0,0));}else{diag.btn40.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn40.setBackground(new Color(240,240,240));}else{diag.btn40.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 41:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn41.setBackground(new Color(255,0,0));}else{diag.btn41.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn41.setBackground(new Color(240,240,240));}else{diag.btn41.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 42:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn42.setBackground(new Color(255,0,0));}else{diag.btn42.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn42.setBackground(new Color(240,240,240));}else{diag.btn42.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 43:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn43.setBackground(new Color(255,0,0));}else{diag.btn43.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn43.setBackground(new Color(240,240,240));}else{diag.btn43.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 44:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn44.setBackground(new Color(255,0,0));}else{diag.btn44.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn44.setBackground(new Color(240,240,240));}else{diag.btn44.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 45:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn45.setBackground(new Color(255,0,0));}else{diag.btn45.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn45.setBackground(new Color(240,240,240));}else{diag.btn45.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 46:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn46.setBackground(new Color(255,0,0));}else{diag.btn46.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn46.setBackground(new Color(240,240,240));}else{diag.btn46.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 47:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn47.setBackground(new Color(255,0,0));}else{diag.btn47.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn47.setBackground(new Color(240,240,240));}else{diag.btn47.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 48:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn48.setBackground(new Color(255,0,0));}else{diag.btn48.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn48.setBackground(new Color(240,240,240));}else{diag.btn48.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 49:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn49.setBackground(new Color(255,0,0));}else{diag.btn49.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn49.setBackground(new Color(240,240,240));}else{diag.btn49.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 50:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn50.setBackground(new Color(255,0,0));}else{diag.btn50.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn50.setBackground(new Color(240,240,240));}else{diag.btn50.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 51:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn51.setBackground(new Color(255,0,0));}else{diag.btn51.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn51.setBackground(new Color(240,240,240));}else{diag.btn51.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 52:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn52.setBackground(new Color(255,0,0));}else{diag.btn52.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn52.setBackground(new Color(240,240,240));}else{diag.btn52.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 53:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn53.setBackground(new Color(255,0,0));}else{diag.btn53.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn53.setBackground(new Color(240,240,240));}else{diag.btn53.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 54:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn54.setBackground(new Color(255,0,0));}else{diag.btn54.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn54.setBackground(new Color(240,240,240));}else{diag.btn54.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 55:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn55.setBackground(new Color(255,0,0));}else{diag.btn55.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn55.setBackground(new Color(240,240,240));}else{diag.btn55.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 56:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn56.setBackground(new Color(255,0,0));}else{diag.btn56.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn56.setBackground(new Color(240,240,240));}else{diag.btn56.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 57:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn57.setBackground(new Color(255,0,0));}else{diag.btn57.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn57.setBackground(new Color(240,240,240));}else{diag.btn57.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 58:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn58.setBackground(new Color(255,0,0));}else{diag.btn58.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn58.setBackground(new Color(240,240,240));}else{diag.btn58.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 59:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn59.setBackground(new Color(255,0,0));}else{diag.btn59.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn59.setBackground(new Color(240,240,240));}else{diag.btn59.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 60:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn60.setBackground(new Color(255,0,0));}else{diag.btn60.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn60.setBackground(new Color(240,240,240));}else{diag.btn60.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 61:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn61.setBackground(new Color(255,0,0));}else{diag.btn61.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn61.setBackground(new Color(240,240,240));}else{diag.btn61.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 62:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn62.setBackground(new Color(255,0,0));}else{diag.btn62.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn62.setBackground(new Color(240,240,240));}else{diag.btn62.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 63:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn63.setBackground(new Color(255,0,0));}else{diag.btn63.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn63.setBackground(new Color(240,240,240));}else{diag.btn63.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 64:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn64.setBackground(new Color(255,0,0));}else{diag.btn64.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn64.setBackground(new Color(240,240,240));}else{diag.btn64.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 65:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn65.setBackground(new Color(255,0,0));}else{diag.btn65.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn65.setBackground(new Color(240,240,240));}else{diag.btn65.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 66:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn66.setBackground(new Color(255,0,0));}else{diag.btn66.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn66.setBackground(new Color(240,240,240));}else{diag.btn66.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 67:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn67.setBackground(new Color(255,0,0));}else{diag.btn67.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn67.setBackground(new Color(240,240,240));}else{diag.btn67.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 68:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn68.setBackground(new Color(255,0,0));}else{diag.btn68.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn68.setBackground(new Color(240,240,240));}else{diag.btn68.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 69:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn69.setBackground(new Color(255,0,0));}else{diag.btn69.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn69.setBackground(new Color(240,240,240));}else{diag.btn69.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 70:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn70.setBackground(new Color(255,0,0));}else{diag.btn70.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn70.setBackground(new Color(240,240,240));}else{diag.btn70.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 71:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn71.setBackground(new Color(255,0,0));}else{diag.btn71.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn71.setBackground(new Color(240,240,240));}else{diag.btn71.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 72:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn72.setBackground(new Color(255,0,0));}else{diag.btn72.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn72.setBackground(new Color(240,240,240));}else{diag.btn72.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 73:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn73.setBackground(new Color(255,0,0));}else{diag.btn73.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn73.setBackground(new Color(240,240,240));}else{diag.btn73.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 74:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn74.setBackground(new Color(255,0,0));}else{diag.btn74.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn74.setBackground(new Color(240,240,240));}else{diag.btn74.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 75:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn75.setBackground(new Color(255,0,0));}else{diag.btn75.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn75.setBackground(new Color(240,240,240));}else{diag.btn75.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 76:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn76.setBackground(new Color(255,0,0));}else{diag.btn76.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn76.setBackground(new Color(240,240,240));}else{diag.btn76.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 77:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn77.setBackground(new Color(255,0,0));}else{diag.btn77.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn77.setBackground(new Color(240,240,240));}else{diag.btn77.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 78:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn78.setBackground(new Color(255,0,0));}else{diag.btn78.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn78.setBackground(new Color(240,240,240));}else{diag.btn78.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 79:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn79.setBackground(new Color(255,0,0));}else{diag.btn79.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn79.setBackground(new Color(240,240,240));}else{diag.btn79.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 80:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn80.setBackground(new Color(255,0,0));}else{diag.btn80.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn80.setBackground(new Color(240,240,240));}else{diag.btn80.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 81:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn81.setBackground(new Color(255,0,0));}else{diag.btn81.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn81.setBackground(new Color(240,240,240));}else{diag.btn81.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 82:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn82.setBackground(new Color(255,0,0));}else{diag.btn82.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn82.setBackground(new Color(240,240,240));}else{diag.btn82.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 83:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn83.setBackground(new Color(255,0,0));}else{diag.btn83.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn83.setBackground(new Color(240,240,240));}else{diag.btn83.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 84:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn84.setBackground(new Color(255,0,0));}else{diag.btn84.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn84.setBackground(new Color(240,240,240));}else{diag.btn84.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 85:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn85.setBackground(new Color(255,0,0));}else{diag.btn85.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn85.setBackground(new Color(240,240,240));}else{diag.btn85.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 86:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn86.setBackground(new Color(255,0,0));}else{diag.btn86.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn86.setBackground(new Color(240,240,240));}else{diag.btn86.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 87:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn87.setBackground(new Color(255,0,0));}else{diag.btn87.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn87.setBackground(new Color(240,240,240));}else{diag.btn87.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 88:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn88.setBackground(new Color(255,0,0));}else{diag.btn88.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn88.setBackground(new Color(240,240,240));}else{diag.btn88.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 89:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn89.setBackground(new Color(255,0,0));}else{diag.btn89.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn89.setBackground(new Color(240,240,240));}else{diag.btn89.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 90:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn90.setBackground(new Color(255,0,0));}else{diag.btn90.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn90.setBackground(new Color(240,240,240));}else{diag.btn90.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 91:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn91.setBackground(new Color(255,0,0));}else{diag.btn91.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn91.setBackground(new Color(240,240,240));}else{diag.btn91.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 92:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn92.setBackground(new Color(255,0,0));}else{diag.btn92.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn92.setBackground(new Color(240,240,240));}else{diag.btn92.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 93:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn93.setBackground(new Color(255,0,0));}else{diag.btn93.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn93.setBackground(new Color(240,240,240));}else{diag.btn93.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 94:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn94.setBackground(new Color(255,0,0));}else{diag.btn94.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn94.setBackground(new Color(240,240,240));}else{diag.btn94.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 95:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn95.setBackground(new Color(255,0,0));}else{diag.btn95.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn95.setBackground(new Color(240,240,240));}else{diag.btn95.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 96:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn96.setBackground(new Color(255,0,0));}else{diag.btn96.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn96.setBackground(new Color(240,240,240));}else{diag.btn96.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 97:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn97.setBackground(new Color(255,0,0));}else{diag.btn97.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn97.setBackground(new Color(240,240,240));}else{diag.btn97.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 98:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn98.setBackground(new Color(255,0,0));}else{diag.btn98.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn98.setBackground(new Color(240,240,240));}else{diag.btn98.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 99:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn99.setBackground(new Color(255,0,0));}else{diag.btn99.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn99.setBackground(new Color(240,240,240));}else{diag.btn99.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 100:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn100.setBackground(new Color(255,0,0));}else{diag.btn100.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn100.setBackground(new Color(240,240,240));}else{diag.btn100.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 101:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn101.setBackground(new Color(255,0,0));}else{diag.btn101.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn101.setBackground(new Color(240,240,240));}else{diag.btn101.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 102:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn102.setBackground(new Color(255,0,0));}else{diag.btn102.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn102.setBackground(new Color(240,240,240));}else{diag.btn102.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 103:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn103.setBackground(new Color(255,0,0));}else{diag.btn103.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn103.setBackground(new Color(240,240,240));}else{diag.btn103.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 104:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn104.setBackground(new Color(255,0,0));}else{diag.btn104.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn104.setBackground(new Color(240,240,240));}else{diag.btn104.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 105:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn105.setBackground(new Color(255,0,0));}else{diag.btn105.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn105.setBackground(new Color(240,240,240));}else{diag.btn105.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 106:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn106.setBackground(new Color(255,0,0));}else{diag.btn106.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn106.setBackground(new Color(240,240,240));}else{diag.btn106.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 107:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn107.setBackground(new Color(255,0,0));}else{diag.btn107.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn107.setBackground(new Color(240,240,240));}else{diag.btn107.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 108:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn108.setBackground(new Color(255,0,0));}else{diag.btn108.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn108.setBackground(new Color(240,240,240));}else{diag.btn108.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 109:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn109.setBackground(new Color(255,0,0));}else{diag.btn109.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn109.setBackground(new Color(240,240,240));}else{diag.btn109.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 110:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn110.setBackground(new Color(255,0,0));}else{diag.btn110.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn110.setBackground(new Color(240,240,240));}else{diag.btn110.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 111:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn111.setBackground(new Color(255,0,0));}else{diag.btn111.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn111.setBackground(new Color(240,240,240));}else{diag.btn111.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 112:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn112.setBackground(new Color(255,0,0));}else{diag.btn112.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn112.setBackground(new Color(240,240,240));}else{diag.btn112.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 113:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn113.setBackground(new Color(255,0,0));}else{diag.btn113.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn113.setBackground(new Color(240,240,240));}else{diag.btn113.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 114:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn114.setBackground(new Color(255,0,0));}else{diag.btn114.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn114.setBackground(new Color(240,240,240));}else{diag.btn114.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 115:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn115.setBackground(new Color(255,0,0));}else{diag.btn115.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn115.setBackground(new Color(240,240,240));}else{diag.btn115.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 116:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn116.setBackground(new Color(255,0,0));}else{diag.btn116.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn116.setBackground(new Color(240,240,240));}else{diag.btn116.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 117:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn117.setBackground(new Color(255,0,0));}else{diag.btn117.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn117.setBackground(new Color(240,240,240));}else{diag.btn117.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 118:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn118.setBackground(new Color(255,0,0));}else{diag.btn118.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn118.setBackground(new Color(240,240,240));}else{diag.btn118.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 119:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn119.setBackground(new Color(255,0,0));}else{diag.btn119.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn119.setBackground(new Color(240,240,240));}else{diag.btn119.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 120:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn120.setBackground(new Color(255,0,0));}else{diag.btn120.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn120.setBackground(new Color(240,240,240));}else{diag.btn120.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 121:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn121.setBackground(new Color(255,0,0));}else{diag.btn121.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn121.setBackground(new Color(240,240,240));}else{diag.btn121.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 122:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn122.setBackground(new Color(255,0,0));}else{diag.btn122.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn122.setBackground(new Color(240,240,240));}else{diag.btn122.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 123:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn123.setBackground(new Color(255,0,0));}else{diag.btn123.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn123.setBackground(new Color(240,240,240));}else{diag.btn123.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 124:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn124.setBackground(new Color(255,0,0));}else{diag.btn124.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn124.setBackground(new Color(240,240,240));}else{diag.btn124.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 125:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn125.setBackground(new Color(255,0,0));}else{diag.btn125.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn125.setBackground(new Color(240,240,240));}else{diag.btn125.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 126:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn126.setBackground(new Color(255,0,0));}else{diag.btn126.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn126.setBackground(new Color(240,240,240));}else{diag.btn126.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 127:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn127.setBackground(new Color(255,0,0));}else{diag.btn127.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn127.setBackground(new Color(240,240,240));}else{diag.btn127.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 128:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn128.setBackground(new Color(255,0,0));}else{diag.btn128.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn128.setBackground(new Color(240,240,240));}else{diag.btn128.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 129:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn129.setBackground(new Color(255,0,0));}else{diag.btn129.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn129.setBackground(new Color(240,240,240));}else{diag.btn129.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 130:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn130.setBackground(new Color(255,0,0));}else{diag.btn130.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn130.setBackground(new Color(240,240,240));}else{diag.btn130.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 131:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn131.setBackground(new Color(255,0,0));}else{diag.btn131.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn131.setBackground(new Color(240,240,240));}else{diag.btn131.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 132:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn132.setBackground(new Color(255,0,0));}else{diag.btn132.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn132.setBackground(new Color(240,240,240));}else{diag.btn132.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 133:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn133.setBackground(new Color(255,0,0));}else{diag.btn133.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn133.setBackground(new Color(240,240,240));}else{diag.btn133.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 134:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn134.setBackground(new Color(255,0,0));}else{diag.btn134.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn134.setBackground(new Color(240,240,240));}else{diag.btn134.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 135:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn135.setBackground(new Color(255,0,0));}else{diag.btn135.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn135.setBackground(new Color(240,240,240));}else{diag.btn135.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 136:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn136.setBackground(new Color(255,0,0));}else{diag.btn136.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn136.setBackground(new Color(240,240,240));}else{diag.btn136.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 137:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn137.setBackground(new Color(255,0,0));}else{diag.btn137.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn137.setBackground(new Color(240,240,240));}else{diag.btn137.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 138:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn138.setBackground(new Color(255,0,0));}else{diag.btn138.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn138.setBackground(new Color(240,240,240));}else{diag.btn138.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 139:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn139.setBackground(new Color(255,0,0));}else{diag.btn139.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn139.setBackground(new Color(240,240,240));}else{diag.btn139.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 140:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn140.setBackground(new Color(255,0,0));}else{diag.btn140.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn140.setBackground(new Color(240,240,240));}else{diag.btn140.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 141:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn141.setBackground(new Color(255,0,0));}else{diag.btn141.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn141.setBackground(new Color(240,240,240));}else{diag.btn141.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 142:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn142.setBackground(new Color(255,0,0));}else{diag.btn142.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn142.setBackground(new Color(240,240,240));}else{diag.btn142.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 143:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn143.setBackground(new Color(255,0,0));}else{diag.btn143.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn143.setBackground(new Color(240,240,240));}else{diag.btn143.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 144:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn144.setBackground(new Color(255,0,0));}else{diag.btn144.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn144.setBackground(new Color(240,240,240));}else{diag.btn144.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 145:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn145.setBackground(new Color(255,0,0));}else{diag.btn145.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn145.setBackground(new Color(240,240,240));}else{diag.btn145.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 146:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn146.setBackground(new Color(255,0,0));}else{diag.btn146.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn146.setBackground(new Color(240,240,240));}else{diag.btn146.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 147:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn147.setBackground(new Color(255,0,0));}else{diag.btn147.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn147.setBackground(new Color(240,240,240));}else{diag.btn147.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 148:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn148.setBackground(new Color(255,0,0));}else{diag.btn148.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn148.setBackground(new Color(240,240,240));}else{diag.btn148.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 149:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn149.setBackground(new Color(255,0,0));}else{diag.btn149.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn149.setBackground(new Color(240,240,240));}else{diag.btn149.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 150:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn150.setBackground(new Color(255,0,0));}else{diag.btn150.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn150.setBackground(new Color(240,240,240));}else{diag.btn150.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 151:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn151.setBackground(new Color(255,0,0));}else{diag.btn151.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn151.setBackground(new Color(240,240,240));}else{diag.btn151.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 152:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn152.setBackground(new Color(255,0,0));}else{diag.btn152.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn152.setBackground(new Color(240,240,240));}else{diag.btn152.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 153:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn153.setBackground(new Color(255,0,0));}else{diag.btn153.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn153.setBackground(new Color(240,240,240));}else{diag.btn153.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 154:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn154.setBackground(new Color(255,0,0));}else{diag.btn154.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn154.setBackground(new Color(240,240,240));}else{diag.btn154.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 155:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn155.setBackground(new Color(255,0,0));}else{diag.btn155.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn155.setBackground(new Color(240,240,240));}else{diag.btn155.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 156:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn156.setBackground(new Color(255,0,0));}else{diag.btn156.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn156.setBackground(new Color(240,240,240));}else{diag.btn156.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 157:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn157.setBackground(new Color(255,0,0));}else{diag.btn157.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn157.setBackground(new Color(240,240,240));}else{diag.btn157.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 158:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn158.setBackground(new Color(255,0,0));}else{diag.btn158.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn158.setBackground(new Color(240,240,240));}else{diag.btn158.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 159:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn159.setBackground(new Color(255,0,0));}else{diag.btn159.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn159.setBackground(new Color(240,240,240));}else{diag.btn159.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 160:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn160.setBackground(new Color(255,0,0));}else{diag.btn160.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn160.setBackground(new Color(240,240,240));}else{diag.btn160.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 161:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn161.setBackground(new Color(255,0,0));}else{diag.btn161.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn161.setBackground(new Color(240,240,240));}else{diag.btn161.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 162:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn162.setBackground(new Color(255,0,0));}else{diag.btn162.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn162.setBackground(new Color(240,240,240));}else{diag.btn162.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 163:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn163.setBackground(new Color(255,0,0));}else{diag.btn163.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn163.setBackground(new Color(240,240,240));}else{diag.btn163.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 164:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn164.setBackground(new Color(255,0,0));}else{diag.btn164.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn164.setBackground(new Color(240,240,240));}else{diag.btn164.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 165:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn165.setBackground(new Color(255,0,0));}else{diag.btn165.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn165.setBackground(new Color(240,240,240));}else{diag.btn165.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 166:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn166.setBackground(new Color(255,0,0));}else{diag.btn166.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn166.setBackground(new Color(240,240,240));}else{diag.btn166.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 167:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn167.setBackground(new Color(255,0,0));}else{diag.btn167.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn167.setBackground(new Color(240,240,240));}else{diag.btn167.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 168:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn168.setBackground(new Color(255,0,0));}else{diag.btn168.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn168.setBackground(new Color(240,240,240));}else{diag.btn168.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 169:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn169.setBackground(new Color(255,0,0));}else{diag.btn169.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn169.setBackground(new Color(240,240,240));}else{diag.btn169.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 170:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn170.setBackground(new Color(255,0,0));}else{diag.btn170.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn170.setBackground(new Color(240,240,240));}else{diag.btn170.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 171:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn171.setBackground(new Color(255,0,0));}else{diag.btn171.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn171.setBackground(new Color(240,240,240));}else{diag.btn171.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 172:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn172.setBackground(new Color(255,0,0));}else{diag.btn172.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn172.setBackground(new Color(240,240,240));}else{diag.btn172.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 173:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn173.setBackground(new Color(255,0,0));}else{diag.btn173.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn173.setBackground(new Color(240,240,240));}else{diag.btn173.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 174:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn174.setBackground(new Color(255,0,0));}else{diag.btn174.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn174.setBackground(new Color(240,240,240));}else{diag.btn174.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 175:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn175.setBackground(new Color(255,0,0));}else{diag.btn175.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn175.setBackground(new Color(240,240,240));}else{diag.btn175.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 176:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn176.setBackground(new Color(255,0,0));}else{diag.btn176.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn176.setBackground(new Color(240,240,240));}else{diag.btn176.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 177:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn177.setBackground(new Color(255,0,0));}else{diag.btn177.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn177.setBackground(new Color(240,240,240));}else{diag.btn177.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 178:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn178.setBackground(new Color(255,0,0));}else{diag.btn178.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn178.setBackground(new Color(240,240,240));}else{diag.btn178.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 179:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn179.setBackground(new Color(255,0,0));}else{diag.btn179.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn179.setBackground(new Color(240,240,240));}else{diag.btn179.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 180:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn180.setBackground(new Color(255,0,0));}else{diag.btn180.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn180.setBackground(new Color(240,240,240));}else{diag.btn180.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 181:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn181.setBackground(new Color(255,0,0));}else{diag.btn181.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn181.setBackground(new Color(240,240,240));}else{diag.btn181.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 182:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn182.setBackground(new Color(255,0,0));}else{diag.btn182.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn182.setBackground(new Color(240,240,240));}else{diag.btn182.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 183:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn183.setBackground(new Color(255,0,0));}else{diag.btn183.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn183.setBackground(new Color(240,240,240));}else{diag.btn183.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 184:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn184.setBackground(new Color(255,0,0));}else{diag.btn184.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn184.setBackground(new Color(240,240,240));}else{diag.btn184.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 185:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn185.setBackground(new Color(255,0,0));}else{diag.btn185.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn185.setBackground(new Color(240,240,240));}else{diag.btn185.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 186:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn186.setBackground(new Color(255,0,0));}else{diag.btn186.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn186.setBackground(new Color(240,240,240));}else{diag.btn186.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 187:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn187.setBackground(new Color(255,0,0));}else{diag.btn187.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn187.setBackground(new Color(240,240,240));}else{diag.btn187.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 188:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn188.setBackground(new Color(255,0,0));}else{diag.btn188.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn188.setBackground(new Color(240,240,240));}else{diag.btn188.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 189:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn189.setBackground(new Color(255,0,0));}else{diag.btn189.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn189.setBackground(new Color(240,240,240));}else{diag.btn189.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 190:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn190.setBackground(new Color(255,0,0));}else{diag.btn190.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn190.setBackground(new Color(240,240,240));}else{diag.btn190.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 191:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn191.setBackground(new Color(255,0,0));}else{diag.btn191.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn191.setBackground(new Color(240,240,240));}else{diag.btn191.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 192:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn192.setBackground(new Color(255,0,0));}else{diag.btn192.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn192.setBackground(new Color(240,240,240));}else{diag.btn192.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 193:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn193.setBackground(new Color(255,0,0));}else{diag.btn193.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn193.setBackground(new Color(240,240,240));}else{diag.btn193.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 194:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn194.setBackground(new Color(255,0,0));}else{diag.btn194.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn194.setBackground(new Color(240,240,240));}else{diag.btn194.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 195:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn195.setBackground(new Color(255,0,0));}else{diag.btn195.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn195.setBackground(new Color(240,240,240));}else{diag.btn195.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 196:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn196.setBackground(new Color(255,0,0));}else{diag.btn196.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn196.setBackground(new Color(240,240,240));}else{diag.btn196.setBackground(new Color(255,204,0));}
                                }
                                break;
                        case 197:
                                if(rs.getInt(2) == 1)
                                {
                                        if(rs.getString(3).equals("0")){diag.btn197.setBackground(new Color(255,0,0));}else{diag.btn197.setBackground(new Color(0,204,0));}
                                }
                                else
                                {
                                        if(rs.getString(3).equals("0")){diag.btn197.setBackground(new Color(240,240,240));}else{diag.btn197.setBackground(new Color(255,204,0));}
                                }
                                break;
                    }
                }
                Thread.sleep(2000);
            }
        }
        catch (SQLException ex)
        {
            
        }
        catch (InterruptedException ex)
        {
            
        }
    }
}
