package com.hrym.rpc.app.dao.model.VO;

import java.io.Serializable;

/**
 * Created by xiaohan on 2017/11/15.
 */
public class SearchResultVO implements Serializable {
    private Integer resource_id;
    private String resource_name;
    private Integer text_id;
    private String text_name;
    private String text_content;
    private Integer item_id;
    private String item_name;
    private String item_intro;
    private Integer order_num;
    private String item_pic;
    private String title_desc;
    private Integer catalogue_id;
    private String music_name;
    private String music_file;
    private String music_subtitle;
    private String singer;
    private String album_name;
    private Integer music_id;
    private String music_pic;
    private Integer type_id;
    private String type_name;
    private String text_type;

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getText_from() {
        return text_from;
    }

    public void setText_from(String text_from) {
        this.text_from = text_from;
    }

    private String text_from;

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public Integer getText_id() {
        return text_id;
    }

    public void setText_id(Integer text_id) {
        this.text_id = text_id;
    }

    public String getText_name() {
        return text_name;
    }

    public void setText_name(String text_name) {
        this.text_name = text_name;
    }

    public String getText_type() {
        return text_type;
    }

    public void setText_type(String text_type) {
        this.text_type = text_type;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    private String type_desc;
    private Integer is_support;

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_intro() {
        return item_intro;
    }

    public void setItem_intro(String item_intro) {
        this.item_intro = item_intro;
    }

    public Integer getOrder_num() {
        return order_num;
    }

    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }

    public String getItem_pic() {
        return item_pic;
    }

    public void setItem_pic(String item_pic) {
        this.item_pic = item_pic;
    }

    public String getTitle_desc() {
        return title_desc;
    }

    public void setTitle_desc(String title_desc) {
        this.title_desc = title_desc;
    }

    public Integer getCatalogue_id() {
        return catalogue_id;
    }

    public void setCatalogue_id(Integer catalogue_id) {
        this.catalogue_id = catalogue_id;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public String getMusic_file() {
        return music_file;
    }

    public void setMusic_file(String music_file) {
        this.music_file = music_file;
    }

    public String getMusic_subtitle() {
        return music_subtitle;
    }

    public void setMusic_subtitle(String music_subtitle) {
        this.music_subtitle = music_subtitle;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public Integer getMusic_id() {
        return music_id;
    }

    public void setMusic_id(Integer music_id) {
        this.music_id = music_id;
    }

    public String getMusic_pic() {
        return music_pic;
    }

    public void setMusic_pic(String music_pic) {
        this.music_pic = music_pic;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getType_desc() {
        return type_desc;
    }

    public void setType_desc(String type_desc) {
        this.type_desc = type_desc;
    }

    public Integer getIs_support() {
        return is_support;
    }

    public void setIs_support(Integer is_support) {
        this.is_support = is_support;
    }


    @Override
    public String toString() {
        return "SearchResultVO{" +
                "resource_id=" + resource_id +
                ", text_id=" + text_id +
                ", text_name='" + text_name + '\'' +
                ", text_content='" + text_content + '\'' +
                ", item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", item_intro='" + item_intro + '\'' +
                ", order_num=" + order_num +
                ", item_pic='" + item_pic + '\'' +
                ", title_desc='" + title_desc + '\'' +
                ", catalogue_id=" + catalogue_id +
                ", music_name='" + music_name + '\'' +
                ", music_file='" + music_file + '\'' +
                ", music_subtitle='" + music_subtitle + '\'' +
                ", singer='" + singer + '\'' +
                ", album_name='" + album_name + '\'' +
                ", music_id=" + music_id +
                ", music_pic='" + music_pic + '\'' +
                ", type_id=" + type_id +
                ", type_name='" + type_name + '\'' +
                ", text_type='" + text_type + '\'' +
                ", text_from='" + text_from + '\'' +
                ", type_desc='" + type_desc + '\'' +
                ", is_support=" + is_support +
                '}';
    }

}


