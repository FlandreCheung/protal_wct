package com.record.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.record.entity.TypeDetails;
import com.record.entity.vo.TypeDetailsQuery;
import com.record.service.TypeDetailsService;
import com.record.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 京茶吉鹿
 * @since 2023-03-07
 */
@RestController
@Api(tags = "电梯种类详情")
@CrossOrigin
public class TypeDetailsController {

    @Autowired
    private TypeDetailsService typeDetailsService;

    /**
     * 添加产品信息
     * @param typeDetails 产品信息
     * @return 结果
     */
    @ApiOperation(value = "添加产品信息")
    @PostMapping("/addTypeDetails")
    public Result addTypeDetails(@RequestBody TypeDetails typeDetails){
        boolean save = typeDetailsService.save(typeDetails);
        if(save){
            return Result.success();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改产品信息")
    @PostMapping("/updateTypeDetails")
    public Result updateTypeDetails(@RequestBody TypeDetails typeDetails){
        boolean flag = typeDetailsService.updateById(typeDetails);
        if(flag){
            return Result.success();
        }else{
            return Result.fail();
        }
    }

    /**
     * 分页查询所有产品信息
     * @param current 当前页
     * @param limit 每页记录数
     * @return 结果
     */
    @ApiOperation(value = "分页查询所有产品信息")
    @PostMapping ("/findTypeDetailsByPageList/{current}/{limit}")
    public Result findTypeDetailsByPageList(@PathVariable long current,@PathVariable long limit) {

        Page<TypeDetails> typeDetailsPage = new Page<>(current,limit);
        typeDetailsService.page(typeDetailsPage,null);
        long total = typeDetailsPage.getTotal();
        List<TypeDetails> records = typeDetailsPage.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        System.out.println("total1"+total);
        return Result.success(map);
    }

    @ApiOperation(value = "分页条件查询所有产品信息")
    @PostMapping("/findTypeDetailsConditionByPageList/{current}/{limit}")
    public Result findTypeDetailsConditionByPageList(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TypeDetailsQuery typeDetailsQuery){
        Page<TypeDetails> typeDetailsPage = new Page<>(current,limit);
        QueryWrapper<TypeDetails> wrapper = new QueryWrapper<>();
        String title = typeDetailsQuery.getTitle();
        String begin = typeDetailsQuery.getBegin();
        String end = typeDetailsQuery.getEnd();
        if(StringUtils.hasLength(title)){
            wrapper.like("title",title);
        }
        if(StringUtils.hasLength(begin)){
            wrapper.ge("create_time",begin);
        }
        if(StringUtils.hasLength(end)){
            wrapper.le("create_time",end);
        }
        typeDetailsService.page(typeDetailsPage,wrapper);
        long total = typeDetailsPage.getTotal();
        List<TypeDetails> records = typeDetailsPage.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        System.out.println("total:"+total);
        return Result.success(map);
    }

//    @ApiOperation(value = "通过电梯种类id查询所有信息")
//    @GetMapping("/findTypeDetailsByTypeId/{typeId}")
//    public Result findTypeDetailsByTypeId(@PathVariable Integer typeId){
//        List<TypeDetails> typeDetails = typeDetailsService.selectTypeDetailsByTypeId(typeId);
//        return Result.success(typeDetails);
//    }

    @ApiOperation(value = "直接查询所有产品(用于网站展示)")
    @GetMapping("/findAllTypeDetails")
    public Result findAllTypeDetails(){
        List<TypeDetails> typeDetails = typeDetailsService.selectAllTypeDetails();
        return Result.success(typeDetails);
    }


    @ApiOperation(value = "通过产品id获取信息")
    @GetMapping("/findTypeDetailsByTypeDetailsId/{typeDetailsId}")
    public Result findTypeDetailsByTypeDetailsId(@PathVariable Integer typeDetailsId){
        TypeDetails typeDetails = typeDetailsService.selectTypeDetailsByTypeDetailsId(typeDetailsId);
        return Result.success(typeDetails);
    }

    @ApiOperation(value = "通过产品id删除产品")
    @DeleteMapping("/removeTypeDetails/{id}")
    public Result removeTypeDetails(@PathVariable String id){
        boolean flag = typeDetailsService.removeById(id);
        if(flag){
            return Result.success();
        }else{
            return Result.fail();
        }
    }
}
