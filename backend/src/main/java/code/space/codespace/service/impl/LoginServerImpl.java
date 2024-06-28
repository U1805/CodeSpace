package code.space.codespace.service.impl;

import code.space.codespace.mapper.UserMapper;
import code.space.codespace.pojo.LoginInfo;
import code.space.codespace.pojo.RegisterInfo;
import code.space.codespace.service.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class LoginServerImpl implements LoginServer {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(LoginInfo loginInfo) {
        int admin_ = loginInfo.getAdmin()? 1 : 0;
        try {
            if (userMapper.login(loginInfo.getUsername(), hashPassword(loginInfo.getPassword()), admin_) == 1){
                return admin_==0?"cs-waerasd":"aw-scerasd"; // TODO: 计算 userToken 并返回
            }
            else {
                return "";
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    /** 加密存储密码，保证安全性
     * @param password 密码明文
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    /**
     * 密码强度验证
     * @param password 密码明文
     * @return
     */
    private boolean isPassowrdSafe(String password){
        if (password.length() < 8) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char ch : password.toCharArray()) {
            if (Character.isLetter(ch)) {
                hasLetter = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }
            if (hasLetter && hasDigit) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String register(RegisterInfo registerInfo) {
        if (registerInfo.getUsername().length() == 0){
            return "username-empty";
        }
        if (registerInfo.getPassword().length() == 0){
            return "password-empty";
        }
        if (userMapper.find_username(registerInfo.getUsername())>0) {
            return "username-repeat"; //username repeat
        }
        if (!isPassowrdSafe(registerInfo.getPassword())) {
            return "password-weak";
        }
        else {
            String[] arr = { // 随机头像
                    "https://avatars.githubusercontent.com/u/55444042",
                    "https://avatars.githubusercontent.com/u/171446778",
                    "https://avatars.githubusercontent.com/u/134131461",
                    "https://avatars.githubusercontent.com/u/34743860",
                    "https://avatars.githubusercontent.com/u/166692320",
                    "https://avatars.githubusercontent.com/u/93321955",
                    "https://avatars.githubusercontent.com/u/141130496",
                    "https://avatars.githubusercontent.com/u/149292952",
//                    "https://avatars.githubusercontent.com/u/33820902",
                    "https://avatars.githubusercontent.com/u/68903738",
                    "https://avatars.githubusercontent.com/u/150766762"
            };
            Random random = new Random();
            int randomIndex = random.nextInt(arr.length);

            try {
                userMapper.register(registerInfo.getUsername(), hashPassword(registerInfo.getPassword()), arr[randomIndex]);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            return "cs-waerasd"; // TODO: 计算 userToken 并返回
        }
    }
}
