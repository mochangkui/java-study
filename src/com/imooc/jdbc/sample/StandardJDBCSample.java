package com.imooc.jdbc.sample;

import java.sql.*;

/**
 * 标准JDBC操作五步骤
 */
public class StandardJDBCSample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // 1、加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、创建数据库连接
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root",
                "root"
            );
            // 3、创建Statement对象
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee");
            // 4、遍历查询结果
            while (rs.next()) {
                Integer eno = rs.getInt(1);
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && conn.isClosed() == false) {
                    // 5、关闭连接，释放资源
                    conn.close();
                }
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    }
}
