/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.LoginService;
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
public class User extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String status;
    private String countryCode;
    private String provinceCode;
    private String districtCode;
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

    public String doLogin() throws Exception {
       
        String result = "FAILURE";
        

        boolean success = LoginService.getInstance().doLogin(this);

        if (success) {
            System.out.println("returning Success from doLogin method");
            sessionMap.put("Loggedin", this);
            ArrayList empList = EmployeeService.getInstance().getAllEmployees();
            ArrayList DeptList = DepartmentService.getAllDepartment();
            ArrayList RoleList = RoleService.getAllRole();

            sessionMap.put("DeptList", DeptList);
            sessionMap.put("RoleList", RoleList);
            sessionMap.put("EmpList", empList);
            result = "SUCCESS";
        } else {
            
            log.error(localdatetime+"  "+"Either Email Address or Password is wrong");
            System.out.println("returning Failure from doLogin method");
        }

        return result;
    }

    public String doLogout() throws Exception {
        sessionMap.clear();
        return "SUCCESS";
    }
    public String signup() throws Exception {
        String result="FAILURE";
        boolean result1 = LoginService.getInstance().doRegister(this);         
        if(result1){
            result = "SUCCESS";            
        }
        else {            
            log.error(localdatetime+"  "+"Either Email Address or Password is wrong");
            System.out.println("returning Failure from doLogin method");
        }
        System.out.println(sessionMap);
        
        return result;
    }
    public String preSignup() throws Exception {
         sessionMap.clear();
        String result="SUCCESS";
        
        
        ArrayList countryList = LoginService.getInstance().getAllCountries();
        ArrayList provinceList = null;
        ArrayList districtList = null;
        
        sessionMap.put("CountryList", countryList);
        System.out.println("countries are"+this.countryCode);
        System.err.println("state code"+ this.provinceCode);
        if(this.countryCode!= null){
             provinceList = LoginService.getInstance().getAllProvinces(this.countryCode);
            sessionMap.put("ProvinceList", provinceList);
            sessionMap.put("User", this);
            result = "PROVINCELIST";
        }if(this.provinceCode!=null){
            System.out.println("Country code: "+ this.countryCode+ "    and  state code:  "+ this.provinceCode);
             districtList = LoginService.getInstance().getAllDistrict(this.provinceCode);
            sessionMap.put("DistrictList", districtList);
            sessionMap.put("User", this);
            result = "DISTRICTLIST";
        }

        System.out.println(sessionMap);
        
        return result;
    }
    

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the provinceCode
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * @param provinceCode the provinceCode to set
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * @return the districtCode
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * @param districtCode the districtCode to set
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * @return the employeeId
     */
}
