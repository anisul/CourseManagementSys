/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.HibernateUtil;
import com.model.Users;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Tariq
 */
public class LoginService {
    
    public String getMark(String username){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Users users;
        List list=session.createQuery("from Users where username='"+username+"'").list();
        if(list.isEmpty())return "Admin";
        users=(Users)list.get(0);
        return users.getMark();
        
    }


}
