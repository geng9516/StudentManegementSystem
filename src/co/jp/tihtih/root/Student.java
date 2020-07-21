/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.jp.tihtih.root;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Student {

    private int id;
    private String classs;
    private String name;
    private String pass;
    private String sex;
    private double kokugo;
    private double math;
    private double english;
    private double science;
    private double history;
    private double sum;

    public Student() {
    }

    public Student(int id, String classs, String name, String pass, String sex, double kokugo, double math, double english, double science, double history, double sum) {
        this.id = id;
        this.classs = classs;
        this.name = name;
        this.pass = pass;
        this.sex = sex;
        this.kokugo = kokugo;
        this.math = math;
        this.english = english;
        this.science = science;
        this.history = history;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", classs=" + classs + ", name=" + name + ", pass=" + pass + ", sex=" + sex + ", kokugo=" + kokugo + ", math=" + math + ", english=" + english + ", science=" + science + ", history=" + history + ", sum=" + sum + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.classs);
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.pass);
        hash = 43 * hash + Objects.hashCode(this.sex);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.kokugo) ^ (Double.doubleToLongBits(this.kokugo) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.math) ^ (Double.doubleToLongBits(this.math) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.english) ^ (Double.doubleToLongBits(this.english) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.science) ^ (Double.doubleToLongBits(this.science) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.history) ^ (Double.doubleToLongBits(this.history) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.sum) ^ (Double.doubleToLongBits(this.sum) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.kokugo) != Double.doubleToLongBits(other.kokugo)) {
            return false;
        }
        if (Double.doubleToLongBits(this.math) != Double.doubleToLongBits(other.math)) {
            return false;
        }
        if (Double.doubleToLongBits(this.english) != Double.doubleToLongBits(other.english)) {
            return false;
        }
        if (Double.doubleToLongBits(this.science) != Double.doubleToLongBits(other.science)) {
            return false;
        }
        if (Double.doubleToLongBits(this.history) != Double.doubleToLongBits(other.history)) {
            return false;
        }
        if (Double.doubleToLongBits(this.sum) != Double.doubleToLongBits(other.sum)) {
            return false;
        }
        if (!Objects.equals(this.classs, other.classs)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.sex, other.sex)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getKokugo() {
        return kokugo;
    }

    public void setKokugo(double kokugo) {
        this.kokugo = kokugo;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.english = english;
    }

    public double getScience() {
        return science;
    }

    public void setScience(double science) {
        this.science = science;
    }

    public double getHistory() {
        return history;
    }

    public void setHistory(double history) {
        this.history = history;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}
