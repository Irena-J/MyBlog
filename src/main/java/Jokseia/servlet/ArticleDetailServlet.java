package Jokseia.servlet;

import Jokseia.dao.ArticleDAO;
import Jokseia.exception.BusinessException;
import Jokseia.exception.ClientException;
import Jokseia.model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取文章id
        String id = req.getParameter("id");
        Integer articleId;
        try {
            articleId = Integer.parseInt(id);
        } catch (Exception e) {
            throw new ClientException("001","请求参数错误:[id="+id+"]");
        }
        Article article = ArticleDAO.query(articleId);
        if (article == null)
            throw new BusinessException("002","查询不到文章详情");
        return article;
        //return testData();//测试代码以后换成数据库根据文章id查询文章操作
    }

   /* public static Article testData() {
        Article a1 = new Article();
        a1.setId(1);
        a1.setTitle("IG");
        a1.setContent("按时打卡，快乐不加班");
        a1.setUserId(1);
        a1.setCreateTime(new Date());
        return a1;
    }*/
}
