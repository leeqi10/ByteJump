package com.qxy.bytejump.service.impl;

import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.entity.Video;
import com.qxy.bytejump.entity.vo.Result;
import com.qxy.bytejump.mapper.UserMapper;
import com.qxy.bytejump.mapper.VideoMapper;
import com.qxy.bytejump.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxy.bytejump.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author leeqi10
 * @since 2023-01-25
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result Upload(MultipartFile file, String token, String title) {
        //存放的文件路径
        String filePath = "";
        //真实文件路径
        String realPath = "video";
        //解析token得到userid
        String userId;
        //解析token的claims
        Claims claims = null;
        //文件名称
        String fileName=file.getOriginalFilename();
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userId = claims.getSubject();

        File dir = new File(realPath+"/"+userId);
        //文件目录不存在，就创建一个
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        filePath=userId+"/"+fileName;
        //开始读写文件
        File file1 = new File(realPath+"/"+filePath);
        try {
            OutputStream fileOutputStream = new FileOutputStream(file1);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            InputStream fileInputStream = file.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            byte[] b=new byte[1024];
            int c;
            while((c=bufferedInputStream.read(b))!=(-1)){
                bufferedOutputStream.write(b,0,c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //地址路径
        String path="http://localhost:8084/"+realPath+"/"+filePath;
        System.out.println(path);
        //设置username
        User user = userMapper.selectById(userId);
        String userName=user.getUsername();
        //插入数据库
        videoMapper.insertFile(path,userName);
        //返回数据设置
        Result result = new Result(0,"发布成功");
        return result;
    }
}
