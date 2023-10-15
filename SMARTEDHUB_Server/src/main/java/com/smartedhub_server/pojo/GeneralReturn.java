package com.smartedhub_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SMARTEDHUB
 * @description:
 * @author: Junxian Cai
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralReturn {
    private int code;
    private String msg;
    private Object data;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static GeneralReturn success(String message){
        return new GeneralReturn(200 , message , null);
    }

    /**
     * 成功返回结果
     * @param message
     * @param obj
     * @return
     */
    public static GeneralReturn success(String message , Object obj){
        return new GeneralReturn(200 , message , obj);
    }

    public static GeneralReturn success(Object obj){
        return success("Successful data retrieval from Back-End" , obj);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static GeneralReturn error(String message){
        return new GeneralReturn(500 , message , null);
    }

    /**
     * 成功返回结果
     * @param message
     * @param obj
     * @return
     */
    public static GeneralReturn error(String message , Object obj){
        return new GeneralReturn(500 , message , obj);
    }

}
