/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Tariq
 */
public class UploadService {
    public void uploadFile(CommonsMultipartFile fileData,HttpServletRequest request,String username) throws IOException{
        
        InputStream input=null;
        OutputStream output=null;
        try{
            
            MultipartFile file=fileData;
           
            if(!file.getOriginalFilename().equals("")){
            input=file.getInputStream();
            long size=file.getSize();
             String type=file.getContentType();
                    String name=file.getOriginalFilename();
            
                    byte b[]=new byte[(int)size+1];
                    int i=0;
                    int temp=0;
                    while((temp=input.read())!=-1){
                        b[i]=(byte)temp;
                        i++;
                    }
            
                    output=new FileOutputStream(request.getRealPath("")+"\\Images\\"+username+name);
                    for(int j=0;j<i;j++){
                        output.write((int)b[j]);
                    }
            
            }
        
        
        }catch(Exception e){
                e.printStackTrace();
        }finally{
            if(input!=null)input.close();
            if(output!=null)output.close();
        }    
    }
}
