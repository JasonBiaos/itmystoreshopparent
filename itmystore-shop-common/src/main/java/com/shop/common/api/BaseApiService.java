package com.shop.common.api;

import com.shop.constants.BaseApiConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BaseApiService
 * @Description 通用BaseApi 父类
 * @Author Jason Biao
 * @Date 2018\11\30 0030 14:34
 * @Version 1.0
 **/
public class BaseApiService {

    /**
     * @MethodDesc 返回成功
     * @param
     * @return
     */
    public Map<String,Object> setResultSuccessData(Object data){
        return setResult(BaseApiConstants.HTTP_RES_CODE_200,BaseApiConstants.HTTP_RES_CODE_MSG,data);
    }

    /**
     * @MethodDesc 返回成功
     * @param
     * @return
     */
    public Map<String,Object> setResultSuccess(String msg){
        return setResult(BaseApiConstants.HTTP_RES_CODE_200,msg,null);
    }

    /**
     * @MethodDesc 返回成功
     * @param
     * @return
     */
    public Map<String,Object> setResultSuccess(){
        return setResult(BaseApiConstants.HTTP_RES_CODE_200,BaseApiConstants.HTTP_RES_CODE_200_VALUE,null);
    }

    /**
     * @MethodDesc 返回错误
     * @param msg
     * @return
     */
    public Map<String,Object> setResultError(String msg){
        return setResult(BaseApiConstants.HTTP_RES_CODE_500,msg,null);
    }

    /**
     * @MethodDesc 自定义返回响应
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public Map<String,Object> setResult(Integer code,String msg,Object data){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(BaseApiConstants.HTTP_RES_CODE_NAME,code);
        map.put(BaseApiConstants.HTTP_RES_CODE_MSG,msg);
        if (data != null)
           map.put(BaseApiConstants.HTTP_RES_CODE_DATA,data);
        return map;
    }

    public Map<String,Object> setResultParameterError(String msg){
        return setResult(BaseApiConstants.HTTP_RES_CODE_400,msg,null);
    }

}
