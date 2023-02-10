/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

/**
 *
 * @author hp
 */
public class Province {
    private String provinceCode,provinceName, countryID;

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
     * @return the provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


    /**
     * @return the countryID
     */
    public String getCountryID() {
        return countryID;
    }

    /**
     * @param countryID the countryID to set
     */
    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }
}
