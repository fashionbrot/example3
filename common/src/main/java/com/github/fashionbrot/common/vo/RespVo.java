package com.github.fashionbrot.common.vo;


import com.github.fashionbrot.common.consts.GlobalConst;
import com.github.fashionbrot.common.enums.RespCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
* 统一返回vo 类
*/
@Data
@Builder
@AllArgsConstructor
public class RespVo implements Serializable{
    private static final long serialVersionUID = -3655390020082644681L;

    public static final int SUCCESS = RespCode.SUCCESS.getCode();
    public static final int FAILED = RespCode.FAIL.getCode();

    private int code;
    private String msg;
    private Object data;


    public static RespVo fail(String msg){
        return RespVo.builder().code(FAILED).msg(msg).build();
    }

    public static RespVo fail(String msg, int code){
        return RespVo.builder().code(code).msg(msg).build();
    }

    public static RespVo success(Object data){
        return RespVo.builder().code(SUCCESS).msg("成功").data(data).build();
    }

    public static RespVo success(){
        return GlobalConst.RESP_VO;
    }

    public static RespVo respCode(RespCode respCode){
        return RespVo.builder().code(respCode.getCode()).msg(respCode.getMsg()).build();
    }

}
