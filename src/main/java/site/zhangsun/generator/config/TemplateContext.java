package site.zhangsun.generator.config;

import lombok.Data;
import org.thymeleaf.context.IContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Data
public class TemplateContext<T> implements IContext {

    private Locale locale;
    private T template;

    @Override
    public Locale getLocale() {
        if (locale == null) {
            this.locale = new Locale("zh","CN");
        }
        return locale;
    }

    @Override
    public boolean containsVariable(String s) {
        return this.getVariableNames().contains(s);
    }

    @Override
    public Set<String> getVariableNames() {

        Class model = template.getClass();
        Set<String> names = new HashSet<>(10);
        while (model != null) {
            Field[] fields = model.getDeclaredFields();
            for (Field field : fields) {
                names.add(field.getName());
            }
            model = model.getSuperclass();
        }
        return names;
    }

    @Override
    public Object getVariable(String filed) {
        filed = "get" + filed.substring(0, 1).toUpperCase() + filed.substring(1);
        try {
            Method method = template.getClass().getMethod(filed);
            return method.invoke(template);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
