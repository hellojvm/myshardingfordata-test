package com.fd.test;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON字符串和 对象相互转换
 * 
 * @author 符冬
 *
 */
public final class MyJsonUtils {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public final static ObjectMapper OM = new ObjectMapper();
	static {
		OM.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		OM.setSerializationInclusion(Include.NON_NULL);
	}

	public static <T> T getObject(String json, Class<T> clazz) {
		try {
			return OM.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("MyJsonUtils:{}", e);
		}
		return null;
	}

	public static <T> T getObject(String json, TypeReference<T> trf) {
		try {
			return OM.readValue(json, trf);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("MyJsonUtils:{}", e);
		}
		return null;
	}

	public static <T> List<T> getList(String json, TypeReference<List<T>> trf) {
		try {
			return OM.readValue(json, trf);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("MyJsonUtils:{}", e);
		}
		return null;
	}

	public static String getJsonString(Object o) {
		try {
			if (o != null) {
				return OM.writeValueAsString(o);
			} else {
				log.error("getJsonString obj is null ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("MyJsonUtils:{}", e);
		}
		return "{}";
	}
}
