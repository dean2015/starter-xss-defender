package cn.devcenter.framework.starter.xss.config.property;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.util.HtmlUtils;

@Data
@ConfigurationProperties(prefix = ExcludeCharatorsProperties.PREFIX)
public class ExcludeCharatorsProperties {

    public static final String PREFIX = "xxs.chars";

    private String[] chars = {"&", "·"};

    private String[] escapeChars;

    public String[] getEscapeChars() {
        if (null == escapeChars) {
            escapeChars = new String[chars.length];
            for (int i = 0; i < chars.length; i++) {
                escapeChars[i] = HtmlUtils.htmlEscape(chars[i]);
            }
        }
        return escapeChars;
    }

    public String backEscape(final String input) {
        if (StringUtils.isBlank(input)) {
            return input;
        }
        String result = input;
        for (int i = 0; i < getEscapeChars().length; i++) {
            result = result.replace(getEscapeChars()[i], chars[i]);
        }
        return result;
    }

}
