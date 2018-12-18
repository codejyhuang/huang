package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**
 * Created by hrym13 on 2017/8/25.
 */
public class SpecialColumn implements Serializable {

    private Integer idtSpecialColumn;//专栏ID
    private Integer columnType;      //专栏类型
    private String columnName;        //专栏名称
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getIdtSpecialColumn() {return idtSpecialColumn;}

    public void setIdtSpecialColumn(Integer idtSpecialColumn) {this.idtSpecialColumn = idtSpecialColumn;}

    public Integer getColumnType() {return columnType;}

    public void setColumnType(Integer columnType) {this.columnType = columnType;}

    public String getColumnName() {return columnName;}

    public void setColumnName(String columnName) {this.columnName = columnName;}

    @Override
    public String toString() {
        return "SpecialColumn{" +
                "idtSpecialColumn=" + idtSpecialColumn +
                ", columnType=" + columnType +
                ", columnName='" + columnName + '\'' +
                '}';
    }
}
