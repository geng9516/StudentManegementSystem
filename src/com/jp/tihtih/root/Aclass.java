/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.tihtih.root;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Aclass {

    private int teacherId;
    private String className;
    private String subject;

    public Aclass() {
    }

    public Aclass(int teacherId, String className, String subject) {
        this.teacherId = teacherId;
        this.className = className;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Aclass{" + "teacherId=" + teacherId + ", className=" + className + ", subject=" + subject + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.teacherId;
        hash = 73 * hash + Objects.hashCode(this.className);
        hash = 73 * hash + Objects.hashCode(this.subject);
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
        final Aclass other = (Aclass) obj;
        if (this.teacherId != other.teacherId) {
            return false;
        }
        if (!Objects.equals(this.className, other.className)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        return true;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
