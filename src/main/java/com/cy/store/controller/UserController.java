package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController//@ResponsBody+@Controller
@RequestMapping("/users")

public class UserController extends BaseController{
    @Autowired
    private IUserService userService;
    /**
     * 从前端接受数据的方式：请求方法(public JsonResult<Void> reg(User user))为实体类型的情况
     * 1.Springboot会从前端url地址的参数名（localhost:/users/reg/username=tom1&password=123）和实体类的属性名进行比较.
     * 如果名称相同 则注入实体类属性上 (user.username user.password)
     */
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user) {
            userService.reg(user);
        return new JsonResult<>(SUCCESS,null);
    }

    /**
     * 从前端接受数据的方式：请求方法(public JsonResult<User> login(String username,String password))为非封装实体类
     * 2.Springboot会从前端url地址的参数名（localhost:/users/reg/username=tom1&password=123）和方法的参数名进行比较.
     * 如果名称相同 则注入参数名上 (username password)
     */
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data =userService.login(username,password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        return new JsonResult<>(SUCCESS,data);
    }

    /**
     * 从前端传过来的新旧密码和从session得到的username uid进行修改
     */
    @RequestMapping("/change_password")
    public JsonResult<Void> changPassword(String oldPassword,String newPassword,HttpSession session){
      Integer uid =getUidFromSession(session);
      String username = getUsernameFromSession(session);
      userService.changPassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(SUCCESS,null);
    }

    /**
     *同过session的uid 得到用户数据
     * @param session
     * @return
     */
    @RequestMapping("/get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
       Integer uid = getUidFromSession(session);
        User result = userService.getByUid(uid);
        return  new JsonResult<>(SUCCESS,result);
    }
    @RequestMapping("/change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username= getUsernameFromSession(session);
        userService.changeInfo(user,uid,username);
        return new JsonResult<>(SUCCESS,null);
    }

    //设置上传头像大小
    public static  final int AVATAR_SIZE=10 * 1024 * 1024;

    //限制上传文件类型

    public static  final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    /**
     * Multipartfile接口是SpringMVC提供的接口
     * 任何类型file都可以接受 SpringBoot整合了SpringMVC
     * 只需要在请求方法中声明一个Mutipartfile类型的参数
     * SpringBoot会将文件赋值给这个参数
     * @param session
     * @param file
     * @return 头像地址
     */
    @Value("${store.upload.path}")
    private String uploadPath;

    @RequestMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file){

//        String parent = session.getServletContext().getRealPath("/upload");
//        System.out.println(parent);
        //判断文件是否是空
        if(file.isEmpty()){
             throw new FileEmptyException("文件为空");
        }
        //判断文件大小
        if (file.getSize() > AVATAR_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        //判断文件类型
        //取得文件类型
        String contentType = file.getContentType();
        //List集合contains方法可以查询是否有对应值
        if(!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException();
        }

        //假设上传的文件目录 ..../upload/文件.png
        //取得相对路径
        //String parent = session.getServletContext().getRealPath("/upload");


        //File对象指定创建文件路径
        File dir = new File(uploadPath);
        if(!dir.exists()){//判断目录是否存在
            dir.mkdirs(); //不存在则创建文件夹
        }

        //重新命名头像文件(防止不同用户上传文件名字一致)
        //获取文件名字和后缀
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");//最后一个点的位置
        String suffix =originalFilename.substring(index);//从最后一个点的位置包含点截取 例：.png
        String filename = UUID.randomUUID().toString().toUpperCase()+suffix;///SINU_NIU42_43SUN5Q.png
        //创建目标目录以及一个空文件filename:SINU_NIU42_43SUN5Q.png
        File dest = new File(dir,filename);

        try {
            file.transferTo(dest);//将file文件的数据写入 需要后缀保证一致
        }catch (IllegalStateException e) {
            throw new IllegalStateException("文件状态异常");
        } catch (IOException e) {
           throw new FileIOException("文件读写异常");
        }

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar = "/upload/"+filename;//头像路径 在目录/upload下
        userService.changeAvatar(uid,avatar,username);
        System.out.println(avatar);
        return new JsonResult<>(SUCCESS,avatar);//返回路径给前端 展示头像

    }
}
