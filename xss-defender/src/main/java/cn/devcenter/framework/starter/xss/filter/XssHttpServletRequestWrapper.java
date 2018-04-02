package cn.devcenter.framework.starter.xss.filter;

import cn.devcenter.framework.starter.xss.config.property.ExcludeCharatorsProperties;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Xss 防御 使用request包装修改请求头和请求体
 * @author dean
 *
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {


	private ExcludeCharatorsProperties excludeCharatorsProperties;

	public void setExcludeCharatorsProperties(ExcludeCharatorsProperties excludeCharatorsProperties){
		this.excludeCharatorsProperties = excludeCharatorsProperties;
	}


	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		return excludeCharatorsProperties.backEscape(HtmlUtils.htmlEscape(value));
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		return excludeCharatorsProperties.backEscape(HtmlUtils.htmlEscape(value));
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
				escapseValues[i] = excludeCharatorsProperties.backEscape(HtmlUtils.htmlEscape(values[i]));
			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}

}