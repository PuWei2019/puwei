package com.cy.store.service.ex;

//RuntimeException->ServiceException->根据不同功能定义的异常(username被占用:UsernameDuplicatedException）
// 业务异常基层类：throws new ServiceException("业务层异常")

public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
