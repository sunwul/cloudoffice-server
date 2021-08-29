package com.sunwul.cloudoffice.server.common.sercurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*****
 * @author sunwul
 * @date 2021/7/27 10:50
 * @description security密码加密   BCryptPasswordEncoder
 */
public class PasswordEncoderTest {

    public static void main(String[] args) {
        String originPassword = "123456"; // 原始密码
        String encryptPassword = encryptPass(originPassword); // security加密后的密码
        // 每次输出的加密后信息都不一致, 是因为BCrypt使用了强哈希方法
        System.out.println("原始密码: " + originPassword + "\n加密后: " + encryptPassword);
        // 对比
        System.out.println("对比结果: " + matchesPass(originPassword, encryptPassword));

    }

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 使用security的BCryptPasswordEncoder加密密码
     * <p>
     * SHA-256 +随机盐+密钥对密码进行加密。SHA系列是Hash算法，不是加密算法，
     * 使用加密算法意味着可以解密（这个与编码/解码一样），但是采用Hash处理，其过程是不可逆的
     * <p>
     * BCrypt使用了强哈希方法, 所以每次的加密结果都不一样,
     * 在进行encode加密时，先调用BCrypt 的String hashpw(String password, String salt)方法。
     * 两个参数即 明文信息 和 salt(随机盐)
     * 先根据传入的盐值salt,然后基于某种规则从salt得到real_salt(真正的盐),
     * 后续的操作都是用这个real_salt来进行，最终得到加密字符串(传入的盐值salt并不是最终用来加密的盐，方法中通过salt得到了real_salt)
     *
     * @param originPassword 原始密码
     * @return 加密后的密码
     */
    public static String encryptPass(String originPassword) {
        return passwordEncoder.encode(originPassword);
    }

    /**
     * 使用security的BCryptPasswordEncoder进行密码对比
     * 密码匹配阶段并没有进行密码解密（因为密码经过Hash处理，是不可逆的），
     * 而是使用相同的算法把用户输入的密码进行hash处理，得到密码的hash值，
     * 然后将其与从数据库中查询到的密码hash值进行比较。如果两者相同，说明用户输入的密码正确。
     * <p>
     * 在进行matches进行比较时，调用BCrypt 的boolean checkpw(String plaintext, String hashed)方法。
     * 第一个参数为明文，第二个参数是加密后的字符串
     * 在checkpw方法中返回的是 调用equalsNoEarlyReturn(hashed, hashpw(plaintext, hashed))
     * 第一个参数是加密后的字符串，而第二个参数是用hashpw方法对明文字符串进行加密
     * hashpw(plaintext, hashed)第一个参数是明文，第二个参数是加密字符串(但是在这里是作为盐值salt传入)，
     * 然后用到了 hashpw 内部通过传入的salt得到real_salt，
     * 这样就保证了对现在要校验的明文的加密和得到已有密文的加密用的是同样的加密策略，算法和盐值都相同，
     * 这样如果新产生的密文和原来的密文相同，则这两个密文对应的明文字符串就是相等的
     * 这也说明了加密时使用的盐值被写在了最终生成的加密字符串中
     * <p>
     * 为什么处理密码时要用hash算法，而不用加密算法?
     * 因为这样处理即使数据库泄漏，黑客也很难破解密码（破解密码只能用彩虹表）
     *
     * @param originPassword  原始密码
     * @param encryptPassword 加密后的密码
     * @return 解密后的密码
     */
    public static boolean matchesPass(String originPassword, String encryptPassword) {
        return passwordEncoder.matches(originPassword, encryptPassword);
    }

}
