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
import java.util.ArrayList;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.User;

/**
 *
 * @author m
 */
public class UserDao {
    public static String validateUser(User user) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select username from Users where userid=? and password=? and usertype=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        System.out.println(user.getUserId());
        ps.setString(1, user.getUserId().trim());
        ps.setString(2, user.getPassword().trim());
        ps.setString(3, user.getUserType().trim());
        System.out.println(user.getUserId()+" "+ user.getPassword()+" "+ user.getUserType());
        ResultSet rs=ps.executeQuery();
        String username=null;
        if(rs.next())
        {
            username=rs.getString(1);
        }
        return username;
    }
    public static ArrayList <User>getUserById(String UserId)throws SQLException
{
 Connection conn=DBConnection.getConnection();
 String qry="Select * from Users where UserID=?";
 PreparedStatement ps=conn.prepareStatement(qry);
 ArrayList <User> UserList=new ArrayList<User> ();
 ps.setString(1,UserId);
 ResultSet rs=ps.executeQuery();
 while(rs.next())
 {
     User u=new User();
       u.setUserId(rs.getString(1));
       u.setUserName(rs.getString(2));
       u.setEmpId(rs.getString(3));    
     UserList.add(u);
 }
 return UserList;
}
public static boolean deleteUser(String UserId) throws SQLException
{
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("delete from Users where USERID=?");
    ps.setString(1,UserId);
    int count=ps.executeUpdate();
    if(count==0)
    return false;
    else 
        return true;
}
 public static boolean setData(User u,String username,String empid)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?,?,?)");
     ps.setString(1,u.getUserId());
     ps.setString(2, username);
     ps.setString(3,u.getPassword());
     ps.setString(4, empid);
     ps.setString(5,u.getUserType());
   int count=  ps.executeUpdate();
   if(count!=0)
       return true;
   else 
       return false;
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
