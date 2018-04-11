package org.sklse.targetedcourse.bean;


//   自定义返回结果

import org.springframework.http.HttpStatus;

public class ResultModel {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 返回内容
     */
    private Object content;



    public ResultModel(Object content) {
        this.code = HttpStatus.OK.value();

        this.content = content;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getContent() {
        return content;
    }

    public ResultModel(HttpStatus code, String message) {
        this.code = code.value();
        this.message = message;
        this.content = "";
    }

    public ResultModel(HttpStatus code, String message, Object content) {
        this.code = code.value();
        this.message = message;
        this.content = content;
    }



    public static ResultModel ok(Object content) {
        return new ResultModel(content);
    }

    public static ResultModel ok(HttpStatus status, Object content) {


        return new ResultModel(status,"success",content);
    }



    public static ResultModel error(HttpStatus status, Object content) {


         return new ResultModel(status,"failure",content);
    }



}
