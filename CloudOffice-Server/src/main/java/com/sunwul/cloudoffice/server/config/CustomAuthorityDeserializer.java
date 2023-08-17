package com.sunwul.cloudoffice.server.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*****
 * @author sunwul
 * @date 2021/3/28 20:46
 * @description 自定义Authority解析器
 * Json反序列化
 */
public class CustomAuthorityDeserializer extends JsonDeserializer {

    /**
     * 反序列化
     *
     * @param p    JSOn解析
     * @param ctxt 上下文
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        // 读取解析对象拿到JsonNode
        JsonNode jsonNode = mapper.readTree(p);
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        // 通过解析对象拿到其中的元素  获取迭代器
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            // 迭代元素
            JsonNode next = elements.next();
            // 从元素中拿到authority     authority是前端传进来的authority参数
            JsonNode authority = next.get("authority");
            // 放入List<GrantedAuthority>
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.asText()));
        }
        return grantedAuthorities;
    }
}
