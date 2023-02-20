package com.qxy.bytejump;

import com.qxy.bytejump.mapper.VideoMapper;
import com.qxy.bytejump.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ByteJumpApplicationTests {
    @Autowired
    private VideoMapper videoMapper;
    @Test
    void contextLoads() {
    }
    @Test
    public void testPasswordEncoder(){
        PasswordEncoder ps = new BCryptPasswordEncoder();
        String encode = ps.encode("447789814");
        //String encode2 = ps.encode("1234");
        System.out.println(encode);
        //System.out.println(encode2);
        //$2a$10$UViL.jTzZHy/m7K29SuwPenDT5s5XcfIoSHoEJImRBjbsnok3Y7Nu
        System.out.println(ps.matches("447789814",
                "$2a$10$BYoaSw87gyLdL677K0RpJOG1yPiG7CBkZ5GWL6J3YS4dt776xkrdq"));
    }
    @Test
    public void testToken() throws Exception {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0YmQxNTM0NDUxNDM0ZjZiOWMwYWY4NjM2NTg1NTk2NCIsInN1YiI6IjE2IiwiaXNzIjoic2ciLCJpYXQiOjE2NzY4ODA4NTgsImV4cCI6MTY3Njg4NDQ1OH0.GfVzmIkMoxoDMknm7Xeqa3LlvbQe2jteY3kc8pk-ulI";
        String userId;
        Claims claims = JwtUtil.parseJWT(token);
        userId = claims.getSubject();
        System.out.println(userId);
    }
    @Test
    public void testVideoInsert(){
        videoMapper.insertFile("http://localhost:8084/video/11/VID_20230121_191709.mp4","11");
    }

}
