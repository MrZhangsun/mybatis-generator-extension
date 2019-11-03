package site.zhangsun.generator.config;

import lombok.Data;
import org.thymeleaf.context.IContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Data
public class TemplateContext<T> implements IContext {

    private Locale locale;
    private T template;

    public TemplateContext() {

    }

    @Override
    public Locale getLocale() {
        if (locale == null) {
            this.locale = new Locale("zh","CN");
        }
        return locale;
    }

    @Override
    public boolean containsVariable(String s) {
        return false;
    }

    @Override
    public Set<String> getVariableNames() {
        Field[] declaredFields = template.getClass().getDeclaredFields();
        Set<String> names = new HashSet<>(declaredFields.length);
        for (Field field : declaredFields) {
            String name = field.getName();
            names.add(name);
        }
        return names;
    }

    @Override
    public Object getVariable(String filed) {
        Method[] declaredMethods = template.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            String methodName = method.getName();
//            try {
//                Object invoke = method.invoke(filed);
//            } catch (Exception e) {
//
//
//            }
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Template template = new ServiceTemplate();
        template.setAuthor("Murphy");
        Method method = template.getClass().getDeclaredMethod("getAuthor", String.class);
        method.invoke()
    }
}
