package com.ytzl.itrip.beans;

import java.util.List;

/**
 * 对应表的实体类
 * Created by Administrator on 2018/4/19 0019.
 */
public class Table {
    // 类名
    private String className;
    // 类名首字母小写
    private String firstLowerCaseClassName;
    // 表名
    private String tableName;
    // 字段集合
    private List<Column> columnList;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFirstLowerCaseClassName() {
        return firstLowerCaseClassName;
    }

        public void setFirstLowerCaseClassName(String firstLowerCaseClassName) {
        this.firstLowerCaseClassName = firstLowerCaseClassName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

}
