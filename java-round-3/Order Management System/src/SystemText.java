import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SystemText {
    public static void main(String[] args) {
        boolean flag =true;
        String orderId,goodId,goodName,orderName,orderTime;
        int goodPrice,goodNum,orderPrice;
        //连接数据库
        Jdbc.getConnection();
        while(flag){
            System.out.println("************欢迎来到订单管理系统**************");
            System.out.println("     1）添加订单            2）删除订单");
            System.out.println("     3) 查询订单            4) 修改订单");
            System.out.println("     5) 退出系统 " );
            Scanner sc = new Scanner(System.in);
            int a =sc.nextInt();
            switch(a){
                //添加订单操作
                case 1:
                    String sql1 = "insert into order1(订单编号,订单金额,下单时间,下单人姓名) values (?,?,?,?);";
                    String sql2 ="insert into good(订单编号,商品编号,商品价格,商品数量,商品名称,商品总价) values (?,?,?,?,?,?);";
                    System.out.println("请输入订单的编号:");
                    orderId = sc.next();
                    System.out.println("请输入订单的总金额:");
                    orderPrice = sc.nextInt();
                    System.out.println("请输入下单人的姓名:");
                    orderName = sc.next();
                    System.out.println("请输入下单的时间:");
                    orderTime = sc.next();
                    try {
                        int n=Jdbc.update(sql1,new Object[]{orderId,orderPrice,orderTime,orderName});
                        if(n>0)
                            System.out.println("订单基础信息添加成功");
                        else
                            System.out.println("订单基础信息添加失败");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("请输入商品信息:");
                    System.out.println("请输入订单商品的种类");
                    int num = sc.nextInt();
                    for(int i=1;i<=num;i++){
                        System.out.println("请输入商品的编号:");
                        goodId = sc.next();
                        System.out.println("请输入商品名:");
                        goodName = sc.next();
                        System.out.println("请输入商品的价格");
                        goodPrice = sc.nextInt();
                        System.out.println("请输入商品的数量");
                        goodNum = sc.nextInt();
                        try {
                            Jdbc.update(sql2,new Object[]{orderId,goodId,goodPrice,goodNum,goodName,goodNum*goodPrice});
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        if(i==num)
                            System.out.println("添加成功");
                    }
                    break;
                    //删除订单操作
                case 2:
                    System.out.println("请输入要删除的订单编号:");
                    orderId=sc.next();
                    String sql3 = "delete from order1 where 订单编号 = ?;";
                    try {
                        int n=Jdbc.update(sql3,new Object []{orderId});
                        if(n>0)
                            System.out.println("订单删除成功");
                        else
                            System.out.println("订单删除失败");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                    //查询订单操作
                case 3:
                    String sql4 = "SELECT order1.`订单编号`,order1.`下单人姓名`,order1.`下单时间`,order1.`订单金额`,good.`商品编号`,good.`商品名称`,good.`商品价格`,good.`商品数量`,good.`商品总价`FROM order1 LEFT JOIN good ON(order1.订单编号=good.订单编号)LIMIT 10 OFFSET ?;";
                    System.out.println("请输入查询订单的页数(每页十条):");
                    int b = sc.nextInt();
                    try {
                        ResultSet rs = Jdbc.query(sql4,new Object[]{(b-1)*10});
                        System.out.println("订单编号   下单人姓名   下单时间    订单金额   商品编号  商品名称  商品价格  商品数量  商品总价");
                        while(rs.next()){
                            System.out.print(rs.getObject("订单编号")+"     ");
                            System.out.print(rs.getObject("下单人姓名")+"     ");
                            System.out.print(rs.getObject("下单时间")+"     ");
                            System.out.print(rs.getObject("订单金额")+"     ");
                            System.out.print(rs.getObject("商品编号")+"     ");
                            System.out.print(rs.getObject("商品名称")+"     ");
                            System.out.print(rs.getObject("商品价格")+"     ");
                            System.out.print(rs.getObject("商品数量")+"     ");
                            System.out.print(rs.getObject("商品总价")+"     ");
                            System.out.println();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                    //修改订单操作
                case 4:
                    String sql6="update good set 商品价格 =?,商品数量= ?,商品名称 =?,商品总价 =? where 订单编号 = ? and 商品编号= ?;";
                    System.out.println("请输入要修改的订单编号:");
                    orderId = sc.next();
                    System.out.println("请输入要修改的内容：");
                    System.out.println("1)下单的时间  2）下单人的姓名");
                    System.out.println("3)订单的金额  4）商品的信息");
                    int c = sc.nextInt();
                    //分支判断修改的种类
                    switch(c){
                        case 1:
                            System.out.println("请输入下单的时间:");
                            orderTime = sc.next();
                            String sql7="update order1 set 下单时间 = ?where 订单编号= ?;";
                            try {
                                int n = Jdbc.update(sql7,new Object[]{orderTime,orderId});
                                if(n>0)
                                    System.out.println("修改成功");
                                else
                                    System.out.println("修改失败");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            String sql8="update order1 set 下单人姓名 = ? where 订单编号= ?;";
                            System.out.println("请输入下单人的姓名:");
                            orderName = sc.next();
                            try {
                                int n = Jdbc.update(sql8,new Object[]{orderName,orderId});
                                if(n>0)
                                    System.out.println("修改成功");
                                else
                                    System.out.println("修改失败");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            String sql9="update order1 set 订单金额 = ? where 订单编号= ?;";
                            System.out.println("请输入订单金额:");
                            orderPrice = sc.nextInt();
                            try {
                                int n = Jdbc.update(sql9,new Object[]{orderPrice,orderId});
                                if(n>0)
                                    System.out.println("修改成功");
                                else
                                    System.out.println("修改失败");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            System.out.println("请输入要修改的商品的编号:");
                            goodId = sc.next();
                            System.out.println("请输入商品名:");
                            goodName = sc.next();
                            System.out.println("请输入商品的价格:");
                            goodPrice = sc.nextInt();
                            System.out.println("请输入商品的数量:");
                            goodNum = sc.nextInt();
                            try {
                                int n = Jdbc.update(sql6,new Object[]{goodPrice,goodNum,goodName,goodPrice*goodNum,orderId,goodId});
                                if(n>0)
                                    System.out.println("修改成功");
                                else
                                    System.out.println("修改失败");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
                    //退出订单管理系统
                case 5:
                    flag = false;
                    break;
            }

        }
        System.out.println("订单管理系统已退出,欢迎下次使用!");
    }
}

