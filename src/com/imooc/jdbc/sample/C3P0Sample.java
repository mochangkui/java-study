package com.imooc.jdbc.sample;

import com.imooc.jdbc.common.DbUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Sample {
    public static void main(String[] args) {
        // 1、加载配置文件
        // 2、创建DataSource
        DataSource dataSource = new ComboPooledDataSource();
        // 3、得到数据库连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("select * from employee limit 0, 10");
            res = pstmt.executeQuery();
            // 4、遍历查询结果
            while (res.next()) {
                Integer eno = res.getInt(1);
                String ename = res.getString("ename");
                Float salary = res.getFloat("salary");
                String dname = res.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.closeConnection(res, pstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
