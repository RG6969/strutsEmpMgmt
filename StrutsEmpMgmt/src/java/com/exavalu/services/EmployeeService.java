/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Employee;
import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class EmployeeService {

    public static EmployeeService employeeService = null;
    static Logger log = Logger.getLogger(User.class.getName());
    static LocalDateTime localdatetime = LocalDateTime.now();

    public static EmployeeService getInstance() {
        if (employeeService == null) {
            return new EmployeeService();
        } else {
            return employeeService;
        }
    }

    public ArrayList getAllEmployees() {
        ArrayList empList = new ArrayList();
        try {
            Connection con = JDBCConnectionManager.getConnection();
            //String sql = "Select * from employees e,departments d,roles r where e.deptId=d.departmentId and e.roleId=r.roleId";
            String sql = "SELECT  employeeId,firstName,lastName,phoneNo,gender,age,departName,roleName,basicSalary,carAllowance,specialAllowance,status FROM employees LEFT JOIN roles On employees.roleId= roles.roleId LEFT JOIN departments On employees.deptId= departments.departmentId";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (rs.getString("status").equalsIgnoreCase("0")) {
                    Employee emp = new Employee();
                    //emp.setAddress(rs.getString("address"));
                    emp.setEmployeeId(rs.getString("employeeId"));
                    emp.setFirstName(rs.getString("firstName"));
                    emp.setLastName(rs.getString("lastName"));
                    emp.setPhone(rs.getString("PhoneNo"));
                    emp.setGender(rs.getString("gender"));
                    emp.setAge(rs.getString("age"));
                    emp.setDepartmentName(rs.getString("departName"));
                    emp.setRoleName(rs.getString("roleName"));
                    emp.setBasicSalary(rs.getString("basicSalary"));
                    emp.setCarAllowance(rs.getString("carAllowance"));
                    emp.setSpecialAllowance(rs.getString("specialAllowance"));

                    empList.add(emp);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Number of employees = " + empList.size());
        return empList;
    }

    public static ArrayList searchEmployee(Employee emp) {
        ArrayList empList = new ArrayList();
        try {

            Connection con = JDBCConnectionManager.getConnection();
            String sql = "select * from employees e, departments d, roles r where e.deptId=d.departmentId and e.roleId=r.roleId "
                    + "having firstName like ?"
                    + "and lastName like ?"
                    + "and gender like ?"
                    + "and departmentId like ?"
                    + "and e.roleId like ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, emp.getFirstName() + "%");
            preparedStatement.setString(2, emp.getLastName() + "%");
            preparedStatement.setString(3, emp.getGender() + "%");
            preparedStatement.setString(4, emp.getDepartmentId() + "%");
            preparedStatement.setString(5, emp.getRoleId() + "%");

            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            while (rs.next()) {
                Employee emp1 = new Employee();
                emp1.setEmployeeId(rs.getString("employeeId"));
                emp1.setFirstName(rs.getString("firstName"));
                emp1.setLastName(rs.getString("lastName"));
                emp1.setPhone(rs.getString("PhoneNo"));
                emp1.setAge(rs.getString("age"));
                emp1.setGender(rs.getString("gender"));
                emp1.setDepartmentId(rs.getString("departmentId"));
                emp1.setRoleId(rs.getString("roleId"));
                emp1.setBasicSalary(rs.getString("basicSalary"));
                emp1.setCarAllowance(rs.getString("carAllowance"));
                emp1.setSpecialAllowance(rs.getString("specialAllowance"));
                emp1.setDepartmentName(rs.getString("departName"));
                emp1.setRoleName(rs.getString("roleName"));

                empList.add(emp1);
            }

        } catch (SQLException ex) {
//            ex.printStackTrace();
            log.error(localdatetime + "" + ex.getErrorCode());
        }
        return empList;
    }

    public static boolean updateEmployee(Employee emp, String employeeId) {

        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "UPDATE employees"
                    + " SET  firstName = ? , lastName = ? , PhoneNo = ? , age = ? , gender = ? ,  basicSalary = ? , carAllowance = ? , specialAllowance = ?, deptId= ?, roleId=?"
                    + " WHERE employeeId = ?";
            System.out.println("con " + con);

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            System.out.println(emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            System.out.println(emp.getPhone());
//            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(4, emp.getAge());
            preparedStatement.setString(5, emp.getGender());
//            preparedStatement.setString(6, emp.getDepartmentName());
//            preparedStatement.setString(7, emp.getRoleName());
            preparedStatement.setDouble(6, Double.parseDouble(emp.getBasicSalary()));
            preparedStatement.setDouble(7, Double.parseDouble(emp.getCarAllowance()));
            preparedStatement.setDouble(8, Double.parseDouble(emp.getSpecialAllowance()));
            preparedStatement.setString(9, emp.getDepartmentId());
            preparedStatement.setString(10, emp.getRoleId());

            preparedStatement.setString(11, employeeId);

//            preparedStatement.setString(9, employeeId);
            int row = preparedStatement.executeUpdate();
            System.out.println("sql" + preparedStatement);

            if (row == 1) {

                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            log.error(localdatetime + "" + ex.getErrorCode());
        }

        return result;

    }

    public static boolean addEmployee(Employee emp) {
        boolean result = false;

        Connection con = JDBCConnectionManager.getConnection();

        String sql = "INSERT INTO employees (firstName, lastName, phoneNo, age, gender, deptId, roleId, basicSalary, carAllowance, specialAllowance) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAge());
            preparedStatement.setString(5, emp.getGender());

            preparedStatement.setString(6, emp.getDepartmentId());
            preparedStatement.setString(7, emp.getRoleId());
            preparedStatement.setDouble(8, Double.parseDouble(emp.getBasicSalary()));
            preparedStatement.setDouble(9, Double.parseDouble(emp.getCarAllowance()));
            preparedStatement.setDouble(10, Double.parseDouble(emp.getSpecialAllowance()));

            System.out.println(preparedStatement);

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
//            ex.printStackTrace();
            log.error(localdatetime + "" + ex.getErrorCode());
        }
        return result;
    }

    public static Employee getEmployee(String employeeId) {

        Employee emp = new Employee();

        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "Select * from employees e,departments d,roles r where e.deptId=d.departmentId and e.roleId=r.roleId and e.employeeId =?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                //emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getString("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("PhoneNo"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentName(rs.getString("departName"));
                emp.setDepartmentId(rs.getString("deptId"));
                emp.setRoleId(rs.getString("roleId"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllowance(rs.getString("carAllowance"));
                emp.setSpecialAllowance(rs.getString("specialAllowance"));

            }

        } catch (SQLException ex) {
//            ex.printStackTrace();
            log.error(localdatetime + "" + ex.getErrorCode());
        }
        return emp;
    }

    public boolean deleteEmployee(String id) {
        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();

            String sql = "Update employees Set status=1 WHERE employeeId = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, id);
            System.out.println(preparedStatement);
            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                result = true;
            }

        } catch (SQLException ex) {
            log.error(localdatetime + "" + ex.getErrorCode());
        }
        return result;

    }
}
