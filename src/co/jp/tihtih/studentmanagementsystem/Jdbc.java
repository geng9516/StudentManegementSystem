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

    //TeacherDateの表にDBの生徒データを表示（DBからデータをとる）
    public List<Students> getStudentDates() throws SQLException {
        List<Students> list = new ArrayList<>();

        String sql = "select a.studentid,a.aclass,a.name,a.pass,a.sex,b.kokugo,b.math,b.english,b.science,b.history,";

        //合計sql
        sql += "(kokugo+math+english+science+history) ";
        sql += "from t_students a left join t_grade b on a.studentid = b.studentid";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Students students = new Students();
                students.setId(Integer.parseInt(rs.getString(1)));
                students.setClasss(rs.getString(2));
                students.setName(rs.getString(3));
                students.setPass(rs.getString(4));
                students.setSex(rs.getString(5));
                students.setKokugo(rs.getFloat(6));
                students.setMath(rs.getFloat(7));
                students.setEnglish(rs.getFloat(8));
                students.setScience(rs.getFloat(9));
                students.setHistory(rs.getFloat(10));
                students.setSum(rs.getInt(11));
                list.add(students);

            }
        }
        return list;

    }

    //生徒IDが重複するかを調べるための
    public boolean getStudentID(int studentId) throws SQLException {
        String sql = "select studentid from t_students where studentid = " + studentId;

        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                return true;
            }
        }
        return false;
    }

    //t_studentにデータをinsert
    public void insertStudent(Students student) throws SQLException {

        String sql = "insert into t_students(studentid,aclass,name,pass,sex) values(";
        sql += student.getId() + ",";
        sql += "'" + student.getClasss() + "',";
        sql += "'" + student.getName() + "',";
        sql += "'" + student.getPass() + "',";
        sql += "'" + student.getSex() + "')";
        stmt.executeUpdate(sql);
    }

    //t_gradeにデータをinsert
    public void insertGrade(Students student) throws SQLException {
        String sql = "insert into t_grade(studentid,aclass,kokugo,math,english,science,history) values(";
        sql += student.getId() + ",";
        sql += "'" + student.getClasss() + "',";
        sql += "'" + student.getKokugo() + "',";
        sql += "'" + student.getMath() + "',";
        sql += "'" + student.getEnglish() + "',";
        sql += "'" + student.getScience() + "',";
        sql += "'" + student.getHistory() + "')";
        stmt.executeUpdate(sql);
    }

    //t_studentsのデータを更新
    public void UpdateStudent(Students student) throws SQLException {
        String sql = "update t_students set ";
        sql += "aclass =" + "'" + student.getClasss() + "',";
        sql += "name =" + "'" + student.getName() + "',";
        sql += "pass =" + "'" + student.getPass() + "',";
        sql += "sex =" + "'" + student.getSex() + "'";
        sql += " where studentid =" + student.getId();
        stmt.executeUpdate(sql);
    }

    //t_gradeのデータを更新
    public void UpdateGrade(Students student) throws SQLException {
        String sql = "update t_grade set ";
        sql += "aclass ='" + student.getClasss() + "',";
        sql += "kokugo ='" + student.getKokugo() + "',";
        sql += "math ='" + student.getMath() + "',";
        sql += "english ='" + student.getEnglish() + "',";
        sql += "science ='" + student.getScience() + "',";
        sql += "history ='" + student.getHistory() + "'";
        sql += " where studentid =" + student.getId();
        stmt.executeUpdate(sql);
    }

    //student情報を削除
    public void deleteStudent(Students student) throws SQLException {
        String sql = "delete from t_students where studentid = " + student.getId();
        stmt.executeUpdate(sql);
    }

    //成績を削除
    public void deleteGrade(Students student) throws SQLException {
        String sql = "delete from t_grade where studentid = " + student.getId();
        stmt.executeUpdate(sql);
    }

    //DBから先生のデータを表に表示
    public List<Teachers> getTeachersDate() throws SQLException {
        List<Teachers> list = new ArrayList<>();

        String sql = "select * from t_Teachers";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Teachers teacher = new Teachers();
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setPass(rs.getString(3));
                teacher.setAclass(rs.getString(4));
                teacher.setSubject(rs.getString(5));
                teacher.setSex(rs.getString(6));

                list.add(teacher);
            }
        }
        return list;
    }

    //t_teachersに先生のデータをinsert
    public void insertTeachers(Teachers teacher) throws SQLException {
        String sql = "insert into t_teachers (id,name,password,aclass,subject,sex)";
        sql += "values(" + teacher.getId() + ",'";
        sql += teacher.getName() + "','";
        sql += teacher.getPass() + "','";
        sql += teacher.getAclass() + "','";
        sql += teacher.getSubject() + "','";
        sql += teacher.getSex() + "')";

        stmt.executeUpdate(sql);
    }

    //t_teachersのデータを更新/編集
    public void updateTeacher(Teachers teacher) throws SQLException {
        String sql = "update t_teachers set ";
        sql += "name= '" + teacher.getName() + "',";
        sql += "password= '" + teacher.getPass() + "',";
        sql += "aclass= '" + teacher.getAclass() + "',";
        sql += "subject= '" + teacher.getSubject() + "',";
        sql += "sex= '" + teacher.getSex() + "'";
        sql += " where id=" + teacher.getId();
        stmt.executeUpdate(sql);
    }

    //先生データを削除
    public void deleteTeacher(Teachers teacher) throws SQLException {
        String sql = "delete from t_teachers where id = " + teacher.getId();
        stmt.executeUpdate(sql);
    }

    //先生のIDが重複するかを調べる
    public boolean getTeacherID(int id) throws SQLException {
        String sql = "select id from t_teachers where id=" + id;
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                return true;
            }
        }
        return false;
    }

    //t_studentにデータをinsert(rootのstudent)
    public void insertStudent(Student student) throws SQLException {

        String sql = "insert into t_students(studentid,aclass,name,pass,sex) values(";
        sql += student.getId() + ",";
        sql += "'" + student.getClasss() + "',";
        sql += "'" + student.getName() + "',";
        sql += "'" + student.getPass() + "',";
        sql += "'" + student.getSex() + "')";
        stmt.executeUpdate(sql);
    }

    //t_gradeにデータをinsert(rootのstudent)
    public void insertGrade(Student student) throws SQLException {
        String sql = "insert into t_grade(studentid,aclass,kokugo,math,english,science,history) values(";
        sql += student.getId() + ",";
        sql += "'" + student.getClasss() + "',";
        sql += "'" + student.getKokugo() + "',";
        sql += "'" + student.getMath() + "',";
        sql += "'" + student.getEnglish() + "',";
        sql += "'" + student.getScience() + "',";
        sql += "'" + student.getHistory() + "')";
        stmt.executeUpdate(sql);
    }

    //t_studentsのデータを更新(rootのstudent)
    public void UpdateStudent(Student student) throws SQLException {
        String sql = "update t_students set ";
        sql += "aclass =" + "'" + student.getClasss() + "',";
        sql += "name =" + "'" + student.getName() + "',";
        sql += "pass=" + "'" + student.getPass() + "',";
        sql += "sex =" + "'" + student.getSex() + "'";
        sql += " where studentid =" + student.getId();
        stmt.executeUpdate(sql);
    }

    //t_gradeのデータを更新(rootのstudent)
    public void UpdateGrade(Student student) throws SQLException {
        String sql = "update t_grade set ";
        sql += "aclass =" + "'" + student.getClasss() + "',";
        sql += "kokugo =" + "'" + student.getKokugo() + "',";
        sql += "math =" + "'" + student.getMath() + "',";
        sql += "english =" + "'" + student.getEnglish() + "',";
        sql += "science =" + "'" + student.getScience() + "',";
        sql += "history =" + "'" + student.getHistory() + "'";
        sql += " where studentid =" + student.getId();
        stmt.executeUpdate(sql);
    }

    //TeacherDateの表にDBの生徒データを表示（DBからデータをとる）
    public List<Student> getStudentDate() throws SQLException {
        List<Student> list = new ArrayList<>();
        //这样写防止对象覆盖
        Student students = null;

        String sql = "select a.studentid,a.aclass,a.name,a.pass,a.sex,b.kokugo,b.math,b.english,b.science,b.history,";

        //合計sql
        sql += "(kokugo+math+english+science+history) ";
        sql += "from t_students a left join t_grade b on a.studentid = b.studentid";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                //这样写防止对象覆盖
                students = new Student();
                students.setId(Integer.parseInt(rs.getString(1)));
                students.setClasss(rs.getString(2));
                students.setName(rs.getString(3));
                students.setPass(rs.getString(4));
                students.setSex(rs.getString(5));
                students.setKokugo(rs.getFloat(6));
                students.setMath(rs.getFloat(7));
                students.setEnglish(rs.getFloat(8));
                students.setScience(rs.getFloat(9));
                students.setHistory(rs.getFloat(10));
                students.setSum(rs.getInt(11));
                list.add(students);

            }
        }
        return list;

    }

    //重複するパスワードを判断する(先生)
    public boolean checkPass(String teacherPass) throws SQLException {
        String sql = "select password from t_teachers where password=" + "'" + teacherPass + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                return true;
            }
        }
        return false;
    }

    ////重複するパスワードを判断する(生徒)
    public boolean checkPasss(String studentPass) throws SQLException {
        String sql = "select password from t_teachers where password=" + "'" + studentPass + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                return true;
            }
        }
        return false;
    }

    //root画面から学生情報を消す
    public void deleteStudent(Student student) throws SQLException {
        String sql = "delete from t_students where studentid = " + student.getId();
        stmt.executeUpdate(sql);
    }

    //root画面から成績を削除
    public void deleteGrade(Student student) throws SQLException {
        String sql = "delete from t_grade where studentid = " + student.getId();
        stmt.executeUpdate(sql);
    }

    //先生のクラス別に生徒表示する
    public List<Student> classDate(String aclass) throws SQLException {

        List<Student> list = new ArrayList<>();
        Student student = null;

        String sql = "select a.studentid,a.aclass,a.name,a.pass,a.sex,b.kokugo,b.math,b.english,b.science,b.history,";
        //合計sql
        sql += "(kokugo+math+english+science+history) ";
        sql += "from t_students a left join t_grade b on a.studentid = b.studentid where a.aclass ='" + aclass + "'and b.aclass = '" + aclass + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                student = new Student();
                student.setId(Integer.parseInt(rs.getString(1)));
                student.setClasss(rs.getString(2));
                student.setName(rs.getString(3));
                student.setPass(rs.getString(4));
                student.setSex(rs.getString(5));
                student.setKokugo(rs.getFloat(6));
                student.setMath(rs.getFloat(7));
                student.setEnglish(rs.getFloat(8));
                student.setScience(rs.getFloat(9));
                student.setHistory(rs.getFloat(10));
                student.setSum(rs.getInt(11));

                list.add(student);

            }

        }
        return list;

    }

    //初期IDとパスワードをDBにinsert(root-teacher)
    public void insertUser(Teachers teacher) throws SQLException {
        String sql = "insert into t_user (id,pass) values ('" + teacher.getId() + "','" + teacher.getPass() + "')";

        stmt.executeUpdate(sql);

    }

    //パスワードを変更(先生)
    public void updateUser(Teachers teacher) throws SQLException {

        String sql = "update t_user set pass ='" + teacher.getPass() + "'where id ='" + teacher.getId() + "'";

        stmt.executeUpdate(sql);

    }

    //パスワード消す(先生)
    public void deteleUser(Teachers teacher) throws SQLException {

        String sql = "delete from t_user where id = '" + teacher.getId() + "'";

        stmt.executeUpdate(sql);
    }

    //初期IDとパスワードをDBにinsert(root-学生)
    public void insertUser(Student student) throws SQLException {
        String sql = "insert into t_user (id,pass) values ('" + student.getId() + "','" + student.getPass() + "')";

        stmt.executeUpdate(sql);

    }

    //パスワードを変更(root-学生)
    public void updateUser(Student student) throws SQLException {

        String sql = "update t_user set pass ='" + student.getPass() + "'where id ='" + student.getId() + "'";

        stmt.executeUpdate(sql);

    }

    //パスワード消す(root-生徒)
    public void deteleUser(Student student) throws SQLException {

        String sql = "delete from t_user where id = '" + student.getId() + "'";

        stmt.executeUpdate(sql);
    }

    //パスワード消す(co.jp.tihtih.teacher-生徒)
    public void deteleUser(Students student) throws SQLException {

        String sql = "delete from t_user where id = '" + student.getId() + "'";

        stmt.executeUpdate(sql);
    }

    //初期IDとパスワードをDBにinsert(co.jp.tihtih.teacher - student)
    public void insertUser(Students student) throws SQLException {
        String sql = "insert into t_user (id,pass) values ('" + student.getId() + "','" + student.getPass() + "')";

        stmt.executeUpdate(sql);

    }

    //パスワードを変更(co.jp.tihtih.teacher-学生)
    public void updateUser(Students student) throws SQLException {

        String sql = "update t_user set pass ='" + student.getPass() + "'where id ='" + student.getId() + "'";

        stmt.executeUpdate(sql);

    }

    //login画面関連
    //DB-t_userからIDとpassを取得
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

    //loginから得たIDとパスワードでDBのteacherのデータを引っ張り出す
    public Teachers getTeacher(String teacherId) throws SQLException {

        Teachers teacher = null;
        String sql = "select * from t_teachers where id=" + Integer.parseInt(teacherId);
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {

                teacher = new Teachers();
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setPass(rs.getString(3));
                teacher.setAclass(rs.getString(4));
                teacher.setSubject(rs.getString(5));
                teacher.setSex(rs.getString(6));

            }
        }
        return teacher;
    }

    //先生のクラス別に生徒表示する(生徒管理画面)
    public List<Students> classDates(Teachers teacher) throws SQLException {

        List<Students> list = new ArrayList<>();
        Students student = null;

        String sql = "select a.studentid,a.aclass,a.name,a.pass,a.sex,b.kokugo,b.math,b.english,b.science,b.history,";
        //合計sql
        sql += "(kokugo+math+english+science+history) ";
        sql += "from t_students a left join t_grade b on a.studentid = b.studentid where a.aclass ='" + teacher.getAclass() + "'and b.aclass = '" + teacher.getAclass() + "'";
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                student = new Students();
                student.setId(Integer.parseInt(rs.getString(1)));
                student.setClasss(rs.getString(2));
                student.setName(rs.getString(3));
                student.setPass(rs.getString(4));
                student.setSex(rs.getString(5));
                student.setKokugo(rs.getFloat(6));
                student.setMath(rs.getFloat(7));
                student.setEnglish(rs.getFloat(8));
                student.setScience(rs.getFloat(9));
                student.setHistory(rs.getFloat(10));
                student.setSum(rs.getInt(11));

                list.add(student);

            }

        }
        return list;
    }

    //学生ログイン画面のデータ
    public List<Students> getStudentDate(String studentid) throws SQLException {

        List<Students> list = new ArrayList<>();
        Students students = null;

        String sql = "select a.studentid,a.aclass,a.name,a.pass,a.sex,b.kokugo,b.math,b.english,b.science,b.history,";

        //合計sql
        sql += "(kokugo+math+english+science+history) ";
        sql += "from t_students a left join t_grade b on a.aclass = b.aclass where a.studentid = " + Integer.parseInt(studentid) + " and b.studentid =" + Integer.parseInt(studentid);
        rs = stmt.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {

                students = new Students();
                students.setId(Integer.parseInt(rs.getString(1)));
                students.setClasss(rs.getString(2));
                students.setName(rs.getString(3));
                students.setPass(rs.getString(4));
                students.setSex(rs.getString(5));
                students.setKokugo(rs.getFloat(6));
                students.setMath(rs.getFloat(7));
                students.setEnglish(rs.getFloat(8));
                students.setScience(rs.getFloat(9));
                students.setHistory(rs.getFloat(10));
                students.setSum(rs.getInt(11));

                list.add(students);
            }
        }
        return list;

    }

    //検索機能
    //管理画面の先生のデータを検索
    public List<Teachers> searchTeacher(int teacherId) throws SQLException {
        List<Teachers> list = new ArrayList<>();
        Teachers teacher = null;

        String sql = "select * from t_teachers where id between " + teacherId + " and 999";
        rs = stmt.executeQuery(sql);

        if (rs != null) {
            while (rs.next()) {
                teacher = new Teachers();
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
                teacher.setPass(rs.getString(3));
                teacher.setAclass(rs.getString(4));
                teacher.setSubject(rs.getString(5));
                teacher.setSex(rs.getString(6));
                list.add(teacher);

            }
        }
        return list;
    }

}
