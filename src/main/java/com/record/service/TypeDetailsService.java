package com.record.service;

import com.record.entity.TypeDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 京茶吉鹿
 * @since 2023-03-07
 */
public interface TypeDetailsService extends IService<TypeDetails> {


    /**
     * 通过电梯种类id查询电梯信息
     * @param typeId
     * @return
     */
//    List<TypeDetails> selectTypeDetailsByTypeId(Integer typeId);


    /**
     * 通过产品id获取产品信息
     * @param typeDetailsId
     * @return
     */
    TypeDetails selectTypeDetailsByTypeDetailsId(Integer typeDetailsId);

    /**
     * 直接查询所有产品，考虑到产品肯定不会很多，就不做分页了
     * @return 产品列表
     */
    List<TypeDetails> selectAllTypeDetails();
}
