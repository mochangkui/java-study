package com.imooc.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.hrapp.entity.Employee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Apache DBUtils + Druid联合使用演示
 */
public class DbUtilsSample {
    private static void query () {
        // 1、加载属性文件
        Properties properties = new Properties();
        // 拿到文件路径
        String propertyFile = DbUtilsSample.class.getResource("/druid-config.properties").getPath();
        // 对文件路径中空格和中文等转义
        propertyFile = URLDecoder.decode(propertyFile);
        Connection conn = null;
        try {
            // 使用FileInputStream读取文件内容，properties.load进行加载
            properties.load(new FileInputStream(propertyFile));
            // 2、获取DataSource数据对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 内部实现了连接数据库
            QueryRunner qr = new QueryRunner(dataSource);
            System.out.println("Employee.class：" + Employee.class);
            List<Employee> list = qr.query(
                "select * from employee limit ?, ?",
                new BeanListHandler<Employee>(Employee.class),
                new Object[]{0, 20});
            for (Employee emp: list) {
                System.out.println(emp.getEname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void update () {
        // 1、加载属性文件
        Properties properties = new Properties();
        // 拿到文件路径
        String propertyFile = DbUtilsSample.class.getResource("/druid-config.properties").getPath();
        // 对文件路径中空格和中文等转义
        propertyFile = URLDecoder.decode(propertyFile);
        Connection conn = null;
        try {
            // 使用FileInputStream读取文件内容，properties.load进行加载
            properties.load(new FileInputStream(propertyFile));
            // 2、获取DataSource数据对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 3、连接数据库
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            String sql1 = "update employee set salary=salary+1000 where eno=?";
            String sql2 = "update employee set salary=salary-1000 where eno=?";
            QueryRunner qr = new QueryRunner();
            qr.update(conn, sql1, new Object[]{1000});
            qr.update(conn, sql2, new Object[]{1001});
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void testVar () {
        Object[] arr = new Object[]{0, 10};
        System.out.println(arr[0]);
    }

    public static void main(String[] args) {
//        query();
//        update();
        new DbUtilsSample().testVar();
    }
}

