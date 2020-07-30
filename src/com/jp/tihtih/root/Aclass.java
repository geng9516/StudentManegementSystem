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

    private String teacherId;
    private String className;
    private String subject;

    public Aclass() {
    }

    public Aclass(String teacherId, String className, String subject) {
        this.teacherId = teacherId;
        this.className = className;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Aclass{" + "teacherId=" + teacherId + ", className=" + className + ", subject=" + subject + '}';
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.teacherId);
        hash = 83 * hash + Objects.hashCode(this.className);
        hash = 83 * hash + Objects.hashCode(this.subject);
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
        if (!Objects.equals(this.teacherId, other.teacherId)) {
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
}
