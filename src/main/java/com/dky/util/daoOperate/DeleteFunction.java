package com.dky.util.daoOperate;


import com.dky.util.ReadProperties;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DeleteFunction {
    static Logger logger = Logger.getLogger(DeleteFunction.class.getName());

    public int deleteTable(String tablename, String whereCondition) throws IOException, SQLException {
        long start = System.currentTimeMillis();
        Connection conn = getgpConnection();
        CallableStatement statement = conn.prepareCall("{call delete(?,?,?) }");
        String sc = "";
        String tab = "";
        String[] sArray = tablename.split("\\.", -1);
        sc = sArray[0];
        tab = sArray[1].toLowerCase();

        try {
            conn.setAutoCommit(false);
            statement.setString(1, sc);
            statement.setString(2, tab);
            statement.setString(3, whereCondition);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            statement.close();
            conn.rollback();
            conn.close();
            return 0;
        }
        logger.info("conn commit, time:" + (System.currentTimeMillis() - start));
        conn.commit();
        statement.close();
        conn.close();
        logger.info("conn close, time:" + (System.currentTimeMillis() - start));
        return 1;
    }


    public int updateTable(String tablename,String update_col, String update_val,  String whereCondition) throws IOException, SQLException {
        long start = System.currentTimeMillis();
        Connection conn = getgpConnection();
        CallableStatement statement = conn.prepareCall("{call update(?,?,?,?,?) }");
        String sc = "";
        String tab = "";
        String[] sArray = tablename.split("\\.", -1);
        sc = sArray[0];
        tab = sArray[1].toLowerCase();

        try {
            conn.setAutoCommit(false);
            statement.setString(1, sc);
            statement.setString(2, tab);
            statement.setString(3, update_col);
            statement.setString(4, update_val);
            statement.setString(5, whereCondition);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            statement.close();
            conn.rollback();
            conn.close();
            return 0;
        }
        logger.info("conn commit, time:" + (System.currentTimeMillis() - start));
        conn.commit();
        statement.close();
        conn.close();
        logger.info("conn close, time:" + (System.currentTimeMillis() - start));
        return 1;
    }



    public static Connection getgpConnection() throws IOException {
        ReadProperties loadProp = new ReadProperties();
        InputStream in = loadProp.getClass().getResourceAsStream("/db.properties");
        Properties prop = new Properties();
        String info = null;
        String driver = null;
        String url = null;
        String user = null;
        String pwd = null;
        InputStreamReader inputStreamReader=null;
        try {
            inputStreamReader=new InputStreamReader(in, "utf-8");
            prop.load(inputStreamReader);
            driver = prop.getProperty("jdbc.driver");
            url = prop.getProperty("jdbc.url");
            user = prop.getProperty("jdbc.username");
            pwd = prop.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStreamReader.close();
            in.close();
        }
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

