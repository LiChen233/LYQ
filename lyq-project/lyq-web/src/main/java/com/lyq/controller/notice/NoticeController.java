package com.lyq.controller.notice;

import com.lyq.common.result.Result;
import com.lyq.app.entity.notice.Notice;
import com.lyq.app.entity.notice.fo.NoticeFo;
import com.lyq.app.entity.notice.fo.NoticeQueryFo;
import com.lyq.app.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-03-31 19:39:42
 */
@CrossOrigin
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 查询
     */
    @GetMapping("/")
    public Result find(@RequestParam("id") String id){
        Notice notice = noticeService.getById(id);
        return Result.ok(notice);
    }

    /**
     * 查询
     */
    @GetMapping("/getNotice")
    public Result getNotice(){
        List<Notice> list = noticeService.getNotice();
        return Result.ok(list);
    }

    /**
     * 上架
     */
    @PostMapping("/push")
    public Result push(@RequestParam("id") String id){
        noticeService.push(id);
        return Result.ok();
    }

    /**
     * 下架
     */
    @PostMapping("/off")
    public Result off(@RequestParam("id") String id){
        noticeService.off(id);
        return Result.ok();
    }

    /**
     * 逻辑删除
     */
    @DeleteMapping("/")
    public Result del(@RequestParam("id") String id){
        noticeService.del(id);
        return Result.ok();
    }

    /**
     * 分页查询
     */
    @PostMapping("/queryPage")
    public Result queryPage(@RequestBody NoticeQueryFo noticeQueryFo){
        return noticeService.queryPage(noticeQueryFo);
    }


    /**
     * 插入
     */
    @PostMapping("/")
    public Result insert(@RequestBody NoticeFo noticeFo){
        return noticeService.insert(noticeFo);
    }

    /**
     * 修改
     */
    @PutMapping("/")
    public Result update(@RequestBody NoticeFo noticeFo){
        noticeService.put(noticeFo);
        return Result.ok();
    }
}
