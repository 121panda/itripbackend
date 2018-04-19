package com.ytzl.itrip;

import com.ytzl.itrip.beans.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
public class GeneratorHandler {
    // 数据
//    private List<Table> tableList;
    // 模板所在地址
    private static String templatePath = "codeParent\\src\\main\\resources";
    private static String outPutPath = "codeParent\\src\\main\\resources\\output";
    private static String packageName = "com.ytzl.itrip";

    public static void executeModel(Table table) throws TemplateException {
        execute(table, "model.ftl", "model\\" + table.getClassName() + ".java");
    }
    public static void executeMapper(Table table) throws TemplateException {
        execute(table, "mapper-interface.ftl", "mapper-interface\\" + table.getClassName() + "Mapper.java");
    }
    public static void executeMapperXml(Table table) throws TemplateException {
        execute(table, "mapper-xml.ftl", "mapper-xml\\" + table.getClassName() + "Mapper.xml");
    }
    public static void executeService(Table table) throws TemplateException {
        execute(table, "service-interface.ftl", "service-interface\\" + table.getClassName() + "Service.java");
    }
    public static void executeServiceImpl(Table table) throws TemplateException {
        execute(table, "service-impl.ftl", "service-impl\\" + table.getClassName() + "ServiceImpl.java");
    }
    public static void execute(Table table, String templateName, String outPutName) throws TemplateException {
        Configuration conf = new Configuration();
        try {
            conf.setDirectoryForTemplateLoading(new File(templatePath));
            Map<String, Object> param = new HashMap<>();
            param.put("table", table);
            param.put("packageName", packageName);
            // 获取模板
            Template template = conf.getTemplate(templateName);

            template.process(param, new OutputStreamWriter(
                    new FileOutputStream(outPutPath + "\\" + outPutName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
