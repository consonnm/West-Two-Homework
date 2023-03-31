import java.sql.*;

public class Jdbc {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url ="jdbc:mysql://127.0.0.1:3306/Order Manage System?characterEncoding=UTf-8";
    private static final String user = "root";
    private static final String pass ="123456789";
    //封装连接数据库方法
    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName(driver);
            System.out.println("加载驱动成功");
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("连接数据库成功");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("加载驱动失败");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("连接数据库失败");
        }
        return conn;
    }
    //封装查询方法
    public static ResultSet query(String sql, Object[] objects) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, pass);
        PreparedStatement pst =conn.prepareStatement(sql);
        ResultSet re;
        for (int i = 0; i < objects.length; i++)
            pst.setObject(i+1,objects[i]);
        re = pst.executeQuery();
        /*conn.close();
        pst.close();*/
        return re;

    }
    //封装增删改方法
    public static int update(String sql,Object[] objects) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, pass);
        PreparedStatement pst = conn.prepareStatement(sql);
        for (int i = 0; i <objects.length; i++)
            pst.setObject(i + 1, objects[i]);
        int n =pst.executeUpdate();
        pst.close();
        conn.close();
        return n;
    }
}
