/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.HibernateUtil;
import com.model.RegistrationData;
import com.model.Studentfile;
import com.model.Users;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tariq
 */
public class StudentRegistrationService {
    private StudentfileService studentFileService;

    public StudentfileService getStudentFileService() {
        return studentFileService;
    }

    public void setStudentFileService(StudentfileService studentFileService) {
        this.studentFileService = studentFileService;
    }
    public  StudentRegistrationService(RegistrationData registrationData,Studentfile sf,HttpServletRequest request) throws IOException{
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
      
            try{
                
                session.beginTransaction();
                MultipartFile file=sf.getFileData();
                if(!file.getOriginalFilename().equals("")){
                    UploadService uploadService = new UploadService();
                    uploadService.uploadFile(sf.getFileData(), request, registrationData.getUsername());
                
                    sf.setFileName(file.getOriginalFilename());
                    sf.setPicture(request.getRealPath("")+"\\Images\\"+registrationData.getUsername()+file.getOriginalFilename());
                    sf.setFileSize(file.getSize());
                    sf.setFileType(file.getContentType());
                }
                else{
                    sf.setFileName("");
                    sf.setPicture("");
                    sf.setFileSize(0L);
                    sf.setFileType("");
            
                }
                sf.setUserName(registrationData.getUsername());
                sf.setPassword(registrationData.getPassword());
                sf.setStudentId(registrationData.getStudentid());
                session.save(sf);
                this.studentFileService=new StudentfileService();
                this.studentFileService.setStudentFile(sf);
            }catch(Exception e){
                e.printStackTrace();
            }
            
            try{
               
               Users users=new Users();
               users.setEnabled(1);
               users.setMark("Student");
               users.setPassword(registrationData.getPassword());
               users.setUsername(registrationData.getUsername());
               users.setRole("ROLE_USER");
               session.save(users);
               session.getTransaction().commit();
            }catch(Exception e){
                e.printStackTrace();
            }
        
       
    }
}
