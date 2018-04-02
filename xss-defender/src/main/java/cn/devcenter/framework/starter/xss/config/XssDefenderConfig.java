package cn.devcenter.framework.starter.xss.config;

import cn.devcenter.framework.starter.xss.XssStringJsonSerializer;
import cn.devcenter.framework.starter.xss.config.property.ExcludeCharatorsProperties;
import cn.devcenter.framework.starter.xss.filter.XssFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dean
 */
@Configuration
public class XssDefenderConfig {

    @Autowired
    private ExcludeCharatorsProperties excludeCharatorsProperties;

    /**
     * 描述 : xssObjectMapper
     *
     * @return xssObjectMapper
     */
    @Bean
    public ObjectMapper updateObjectMapper(ObjectMapper objectMapper) {
        // 注册xss解析器
        SimpleModule xssModule = new SimpleModule("XssStringJsonSerializer");
        xssModule.addSerializer(new XssStringJsonSerializer(excludeCharatorsProperties));
        objectMapper.registerModule(xssModule);
        // 返回
        return objectMapper;
    }

    @Bean
    public XssFilter getXssFileter() {
        return new XssFilter();
    }

}
