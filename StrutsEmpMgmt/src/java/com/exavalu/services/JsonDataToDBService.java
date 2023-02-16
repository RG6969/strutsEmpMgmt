/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Transcript;
import static com.exavalu.services.EmployeeService.localdatetime;
import static com.exavalu.services.EmployeeService.log;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class JsonDataToDBService {

    public static boolean InsertJsonData(Transcript transcript) {
        boolean result = false;

        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO transcript (userId,id,title,completed) VALUES (?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, transcript.getUserId());
            preparedStatement.setString(2, transcript.getId());
            preparedStatement.setString(3, transcript.getTitle());
            preparedStatement.setString(4, transcript.getCompleted());
            System.out.println("preparedstatement= " + preparedStatement);

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            log.error(localdatetime + "" + ex.getErrorCode());
        }

        return result;
    }

    public static boolean InsertJsonData(Transcript[] transcript) {

        boolean result = true;

        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO transcript (userId,id,title,completed) VALUES (?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            for (Transcript data : transcript) {

                preparedStatement.setString(1, data.getUserId());
                preparedStatement.setString(2, data.getId());
                preparedStatement.setString(3, data.getTitle());
                preparedStatement.setString(4, data.getCompleted());
                //System.out.println("preparedstatement= " + preparedStatement);

                preparedStatement.executeUpdate();
            }

//            if (row == 1) {
//                result = true;
//            }
        } catch (SQLException ex) {
//            ex.printStackTrace();
            log.error(localdatetime + "" + ex.getErrorCode());
        }

        return result;
    }

}
