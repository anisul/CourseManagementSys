/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;


import com.model.*;
import com.service.*;
import java.io.*;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Tariq
 */
@Controller

public class DefaultController {
    
    @Resource(name="registrationService")
    RegistrationService registrationService;
    
    @Resource(name="teacherFileService")
    TeacherfileService teacherFileService;
    
    
    @Resource(name="studentFileService")
    StudentfileService studentFileService;
    
    @Resource(name="messageService")
    MessageService messageService;
    
    
    
   
     
     
     @RequestMapping(value="/index.htm",method=RequestMethod.GET)
    public String showIndex(ModelMap map){
        return "index";
        
    }
    
    @RequestMapping(value="/login.htm",method=RequestMethod.GET)
    public String getLogin(ModelMap map){
        
        return "login";
        
    }
    
     
    
    @RequestMapping(value="/loginfailed.htm", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
    
    @RequestMapping(value="/logout.htm", method = RequestMethod.GET)
    public String logout(ModelMap model) {
 
        return "login";
    }
    
    @RequestMapping(value="/interim.htm",method=RequestMethod.GET)
    public String getInterim(ModelMap map){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=user.getUsername();
        LoginService loginService=new LoginService();
        String str=loginService.getMark(username);
        
        
            
        if(str.equals("Student")){
            
           
            this.studentFileService.setStudentFile(this.studentFileService.getStudentFile(username));
            return "redirect:student/home.htm";
        }
        else if(str.equals("Teacher")){
            this.teacherFileService.setTeacherFile(this.teacherFileService.getTeacherFile(username));
            return "redirect:teacher/home.htm";
        }
        else if(str.equals("Admin"))return "redirect:admin/home.htm";
        else return null;
        
    }
	
    
    
    @ModelAttribute("Message")
    public String showMessage(){
        
        return this.messageService.getMessage();
     }
    
    @RequestMapping(value="/registration.htm", method=RequestMethod.GET)
    public String showRegistration(ModelMap map){
        RegistrationData rd=new RegistrationData();
        map.addAttribute("reg",rd);
        
        return "registration";
     
    }
    
   
    
    @RequestMapping(value="/registration.htm", method=RequestMethod.POST)
    public String postRegistration(@ModelAttribute(value="reg")RegistrationData reg,BindingResult result,ModelMap map){
        
        DataService ds=new DataService();
        int flag=ds.regUser(reg);
  
        String message=null;
        if(flag==0){
            message="Empty Field Error";
            map.addAttribute("message",message);
            return null;        
        }
        
        else  if(flag==1){
            message="Password Missmatch Error";
            map.addAttribute("message",message);
             return null;
            }
        
        else if(flag==2){
            message="Change Your Username";
            map.addAttribute("message",message);
            return null;
       }
        
        else if(flag==3){
            message="Change Your Student ID";
            map.addAttribute("message",message);
            return null;
        }
        else{
            if(reg.getMark().equals("Student")){
                
                this.registrationService.setRegistrationData(reg);
                return "redirect:student.htm" ;
            }else if(reg.getMark().equals("Teacher")){
                this.registrationService.setRegistrationData(reg);
                return "redirect:teacherpin.htm" ;
            }
            else return  null;
       }
    }
    
   
    @ModelAttribute("registrationData")
    public RegistrationData getregData(){
        return this.registrationService.getRegistrationData();
    }
    
   
    @RequestMapping(value="/student.htm", method=RequestMethod.GET)
    public String showStudent(ModelMap map){
        if(this.registrationService.getRegistrationData().getUsername().equals(""))return "redirect:registration.htm";
        Studentfile sf=new Studentfile();
        map.addAttribute("sf", sf);
        return "newstudent";
        
    }
    
    
    @RequestMapping(value="/student.htm", method=RequestMethod.POST)
    public String getStudent(@ModelAttribute(value="sf")Studentfile sf,@ModelAttribute(value="registrationData")RegistrationData registrationData, BindingResult result,HttpServletRequest request,ModelMap map) throws IOException{
        if(result.hasErrors())
            return "newstudent";
        if(sf.getStudentId().equals("")){
            map.addAttribute("Message","Invalid ID");
            return null;
        
        }
        StudentRegistrationService studentRegistrationService=new StudentRegistrationService(registrationData,sf,request);        
        this.studentFileService=studentRegistrationService.getStudentFileService();
        
        
        
        return "redirect:student/home.htm";
    } 
    
    
     
     
     @ModelAttribute("studentData")
     public Studentfile getStudentfileData(){
         return this.studentFileService.getStudentFile();
     }
     
     
     @RequestMapping(value="/student/home.htm",method=RequestMethod.GET)
     public String showHome(){
         PermissionService ps=new PermissionService();
         if(ps.getPermission().equals("Teacher")){
             SecurityContextHolder.clearContext();
             return "error";
         }
        if(this.registrationService.getRegistrationData()!=null){
            
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username=user.getUsername();
            this.studentFileService.setStudentFile(this.studentFileService.getStudentFile(username));
        }
         
        
        
         return "studenthome";
     }
     
     
     @RequestMapping(value="/student/home.htm",method=RequestMethod.POST)
     public String postHome(HttpServletRequest request,ModelMap model){
         String mark=request.getParameter("mark");
         String search=request.getParameter("serarchtext");
         Session session=HibernateUtil.getSessionFactory().getCurrentSession();
         session.beginTransaction();
         try{
             String term=null;
             if(mark.equals("Student")){
                 term=request.getParameter("checkBox");
                 List list=session.createQuery("from "+mark+"file where "+term+"='"+search+"'").list();
                 model.addAttribute("students",list);
                 return "redirect:profile.htm";
             }
             else{
                 term=request.getParameter("checkBox2");
                 
                 List list=session.createQuery("from "+mark+"file where "+term+"='"+search+"'").list();
                 model.addAttribute("list", list);
                 return "redirect:/teacher/profile.htm";
                 
             }
         
         
         }catch(Exception e){
             e.printStackTrace();
         }
         session.getTransaction().commit();
         return null;
     }
     
     
     
     @RequestMapping(value="/student/edit.htm",method=RequestMethod.GET)
     String getStudentEdit(ModelMap model){
         PermissionService ps=new PermissionService();
         if(ps.getPermission().equals("Teacher")){
             SecurityContextHolder.clearContext();
             return "error";
         }
         
         
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username=user.getUsername();
            this.studentFileService.setStudentFile(this.studentFileService.getStudentFile(username));
        
         
            Studentfile sf=new Studentfile();
         
         model.addAttribute("sf",sf);
         return "studentedit";
     }
     @RequestMapping(value="/student/edit.htm",method=RequestMethod.POST)
     String postStudentEdit(@ModelAttribute(value="sf")Studentfile studentfile,HttpServletRequest request,ModelMap model) throws IOException{
         Studentfile sf=this.studentFileService.getStudentFile();
         sf.setName(request.getParameter("name"));         
         sf.setRoom(request.getParameter("room"));      
         
         sf.setAddress(request.getParameter("address"));         
         sf.setTelephone(request.getParameter("telephone"));         
         sf.setEmail(request.getParameter("email"));     
         sf.setFileData(studentfile.getFileData());
         StudentfileService sfs=new StudentfileService();
         sfs.setStudentFile(sf);
         
         
         StudentUpdateService studentUpdateService=new StudentUpdateService();
         this.studentFileService=studentUpdateService.updateStudentInfo(sfs,request);
         this.messageService.setMessage("Profile Updated");
         return "redirect:edit.htm";
     }
     
     
    @RequestMapping (value="/student/changepassword.htm",method=RequestMethod.GET)
    public String getPassword(ModelMap model){
        PermissionService ps=new PermissionService();
         if(ps.getPermission().equals("Teacher")){
             SecurityContextHolder.clearContext();
             return "error";
         }
        PasswordData passwordData=new PasswordData();
        model.addAttribute("pd", passwordData);
       
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=user.getUsername();
        this.studentFileService.setStudentFile(this.studentFileService.getStudentFile(username));
      
        return "changepassword";
    
    }
     
    @RequestMapping (value="/student/changepassword.htm",method=RequestMethod.POST)
    public String postPassword(@ModelAttribute(value="pd")PasswordData pd,ModelMap map){
        pd.setUsername(this.studentFileService.getStudentFile().getUserName());
        DataService ds=new DataService();
        int flag=ds.matchPassword(pd);
        if(flag==1){
            this.messageService.setMessage("Password Missmatch");
            return "redirect:changepassword.htm";
        }
        else if(flag==2){
            this.messageService.setMessage("Username / Password Incorrect!");
            return "redirect:changepassword.htm";
        
        }
        else {
            this.messageService.setMessage("Password Changed");
            return "redirect:home.htm";
        }
        
    }
    
        
        @RequestMapping(value="student/{userName}.htm",method=RequestMethod.GET)
        public String showOtherProfile(@PathVariable String userName,ModelMap map){
            
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                String username=user.getUsername();
            PermissionService ps=new PermissionService();
        String message; 
        if(ps.getPermission().equals("Teacher")){
             message="/CourseManagementProject/teacher/home.htm";
              this.teacherFileService.setTeacherFile(this.teacherFileService.getTeacherFile(username));
         }
        else{
           this.studentFileService.setStudentFile(this.studentFileService.getStudentFile(username));
        
            message="/CourseManagementProject/student/home.htm";
        }
            Studentfile sf;
            StudentfileService sfs=new StudentfileService();
            sf=sfs.getStudentFile(userName);
            map.addAttribute("sf", sf);
             map.addAttribute("message", message);
                
         
            return "fullprofilestudent";
        }
    
    
    
        
    @RequestMapping (value="/student/profile.htm",method=RequestMethod.GET)
    public  String showProfile(ModelMap map){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username=user.getUsername();
            
        PermissionService ps=new PermissionService();
        String message; 
        if(ps.getPermission().equals("Teacher")){
             message="/CourseManagementProject/teacher/home.htm";
             this.teacherFileService.setTeacherFile(this.teacherFileService.getTeacherFile(username));
         }
        else {
              this.studentFileService.setStudentFile(this.studentFileService.getStudentFile(username));
            message= "/CourseManagementProject/student/home.htm";
        }
         
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List list=session.createQuery("from Studentfile").list();
        session.getTransaction().commit();
        map.addAttribute("students",list);
        map.addAttribute("message", message);
        
            
          
        
        return "studentprofile";
    }
    
    
    
   
    
    
    @RequestMapping(value="download/{userName}.htm", method=RequestMethod.GET)
    public String downloadLink(@PathVariable String userName,HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List list=session.createQuery("from Studentfile where UserName='"+userName+"'").list();
        if(list.isEmpty()){
             list=session.createQuery("from Teacherfile where UserName='"+userName+"'").list();
             Teacherfile tf;
            tf=(Teacherfile)list.get(0);
        
            response.setContentType(tf.getFileType());
            response.setContentLength((int)tf.getFileSize().longValue());
            response.setHeader("Content-Disposition","attachment; filename=\"" + tf.getUserName()+tf.getFileName() +"\"");
        
            byte []b=new byte[(int)tf.getFileSize().longValue()+1];
            InputStream input=new FileInputStream(tf.getPicture());
            int temp=0,k=0;
            while((temp=input.read())!=-1){
                b[k]=(byte)temp;
                k++;
            }

            FileCopyUtils.copy(b, response.getOutputStream());
        
            return null;
        }
        Studentfile sf;
        sf=(Studentfile)list.get(0);
        
        response.setContentType(sf.getFileType());
        response.setContentLength((int)sf.getFileSize().longValue());
        response.setHeader("Content-Disposition","attachment; filename=\"" + sf.getUserName()+sf.getFileName() +"\"");
        
        byte []b=new byte[(int)sf.getFileSize().longValue()+1];
        InputStream input=new FileInputStream(sf.getPicture());
        int temp=0,k=0;
        while((temp=input.read())!=-1){
            b[k]=(byte)temp;
            k++;
        }

        FileCopyUtils.copy(b, response.getOutputStream());
        
        return null;
        
    }
    
    
    
    
    
    
    
    
    
    
  
    
    
    @RequestMapping(value="/teacherpin.htm" ,method=RequestMethod.GET)
    public String getTeacherPin(ModelMap model){
        TeacherService ts=new TeacherService();
        ts.generatePin(this.registrationService);
        return "teacherpin";
    }
    
    
   @RequestMapping(value="/validation.htm" ,method=RequestMethod.GET)
    public String getValidation(ModelMap model){
        Teacherauthentication ta=new Teacherauthentication();
        model.addAttribute("ta", ta);
        
    
        return "validation";
    }
   
     
    @RequestMapping(value="/validation.htm" ,method=RequestMethod.POST)
    public String postValidation(@ModelAttribute(value="ta")Teacherauthentication ta, ModelMap model){
        TeacherService ts=new TeacherService();
        if(ts.teacherValidation(ta)){
            RegistrationData rd=new RegistrationData();
            rd.setUsername(ta.getUserName());
            rd.setPassword(ta.getPassword());
            rd.setMark("Teacher");
            rd.setRepassword(ta.getPassword());
            this.registrationService.setRegistrationData(rd);
            return "redirect:teacher.htm";
        }
    
        else return "index";
    }
   
    @RequestMapping(value="/teacher.htm" ,method=RequestMethod.GET)
    public String getTeacher(ModelMap model){
        
         if(this.registrationService.getRegistrationData().getUsername().equals(""))return "redirect:registration.htm";
         Teacherfile tf=new Teacherfile();
         model.addAttribute("tf", tf);
         
         
         return "teacher";
    }
    
    @RequestMapping(value="/teacher.htm" ,method=RequestMethod.POST)
    public String postTeacher(@ModelAttribute(value="tf")Teacherfile tf,BindingResult result,HttpServletRequest request){
         if(this.registrationService.getRegistrationData().getUsername().equals(""))return "redirect:registration.htm";
       if(result.hasErrors())
            return "teacher";
        
             
        TeacherService ts=new TeacherService();
        ts.teacherRegistration(tf, this.registrationService.getRegistrationData(), request);
        this.teacherFileService=ts.getTeacherFileService();
        
        return "redirect:teacher/home.htm";
    }
    
    @ModelAttribute("teacherData")
    public Teacherfile getTeacherData(){
        return this.teacherFileService.getTeacherFile();
    }
    
    
    
     @RequestMapping(value="/teacher/home.htm" ,method=RequestMethod.GET)
     public String getTeacherHome(ModelMap model){
         PermissionService ps=new PermissionService();
         if(ps.getPermission().equals("Student")){
             SecurityContextHolder.clearContext();
             return "error";
         }
         if(this.registrationService.getRegistrationData()!=null){
            
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username=user.getUsername();
            this.teacherFileService.setTeacherFile(this.teacherFileService.getTeacherFile(username));
        }
         
        
         this.messageService.setMessage("");
         
         return "teacherhome";
     
     
     }
    
     @RequestMapping(value="/teacher/home.htm",method=RequestMethod.POST)
     public String posTeachertHome(HttpServletRequest request,ModelMap model){
         String mark=request.getParameter("mark");
         String search=request.getParameter("serarchtext");
         Session session=HibernateUtil.getSessionFactory().getCurrentSession();
         session.beginTransaction();
         try{
             String term=null;
             if(mark.equals("Student")){
                 term=request.getParameter("checkBox");
                 List list=session.createQuery("from "+mark+"file where "+term+"='"+search+"'").list();
                 model.addAttribute("students",list);
                 return "redirect:/student/profile.htm";
             }
             else{
                 term=request.getParameter("checkBox2");
                 
                 List list=session.createQuery("from "+mark+"file where "+term+"='"+search+"'").list();
                 model.addAttribute("list", list);
                 return "redirect:profile.htm";
                 
             }
         
         
         }catch(Exception e){
             e.printStackTrace();
         }
         session.getTransaction().commit();
         return null;
     }
     
    
    
     @RequestMapping(value="/teacher/edit.htm" ,method=RequestMethod.GET)
     public String getTeacherEdit(ModelMap map){
         
         PermissionService ps=new PermissionService();
         if(ps.getPermission().equals("Student")){
             SecurityContextHolder.clearContext();
             return "error";
         }
         
         
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=user.getUsername();
        this.teacherFileService.setTeacherFile(this.teacherFileService.getTeacherFile(username));
        
         Teacherfile tf=this.teacherFileService.getTeacherFile();
         this.messageService.setMessage("");
         map.addAttribute("tf",tf);
         return "teacheredit";
         
         
     }
     
     @RequestMapping(value="/teacher/edit.htm" ,method=RequestMethod.POST)
     public String postTeacherEdit(@ModelAttribute(value="tf")Teacherfile teacherFile,HttpServletRequest request,ModelMap model) throws IOException{
        
        Teacherfile tf=this.teacherFileService.getTeacherFile();
        tf.setName(request.getParameter("name"));         
        tf.setOffice(request.getParameter("office"));      
        tf.setDesignation(request.getParameter("designation")); 
        tf.setAddress(request.getParameter("address"));         
        tf.setTelephone(request.getParameter("telephone"));         
        tf.setEmail(request.getParameter("email"));     
        tf.setFileData(teacherFile.getFileData());
         
        TeacherfileService tfs = new TeacherfileService();
        tfs.setTeacherFile(tf);
         
        TeacherService teacherService= new TeacherService();
        this.teacherFileService=teacherService.teacherUpdateService(tfs, request);
        return "redirect:edit.htm";
     
     }
     
     
    @RequestMapping (value="/teacher/changepassword.htm",method=RequestMethod.GET)
    public String getPasswordTeacher(ModelMap model){
        PermissionService ps=new PermissionService();
         if(ps.getPermission().equals("Student")){
             SecurityContextHolder.clearContext();
             return "error";
         }
        PasswordData passwordData=new PasswordData();
        model.addAttribute("pd", passwordData);
       
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=user.getUsername();
        this.teacherFileService.setTeacherFile(this.teacherFileService.getTeacherFile(username));
      
        return "changepassword";
    
    }
     
    @RequestMapping (value="/teacher/changepassword.htm",method=RequestMethod.POST)
    public String postPasswordTeacher(@ModelAttribute(value="pd")PasswordData pd,ModelMap map){
        pd.setUsername(this.teacherFileService.getTeacherFile().getUserName());
        DataService ds=new DataService();
        int flag=ds.matchPassword(pd);
        if(flag==1){
            this.messageService.setMessage("Password Missmatch");
            return "redirect:changepassword.htm";
        }
        else if(flag==2){
            this.messageService.setMessage("Password Incorrect!");
            return "redirect:changepassword.htm";
        
        }
        else {
            this.messageService.setMessage("Password Changed");
            return "redirect:home.htm";
        }
        
    }
    
    @RequestMapping(value="/teacher/profile.htm" , method=RequestMethod.GET)
    public String postTeacherProfile(ModelMap model){
        
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         String username=user.getUsername();
        PermissionService ps=new PermissionService();
        String message; 
        if(ps.getPermission().equals("Student")){
             message="/CourseManagementProject/student/home.htm";
             this.studentFileService.setStudentFile(this.studentFileService.getStudentFile(username));
         }
        else {
            this.teacherFileService.setTeacherFile(this.teacherFileService.getTeacherFile(username));
            message= "/CourseManagementProject/teacher/home.htm";
        }
        
        
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            List list=session.createQuery("from Teacherfile").list();
            session.getTransaction().commit();
            model.addAttribute("list", list);
            model.addAttribute("message", message);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return "teacherprofile";
    }
    
    @RequestMapping(value="teacher/{userName}.htm",method=RequestMethod.GET)
        
    public String showOtherProfileTeacher(@PathVariable String userName,ModelMap map){
         User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         String username=user.getUsername();
           
        PermissionService ps=new PermissionService();
        String message; 
        if(ps.getPermission().equals("Student")){
             message="/CourseManagementProject/student/home.htm";
             this.studentFileService.setStudentFile(this.studentFileService.getStudentFile(username));
         
         }
        else {
            message="/CourseManagementProject/teacher/home.htm";
            this.teacherFileService.setTeacherFile(this.teacherFileService.getTeacherFile(username));
        }
            Teacherfile tf;
            TeacherfileService tfs=new TeacherfileService();
            tf=tfs.getTeacherFile(userName);
            map.addAttribute("tf", tf);
            map.addAttribute("message", message);
            
            return "fullprofileteacher";
        }


















}
