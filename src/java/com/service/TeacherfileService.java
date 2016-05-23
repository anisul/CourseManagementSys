/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.HibernateUtil;
import com.model.Studentfile;
import com.model.Teacherfile;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Tariq
 */
public class TeacherfileService {
    
    Teacherfile teacherFile;

    public Teacherfile getTeacherFile() {
        return teacherFile;
    }

    public Teacherfile getTeacherFile(String userName) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        this.teacherFile=new Teacherfile();
        try{
            String str="from Teacherfile where UserName='"+userName+"'";
            List list=session.createQuery(str).list();
            this.teacherFile=(Teacherfile)list.get(0);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return this.teacherFile;
    }
    public void setTeacherFile(Teacherfile teacherFile) {
        this.teacherFile = teacherFile;
    }
    

}
