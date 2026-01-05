package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;




/** 控制层基类*/
public class BaseController {
    //操作状态码
    public static final int SUCCESS =200;
    public static final int ERROR_USERNAME_DUPLICATE =300;
    public static final int ERROR_INSERT =400;
    public static final int ERROR_UserNotFound=301;
    public static final int ERROR_PasswordNotMatchException=302;
    public static final int ERROR_UpdateException=303;
    public static final int ERROR_FileEmpty=401;
    public static final int ERROR_FileIO=402;
    public static final int ERROR_FileSize=403;
    public static final int ERROR_FileState=404;
    public static final int ERROR_FileType=405;
    public static final int ERROR_AddressRows=406;
    public static final int ERROR_AccessDenied=407;
    public static final int ERROR_AddressNotFound=408;
    public static final int ERROR_ProductNotFound=409;
    public static final int ERROR_CartNotFound=410;

    //异常时统一抛出一样
    //@ExceptionHandler:统一抛出异常 ServiceException.class:指定来自哪里的异常
    @ExceptionHandler({ServiceException.class, FileUplodaException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<Void>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(ERROR_USERNAME_DUPLICATE);
        }else if(e instanceof InsertException){
            result.setState(ERROR_INSERT);
        }
        else if(e instanceof UserNotFoundException){
            result.setState(ERROR_UserNotFound);
        }
        else if(e instanceof PasswordNotMatchException){
            result.setState(ERROR_PasswordNotMatchException);
        }
        else if(e instanceof UpdateException){
            result.setState(ERROR_UpdateException);
        }
        else if(e instanceof FileEmptyException){
            result.setState(ERROR_FileEmpty);
        }
        else if(e instanceof FileIOException){
            result.setState(ERROR_FileIO);
        }
        else if(e instanceof FileSizeException){
            result.setState(ERROR_FileSize);
        }
        else if(e instanceof FileStateException){
            result.setState(ERROR_FileState);
        }
        else if(e instanceof FileTypeException){
            result.setState(ERROR_FileType);
        }
        else if(e instanceof AddressCountLimitException){
            result.setState(ERROR_AddressRows);
        }else if(e instanceof AccessDeniedException){
            result.setState(ERROR_AccessDenied);
        }else if(e instanceof AddressNotFoundException){
            result.setState(ERROR_AddressNotFound);
        }else if(e instanceof ProductNotFoundException){
            result.setState(ERROR_ProductNotFound);
        }else if(e instanceof CartNotFoundException){
            result.setState(ERROR_CartNotFound);
        }


        return result;
    }

    //获取在登陆时传递给session对象
    //是object类 session.getAttribute("uid") toString()转换为String
    //最后转换为Integer
    public Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    //获取在登陆时传递给session对象
    //是object类 session.getAttribute("uid") toString()转换为String
    public String getUsernameFromSession(HttpSession session){

        return session.getAttribute("username").toString();
    }

}
