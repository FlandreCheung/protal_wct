package com.record.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.record.entity.Article;
import com.record.entity.TypeDetails;
import com.record.entity.vo.ArticleQuery;
import com.record.service.ArticleService;
import com.record.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "文章")
@RestController
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    private static final Integer PAGE_SIZE = 6;

    @ApiOperation(value = "获取所有的文章(分页)")
    @GetMapping("/findArticles/{pageIndex}")
    public Result findArticles(@PathVariable Integer pageIndex){
        /* Page<Article> page = new Page<>(pageIndex,PAGE_SIZE);
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Article::getCreateTime);
        Page<Article> articlePage = articleService.page(page, lambdaQueryWrapper); */

        Page<Article> articlePage = articleService.selectArticles(pageIndex);
        return Result.success(articlePage);
    }


    @ApiOperation(value = "通过文章id查询文章信息")
    @GetMapping("/findArticleByArticleId/{articleId}")
    public Result findArticleByArticleId(@PathVariable Integer articleId){
        Article article = articleService.selectArticleByArticleId(articleId);
        return Result.success(article);
    }


    @ApiOperation(value = "查找非当前文章的文章信息")
    @GetMapping("/findRecentArticle/{currentArticleId}")
    public Result findRecentArticle(@PathVariable Integer currentArticleId){
        List<Article> list = articleService.selectRecentArticle(currentArticleId);
        return Result.success(list);
    }

    @ApiOperation(value = "分页条件查询所有新闻")
    @PostMapping("/findNewsConditionByPageList/{current}/{limit}")
    public Result findNewsByPageList(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) ArticleQuery articleQuery){
        Page<Article> articlePage = new Page<>(current,limit);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        String title = articleQuery.getTitle();
        String begin = articleQuery.getBegin();
        String end = articleQuery.getEnd();
        if(StringUtils.hasLength(title)){
            wrapper.like("title",title);
        }
        if(StringUtils.hasLength(begin)){
            wrapper.ge("create_time",begin);
        }
        if(StringUtils.hasLength(end)){
            wrapper.le("create_time",end);
        }
        articleService.page(articlePage,wrapper);
        long total = articlePage.getTotal();
        List<Article> records = articlePage.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        System.out.println("total:"+total);
        return Result.success(map);
    }

    @ApiOperation(value = "添加文章")
    @PostMapping("/addArticle")
    public Result addArticle(@RequestBody Article article){
        boolean save = articleService.save(article);
        if(save){
            return Result.success();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改文章")
    @PostMapping("/updateArticle")
    public Result updateArticle(@RequestBody Article article){
        boolean flag = articleService.updateById(article);
        if(flag){
            return Result.success();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation(value = "通过产品id删除产品")
    @DeleteMapping("/removeArticle/{id}")
    public Result removeArticle(@PathVariable String id){
        boolean flag = articleService.removeById(id);
        if(flag){
            return Result.success();
        }else{
            return Result.fail();
        }
    }
}
