package com.imooc.jdbc.sample;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * JDBC批处理
 */
public class batchSample {
    // 标准方式插入若干数据
    private static void tc1 () {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            long startTime = new Date().getTime();
            conn = DbUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into employee(eno, ename, salary, dname) values(?, ?, ?, ?)";
            for (int i = 100000; i < 200000; i++) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, i);
                pstmt.setString(2, "员工" + i);
                pstmt.setFloat(3, 40000f);
                pstmt.setString(4, "市场部");
                pstmt.executeUpdate();
            }
            conn.commit();
            long endTime = new Date().getTime();
            System.out.println("tc1执行时长：" + (endTime - startTime));
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();throw new RuntimeException(e);
        } finally {
            try {
                DbUtils.closeConnection(null, pstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 使用批处理插入若干数据
    private static void tc2 () {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            long startTime = new Date().getTime();
            conn = DbUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into employee(eno, ename, salary, dname) values(?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (int i = 200000; i < 300000; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "员工" + i);
                pstmt.setFloat(3, 40000f);
                pstmt.setString(4, "市场部");
                pstmt.addBatch(); // 将参数加入到批处理任务
            }
            pstmt.executeBatch(); // 执行批处理任务
            conn.commit();
            long endTime = new Date().getTime();
            System.out.println("tc2执行时长：" + (endTime - startTime));
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();throw new RuntimeException(e);
        } finally {
            try {
                DbUtils.closeConnection(null, pstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main (String[] args) {
        tc1();
        tc2();
    }
}
