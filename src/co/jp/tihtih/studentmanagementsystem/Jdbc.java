/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.jp.tihtih.studentmanagementsystem;

import co.jp.tihtih.root.Teachers;
import co.jp.tihtih.root.Student;
import co.jp.tihtih.teacher.Students;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Jdbc {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    String DB_URL = "jdbc:postgresql://localhost:5432/chen";
    String USER = "postgres";
    String PASS = "postgres";
//    String DB_URL = "jdbc:mysql://localhost:3306/studentmanagementsystem";
//    String USER = "root";
//    String PASS = "213sos1995";

    //DBに接続
    public Connection getDbcom() throws ClassNotFoundException {
        if (conn == null) {
            Class.forName("org.postgresql.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

    //DBを閉じる
    public void closeDbcom() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

}
