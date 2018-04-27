package com.ytzl.itrip.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/4/20 0020.
 */
@ApiModel(value = "itripHotelRoomVO", description = "酒店房间业务模块")
public class ItripHotelRoomVO {
    // 酒店id
    @ApiModelProperty(value = "[必填]酒店id", required = true) // 对model的属性的详细描述
    private Long hotelId;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

}
