package com.ytzl.itrip;

import com.ytzl.itrip.beans.Table;
import freemarker.template.TemplateException;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
public class Test {
    public static void main(String[] args) throws TemplateException {
        List<Table> tableList = TableHandler.getTables();
        for (Table table: tableList
             ) {
//            GeneratorHandler.execute(table);
            GeneratorHandler.executeMapper(table);
            GeneratorHandler.executeMapperXml(table);
            GeneratorHandler.executeModel(table);
            GeneratorHandler.executeService(table);
            GeneratorHandler.executeServiceImpl(table);
        }
    }
}
