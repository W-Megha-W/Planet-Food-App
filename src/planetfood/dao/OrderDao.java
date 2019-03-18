/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Order;
import planetfood.pojo.OrderDetail;

/**
 *
 * @author m
 */
public class OrderDao {
    public static ArrayList<Order> getOrderByDate(Date startDate,Date endDate)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from orders where ord_Date between ? and ?");
        long ms1=startDate.getTime();
        long ms2=endDate.getTime();
        java.sql.Date d1=new java.sql.Date(ms1);
        java.sql.Date d2=new java.sql.Date(ms2);
        ps.setDate(1, d1);
        ps.setDate(2, d2);
        ArrayList<Order> orderList=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            Order obj=new Order();
            obj.setOrdId(rs.getString("ord_id"));
            java.sql.Date d=rs.getDate("ord_Date");
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
            String dateStart=sdf.format(d);
            obj.setOrdDate(dateStart);
            obj.setGst(rs.getDouble("gst"));
            obj.setGstAmount(rs.getDouble("gst_amount"));
            obj.setDiscount(rs.getDouble("discount"));
            obj.setGrandTotal(rs.getDouble("grand_total"));
            obj.setUserId(rs.getString("userid"));
            obj.setOrdAmount(rs.getDouble("ord_amount"));
            orderList.add(obj);
        }
        return orderList;
    }
     public static String getNewId()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        int id=1;
        ResultSet rs=st.executeQuery("Select count(*) from Orders");
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
        return "ORD -"+id;
    } 
    public static boolean addOrder(Order order,ArrayList<OrderDetail>orderList)throws Exception
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?) ");
        ps.setString(1,order.getOrdId());
        String datestr=order.getOrdDate();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date d1=sdf.parse(datestr);
        long ms=d1.getTime();
        java.sql.Date d2=new java.sql.Date(ms);
        ps.setDate(2, d2);
        ps.setDouble(3, order.getGst());
        ps.setDouble(4, order.getGstAmount());
        ps.setDouble(5, order.getDiscount());
        ps.setDouble(6, order.getGrandTotal());
        ps.setString(7, order.getUserId());
        ps.setDouble(8, order.getOrdAmount());
        int x=ps.executeUpdate();
        PreparedStatement ps2=conn.prepareStatement("insert into order_Details values(?,?,?,?)");
        int count=0,y;
        for(OrderDetail detail:orderList)
        {
            ps2.setString(1, detail.getOrdId());
            ps2.setString(2, detail.getProdId());
            ps2.setDouble(3, detail.getQuantity());
            ps2.setDouble(4, detail.getCost());
            y=ps2.executeUpdate();
            if(y>0)
                count=count+y;
        }
        if(x>0 && count==orderList.size())
            return true;
        else
            return false;
    }
    public static ArrayList<Order>getAllOrderData()throws SQLException
{
    ArrayList <Order> orderList=new ArrayList <Order> ();
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from Orders");
    while(rs.next())
    {
        Order obj=new Order();
            obj.setOrdId(rs.getString("ORD_ID"));
            java.sql.Date d=rs.getDate("ORD_DATE");
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy"); 
            String dateStr=sdf.format(d);
            obj.setOrdDate(dateStr);
            obj.setGst(rs.getDouble("GST"));
            obj.setGstAmount(rs.getDouble("GST_AMOUNT"));
            obj.setDiscount(rs.getDouble("DISCOUNT"));
            obj.setGrandTotal(rs.getDouble("GRAND_TOTAL"));          
            obj.setUserId(rs.getString("USERID"));
            obj.setOrdAmount(rs.getDouble("ORD_AMOUNT"));
            orderList.add(obj); 
      
    }
    return orderList;
}
    public static boolean cancelOrder(String prdId)throws SQLException
    {
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("delete from order_details where prod_id=?");
    ps.setString(1,prdId);
    int count=ps.executeUpdate();
    System.out.println(count);
    System.out.println(prdId);
    if(count==0)
        return false;
    else
        return true;
    }

}
