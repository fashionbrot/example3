package com.github.fashionbrot.common.util;

import com.github.fashionbrot.tool.UuidUtil;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


public class PasswordUtil {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    public static String getSalt(){
        return UuidUtil.getUuid();
    }

    public static String encryptPassword(String password, String salt) {
        return new SimpleHash(algorithmName, password, ByteSource.Util.bytes(salt), hashIterations).toHex();
    }

    public static void main(String[] args) {
        String salt = getSalt();
        System.out.println("salt:"+salt);
        String enPwd = encryptPassword("admin",salt);
        System.out.println("password:"+enPwd);

    }
}
