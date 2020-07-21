/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.jp.tihtih.root;

import java.util.Objects;

/**
 *
 * @author geng9516
 */
public class Teachers {

    private int id;
    private String name;
    private String pass;
    private String aclass;
    private String Subject;
    private String sex;
    

    public Teachers() {
    }

    public Teachers(int id, String name, String pass, String aclass, String Subject, String sex) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.aclass = aclass;
        this.Subject = Subject;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Teachers{" + "id=" + id + ", name=" + name + ", pass=" + pass + ", aclass=" + aclass + ", Subject=" + Subject + ", sex=" + sex + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.pass);
        hash = 67 * hash + Objects.hashCode(this.aclass);
        hash = 67 * hash + Objects.hashCode(this.Subject);
        hash = 67 * hash + Objects.hashCode(this.sex);
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
        final Teachers other = (Teachers) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.aclass, other.aclass)) {
            return false;
        }
        if (!Objects.equals(this.Subject, other.Subject)) {
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

    public String getAclass() {
        return aclass;
    }

    public void setAclass(String aclass) {
        this.aclass = aclass;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    }
