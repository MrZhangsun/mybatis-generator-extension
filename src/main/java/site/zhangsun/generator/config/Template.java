package site.zhangsun.generator.config;

import lombok.Data;

@Data
public class Template {
    private String targetPackage;
    private String author;
    private String version;
    private String date;
    private String entity;
}
