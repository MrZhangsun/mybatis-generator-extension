package site.zhangsun.generator.config;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import site.zhangsun.generator.pojo.bo.Person;

import java.util.List;
import java.util.Map;

public enum ThymeLeafConfig {
    INSTANCE;
    private TemplateEngine templateEngine;

    ThymeLeafConfig(){
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix(getTemplatePath());
        templateResolver.setSuffix(".tlf");
        templateResolver.setTemplateMode("TEXT");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    private String getTemplatePath(){
        return ThymeLeafConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "templates/";
    }

    public static TemplateEngine getTemplateEngine(){
        return INSTANCE.templateEngine;
    }

    public static void main(String[] args) {
        TemplateEngine templateEngine = getTemplateEngine();
        TemplateContext<Template> context = new TemplateContext<>();
        Template service = new ServiceTemplate();
        service.setAuthor("Murphy Zhang Sun");
        service.setEntity("User");
        service.setTargetPackage("site.zhangsun.generator.service");
        service.setVersion("0.0.1");
        context.setTemplate(service);
        String code = templateEngine.process("service", context);
        System.out.println(code);
    }
}
