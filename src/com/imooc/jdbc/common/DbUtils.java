package com.imooc.jdbc.common;

import java.sql.*;

public class DbUtils {
    /**
     * 创建新的数据库连接
     * @return 新的Connection对象
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection () throws SQLException, ClassNotFoundException {
        // 1、加载并注册JDBC驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2、创建数据库连接
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
            "root",
            "root"
        );
        return conn;
    }

    /**
     * 关闭连接，释放资源
     * @param res 结果集对象
     * @param stmt Statement对象
     * @param conn Connection对象
     * @throws SQLException
     */
    public static void closeConnection (ResultSet res, Statement stmt, Connection conn) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if (res != null) {
            res.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
