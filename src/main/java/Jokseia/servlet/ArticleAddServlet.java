package Jokseia.servlet;

import Jokseia.dao.ArticleDAO;
import Jokseia.exception.BusinessException;
import Jokseia.model.Article;
import Jokseia.uti.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends AbstractBaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // 如果json中有的字段在java中没有，就会报错
        InputStream is = req.getInputStream();
        Article article = JSONUtil.deserialize(is,Article.class);
        // 模拟数据库插入操作
        System.out.println("================\n"+article);
        int num = ArticleDAO.insert(article);
        if (num != 1)
            throw new BusinessException("001","插入文章错误");
        // 模拟数据库插入数据操作
        return null;
    }
}
