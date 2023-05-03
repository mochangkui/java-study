package com.imooc.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.common.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Druid连接池配置与使用
 */
public class DruidSample {
    public static void main(String[] args) {
        // 1、加载属性文件
        Properties properties = new Properties();
        String propertyFile = DruidSample.class.getResource("/druid-config.properties").getPath();
        try {
            propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            // 2、获取DataSource数据对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 3、创建数据库连接
            for (int i = 0; i < 20; i++) {
                conn = dataSource.getConnection();
            }
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
        } catch (Exception e) {
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
