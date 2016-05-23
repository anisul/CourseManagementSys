/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tariq
 */
public class TeacherService {
    TeacherfileService teacherFileService;

    public TeacherfileService getTeacherFileService() {
        return teacherFileService;
    }

    
    public void setTeacherFileService(TeacherfileService teacherFileService) {
        this.teacherFileService = teacherFileService;
    }
    public void generatePin(RegistrationService registrationService){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Users users=new Users();
        users.setUsername(registrationService.getRegistrationData().getUsername());
        users.setPassword(registrationService.getRegistrationData().getPassword());
        users.setMark("Teacher");
        users.setEnabled(0);
        users.setRole("ROLE_USER");
        session.save(users);
        
        Teacherauthentication ta=new Teacherauthentication();
        String str="";
        for(int i=0;i<6;i++){
            Random rand=new Random();
            Integer num=rand.nextInt(9);
            str=str+num.toString();
        }
        
        ta.setUserName(registrationService.getRegistrationData().getUsername());
        ta.setPassword(registrationService.getRegistrationData().getPassword());
        ta.setPin(str);
        session.save(ta);
        session.getTransaction().commit();
    }
    
    public boolean teacherValidation(Teacherauthentication ta){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        List list=session.createQuery("from Teacherauthentication where UserName='"+ta.getUserName()+"'").list();
        if(list.isEmpty())return false;
        Teacherauthentication temp=(Teacherauthentication)list.get(0);
        session.getTransaction().commit();
        if(temp.getPin().equals(ta.getPin())){
            session=HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Query query=session.createSQLQuery("DELETE FROM teacherauthentication WHERE UserName='"+ta.getUserName()+"'");
            query.executeUpdate();
            
            query=session.createSQLQuery("UPDATE users SET enabled='1' WHERE username='"+ta.getUserName()+"'");
            query.executeUpdate();
            
            session.getTransaction().commit();
            return true;
        }
        else return false;
        
    }
    
    public void teacherRegistration(Teacherfile tf,RegistrationData registrationData,HttpServletRequest request){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
         session.beginTransaction();
            try{
                
               
                MultipartFile file=tf.getFileData();
                if(!file.getOriginalFilename().equals("")){
                    UploadService uploadService = new UploadService();
                    uploadService.uploadFile(tf.getFileData(), request,registrationData.getUsername());
                
                    tf.setFileName(file.getOriginalFilename());
                    tf.setPicture(request.getRealPath("")+"\\Images\\"+registrationData.getUsername()+file.getOriginalFilename());
                    tf.setFileSize(file.getSize());
                    tf.setFileType(file.getContentType());
                }
                else{
                    tf.setFileName("");
                    tf.setPicture("");
                    tf.setFileSize(0L);
                    tf.setFileType("");
            
                }
               
               
                
                tf.setUserName(registrationData.getUsername());
                tf.setPassword(registrationData.getPassword());
                
                System.out.println(tf.getUserName());
                System.out.println(tf.getPassword());
                System.out.println(tf.getName());
                System.out.println(tf.getAddress());
                System.out.println(tf.getDepartment());
                System.out.println(tf.getDesignation());
                System.out.println(tf.getEmail());
                System.out.println(tf.getFileName());
                System.out.println(tf.getFileSize());
                System.out.println(tf.getFileType());
                System.out.println(tf.getOffice());
                System.out.println(tf.getPicture());
                System.out.println(tf.getTelephone());
                
                
                session.save(tf);
                
             
               
                
                this.teacherFileService=new TeacherfileService();
                this.teacherFileService.setTeacherFile(tf);
            }catch(Exception e){
                e.printStackTrace();
            }
            session.getTransaction().commit();
    }
    
    
    public TeacherfileService teacherUpdateService(TeacherfileService teacherFileService, HttpServletRequest request) throws IOException{
        //Studentfile sf=studentFileService.getStudentFile();
        Teacherfile tf=teacherFileService.getTeacherFile();
        
        MultipartFile file=tf.getFileData();
        String filesql="";
       
        if(!file.getOriginalFilename().equals("")){
           String temp="";
           for(int i=0;i<request.getRealPath("").length();i++){
               char c=request.getRealPath("").charAt(i);
                if(c=='\\') {
                    temp=temp+'\\';
                }
                temp=temp+c;
           }
           String location=temp+"\\\\Images\\\\"+tf.getUserName()+tf.getFileData().getOriginalFilename();
           filesql=", FileSize='"+file.getSize()+"', FileType='"+file.getContentType()+"', FileName='"+file.getOriginalFilename()+"', Picture='"+location+"'";
           File f=new File(tf.getPicture());
           
           System.out.println(f.delete());
           
       
           UploadService uploadService=new UploadService();
           uploadService.uploadFile(tf.getFileData(), request, tf.getUserName());
       
       }
       
       
       
       Session session=HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       try{
           String str="UPDATE teacherfile SET Name='"+tf.getName()+"', Office='"+tf.getOffice()+"', Address='"+tf.getAddress()+"', Telephone='"+tf.getTelephone()+"', Email='"+tf.getEmail()+"', Designation='"+tf.getDesignation()+"'"+filesql+" WHERE UserName='"+tf.getUserName()+"'";
                  
           Query query=session.createSQLQuery(str);
           query.executeUpdate();
           session.getTransaction().commit();
           
           session=HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           List list=session.createQuery("from Teacherfile where UserName='"+tf.getUserName()+"'").list();
           tf=(Teacherfile)list.get(0);
           teacherFileService.setTeacherFile(tf);
           session.getTransaction().commit();
       }catch(Exception e){
           e.printStackTrace();
       }
       return teacherFileService;
    }
    
    
    
}
