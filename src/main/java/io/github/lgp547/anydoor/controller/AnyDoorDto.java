package io.github.lgp547.anydoor.controller;

import io.github.lgp547.anydoor.core.JsonUtil;
import io.github.lgp547.anydoor.core.SpringUtil;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.List;

/**
 * className      全链路名
 * methodName     方法名
 * content        入参，要求是json类型，允许为null
 * parameterTypes 参数类型 （若是方法名是唯一的，这个parameterTypes是可选填）
 * isSync         是否同步（默认异步）
 */
public class AnyDoorDto {

    @NonNull
    private String className;

    @NonNull
    private String methodName;

    private Object content;

    private List<String> parameterTypes;

    private Boolean isSync;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getContent() {
        return content;
    }

    public String getContentStr() {
        if (content instanceof String) {
            return (String) content;
        } else {
            return JsonUtil.toJsonString(content);
        }
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(List<String> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Boolean getSync() {
        return isSync;
    }

    public void setSync(Boolean sync) {
        isSync = sync;
    }

    @Override
    public String toString() {
        return "AnyDoorDto{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", content='" + content + '\'' +
                ", parameterTypes=" + parameterTypes +
                '}';
    }

    public void verify() {
        Assert.notNull(className, "className is required");
        Assert.notNull(methodName, "methodName is required");
    }

}
