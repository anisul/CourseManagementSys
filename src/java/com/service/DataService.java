/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Tariq
 */
public class DataService {
    public int regUser(RegistrationData rd){
        if("".equals(rd.getUsername())||"".equals(rd.getPassword())||"".equals(rd.getRepassword())||("".equals(rd.getStudentid())&&rd.getMark().equals("Student")))return 0;
        if(!rd.getPassword().equals(rd.getRepassword()))
            return 1;
        
        
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        
        
        try{
            String str;
            str="from Users";
            session.beginTransaction();
            List list=session.createQuery(str).list();
            
            Users users;
            for(int i=0;i<list.size();i++){
                users=(Users)list.get(i);
                if(users.getUsername().equals(rd.getUsername())){
                    
                    session.getTransaction().commit();
                    return  2;
                }
            }
            if("Student".equals(rd.getMark())){
                str="from Studentfile";
                list.clear();
                list=session.createQuery(str).list();
                Studentfile sf=new  Studentfile();
                for(int i=0;i<list.size();i++){
                    sf=(Studentfile)list.get(i);
                    
                    
                    if(sf.getStudentId().equals(rd.getStudentid())){
                        session.getTransaction().commit();
                        return 3;
                    }
                }
                
                
            }
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            
        }
        return 4; 
      
    }
    
    
    
    public int matchPassword(PasswordData pd){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        if(!pd.getRepassword().equals(pd.getPassword()))return 1;
        try{
            List list=session.createQuery("from Users where username='"+pd.getUsername()+"'").list();
            if(list.isEmpty())return 2;
            Users users=(Users)list.get(0);
            if(!users.getPassword().equals(pd.getOldPassword()))return 2;
        }catch(Exception e){
            e.printStackTrace();
        }
        session.getTransaction().commit();
        session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String str="UPDATE users SET password='"+pd.getPassword()+"' WHERE username='"+pd.getUsername()+"'";
        Query query=session.createSQLQuery(str);
        query.executeUpdate();
        
        str="UPDATE studentfile SET password='"+pd.getPassword()+"' WHERE username='"+pd.getUsername()+"'";
        query=session.createSQLQuery(str);
        query.executeUpdate();
        
        session.getTransaction().commit();
        return 3;
        
    }
    
}
