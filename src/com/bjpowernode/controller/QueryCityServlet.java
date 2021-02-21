package com.bjpowernode.controller;

import com.bjpowernode.dao.QueryDao;
import com.bjpowernode.entity.City;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryCityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        String strProvinceId = request.getParameter("proid");
        if (strProvinceId != null && !"".equals(strProvinceId.trim())) {
            QueryDao dao = new QueryDao();
            List<City> cityList = dao.queryCityList(Integer.valueOf(strProvinceId));
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(cityList);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}