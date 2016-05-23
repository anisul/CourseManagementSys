/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Tariq
 */
@Controller
public class AdminController {
    @RequestMapping(value="/admin/home.htm", method=RequestMethod.GET)
    public String showHome(ModelMap map){
        return "adminhome";
    }
    
    @RequestMapping(value="/admin/teacherverify.htm", method=RequestMethod.GET)
    public String showTeacherVerify(ModelMap map){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        try{
            List list=session.createQuery("from Teacherauthentication").list();
            map.addAttribute("list", list);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return "teacherverify";
    }
}
