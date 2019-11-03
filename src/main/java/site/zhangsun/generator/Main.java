package site.zhangsun.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.thymeleaf.TemplateEngine;
import site.zhangsun.generator.callback.CallBack;
import site.zhangsun.generator.config.MyCommentConfig;
import site.zhangsun.generator.config.ServiceTemplate;
import site.zhangsun.generator.config.Template;
import site.zhangsun.generator.config.TemplateContext;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static site.zhangsun.generator.config.ThymeLeafConfig.getTemplateEngine;

public class Main {

    public static void main(String[] args) throws InvalidConfigurationException, InterruptedException,
            SQLException, IOException, XMLParserException {

        // DAO 生成
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("mybatis-generator-config.xml")).getFile()));
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        ProgressCallback progressCallback = new CallBack();
        myBatisGenerator.generate(progressCallback);

        // Service 接口生成
        List<GeneratedJavaFile> files = myBatisGenerator.getGeneratedJavaFiles();
        GeneratedJavaFile file = files.get(0);
        List<GeneratedXmlFile> xmlFiles = myBatisGenerator.getGeneratedXmlFiles();
        boolean mergeSupported = callback.isMergeSupported();
        Context mysql = config.getContext("mysql");
        List<TableConfiguration> tables = mysql.getTableConfigurations();

        // site.zhangsun.generator.service
        String serviceTargetPackage = mysql.getJavaClientGeneratorConfiguration().getTargetPackage().replace("mapper", "service");
        String targetProject = mysql.getJavaClientGeneratorConfiguration().getTargetProject();
        MyCommentConfig commentGenerator = (MyCommentConfig) mysql.getCommentGenerator();

        tables.forEach(table -> {
            String domainObjectName = table.getDomainObjectName();
            String shortName = domainObjectName.replace("Entity", "");
            TemplateEngine templateEngine = getTemplateEngine();
            TemplateContext<Template> context = new TemplateContext<>();

            Template service = new ServiceTemplate();
            service.setAuthor(commentGenerator.getAuthor());
            service.setEntity(shortName);
            service.setTargetPackage(serviceTargetPackage);
            service.setVersion(commentGenerator.getSince());
            service.setDate(commentGenerator.getDate());
            context.setTemplate(service);
            String code = templateEngine.process("service", context);
            System.out.println(code);
        });

    }


}
