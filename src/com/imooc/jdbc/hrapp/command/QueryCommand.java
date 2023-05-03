package com.imooc.jdbc.hrapp.command;

import java.sql.*;
import java.util.Scanner;

public class QueryCommand implements Command{
    @Override
    public void execute() {
        System.out.println("请输入部门名称：");
        Scanner in = new Scanner(System.in);
        String pdname = in.next();
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
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
            stmt = conn.createStatement();
            res = stmt.executeQuery("select * from employee where dname='" + pdname + "'");
            // 4、遍历查询结果
            while (res.next()) {
                Integer eno = res.getInt(1);
                String ename = res.getString("ename");
                Float salary = res.getFloat("salary");
                String dname = res.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
            // 5、关闭连接，释放资源
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (res != null) {
                    res.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
