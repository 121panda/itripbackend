package com.ytzl.itrip;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static final String templatePath = "codeParent\\src\\main\\resources";

    public static void main(String[] args) throws IOException {
        Map<String, Object> param = new HashMap<>();
        param.put("title", "freemarker Demo");
        param.put("name", "张三");
        Configuration conf = new Configuration();
        try {
            // 设置模板所在地址
            conf.setDirectoryForTemplateLoading(new File(templatePath));
            // 获取模板
            Template template = conf.getTemplate("index.ftl");

            // 输出模板与数据整合的内容
            template.process(param, new OutputStreamWriter(new FileOutputStream("D:\\index\\index.html")));
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
