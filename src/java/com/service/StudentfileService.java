/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.HibernateUtil;
import com.model.Studentfile;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Tariq
 */
public class StudentfileService {
    private Studentfile studentFile;

    public Studentfile getStudentFile() {
        return studentFile;
    }

    public void setStudentFile(Studentfile studentFile) {
        this.studentFile = studentFile;
    }
    
    public Studentfile getStudentFile(String username) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        this.studentFile=new Studentfile();
        try{
            String str="from Studentfile where UserName='"+username+"'";
            List list=session.createQuery(str).list();
            this.studentFile=(Studentfile)list.get(0);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return this.studentFile;
    }
    
}
