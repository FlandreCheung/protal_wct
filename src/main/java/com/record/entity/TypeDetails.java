package com.record.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 京茶吉鹿
 * @since 2023-03-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_type_details")
@ApiModel(value = "TypeDetails对象", description = "")
public class TypeDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("种类详情id")
    @TableId(value = "type_detais_id", type = IdType.AUTO)
    private Integer typeDetaisId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图片")
    private String imageUrl1;

    private String imageUrl2;

    private String imageUrl3;

    private String imageUrl4;

    private String imageUrl5;

    @ApiModelProperty("描述内容")
    private String introduction;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("逻辑删除")
    @TableLogic(value = "0",delval = "1")
    private Integer isDelete;
}
