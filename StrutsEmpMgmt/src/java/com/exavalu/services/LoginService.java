/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Country;
import com.exavalu.models.District;
import com.exavalu.models.Province;
import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class LoginService {

    public static LoginService loginService = null;



    private LoginService() {
    }

    public static LoginService getInstance() {
        if (loginService == null) {
            return new LoginService();
        } else {
            return loginService;
        }
    }

    public boolean doLogin(User emp) {
        boolean success = false;

        String sql = "Select * from users where emailAddress=? and password=?";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emp.getEmailAddress());
            ps.setString(2, emp.getPassword());

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                success = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return success;
    }

    public boolean doRegister(User user) {

        boolean result = false;
        String sql = "INSERT INTO users(emailAddress,password,firstName,lastName,countryCode,provinceCode,districtCode)" + "VALUES(? ,? ,? ,?,?,?,?)";

        try {
            Connection con = JDBCConnectionManager.getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmailAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getCountryCode());
            preparedStatement.setString(6, user.getProvinceCode());
            preparedStatement.setString(7, user.getDistrictCode());

            int res = preparedStatement.executeUpdate();

            if (res == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;

    }

    public ArrayList getAllCountries() {
        ArrayList countryList = new ArrayList();
        String sql = "Select * from countries";
        
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
           

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Country country = new Country();
                country.setCountryCode(rs.getString("countryCode"));
                country.setCountryName(rs.getString("countryName"));
                countryList.add(country);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.err.println("country size"+countryList.size());
        return countryList;
    }

    public ArrayList getAllProvinces(String countryID) {
        ArrayList provinceList = new ArrayList();
        String sql = "Select * from province where countryID=?";
        
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, countryID);
           

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Province province = new Province();
                province.setProvinceCode(rs.getString("provinceCode"));
                province.setProvinceName(rs.getString("provinceName"));
                provinceList.add(province);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.err.println("province size"+provinceList.size());
        return provinceList;
    }

    public ArrayList getAllDistrict(String provinceCode) {
        ArrayList districtList = new ArrayList();
        String sql = "Select * from district where provinceId=?";
        
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, provinceCode);
           

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                District district = new District();
                district.setDistrictCode(rs.getString("districtCode"));
                district.setDistrictName(rs.getString("districtName"));
                districtList.add(district);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.err.println("province size"+districtList.size());
        return districtList;
    }
    }
    


