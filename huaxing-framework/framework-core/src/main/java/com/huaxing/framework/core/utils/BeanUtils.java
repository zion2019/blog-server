package com.huaxing.framework.core.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanUtils extends org.springframework.beans.BeanUtils {
	protected final static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 反射取对象属性值
	 * 
	 * @param target
	 *            目标对象
	 * @param fieldPath
	 *            对象嵌套多级用.分隔
	 * @return
	 */
	public static Object readFieldByPath(Object target, String fieldPath) {

		try {
			String[] fieldPaths = fieldPath.split("\\.");

			PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(target, fieldPaths[0]);
			Method readMethod = propertyDescriptor.getReadMethod();
			Object value = readMethod.invoke(target);

			if (value != null && fieldPaths.length > 1) {
				return readFieldByPath(value, fieldPath.substring(fieldPaths[0].length() + 1)); // 多级嵌套递归调用
			} else {
				return value;
			}
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 复制对象List
	 * 
	 */
	public static <T> List<T> copyPropertiesByList(List<?> sourceList, Class<T> targetClass) {
		try {
			if (sourceList != null) {
				List<T> newList = new ArrayList<T>();
				for (Object obj : sourceList) {
					T target = targetClass.newInstance();
					org.springframework.beans.BeanUtils.copyProperties(obj, target);
					newList.add(target);
				}
				return newList;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
}
