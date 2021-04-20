package com.huaxing.framework.core.utils;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import org.springframework.context.MessageSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.huaxing.framework.core.exception.HbException;


public class Assert extends org.springframework.util.Assert {
	
	public static String getLocaleMsg(String errorKey, Object[] params){
		MessageSource messageSource = SpringBeanUtil.getBean("messageSource");
		String msg = "";
		try {
			msg = messageSource.getMessage(errorKey, params,Locale.SIMPLIFIED_CHINESE);
		} catch (Exception e) {
			e.printStackTrace();
			msg = errorKey;
		}

		return msg;
	}
	
	public static void throwException() {
		throw new HbException(getLocaleMsg("system.exception",null));
	}

	public static void throwException(String errorKey) {
		throw new HbException(errorKey,getLocaleMsg(errorKey,null));
	}
	
//	public static void throwException(String errorKey,String errorMsg) {
//		throw new BspException(errorKey,errorMsg);
//	}

	public static void isTrue(boolean expression, String errorKey) {
		if (expression) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}

	public static void isTrue(boolean expression, String errorKey,
			Object[] params) {
		if (expression) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}

	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}
	
	public static void isBlank(String str, String errorKey) {
		if (StrUtil.isBlank(str)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void isBlank(String str, String errorKey,Object[] params) {
		if (StrUtil.isBlank(str)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}

	public static void notBlank(String str, String errorKey) {
		if (StrUtil.isNotBlank(str)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void notBlank(String str, String errorKey,Object[] params) {
		if (StrUtil.isNotBlank(str)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}

//	public static void notBlank(String str, String errorKey, Object[] params) {
//		if (StringUtils.isBlank(str)) {
//			throw new MipException(errorKey, getLocaleMsg(errorKey, params));
//		}
//	}
	
	public static void isNull(Object object, String errorKey) {
		if (object == null) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void isNull(Object object, String errorKey,Object[] params) {
		if (object == null) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}
	
	public static void notNull(Object object, String errorKey) {
		if (object != null) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void notNull(Object object, String errorKey,Object[] params) {
		if (object != null) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}

	public static void isEmpty(Object[] array, String errorKey) {
		if (ObjectUtils.isEmpty(array)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void isEmpty(Object[] array, String errorKey,Object[] params) {
		if (ObjectUtils.isEmpty(array)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}
	
	public static void notEmpty(Object[] array, String errorKey) {
		if (!ObjectUtils.isEmpty(array)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void notEmpty(Object[] array, String errorKey,Object[] params) {
		if (!ObjectUtils.isEmpty(array)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}

	public static void noNullElements(Object[] array, String errorKey) {
		if (array != null) {
			Object[] arg4 = array;
			int arg3 = array.length;

			for (int arg2 = 0; arg2 < arg3; ++arg2) {
				Object element = arg4[arg2];
				if (element == null) {
					throw new HbException(errorKey,getLocaleMsg(errorKey,null));
				}
			}
		}
	}
	
	public static void noNullElements(Object[] array, String errorKey,Object[] params) {
		if (array != null) {
			Object[] arg4 = array;
			int arg3 = array.length;

			for (int arg2 = 0; arg2 < arg3; ++arg2) {
				Object element = arg4[arg2];
				if (element == null) {
					throw new HbException(errorKey,getLocaleMsg(errorKey,params));
				}
			}
		}

	}
	
	public static void isEmpty(Map<?, ?> map, String errorKey) {
		if (CollectionUtils.isEmpty(map)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void isEmpty(Map<?, ?> map, String errorKey,Object[] params) {
		if (CollectionUtils.isEmpty(map)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}

	public static void notEmpty(Map<?, ?> map, String errorKey) {
		if (!CollectionUtils.isEmpty(map)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void notEmpty(Map<?, ?> map, String errorKey,Object[] params) {
		if (!CollectionUtils.isEmpty(map)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}
	
	public static void isEmpty(Collection<?> collection, String errorKey) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void isEmpty(Collection<?> collection, String errorKey,Object[] params) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}
	
	public static void notEmpty(Collection<?> collection, String errorKey) {
		if (!CollectionUtils.isEmpty(collection)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void notEmpty(Collection<?> collection, String errorKey,Object[] params) {
		if (!CollectionUtils.isEmpty(collection)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}

	public static void doesNotContain(String textToSearch, String substring,
			String errorKey) {
		if (!StrUtil.isEmpty(textToSearch)
				&& !StrUtil.isEmpty(substring)
				&& textToSearch.contains(substring)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,null));
		}
	}
	
	public static void doesNotContain(String textToSearch, String substring,
			String errorKey,Object[] params) {
		if (!StrUtil.isEmpty(textToSearch)
				&& !StrUtil.isEmpty(substring)
				&& textToSearch.contains(substring)) {
			throw new HbException(errorKey,getLocaleMsg(errorKey,params));
		}
	}

	public static void doesNotContain(String textToSearch, String substring) {
		doesNotContain(textToSearch, substring,
				"[Assertion failed] - this String argument must not contain the substring ["
						+ substring + "]");
	}

	public static void isInstanceOf(Class<?> clazz, Object obj) {
		isInstanceOf(clazz, obj, "");
	}

	public static void isInstanceOf(Class<?> type, Object obj, String errorKey) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			if (StrUtil.isNotEmpty(errorKey)) {
				throw new HbException(errorKey,getLocaleMsg(errorKey,null));
			} else {
				String errorMessage = "Object of class ["
						+ (obj != null ? obj.getClass().getName() : "null")
						+ "] must be an instance of " + type;
				throw new HbException(errorKey,getLocaleMsg(errorKey,null));
			}
		}
	}
	
	public static void isInstanceOf(Class<?> type, Object obj, String errorKey,Object[] params) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			if (StrUtil.isNotEmpty(errorKey)) {
				throw new HbException(errorKey,getLocaleMsg(errorKey,params));
			} else {
				String errorMessage = "Object of class ["
						+ (obj != null ? obj.getClass().getName() : "null")
						+ "] must be an instance of " + type;
				throw new HbException(errorKey,getLocaleMsg(errorKey,params));
			}
		}
	}
}
