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

/**
 *
 * @author m
 */
public class CategoryDao {
    public static HashMap<String,String> getAllCategory()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select cat_name,cat_id from categories");
        HashMap<String,String> categories=new HashMap<>();
        while(rs.next())
        {
            String catName=rs.getString(1);
            String catId=rs.getString(2);
            categories.put(catName, catId);
        }
        return categories;
    }
    public static ArrayList <Category> getAllCatData()throws SQLException
{
    ArrayList <Category> catList=new ArrayList <Category>();
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from Categories");
    while(rs.next())
    {
        Category c=new Category();
        c.setCat_Id(rs.getString(1));
        c.setCat_Name(rs.getString(2));
        catList.add(c);
    }
    return catList;
}
public static boolean updateCat(Category c)throws SQLException
{
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("update Categories set CAT_NAME=? where CAT_ID=?");
    ps.setString(1,c.getCat_Name());
    int x=ps.executeUpdate();
    if(x>0)
    {
        return true;
    }
    else 
        return false;
}
public static HashMap <String,String> getAllCatId() throws SQLException 
{
Connection conn=DBConnection.getConnection();
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("select CAT_ID,CAT_NAME from Categories");
HashMap <String,String> Categories=new HashMap<>();
while(rs.next())
{
    String catName=rs.getString(2);
    String catId=rs.getString(1);
    Categories.put(catId,catName);
}
return Categories;
}
public static boolean editCategories(Category c)throws SQLException
   {
       Connection conn = DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("update Categories set CAT_NAME=? where CAT_ID=?");
   ps.setString(1,c.getCat_Name());
   ps.setString(2,c.getCat_Id());
   int count=ps.executeUpdate();
   if(count!=0)
       return true;
   else
       return false;
   }
 public static boolean addCategory(Category c) throws SQLException{
        
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into categories values(?,?)");
        ps.setString(1, c.getCat_Id());
        ps.setString(2, c.getCat_Name());
       
        int count = ps.executeUpdate();
        if(count>0)
            return true;
        else 
            return false;
    }
  public static String getNewId() throws SQLException{
        
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        String str = "select count(*) from categories";
        int id = 101;
        ResultSet rs = st.executeQuery(str);
        if(rs.next()){
            id = id + rs.getInt(1);
        }
        return "C" + id;
    }
}
    

