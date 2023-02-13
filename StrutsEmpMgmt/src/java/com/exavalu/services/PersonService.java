/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Person;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class PersonService {

    public static boolean addPerson(Person person) {
        boolean result = false;
        Connection con = JDBCConnectionManager.getConnection();
        String sql = "INSERT INTO person(userId,id,title,body)" + "VALUES(? ,? ,? ,?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, person.getUserId());
            preparedStatement.setInt(2, person.getId());
            preparedStatement.setString(3, person.getTitle());
            preparedStatement.setString(4, person.getBody());

            System.out.println("AddPerson sql :: " + preparedStatement);

            int rs = preparedStatement.executeUpdate();

            if (rs == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    
    }
    
}
