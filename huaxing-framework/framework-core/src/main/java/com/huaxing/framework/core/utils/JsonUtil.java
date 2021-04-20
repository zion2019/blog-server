package com.huaxing.framework.core.utils;

import java.io.StringWriter;
import java.text.SimpleDateFormat;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	protected static final Logger logger = LoggerFactory
			.getLogger(JsonUtil.class);
	private static String SPACE = "   ";

	public static String json(Object source) {
		return json(source, (String) null);
	}

	public static String json(Object source, String dateformat) {
		ObjectMapper objectMapper = new ObjectMapper();
		if (StrUtil.isNotEmpty(dateformat)) {
			objectMapper.setDateFormat(new SimpleDateFormat(dateformat));
		}

		StringWriter sw = new StringWriter();

		try {
			objectMapper.writeValue(sw, source);
		} catch (Throwable arg4) {
			logger.error("对象转json失败", arg4);
		}

		return sw.toString();
	}

	public static String formatJson(String json) {
		StringBuffer result = new StringBuffer();
		int length = json.length();
		int number = 0;

		for (int i = 0; i < length; ++i) {
			char arg5 = json.charAt(i);
			if (arg5 != 91 && arg5 != 123) {
				if (arg5 != 93 && arg5 != 125) {
					if (arg5 == 44) {
						result.append(arg5);
						result.append('\n');
						result.append(indent(number));
					} else {
						result.append(arg5);
					}
				} else {
					result.append('\n');
					--number;
					result.append(indent(number));
					result.append(arg5);
					if (i + 1 < length && json.charAt(i + 1) != 44) {
						result.append('\n');
					}
				}
			} else {
				if (i - 1 > 0 && json.charAt(i - 1) == 58) {
					result.append('\n');
					result.append(indent(number));
				}

				result.append(arg5);
				result.append('\n');
				++number;
				result.append(indent(number));
			}
		}

		return result.toString();
	}

	private static String indent(int number) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < number; ++i) {
			result.append(SPACE);
		}

		return result.toString();
	}

	public static <T> T parseJson(String json, Class<T> clazz, String dateFormat)
			throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		if (StrUtil.isNotEmpty(dateFormat)) {
			objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
		}

		return objectMapper.readValue(json, clazz);
	}

}
