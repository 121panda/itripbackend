package com.ytzl.itrip.beans.vo;

/**
 * Created by Administrator on 2018/4/25 0025.
 */
public class ItripLabelDicVO {
    // id
    private Long id;
    // 描述
    private String description;
    // 姓名
    private String name;
    // 图片地址
    private String pic;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


}
