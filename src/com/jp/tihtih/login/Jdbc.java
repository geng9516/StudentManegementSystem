/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.tihtih.login;

import com.jp.tihtih.root.Aclass;
import com.jp.tihtih.root.Grade;
import com.jp.tihtih.root.Student;
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

//    String DB_URL = "jdbc:postgresql://localhost:5432/chen";
//    String USER = "postgres";
//    String PASS = "postgres";
    String DB_URL = "jdbc:mysql://localhost:3306/studentmanagementsystem";
    String USER = "root";
    String PASS = "213sos1995";

    //DBに接続
    public Connection getDbcom() throws ClassNotFoundException {
        if (conn == null) {
//            Class.forName("org.postgresql.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
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
    public boolean checkTeacherID(String id) throws SQLException {
        String sql = "select id from t_teachers where id='" + id + "'";
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
        String sql = "insert into t_teachers (id,name,pass,subject,sex)values('";
        sql += teacher.getId() + "','";
        sql += teacher.getName() + "','";
        sql += teacher.getPass() + "','";
        sql += teacher.getSubject() + "','";
        sql += teacher.getSex() + "')";
        stmt.executeUpdate(sql);
    }

    //t_classにクラス情報を挿入
    public void insertClass(Aclass aclass) throws SQLException {
        String sql = "insert into t_class(teacherid,classname,subject)values('";
        sql += aclass.getTeacherId() + "','" + aclass.getClassName() + "','" + aclass.getSubject() + "')";
        stmt.executeUpdate(sql);
    }

    //t_teacherのクラス情報を更新
    public void updateTeacher(Teacher teacher) throws SQLException {
        String sql = "update t_teachers set ";
        sql += "name= '" + teacher.getName() + "',";
        sql += "pass= '" + teacher.getPass() + "',";
        sql += "subject= '" + teacher.getSubject() + "',";
        sql += "sex= '" + teacher.getSex() + "'";
        sql += "where id='" + teacher.getId() + "'";
        stmt.executeUpdate(sql);
    }

    //t_teacherのクラス情報を更新
    public void updateClass(Aclass aclass) {
        String sql = "update t_teachers set ";
        sql += "classname ='" + aclass.getClassName();
        sql += "' where teacherid = '" + aclass.getTeacherId() + "' and subje = '" + aclass.getClassName() + "'";
    }

    //クラス重複チェック
    public boolean checkClass(String classname, String subject) throws SQLException {
        String sql = "select * from t_class where classname = '" + classname + "' and subject='" + subject + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                return false;
            }
        }
        return true;
    }

    //先生のデータをすべて読み取ってjtableに表示
    public List<Teacher> getTeachersDate() throws SQLException {
        List<Teacher> list = new ArrayList<>();
        Teacher teacher = null;
        String sql = "select * from t_teachers ";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getString(1));
                teacher.setName(rs.getString(2));
                teacher.setPass(rs.getString(3));
                teacher.setSubject(rs.getString(4));
                teacher.setSex(rs.getString(5));
                list.add(teacher);
            }
        }
        return list;
    }

    //クラスのデータをすべて読み取るjtableに表示
    public List<Aclass> getClassDB(String teacherid) throws SQLException {
        List<Aclass> list = new ArrayList<>();
        Aclass aclass = null;

        String sql = "select * from t_class where teacherid = '" + teacherid + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                aclass = new Aclass();
                aclass.setTeacherId(rs.getString(1));
                aclass.setClassName(rs.getString(2));
                aclass.setSubject(rs.getString(3));
                list.add(aclass);
            }
        }
        return list;
    }

    //先生データ削除
    public void deleteTecher(String teacherid) throws SQLException {
        String sql = "delete from t_teachers where id ='" + teacherid + "'";
        stmt.executeUpdate(sql);
    }

    //classデータ削除
    public void deleteClass(String teacherid) throws SQLException {
        String sql = "delete from t_class where teacherid ='" + teacherid + "'";
        stmt.executeUpdate(sql);
    }

    //管理画面から生徒一覧へ
    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "select * from t_students";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString(1));
                student.setAclass(rs.getString(2));
                student.setName(rs.getString(3));
                student.setPass(rs.getString(4));
                student.setSex(rs.getString(5));
                list.add(student);
            }
        }
        return list;
    }

    //loginIDとパスワードを追加
    public void insertTeacherUser(String teacherId, String pass) throws SQLException {
        String sql = "insert into t_user(id,pass)values('" + teacherId + "','" + pass + "')";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    //loginIDとパスワードを更新
    public void updateTeacherUser(String teacherId, String pass) throws SQLException {
        String sql = "update t_user set pass = '" + pass + "' where id ='" + teacherId + "'";
        stmt.executeUpdate(sql);
    }

    //先生IDとパスワードを削除
    public void deleteTeacherUser(String teacherId) throws SQLException {
        String sql = "delete from t_user where id = '" + teacherId + "'";
        stmt.executeUpdate(sql);
    }

    /*
    管理者画面生徒データの追加編集
     */
    //生徒IDの重複チェック
    public boolean checkStudentId(String studentId) throws SQLException {
        String sql = "select studentid from t_students where studentid = '" + studentId + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                return true;
            }
        }
        return false;
    }

    //生徒データを挿入
    public void insertStudent(Student student) throws SQLException {
        String sql = "insert into t_students(studentid,classname,name,pass,sex) values('";
        sql += student.getId() + "',";
        sql += "'" + student.getAclass() + "',";
        sql += "'" + student.getName() + "',";
        sql += "'" + student.getPass() + "',";
        sql += "'" + student.getSex() + "')";
        stmt.executeUpdate(sql);
        System.out.println(sql);
    }

    //生徒データを更新する
    public void updateStudent(Student student) throws SQLException {
        String sql = "update t_students set ";
        sql += "classname =" + "'" + student.getAclass() + "',";
        sql += "name =" + "'" + student.getName() + "',";
        sql += "pass=" + "'" + student.getPass() + "',";
        sql += "sex =" + "'" + student.getSex() + "'";
        sql += " where studentid ='" + student.getId() + "'";
        stmt.executeUpdate(sql);
    }

    //生徒1人一人の成績を取り出す
    public List<Grade> getGrade(String studentId) throws SQLException {
        List<Grade> list = new ArrayList<>();
        String sql = "select studentid,testtype,kokugo,math,english,science,history,(kokugo+math+english+science+history) from t_grades where studentid ='" + studentId + "'";
        System.out.println(sql);
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setStudentid(rs.getString(1));
                grade.setTest(rs.getString(2));
                grade.setKokugo(rs.getDouble(3));
                grade.setMath(rs.getDouble(4));
                grade.setEnglish(rs.getDouble(5));
                grade.setScience(rs.getDouble(6));
                grade.setHistory(rs.getDouble(7));
                grade.setSum(rs.getDouble(8));
                list.add(grade);
            }
        }

        return list;
    }

    //成績をすべて一覧に表示（クラス関係なし）
    public List<Grade> getGrade() throws SQLException {
        List<Grade> list = new ArrayList<>();

        String sql = "select studentid,testtype,kokugo,math,english,science,history,(kokugo+math+english+science+history) from t_grades";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setStudentid(rs.getString(1));
                grade.setTest(rs.getString(2));
                grade.setKokugo(rs.getDouble(3));
                grade.setMath(rs.getDouble(4));
                grade.setEnglish(rs.getDouble(5));
                grade.setScience(rs.getDouble(6));
                grade.setHistory(rs.getDouble(7));
                list.add(grade);
            }
        }
        return list;
    }

    //テストの種類が重複するかをチェック
    public boolean checkTestType(String testType, String studentId) throws SQLException {
        String sql = "select * from t_grades where testtype='" + testType + "' and studentid ='" + studentId + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                return true;
            }
        }
        return false;
    }

    //生徒の成績を挿入
    public void insertGrade(Grade grade) throws SQLException {
        String sql = "insert into t_grades(studentid,testtype,kokugo,math,english,science,history)values('";
        sql += grade.getStudentid() + "','";
        sql += grade.getTest() + "','";
        sql += grade.getKokugo() + "','";
        sql += grade.getMath() + "','";
        sql += grade.getEnglish() + "','";
        sql += grade.getScience() + "','";
        sql += grade.getHistory() + "')";
        stmt.executeUpdate(sql);
    }

    //成績を編集
    public void updateGrade(Grade grade) throws SQLException {
        String sql = "update t_grades set kokugo =" + grade.getKokugo();
        sql += "," + "math=" + grade.getMath();
        sql += "," + "english=" + grade.getEnglish();
        sql += "," + "science=" + grade.getScience();
        sql += "," + "history=" + grade.getHistory();
        sql += " where studentid ='" + grade.getStudentid() + "' and testtype = '" + grade.getTest() + "'";
        stmt.executeUpdate(sql);
        System.out.println(sql);
    }

    //生徒ごとの成績をすべて読み取って一覧に表示
    public List<Grade> selectGrade(String studentid) throws SQLException {
        List<Grade> list = new ArrayList<>();

        String sql = "select studentid,testtype,kokugo,math,english,science,history,(kokugo+math+english+science+history) from t_grades where studentid ='" + studentid + "'";
        System.out.println(sql);
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setStudentid(rs.getString(1));
                grade.setTest(rs.getString(2));
                grade.setKokugo(rs.getDouble(3));
                grade.setMath(rs.getDouble(4));
                grade.setEnglish(rs.getDouble(5));
                grade.setScience(rs.getDouble(6));
                grade.setHistory(rs.getDouble(7));
                grade.setSum(rs.getDouble(8));
                list.add(grade);
            }
        }
        return list;
    }

    //クラス毎の生徒一覧
    public List<Student> selectClass(String className) throws SQLException {

        String sql = "select * from t_students where classname = '" + className + "' order by studentid";
        List<Student> list = new ArrayList<>();
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString(1));
                student.setAclass(rs.getString(2));
                student.setName(rs.getString(3));
                student.setPass(rs.getString(4));
                student.setSex(rs.getString(5));
                list.add(student);
            }
        }
        return list;
    }
    
  

    //生徒データ削除
    public void deleteStudent(String studentId) throws SQLException {
        String sql = "delete from t_students where studentid ='" + studentId + "'";
        stmt.executeUpdate(sql);
    }

    //生徒すべての成績削除
    public void deleteGrade(String studentId) throws SQLException {
        String sql = "delete from t_grades where studentid ='" + studentId + "'";
        stmt.executeUpdate(sql);
    }

    //成績をテスト別に削除
    public void deleteGrade(String studentId, String testType) throws SQLException {
        String sql = "delete from t_grades where studentid ='" + studentId + "' and testtype = '" + testType + "'";
        stmt.executeUpdate(sql);
    }

    //学生IDとパスワードを追加
    public void insertStudentUser(String studentId, String pass) throws SQLException {
        String sql = "insert into t_user(id,pass)values('" + studentId + "','" + pass + "')";
        stmt.executeUpdate(sql);
    }

    //学生IDとパスワードを更新
    public void updateStudentUser(String studentId, String pass) throws SQLException {
        String sql = "update t_user set pass = '" + pass + "' where id ='" + studentId + "'";
        stmt.executeUpdate(sql);
    }

    //生徒IDとパスワードを削除
    public void deleteStudentUser(String studentId) throws SQLException {
        String sql = "delete from t_user where id = '" + studentId + "'";
        stmt.executeUpdate(sql);
    }

    //名前で生徒IDを取得
    public String getStudentId(String studentName) throws SQLException {

        String sql = "select studentid from t_students where name ='" + studentName + "'";
        String studentId = null;
        rs = stmt.executeQuery(sql);
        System.out.println(sql);
        if (rs != null) {
            while (rs.next()) {
                studentId = rs.getString(1);
            }
        }
        return studentId;
    }

    /*
        Login
     */
    //t_userにログインデータあるかをチェック
    public Users selectUser(String id) throws SQLException {

        Users user = null;
        String sql = "select * from t_user where id='" + id + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {

                user = new Users();
                user.setId(rs.getString(1));
                user.setPass(rs.getString(2));

            }
        }
        return user;
    }

    //先生情報を調べる
    public Teacher getTeacher(String teacherId) throws SQLException {
        Teacher teacher = null;
        String sql = "select * from t_teachers where id='" + teacherId + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getString(1));
                teacher.setName(rs.getString(2));
                teacher.setPass(rs.getString(3));
                teacher.setSubject(rs.getString(4));
                teacher.setSex(rs.getString(5));
            }
        }
        return teacher;
    }

    //passを探す
    public String selectPass(String Id) throws SQLException {
        String sql = "select * from t_user where id='" + Id + "'";
        String pass = null;
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                pass = rs.getString(1);
            }
        }
        return pass;
    }

    //passを変更
    public void setPass(String Id, String newPass) throws SQLException {
        String sql = "update t_user set pass = '" + newPass + "' where id = '" + Id + "'";
        stmt.executeUpdate(sql);
    }

    //生徒Login
    public Student getStudentDate(String studentId) throws SQLException {
        Student student = null;
        String sql = "select * from t_students where studentid='" + studentId + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                student = new Student();
                student.setId(rs.getString(1));
                student.setAclass(rs.getString(2));
                student.setName(rs.getString(3));
                student.setPass(rs.getString(4));
                student.setSex(rs.getString(5));
            }
        }
        return student;
    }

    /*
    検索機能
     */
    //teacherIdで検索（あいまいと特定検索できる）
    public List<Teacher> searchTeacherId(String teacherId) throws SQLException {
        List<Teacher> list = new ArrayList<>();
        String sql = "select * from t_teachers where id like '%" + teacherId + "%' order by id";
        System.out.println(sql);
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getString(1));
                teacher.setName(rs.getString(2));
                teacher.setPass(rs.getString(3));
                teacher.setSubject(rs.getString(4));
                teacher.setSex(rs.getString(5));
                list.add(teacher);
            }
        }
        return list;
    }

    //先生の名前/クラス/性別で検索
    public List<Teacher> searchTeacherName(String text) throws SQLException {
        List<Teacher> list = new ArrayList<>();
        String sql = "select * from t_teachers where name like " + "'%" + text + "%' or subject like ";
        sql += "'%" + text + "%' or sex like '%" + text + "%' or pass like '%" + text + "%' or id like '%" + text + "%' order by id";
        System.out.println(sql);
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getString(1));
                teacher.setName(rs.getString(2));
                teacher.setPass(rs.getString(3));
                teacher.setSubject(rs.getString(4));
                teacher.setSex(rs.getString(5));
                list.add(teacher);
            }
        }
        return list;
    }

    //生徒Idで検索
    public List<Student> searchStudentId(String studentId,String className) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "select * from t_Students where studentid like '%" + studentId + "%' and classname =" + className + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString(1));
                student.setAclass(rs.getString(2));
                student.setName(rs.getString(3));
                student.setPass(rs.getString(4));
                student.setSex(rs.getString(5));
                list.add(student);
            }
        }
        return list;
    }
//生徒のクラス/名前/性別

    public List<Student> searchStudentName(String text , String className) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "select * from t_students where calssname = '" + className + "' and (name like " + "'%" + text + "%' or classname like ";
        sql += "'%" + text + "%' or sex like '%" + text + "%' or pass like '%" + text + "%' or studentid like '%" + text + "%')";
        System.out.println(sql);
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString(1));
                student.setAclass(rs.getString(2));
                student.setName(rs.getString(3));
                student.setPass(rs.getString(4));
                student.setSex(rs.getString(5));
                list.add(student);
            }
        }
        return list;
    }

    //数値で検索
    public List<Grade> searchGrade(int text) throws SQLException {
        List<Grade> list = new ArrayList<>();
        String sql = "select studentid,testtype,kokugo,math,english,science,history,(kokugo+math+english+science+history) from t_Students ";
        sql += "where cast(studentid as text) like" + "'%" + text + "%'";
        sql += "and cast(kokugo as text) like" + "'%" + text + "%'";
        sql += "and cast(math as text) like" + "'%" + text + "%'";
        sql += "and cast(english as text) like" + "'%" + text + "%'";
        sql += "and cast(science as text) like" + "'%" + text + "%'";
        sql += "and cast(history as text) like" + "'%" + text + "%'";
        System.out.println(sql);
        rs = stmt.executeQuery(sql);
        
        if (rs != null) {
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setStudentid(rs.getString(1));
                grade.setTest(rs.getString(2));
                grade.setKokugo(rs.getDouble(3));
                grade.setMath(rs.getDouble(4));
                grade.setEnglish(rs.getDouble(5));
                grade.setScience(rs.getDouble(6));
                grade.setHistory(rs.getDouble(7));
                list.add(grade);
            }
        }
        return list;
    }

    //
    public List<Grade> searchTesttype(String text) throws SQLException {
        List<Grade> list = new ArrayList<>();
        String sql = "select studentid,testtype,kokugo,math,english,science,history,(kokugo+math+english+science+history) from t_Students ";
        sql += "where studentid like " + "'%" + text + "%' or testtype like " + "'%" + text + "%'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setStudentid(rs.getString(1));
                grade.setTest(rs.getString(2));
                grade.setKokugo(rs.getDouble(3));
                grade.setMath(rs.getDouble(4));
                grade.setEnglish(rs.getDouble(5));
                grade.setScience(rs.getDouble(6));
                grade.setHistory(rs.getDouble(7));
                list.add(grade);
            }
        }
        return list;
    }
}
