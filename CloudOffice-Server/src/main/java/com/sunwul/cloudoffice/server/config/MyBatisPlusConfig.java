package com.sunwul.cloudoffice.server.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*****
 * @author sunwul
 * @date 2021/3/27 9:48
 * @description mybatis-plus配置
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * 配置分页插件 --- 经过验证,这样配置不生效,官方文档说这是旧版配置
     *
     * @return PaginationInnerInterceptor
     */
//    @Bean
//    public PaginationInnerInterceptor paginationInnerInterceptor() {
//        PaginationInnerInterceptor page = new PaginationInnerInterceptor();
//        page.setDbType(DbType.MYSQL);
//        return page;
//    }

    /**
     * 查看官方文档, 发现新版本的需要这样配置
     * 注意,与 PaginationInterceptor 一起用会有冲突
     *
     * @return MybatisPlusInterceptor
     * @see [https://mybatis.plus/guide/page.html]
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 经过测试,这里需要声明具体的数据库类型,否则分页也是会失效的
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 这种方式也是可行的 -- 但是不知道为什么不推荐使用
     * 另外,经过测试这种方式与 MybatisPlusInterceptor 一起用会有冲突
     *
     * @return PaginationInterceptor
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }
}
