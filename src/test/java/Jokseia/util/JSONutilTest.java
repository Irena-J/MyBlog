package Jokseia.util;

import Jokseia.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Date;

public class JSONutilTest {
    @Test
    public void t1() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Article article = new Article();
            article.setId(1);
            article.setTitle("IG");
            article.setContent("按时打卡，快乐不加班");
            article.setUserId(1);
            article.setCreateTime(new Date());
            // 将对象序列化为json字符串
            String s = mapper.writeValueAsString(article);
            System.out.println(s);
            // 将json字符串反序列化为java对象
            Article des = mapper.readValue(s,Article.class);
            System.out.println(des);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
