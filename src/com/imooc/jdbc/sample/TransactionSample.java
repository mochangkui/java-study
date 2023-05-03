package com.imooc.jdbc.sample;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC中的事务控制
 */
public class TransactionSample {
    public static void main (String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into employee(eno, ename, salary, dname) values(?, ?, ?, ?)";
            for (int i = 1000; i < 2000; i++) {
                if (i == 1005) {
                    // throw new RuntimeException("插入失败");
                }
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, i);
                pstmt.setString(2, "员工" + i);
                pstmt.setFloat(3, 40000f);
                pstmt.setString(4, "市场部");
                pstmt.executeUpdate();
            }
            conn.commit();
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
}
