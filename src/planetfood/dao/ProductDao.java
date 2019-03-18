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
import planetfood.pojo.Product;

/**
 *
 * @author m
 */
public class ProductDao {
    public static String getNewId()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        int id=101;
        ResultSet rs=st.executeQuery("Select count(*) from Products");
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
        return "P"+id;
    }
    public static boolean addProduct(Product p)throws SQLException
    {
       Connection conn=DBConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("insert into Products values(?,?,?,?,?)");
       ps.setString(1,p.getProdId());
       ps.setString(2, p.getCatId());
       ps.setString(3, p.getProdName());
       ps.setDouble(4, p.getProdPrice());
       ps.setString(5, p.getIsActive());
       int x=ps.executeUpdate();
       if(x>0)
           return true;
       else
           return false;

    }
    /*public static HashMap<String,String> addCategories( )throws SQL Exception
    {
        HashMap <String,String>hs=new HashMap<>();
        Connection conn= DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from categories");
        while(rs.next())
        {
            hs.put(rs.getString(1),rs.getString(2));
        }
        
    }*/
    public static HashMap<String,Product> getProductsByCategory(String catId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select * from Products where cat_id=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        HashMap<String,Product> productList=new HashMap<>();
        ps.setString(1,catId);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            Product p= new Product();
            p.setCatId(catId);
            p.setProdId(rs.getString("prod_id"));
            p.setProdName(rs.getString("prod_Name"));
            p.setProdPrice(rs.getDouble("prod_price"));
            p.setIsActive("active");
            productList.put(p.getProdId(),p);
            
        }
        return productList;
    }
    public static HashMap<String,Product> getProductsByCategoryActive(String catId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select * from Products where cat_id=? and active='Y'";
        PreparedStatement ps=conn.prepareStatement(qry);
        HashMap<String,Product> productList=new HashMap<>();
        ps.setString(1,catId);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            Product p= new Product();
            p.setCatId(catId);
            p.setProdId(rs.getString("prod_id"));
            p.setProdName(rs.getString("prod_Name"));
            p.setProdPrice(rs.getDouble("prod_price"));
            p.setIsActive("active");
            productList.put(p.getProdId(),p);
            
        }
        return productList;
    }
    public static ArrayList<Product> getAllData()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from products");
        ArrayList <Product> productList=new ArrayList<>();
        
        while(rs.next())
        {
            Product p=new Product();
            p.setCatId(rs.getString("cat_Id"));
            p.setProdId(rs.getString("prod_id"));
            p.setProdName(rs.getString("prod_name"));
            p.setProdPrice(rs.getDouble("prod_price"));
            p.setIsActive(rs.getString("active"));
            productList.add(p);
        }
        return productList;
    }  
    public static boolean updateProduct(Product p)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Update Products set prod_name=?,prod_price=?,active=? where prod_id=?");
        ps.setString(1, p.getProdName());
        System.out.println(p.getProdName());
        ps.setDouble(2, p.getProdPrice());
        ps.setString(3, p.getIsActive());
        ps.setString(4, p.getProdId().trim());
        int x=ps.executeUpdate();
        System.out.println("Dao "+x);
        if(x>0)
            return true;
        else 
            return false;
    }
    public static boolean removeProduct(String catId, String prodId)throws SQLException
    {
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("delete from products where cat_id=? && prod_id=?");
    ps.setString(1,catId);
    ps.setString(2, prodId);
    int count=ps.executeUpdate();
    if(count==0)
        return false;
    else
        return true;
    }
    public static HashMap <String,Product>getProducts(String catId)throws SQLException
{
 Connection conn=DBConnection.getConnection();
 String qry="Select PROD_NAME from products where CAT_ID=?";
 PreparedStatement ps=conn.prepareStatement(qry);
 HashMap <String,Product> productList=new HashMap <String,Product> ();
 ps.setString(1,catId);
 ResultSet rs=ps.executeQuery();
 while(rs.next())
 {
     Product p=new Product();
     p.setProdName(rs.getString("prod_name"));
     productList.put(p.getProdName(),p);
 }
 return productList;
}
public static ArrayList<Product>getAllProdData(String catId)throws SQLException
{
    ArrayList <Product> prodList=new ArrayList <Product>();
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from Products where CATID=?");
    while(rs.next())
    {
        Product p=new Product();
        p.setProdId(rs.getString(1));
        p.setCatId(rs.getString(2));
        p.setProdName(rs.getString(3));
        p.setProdPrice(rs.getDouble(4));
        p.setIsActive(rs.getString(5));
        prodList.add(p);
    }
    return prodList;
}
public static ArrayList <Product>getProdById(String catId)throws SQLException
{
 Connection conn=DBConnection.getConnection();
 String qry="Select * from Products where CAT_ID=?";
 PreparedStatement ps=conn.prepareStatement(qry);
 ArrayList <Product> prodList=new ArrayList<Product> ();
 ps.setString(1,catId);
 ResultSet rs=ps.executeQuery();
 while(rs.next())
 {
     Product p=new Product();
       p.setProdId(rs.getString(1));
       p.setCatId(rs.getString(2));
       p.setProdName(rs.getString(3));
       p.setProdPrice(rs.getDouble(4));
       p.setIsActive(rs.getString(5));
     prodList.add(p);
 }
 return prodList;
}

}
