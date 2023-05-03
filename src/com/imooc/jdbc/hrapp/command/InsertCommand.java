package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertCommand implements Command{
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        System.out.print("请输入员工编号：");
        Integer eno = in.nextInt();

        System.out.print("请输入员工姓名：");
        String ename = in.next();

        System.out.print("请输入员工薪资：");
        Float salary = in.nextFloat();

        System.out.print("请输入员工隶属部门：");
        String dname = in.next();

        System.out.print("请入职日期：");
        String strHiredate = in.next();

        Connection conn = null;
        PreparedStatement pstmt = null;
        java.util.Date udHiredate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DbUtils.getConnection();
            String sql = "insert into employee(eno, ename, salary, dname, hiredate) values(?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eno);
            pstmt.setString(2, ename);
            pstmt.setFloat(3, salary);
            pstmt.setString(4, dname);
            try {
                udHiredate = sdf.parse(strHiredate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long time = udHiredate.getTime();
            java.sql.Date sdHiredate = new java.sql.Date(time);
            pstmt.setDate(5, sdHiredate);
            pstmt.executeUpdate();
            System.out.println("员工入职手续已办理！");
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
