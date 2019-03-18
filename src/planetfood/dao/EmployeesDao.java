/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Category;
import planetfood.pojo.Employees;

/**
 *
 * @author m
 */
public class EmployeesDao {
public static ArrayList <Employees> getAllEmp()throws SQLException
{
    ArrayList <Employees> eList=new ArrayList <Employees>();
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from employees");
    while(rs.next())
    {
        Employees e=new Employees();
        e.setEmpId(rs.getString(1));
        e.setEname(rs.getString(2));
        e.setJob(rs.getString(3));
        e.setSal(rs.getDouble(4));
        eList.add(e);
    }
    return eList;
}   
public static HashMap <String,Employees>getEmpById(String empId)throws SQLException
{
 Connection conn=DBConnection.getConnection();
 String qry="Select * from Employees where EMPID=?";
 PreparedStatement ps=conn.prepareStatement(qry);
 HashMap <String,Employees> empList=new HashMap<String,Employees> ();
 ps.setString(1,empId);
 ResultSet rs=ps.executeQuery();
 while(rs.next())
 {
     Employees p=new Employees();
       p.setEmpId(rs.getString(1));
        p.setEname(rs.getString(2));
        p.setJob(rs.getString(3));
        p.setSal(rs.getDouble(4));
      
    
     empList.put(p.getEmpId(),p);
 }
 return empList;
}
public static String getUserName(String empid)throws SQLException
    {
     Connection conn = DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("select ENAME from EMPLOYEES where EMPID=?");
    ps.setString(1, empid);
   ResultSet rs=ps.executeQuery();
    String ename=null;
    if(rs.next())
    {
    ename= rs.getString(1);
    }
    return ename;
    }
 public static boolean setJob(String empid)throws SQLException
    {
       String job= getJob(empid);
    Connection conn = DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("update Employees set job='cashier' where empid=?");
    ps.setString(1,empid);
    int count=ps.executeUpdate();
    if(count!=0)
        return true;
    else 
        return false;
    }
 public static String getJob(String empid)throws SQLException
 {
 Connection conn = DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("select job from Employees where empid=?");
    ps.setString(1, empid);
    ResultSet rs=ps.executeQuery();
    String job;
    if(rs.next())
    {
     job=rs.getString(1);
     return job;
    }
    else
        return null;
    
   }
 public static boolean deleteEmp(String userid)throws SQLException
 {
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("delete from users where userid=?");
    ps.setString(1,userid);
    int count=ps.executeUpdate();
    if(count==0)
    return false;
    else 
        return true;
 }
  public static boolean removeCashier(String userid)throws SQLException
    {
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("delete from users where userid=?");
    ps.setString(1,userid);
    int count=ps.executeUpdate();
    if(count==0)
        return false;
    else
        return true;
    }

}
    

