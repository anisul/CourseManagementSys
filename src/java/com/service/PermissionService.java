/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.HibernateUtil;
import com.model.Users;
import java.util.List;
import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Tariq
 */
public class PermissionService {
    public String getPermission(){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=user.getUsername();
        Users users=new Users();
        try{
            List list=session.createQuery("from Users where UserName='"+username+"'").list();
            if(list.isEmpty())return null;
            users=(Users)list.get(0);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return users.getMark();
    }
}
