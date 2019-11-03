package site.zhangsun.generator.config;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

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
}
