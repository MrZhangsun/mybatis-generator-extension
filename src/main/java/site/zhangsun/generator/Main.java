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
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafTemplateAvailabilityProvider;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.context.IContext;
import site.zhangsun.generator.callback.CallBack;
import site.zhangsun.generator.config.ThymeLeafConfig;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws InvalidConfigurationException, InterruptedException,
            SQLException, IOException, XMLParserException {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("mybatis-generator-config.xml")).getFile()));
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        ProgressCallback progressCallback = new CallBack();
        myBatisGenerator.generate(progressCallback);

        List<GeneratedJavaFile> files = myBatisGenerator.getGeneratedJavaFiles();
        GeneratedJavaFile file = files.get(0);
        List<GeneratedXmlFile> xmlFiles = myBatisGenerator.getGeneratedXmlFiles();
        boolean mergeSupported = callback.isMergeSupported();
        Context mysql = config.getContext("mysql");
        List<TableConfiguration> tables = mysql.getTableConfigurations();
        tables.forEach(table -> {
            String tableName = table.getTableName();

        });

    }


}
