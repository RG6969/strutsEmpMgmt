/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Roles;
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
public class RoleService {
    public static ArrayList getAllRole() {
        ArrayList roleList = new ArrayList();
        try {
            
            
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "SELECT * from roles";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                Roles roles = new Roles();
                roles.setRoleId(rs.getInt("roleId"));
                roles.setRoleName(rs.getString("roleName"));
                
                roleList.add(roles);
            }
            
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return roleList;
    }
}
