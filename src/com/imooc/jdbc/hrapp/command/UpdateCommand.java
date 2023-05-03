package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateCommand implements Command{
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        System.out.print("请输入员工编号：");
        Integer eno = in.nextInt();

        System.out.print("请输入员工薪资：");
        Float salary = in.nextFloat();

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "update employee set salary=? where eno=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1, salary);
            pstmt.setInt(2, eno);
            int cnt = pstmt.executeUpdate();
            if (cnt == 1) {
                System.out.println("员工薪资调整成功！");
            } else {
                System.out.println("未找到" + eno + "编号员工的数据");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.closeConnection(null, pstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
