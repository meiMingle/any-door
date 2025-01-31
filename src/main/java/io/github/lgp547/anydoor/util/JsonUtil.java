package io.github.lgp547.anydoor.util;

import java.lang.reflect.Type;
import java.time.temporal.Temporal;
import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 日期和时间格式化
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);
    }

    public static <T> T toJavaBean(String content, Type valueType) {
        try {
            JavaType javaType = JsonUtil.objectMapper.getTypeFactory().constructType(valueType);
            if (javaType.isTypeOrSubTypeOf(Temporal.class)) {
                content = "\"" + content + "\"";
            }
            return objectMapper.readValue(content, javaType);
        } catch (Exception e) {
            log.debug("toJavaBean exception ", e);
            throw new IllegalArgumentException(e);
        }
    }

    public static Map<String, Object> toMap(String content) {
        try {
            return objectMapper.readValue(content, Map.class);
        } catch (Exception e) {
            log.debug("toMap exception ", e);
            throw new IllegalArgumentException(e);
        }
    }

    public static String toStrNotExc(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return (String) value;
        } else {
            try {
                return objectMapper.writeValueAsString(value);
            } catch (Exception e) {
                log.debug("toStrNotExc writeValueAsString ", e);
                return null;
            }
        }
    }
}
