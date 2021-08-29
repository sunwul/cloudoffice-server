package com.sunwul.cloudoffice.server.config;

import com.sunwul.cloudoffice.server.common.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*****
 * @author sunwul
 * @date 2021/3/28 19:07
 * @description WebSocket配置类
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${jwt.tokenHead}")
    private String tokenHead; // token前缀

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    /**
     * 添加端点 Endpoint  这样在网页可以通过WebSocket连接上服务
     * 配置WebSocket的服务地址, 并且可以指定是否使用SocketJS
     *
     * @param registry StompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 1.将/ws/ep路径注册为stomp的端点, 用户连接了这个端点就可以进行WebSocket通讯
         * 2.setAllowedOrigins("*")  允许跨域
         * 3.withSockJS()  支持SocketJS
         */
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置输入通道参数
     * 没有使用JWT登录授权过滤器的情况下不需要配置
     *
     * @param registration ChannelRegistration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                // 判断是否为连接, 如果是, 需要获取token,并且设置用户对象
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    // 获取请求头中的token    (Authorization)这个参数是前端发送过来的
                    String token = accessor.getFirstNativeHeader("Authorization");
                    if (!StringUtils.isEmpty(token)) {
                        // 截取token 去掉前缀
                        String authToken = token.substring(tokenHead.length());
                        // 获取用户名
                        String username = jwtTokenUtil.getUserNameFromToken(authToken);
                        // 判断token中是否存在用户名
                        if (!StringUtils.isEmpty(username)) {
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            // 验证token是否有效, 重新设置用户对象
                            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities());
                                // 在security全局对象中设置用户
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                // 在accessor中设置用户
                                accessor.setUser(authenticationToken);
                            }
                        }
                    }
                }
                return message;
            }
        });
    }

    /**
     * 配置消息代理
     *
     * @param registry MessageBrokerRegistry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置代理域, 可以配置多个, 配置代理目的地前缀为/queue, 可以在配置域上向客户端推送消息
        registry.enableSimpleBroker("/queue");
    }
}
