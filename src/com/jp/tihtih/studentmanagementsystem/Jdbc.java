/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.tihtih.studentmanagementsystem;

import com.jp.tihtih.root.Aclass;
import com.jp.tihtih.root.Teacher;
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

    /*
    管理者が先生情報を追加/編集
    
     */
    //先生のIDが重複するかを調べる
    public boolean checkTeacherID(int id) throws SQLException {
        String sql = "select id from t_teachers where id=" + id;
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                return true;
            }
        }
        return false;
    }

    //重複するパスワードを判断する(先生)
    public boolean checkPass(String teacherPass) throws SQLException {
        String sql = "select pass from t_teachers where pass=" + "'" + teacherPass + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                return true;
            }
        }
        return false;
    }

    //t_teacherにクラス情報を挿入
    public void insertTeacher(Teacher teacher) throws SQLException {
        String sql = "insert into t_teachers (id,name,pass,subject,sex)values(";
        sql += teacher.getId() + ",'";
        sql += teacher.getName() + "','";
        sql += teacher.getPass() + "','";
        sql += teacher.getSubject() + "','";
        sql += teacher.getSex() + "')";
        stmt.executeUpdate(sql);
    }

    //t_classにクラス情報を挿入
    public void insertClass(Aclass aclass) throws SQLException {
        String sql = "insert into t_class(teacherid,classname,subject)values(";
        sql += aclass.getTeacherId() + ",'" + aclass.getClassName() + "','" + aclass.getSubject() + "')";
        stmt.executeUpdate(sql);
    }

    //t_teacherのクラス情報を更新
    public void updateTeacher(Teacher teacher) throws SQLException {
        String sql = "update t_teachers set ";
        sql += "name= '" + teacher.getName() + "',";
        sql += "password= '" + teacher.getPass() + "',";
        sql += "subject= '" + teacher.getSubject() + "',";
        sql += "sex= '" + teacher.getSex() + "'";
        sql += " where id=" + teacher.getId();
        stmt.executeUpdate(sql);
    }

    //t_teacherのクラス情報を更新
    public void updateClass(Aclass aclass) {
        String sql = "update t_teachers set ";
        sql += "classname '" + aclass.getClassName();
        sql += " where teacherid = " + aclass.getTeacherId() + "and classname = '" + aclass.getClassName() + "'";
    }

    //クラス重複チェック
    public boolean checkClass(Aclass aclass) throws SQLException {
        String sql = "select classname from t_class where teacherid = " + aclass.getTeacherId() + "and '" + aclass.getSubject() + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                return true;
            }
        }
        return false;
    }

    //先生のデータをすべて読み取る
    public List<Teacher> getTeachersDate() throws SQLException {
        List<Teacher> list = new ArrayList<>();
        Teacher teacher = null;
        String sql = "select * from t_teachers ";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setPass(rs.getString(3));
                teacher.setSubject(rs.getString(4));
                teacher.setSex(rs.getString(5));
                list.add(teacher);
            }
        }
        return list;
    }

    //クラスのデータをすべて読み取る
    public List<Aclass> getClassDB(int teacherid) throws SQLException {
        List<Aclass> list = new ArrayList<>();
        Aclass aclass = null;

        String sql = "select * from t_class where teacherid = " + teacherid;
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                aclass = new Aclass();
                aclass.setTeacherId(rs.getInt(1));
                aclass.setClassName(rs.getString(2));
                aclass.setSubject(rs.getString(3));
                list.add(aclass);
            }
        }
        return list;
    }

}
