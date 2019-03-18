/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Emp;
import planetfood.pojo.Employees;

/**
 *
 * @author m
 */
public class EmpDao {
    public static boolean addEmployees(Employees e) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into Employees values(?,?,?,?)");
        ps.setString(1, e.getEmpId());
        ps.setString(2, e.getEname());
        ps.setObject(3, e.getJob());
        ps.setDouble(4, e.getSal());
        int count=ps.executeUpdate();
        if(count==0)
            return false;
        else
            return true;
    }
      public static boolean updateEmployee(Emp e)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update emp set Ename=?,Sal=? where Eno=?");
        ps.setString(1, e.getEname());
        ps.setDouble(2, e.getSal());
        ps.setString(3, e.getEno());
        int count=ps.executeUpdate();
        if(count==0)
            return false;
        else
            return true;   
    }
    
}
