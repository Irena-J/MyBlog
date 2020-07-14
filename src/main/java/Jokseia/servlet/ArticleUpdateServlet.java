package Jokseia.servlet;

import Jokseia.dao.ArticleDAO;
import Jokseia.exception.BusinessException;
import Jokseia.model.Article;
import Jokseia.uti.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // 如果json中有的字段在java中没有，就会报错
        InputStream is = req.getInputStream();
        // http请求Content—Type为application/json，请求体是json字符串，需要反序列化为java代码
        // 需要检查json字符串的键，必须和java类型中的属性能匹配
        // 如果json中的字段在java类型中没用，被抛异常
        // 文章新增：包括title，content，userAccount
        Article article = JSONUtil.deserialize(is,Article.class);
        // 模拟数据库修改操作
        System.out.println("================\n"+article);
        int num = ArticleDAO.update(article);
        if (num != 1)
            throw new BusinessException("004","文章修改出错");
        return null;
    }
}
