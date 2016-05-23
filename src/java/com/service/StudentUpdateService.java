/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.HibernateUtil;
import com.model.Studentfile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tariq
 */
public class StudentUpdateService {
    public StudentfileService updateStudentInfo(StudentfileService studentFileService,HttpServletRequest request) throws IOException{
       Studentfile sf=studentFileService.getStudentFile();
       
       MultipartFile file=sf.getFileData();
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
           String location=temp+"\\\\Images\\\\"+sf.getUserName()+sf.getFileData().getOriginalFilename();
           filesql=", FileSize='"+file.getSize()+"', FileType='"+file.getContentType()+"', FileName='"+file.getOriginalFilename()+"', Picture='"+location+"'";
           File f=new File(sf.getPicture());
           
           System.out.println(f.delete());
           System.out.println(sf.getPicture());
       
           UploadService uploadService=new UploadService();
           uploadService.uploadFile(sf.getFileData(), request, sf.getUserName());
       
       }
       
       
       
       Session session=HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       try{
           String str="UPDATE studentfile SET Name='"+sf.getName()+"', Room='"+sf.getRoom()+"', Address='"+sf.getAddress()+"', Telephone='"+sf.getTelephone()+"', Email='"+sf.getEmail()+"'"+filesql+" WHERE Student_ID='"+sf.getStudentId()+"'";
                  
           Query query=session.createSQLQuery(str);
           query.executeUpdate();
           session.getTransaction().commit();
           
           session=HibernateUtil.getSessionFactory().getCurrentSession();
           session.beginTransaction();
           List list=session.createQuery("from Studentfile where Student_ID='"+sf.getStudentId()+"'").list();
           sf=(Studentfile)list.get(0);
           studentFileService.setStudentFile(sf);
           session.getTransaction().commit();
       }catch(Exception e){
           e.printStackTrace();
       }
       return studentFileService;
    }
}
