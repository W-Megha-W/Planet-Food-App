/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author m
 */
public class DBConnection {
    private static Connection conn;
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//HP-NOTEBOOK/XE","myproject","megha");
            JOptionPane.showMessageDialog(null,"Connected Successfully To The Database","Success!",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null,"Error in loading Driver Class"+ex,"Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"DB Error"+ex,"Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
        public static Connection getConnection()
        {
            return conn;
        }
        public static void closeConnection()
        {
           //conn.close(); 
        }
    }
    

