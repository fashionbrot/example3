package com.github.fashionbrot.common.req;

import lombok.Data;

@Data
public class SysUserReq extends PageReq {

    private String username;

    private String password;

}
