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
@TableName("t_article")
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章id")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("封面")
    private String cover;

    @ApiModelProperty("文章介绍")
    private String introduction;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("内容html")
    private String contentHtml;

    @ApiModelProperty("文章作者")
    private String author;

    @ApiModelProperty("发布时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDelete;
}
