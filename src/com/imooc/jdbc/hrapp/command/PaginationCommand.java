package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;
import com.imooc.jdbc.hrapp.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 分页查询员工数据
 */
public class PaginationCommand implements Command{
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入页号：");
        int page = in.nextInt();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List<Employee> list = new ArrayList();
        try {
            conn = DbUtils.getConnection();
            String sql = "select * from employee limit ?, 10";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, (page - 1) * 10);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer eno = res.getInt("eno");
                String ename = res.getString("ename");
                Float salary = res.getFloat("salary");
                String dname = res.getString("dname");
                Date hiredate = res.getDate("hiredate");
                Employee emp = new Employee();
                emp.setEno(eno);
                emp.setEname(ename);
                emp.setSalary(salary);
                emp.setDname(dname);
                emp.setHiredate(hiredate);
                list.add(emp);
            }
            System.out.println(list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    DbUtils.closeConnection(res, pstmt, conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
