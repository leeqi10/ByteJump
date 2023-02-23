package com.qxy.bytejump.service.impl;

import com.qxy.bytejump.entity.User;
import com.qxy.bytejump.entity.Video;
import com.qxy.bytejump.entity.response.RePUserVideo;
import com.qxy.bytejump.entity.vo.Result;
import com.qxy.bytejump.entity.vo.VideoPlus;
import com.qxy.bytejump.mapper.UserMapper;
import com.qxy.bytejump.mapper.VideoMapper;
import com.qxy.bytejump.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxy.bytejump.utils.JwtUtil;
import com.qxy.bytejump.utils.VideoCoverUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

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
        //存放主要的路由地址
        String routing="http://192.168.1.105:8084/";
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
        String path=routing+realPath+"/"+filePath;
        //设置username
        User user = userMapper.selectById(userId);
        String userName=user.getUsername();
        //设置cover路径
        String pathImage= VideoCoverUtils.getImageAddress(routing,userId,fileName,1);
        //插入数据库
        videoMapper.insertFile(path,userName,pathImage,title);
        //返回数据设置
        Result result = new Result(0,"发布成功");
        return result;
    }

    @Override
    public RePUserVideo selectAllUserVideo(String token, String userId) {
        //可以根据token和userid是否相等进行验证，来提高安全性和隐私性
      /**
        Claims claims=null;
        String userId1;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userId1 = claims.getSubject();
        */
          //查询该用户信息
          User user = userMapper.selectById(userId);
          //查询用户的名字
          String userName = user.getUsername();
          //查询用户信息的所有视频
          List<VideoPlus> userVideos = videoMapper.selectAllVideoByUserName(userName);
          for (VideoPlus userVideo : userVideos
          ) {
              userVideo.setAuthor(user);
          }
        System.out.println(userVideos);
        RePUserVideo rePUserVideo = new RePUserVideo(0, "查询成功", userVideos);
        return rePUserVideo;
    }

    @Override
    public RePUserVideo selectAllVideo(String lastTime, String token) {
        //查询所有视频
        List<VideoPlus> videos = videoMapper.selectAllVideo();
        //查询所有用户
        List<User> users = userMapper.selectAllUser();
        //时间戳
        String time = VideoCoverUtils.getSecondTimestamp(new Date());
        //实现每个视频关联自己的用户
        //遍历每一个视频
        for (VideoPlus userVideo:videos
             ) {
            //遍历每一个用户
            for (User user:users
                 ) {
                if (userVideo.getUserName().equals(user.getUsername())){
                    userVideo.setAuthor(user);
                }
            }
        }
        RePUserVideo rePUserVideo = new RePUserVideo(0,"成功",time,videos);
        return rePUserVideo;
    }
}
