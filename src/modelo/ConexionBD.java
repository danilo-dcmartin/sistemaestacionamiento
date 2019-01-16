/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Danilo Martinez
 */
public class ConexionBD
{
    public Connection conexion()
    {
        Connection cn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb-sea","root", "");
            //cn = DriverManager.getConnection("jdbc:mysql://10.26.27.114:3306/mydb-sea","smartinacap", "12345");
        }
        catch (ClassNotFoundException ex)
        {
            
        }
        catch (SQLException ex)
        {

        }
        return cn;  
    }
}
