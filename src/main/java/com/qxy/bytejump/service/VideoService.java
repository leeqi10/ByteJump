package com.qxy.bytejump.service;

import com.qxy.bytejump.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qxy.bytejump.entity.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
public interface VideoService extends IService<Video> {
    Result Upload(MultipartFile file, String token, String title);

}
