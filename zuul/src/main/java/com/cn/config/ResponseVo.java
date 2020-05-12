package com.cn.config;



import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 	通用返回结构
 * @date 2019年7月22日
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Builder
public class ResponseVo {

    private static final String MSG_FAIL = "fail";
    private static final String MSG_SUCCESS = "success";

    private Integer code;		//结果代码， 见 ResultCode.java
    private String msg;			//附带信息
    private Object data;		//业务数据
    private Long count;			//总数 (分页或者列表时需要)


    public static ResponseVo success() {
        return ResponseVo.of(ResultCode.SUCCESS.ordinal(), MSG_SUCCESS,null,null);
    }

    /**
     * 	成功并返回数据
     * @author 李科利
     * @date 2019年8月8日
     * @param data
     * @return
     */
    public static ResponseVo success(Object data) {
        return ResponseVo.of(ResultCode.SUCCESS.ordinal(), MSG_SUCCESS,data,null);
    }

    /**
     * 	成功并返回数据和总数 （适合于分页）
     * @author 李科利
     * @date 2019年8月8日
     * @param data
     * @param count
     * @return
     */
    public static ResponseVo success(Object data, Long count) {
        return ResponseVo.of(ResultCode.SUCCESS.ordinal(), MSG_SUCCESS,data,count);
    }

    /**
     * 方法描述： 成功并返回数据、信息、总数
     * @author 莫添杰
     * @date 2019年11月13日
     * @param data
     * @param msg
     * @param count
     * @return
     */
    public static ResponseVo success(Object data, String msg, Long count) {
        return ResponseVo.of(ResultCode.SUCCESS.ordinal(), msg,data,count);
    }

    /**
     * 	success(Object data)的懒加载方式
     * @author 李科利
     * @date 2019年8月8日
     * @param data
     * @return
     */
    public static ResponseVo successForLazy(Object data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return ResponseVo.of(ResultCode.SUCCESS.ordinal(), MSG_SUCCESS,mapper.readValue(mapper.writeValueAsString(data), Object.class),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fail();
    }

    /**
     * 	success(Object data, Long count)的懒加载方式
     * @author 李科利
     * @date 2019年8月8日
     * @param data
     * @param count
     * @return
     */
    public static ResponseVo successForLazy(Object data,Long count) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return ResponseVo.of(ResultCode.SUCCESS.ordinal(), MSG_SUCCESS,mapper.readValue(mapper.writeValueAsString(data), Object.class),count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fail();
    }

    public static ResponseVo fail() {
        return ResponseVo.of(ResultCode.FAIL.ordinal(), MSG_FAIL,null,null);
    }

    public static ResponseVo fail(String msg) {
        return ResponseVo.of(ResultCode.FAIL.ordinal(), msg,null,null);
    }
}
