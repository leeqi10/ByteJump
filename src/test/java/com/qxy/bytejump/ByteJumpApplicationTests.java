package com.qxy.bytejump;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qxy.bytejump.entity.Video;
import com.qxy.bytejump.mapper.VideoMapper;
import com.qxy.bytejump.utils.JwtUtil;
import com.qxy.bytejump.utils.VideoCoverUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.swing.plaf.synth.SynthToggleButtonUI;


@SpringBootTest
class ByteJumpApplicationTests {
    @Autowired
    private VideoMapper videoMapper;

    @Test
    void setRestTemplate() {

    }

    /**
     * @description this is a test,which makes sure token made true.
     */
    @Test
    public void testPasswordEncoder() {
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
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0YmQxNTM0NDUxNDM0ZjZiOWMwYWY4NjM2NTg1NTk2NCIsInN1YiI6IjE2IiwiaXNzIjoic2ciLCJpYXQiOjE2NzY4ODA4NTgsImV4cCI6MTY3Njg4NDQ1OH0.GfVzmIkMoxoDMknm7Xeqa3LlvbQe2jteY3kc8pk-ulI";
        String userId;
        Claims claims = JwtUtil.parseJWT(token);
        userId = claims.getSubject();
        System.out.println(userId);
    }

    @Test
    public void testVideoInsert() {
        videoMapper.insertFile("http://localhost:8084/video/11/VID_20230121_191709.mp4", "11", "ss", "sd");
    }

    @Test
    public void testVideoImage() {
        Map<String, Object> vedioImg = VideoCoverUtils.getVedioImg("D:", "1234.MP4", 20);
        System.out.println(vedioImg);
    }

    @Test
    public void testAddress() {
        System.out.println(System.getProperty("user.dir"));
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            String corrected = correctSpelling(str);
            System.out.println(corrected);
        }

        scanner.close();
    }

    public static String correctSpelling(String str) {
        StringBuilder corrected = new StringBuilder();
        int len = str.length();

        // 遍历字符串
        for (int i = 0; i < len; i++) {
            char currentChar = str.charAt(i);
            corrected.append(currentChar);

            // 规则1：三个连续相同字符，去除一个
            if (i > 1 && currentChar == str.charAt(i - 1) && currentChar == str.charAt(i - 2)) {
                corrected.deleteCharAt(corrected.length() - 1);
            }

            // 规则2：两对连续相同字符，去除第二对的一个字符
            if (i > 2 && currentChar == str.charAt(i - 1) && str.charAt(i - 2) == str.charAt(i - 3)) {
                corrected.deleteCharAt(corrected.length() - 1);
            }
        }

        return corrected.toString();
    }

    @Test
    public void Translate() throws JsonProcessingException {
        String s = translateText("虎跃线路安全名称", "zh", "en", "common");

        System.out.println(extractDstValue(s));
    }

    private static String extractDstValue(String input) {
        // 找到 "src":" 索引
        int srcIndex = input.indexOf("\"src\":\"");
        if (srcIndex == -1) {
            return null; // 未找到 "src":"，返回 null 或者其他错误处理
        }

        // 截取 "src":" 索引之后的子字符串
        String substringAfterSrc = input.substring(srcIndex + "\"src\":\"".length());

        // 找到 "dst":" 索引
        int dstIndex = substringAfterSrc.indexOf("\"dst\":\"");
        if (dstIndex == -1) {
            return null; // 未找到 "dst":"，返回 null 或者其他错误处理
        }

        // 截取 "dst":" 索引之后的子字符串，直到下一个双引号结束
        String dstValue = substringAfterSrc.substring(dstIndex + "\"dst\":\"".length(), substringAfterSrc.indexOf("\"", dstIndex + "\"dst\":\"".length()));
        dstValue = dstValue.toUpperCase().replaceAll(" ", "_");
        return dstValue;
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String apiUrl = "https://fanyi.baidu.com/ait/text/translate";

    private final RestTemplate restTemplate = new RestTemplate();

    public String translateText(String query, String from, String to, String domain) {
        // 构建请求体
        String requestBody = String.format("{\"query\":\"%s\",\"from\":\"%s\",\"to\":\"%s\",\"domain\":\"%s\"}",
                query, from, to, domain);

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 封装请求体和请求头
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // 发送POST请求
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            System.err.println("Failed to translate text. Status code: " + response.getStatusCode());
            return null;
        }
    }

    @Test
    public void translateWebclient() {
        String apiUrl = "https://fanyi.baidu.com/ait/text/translate";
        String query = "你好";
        String from = "zh";
        String to = "en";
        String domain = "common";

        WebClient client = WebClient.create();

        // 构建请求体参数
        String requestBody = String.format("{\"query\":\"%s\",\"from\":\"%s\",\"to\":\"%s\",\"domain\":\"%s\"}",
                query, from, to, domain);

        Flux<String> responseFlux = client.post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToFlux(String.class);

        responseFlux.subscribe(response -> {
            // 处理每个事件的响应
            System.out.println("Response: " + response);
        }, Throwable::printStackTrace);
    }

    @Test
    public void translateWebclient2() {
        Video video = new Video();
        video.setId(0);
        for (int i = 0; i < 100; i++) {

            video.setId(video.getId() + i);
        }
        System.out.println(video);

    }

    @Test

    public void translateWebclient3() {

        System.out.println(translateWebclient1());

    }

    public static int translateWebclient1() {
        int nums[] = {1, 0, 2};

        int n = nums.length;

        // 将正整数移动到正确的位置
        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        // 找到第一个缺失的正整数
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    public static List<List<Integer>> flipImage(int width, int[] pixels) {
        List<List<Integer>> result = new ArrayList<>();

        List<List<Integer>> result1 = new ArrayList<>();
        // 遍历每个像素点，每个像素点由四个值表示
        for (int i = 0; i < pixels.length; i += 4) {
            // 将当前像素点的四个值添加到临时列表中
            List<Integer> pixel = new ArrayList<>();
            for (int j = i; j < i + 4; j++) {
                pixel.add(pixels[j]);
            }
            result.add(pixel);
            // 根据宽度进行翻转操作，每次添加到结果列表中
            if ((i + 4) % (4 * width) == 0) {
                Collections.reverse(result);

                result1.addAll(result);
                result.clear();
            }


        }

        return result1;
    }

    public static List<List<Integer>> flipImage1(int width, int[] pixels) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> result1 = new ArrayList<>();
        // 遍历每个像素点，每个像素点由四个值表示
        for (int i = 0; i < pixels.length; i += 4) {
            // 将当前像素点的四个值添加到临时列表中
            List<Integer> pixel = new ArrayList<>();
            for (int j = i; j < i + 4; j++) {
                pixel.add(pixels[j]);
            }
            result.add(pixel);
            // 根据宽度进行翻转操作，每次添加到结果列表中
            if ((i + 4) % (4 * width) == 0) {
                Collections.reverse(result);
                result1.addAll(result);
                result.clear();
            }
        }
        return result1;
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums   int整型一维数组
     * @param target int整型
     * @return int整型
     */
    public static int search(int[] nums, int target) {
        // write code here
        if (nums.length == 0) {
            return -1;
        }

        //声明最小值临时索引
        int min = 0;
        //首先声明数列的中间临时索引
        int center = (nums.length - 1) / 2;

        //声明最大值临时索引
        int max = nums.length - 1;
        for (int i = 0; i <= nums.length; i++) {

            if (target == nums[center]) {
                return center;
            }
            if (target > nums[center]) {
                min = center;
                center = (max + center) / 2;

            } else {
                max = center;
                center = (min + center) / 2;

            }


        }

        return -1;

    }

    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    private static void postOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null)
            return;

        postOrderHelper(node.left, result); // 先递归遍历左子树
        postOrderHelper(node.right, result); // 再递归遍历右子树
        result.add(node.val); // 将当前节点的值添加到结果列表中
    }


    public static int[] verticalFlip(int width, int[] pixelMap) {
        int unitSize = 4; // 每个像素由4个数字表示
        int unitCount = pixelMap.length / unitSize; // 计算像素单元个数

        for (int i = 0; i < unitCount / width; i++) {
            int startIndex = i * width * unitSize;
            int endIndex = (i + 1) * width * unitSize - unitSize; // 修正 endIndex

            while (startIndex < endIndex) {
                // 交换像素单元的两组数据
                for (int j = 0; j < unitSize; j++) {
                    int temp = pixelMap[startIndex + j];
                    pixelMap[startIndex + j] = pixelMap[endIndex + j];
                    pixelMap[endIndex + j] = temp;
                }
                startIndex += unitSize;
                endIndex -= unitSize;
            }
        }

        return pixelMap;
    }


    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList();
        //声明一个全局变量规则
        int status = 0;
        //遍历矩阵数组
        int n = matrix.length;
        int m = matrix[0].length;
        int sum = m * n;
        int i = 0;
        int j = 0;
        //增加四个全局变量 支持他旋转记录
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 1;
        while (sum > 0) {
            if (status == 0 && j + a < m) {
                list.add(matrix[i][j]);
                j++;
                sum--;
                if (j + a == m) {
                    status = 1;
                    i++;
                    a++;
                }
                continue;
            } else if (status == 1 && i + b < n) {
                list.add(matrix[i][j - 1]);
                i++;
                sum--;
                if (i + b == n) {
                    status = 2;
                    j--;
                    b++;
                }
                continue;
            } else if (status == 2 && j - c > 0) {
                list.add(matrix[i - 1][j - 1]);
                j--;
                sum--;
                if (j - c == 0) {
                    status = 3;
                    i--;
                    c++;
                }
                continue;
            } else if (status == 3 && i - d > 0) {
                list.add(matrix[i - 1][j]);
                i--;
                sum--;
                if (i - d == 0) {
                    status = 0;
                    j++;
                    d++;
                }
                continue;
            }

        }
        return list;
    }

    public long getMaxMatrix(int[][] matrix) {
        //有m*n矩阵，已知矩阵每个位置为一int类型数值，求其子矩阵的最大值。
        // 返回最大子矩阵的数值
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                for (int k = i; k < m; k++) {
                    for (int l = j; l < n; l++) {
                        int sum = 0;
                        for (int x = i; x <= k; x++) {
                            for (int y = j; y <= l; y++) {
                                sum += matrix[x][y];
                            }
                        }

                        if (sum > max) {
                            max = sum;
                        }
                    }
                }
            }
        }
        return max;
    }

    //S = s1 s2…s2n 是一个格式良好的括号字符串。S可以用两种不同的方式编码： q 通过一个整数序列P = p1 p2…pn，其中pi是S中第i个右括号之前的左括号数量（P序列）。 q 通过一个整数序列W = w1 w2…wn，对于S中的每个右括号a，我们关联一个整数，该整数是从a的匹配左括号开始到a为止的右括号数量。（W序列）。
    //以下是上述编码的一个例子：
    //S  (((()()())))
    //P-sequence  4 5 6 6 6 6
    //W-sequence  1 1 1 4 5 6
    //编写一个程序，将格式良好的字符串的P序列转换为相同字符串的W序列。
    // typeP  = 4 5 6 6 6 6
    public int[] transform(int[] typeP) {
        // write code here

        // 3 5 5 5 5
        // 1 1 2 4 5

        //4 4 6 6 6 6
        //1 2 1 2 5 6

        //4 4 4 4 5 6
        //1 2 3 4 1 1
        int temp = 1;
        int[] result = new int[typeP.length];
        for (int i = 0; i < typeP.length - 1; i++) {
            if (typeP[i] == typeP[i + 1]) {
                result[i] = temp;
                temp++;
                result[i + 1] = temp;
            } else {
                result[i] = temp;
                temp = 1;
                result[i + 1] = temp;
            }
        }
        return result;
    }
}
