package com.ytzl.itrip.beans;

/**
 * 对应表中字段的实体类
 * Created by Administrator on 2018/4/19 0019.
 */
public class Column {
    // 字段名
    private String columnName;
    // 字段類型
    private String columnType;
    // 字段首字母大写
    private String firstUpperCaseColumnName;
    // 字段描述(注解)
    private String columnRemark;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getFirstUpperCaseColumnName() {
        return firstUpperCaseColumnName;
    }

    public void setFirstUpperCaseColumnName(String firstUpperCaseColumnName) {
        this.firstUpperCaseColumnName = firstUpperCaseColumnName;
    }

    public String getColumnRemark() {
        return columnRemark;
    }

    public void setColumnRemark(String columnRemark) {
        this.columnRemark = columnRemark;
    }
}
