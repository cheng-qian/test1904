package com.bjpowernode.controller;

import com.bjpowernode.dao.QueryDao;
import com.bjpowernode.entity.Province;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        QueryDao dao = new QueryDao();
        List<Province> provinces = dao.queryProvinceList();
        if(provinces != null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(provinces);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println(json);
            pw.flush();
            pw.close();
        }
    }
}
