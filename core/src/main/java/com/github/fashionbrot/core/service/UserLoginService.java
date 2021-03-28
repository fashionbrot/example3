package com.github.fashionbrot.core.service;

import com.github.fashionbrot.common.consts.GlobalConst;
import com.github.fashionbrot.common.enums.RespCode;
import com.github.fashionbrot.common.exception.MarsException;
import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.util.CookieUtil;
import com.github.fashionbrot.common.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class UserLoginService {

    @Autowired
    private HttpServletRequest request;

    public LoginModel getLogin() {
        String token = CookieUtil.getCookieValue(request, GlobalConst.AUTH_KEY, false);
        if (StringUtils.isEmpty(token)) {
            throw new MarsException(RespCode.SIGNATURE_MISMATCH);
        }
        LoginModel model = JwtTokenUtil.getLogin(token);
        if (model!=null){
            request.setAttribute(GlobalConst.AUTH_KEY,model);
        }
        return model;
    }

    public LoginModel getSafeLogin() {
        try {
            Object attribute = request.getAttribute(GlobalConst.AUTH_KEY);
            if (attribute==null) {
                return getLogin();
            }
            return (LoginModel)attribute;
        }catch (Exception e){
        }
        return null;
    }


}
