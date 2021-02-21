package com.bjpowernode.dao;

import com.bjpowernode.entity.City;
import com.bjpowernode.entity.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDao {

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private String sql;
    private String url = "jdbc:mysql://localhost:3306/springdb";
    private String username = "root";
    private String password = "333";

    public List<Province> queryProvinceList() {
        List<Province> provinces = new ArrayList<>();
        try {
            Province p = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            sql = "select id,name,jiancheng,shenghui from province order by id";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
              p= new Province();
              p.setId(rs.getInt("id"));
              p.setName(rs.getString("name"));
              p.setJiancheng(rs.getString("jiancheng"));
              p.setShenghui(rs.getString("shenghui"));
              provinces.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
          try{
              if(rs !=null){
                  rs.close();
              }
              if(pst!=null){
                  pst.close();
              }
              if(conn != null){
                  conn.close();
              }
          }catch (Exception ex){
              ex.printStackTrace();
          }
        }
        return provinces;
    }

    public List<City> queryCityList(Integer provinceId) {
        List<City> cities = new ArrayList<>();
        try {
            City city = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            sql = "select id,name from city where provinceid = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,provinceId);
            rs = pst.executeQuery();
            while (rs.next()){
              city = new City();
              city.setId(rs.getInt("id"));
              city.setName(rs.getString("name"));
              cities.add(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
              if(rs !=null){
                  rs.close();
              }
              if(pst!=null){
                  pst.close();
              }
              if(conn != null){
                  conn.close();
              }
          }catch (Exception ex){
              ex.printStackTrace();
          }

        }
        return cities;
    }

}



