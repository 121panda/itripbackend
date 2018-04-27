package com.ytzl.itrip.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/4/27 0027.
 */
@ApiModel(value = "searchHotCityVO", description = "根据热门城市查询列表")
public class SearchHotCityVO {
    @ApiModelProperty("[必填] 城市id")
    private Integer cityId;
    @ApiModelProperty("[必填] 查询数量")
    private Integer count;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
