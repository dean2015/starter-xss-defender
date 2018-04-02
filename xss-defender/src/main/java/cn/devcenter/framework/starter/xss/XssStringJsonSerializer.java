package cn.devcenter.framework.starter.xss;

import cn.devcenter.framework.starter.xss.config.property.ExcludeCharatorsProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

/**
 * 
 * @author dean
 *
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {

	private ExcludeCharatorsProperties excludeCharatorsProperties;

	public XssStringJsonSerializer(ExcludeCharatorsProperties excludeCharatorsProperties){
		this.excludeCharatorsProperties = excludeCharatorsProperties;
	}

	@Override
	public Class<String> handledType() {
		return String.class;
	}

	@Override
	public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		if (value != null) {
			String encodedValue = excludeCharatorsProperties.backEscape(HtmlUtils.htmlEscape(value));
			jsonGenerator.writeString(encodedValue);
		}
	}

}