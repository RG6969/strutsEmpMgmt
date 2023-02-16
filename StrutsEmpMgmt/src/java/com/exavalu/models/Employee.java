/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class Employee extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String gender;
    private String age;
    private String departmentId;
    private String roleId;
    private String DepartmentName;
    private String RoleName;
    private String basicSalary;
    private String carAllowance;
    private String specialAllowance;
    Logger log = Logger.getLogger(User.class.getName());
    LocalDateTime localdatetime = LocalDateTime.now();

    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    public String search() throws Exception {
        String result = "SUCCESS";

        ArrayList empList1 = EmployeeService.searchEmployee(this);
        ArrayList DeptList = DepartmentService.getAllDepartment();
        ArrayList RoleList = RoleService.getAllRole();
        sessionMap.put("searchEmployee", empList1);
        sessionMap.put("DeptList", DeptList);
        sessionMap.put("RoleList", RoleList);

        return result;
    }

    public String add() throws Exception {
        String result = "FAILURE";

        boolean result1 = EmployeeService.addEmployee(this);
        if (result1) {
            ArrayList empList2 = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList2);
            result = "SUCCESS";
        }else {            
            log.error(localdatetime+"  "+"Might be some error with service method");
            System.out.println("returning Failure from doLogin method");
        }

        return result;
    }

    public String edit() throws Exception {
        String result = "FAILURE";

        Employee emp = EmployeeService.getEmployee(this.employeeId);
        if (emp != null) {

            sessionMap.put("Emp", emp);
            result = "SUCCESS";
        }
        else {            
            log.error(localdatetime+"  "+"Get employee service method is not fetching details properly");
            System.out.println("returning Failure from doLogin method");
        }

        return result;
    }

    public String update() throws Exception {
        String result = "FAILURE";

        boolean result1 = EmployeeService.updateEmployee(this, this.employeeId);
        if (result1) {
            ArrayList empList2 = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList2);

            result = "SUCCESS";
        }else {            
            log.error(localdatetime+"  "+"Data inserted is invalid");
            System.out.println("returning Failure from doLogin method");
        }

        return result;
    }
    public String delete() throws Exception {
        String result = "FAILURE";

        boolean result1 = EmployeeService.getInstance().deleteEmployee(this.employeeId);
        if (result1) {
            ArrayList empList2 = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList2);

            result = "SUCCESS";
        }

        return result;
    }

    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the DepartmentName
     */
    public String getDepartmentName() {
        return DepartmentName;
    }

    /**
     * @param DepartmentName the DepartmentName to set
     */
    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    /**
     * @return the RoleName
     */
    public String getRoleName() {
        return RoleName;
    }

    /**
     * @param RoleName the RoleName to set
     */
    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

    /**
     * @return the basicSalary
     */
    public String getBasicSalary() {
        return basicSalary;
    }

    /**
     * @param basicSalary the basicSalary to set
     */
    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    /**
     * @return the carAllowance
     */
    public String getCarAllowance() {
        return carAllowance;
    }

    /**
     * @param carAllowance the carAllowance to set
     */
    public void setCarAllowance(String carAllowance) {
        this.carAllowance = carAllowance;
    }

    /**
     * @return the specialAllowance
     */
    public String getSpecialAllowance() {
        return specialAllowance;
    }

    /**
     * @param specialAllowance the specialAllowance to set
     */
    public void setSpecialAllowance(String specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    /**
     * @return the emailAddress
     */
    /**
     * @return the employeeId
     */
}
