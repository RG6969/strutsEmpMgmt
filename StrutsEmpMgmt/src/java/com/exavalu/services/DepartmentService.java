/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Department;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class DepartmentService {
     public static ArrayList getAllDepartment() {
        ArrayList deptList = new ArrayList();
        try {
            
            
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "SELECT * from departments";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                Department dept = new Department();
                dept.setDepartmentId(rs.getInt("departmentId"));
                dept.setDepartName(rs.getString("departName"));
                
                deptList.add(dept);
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return deptList;
    }
}
