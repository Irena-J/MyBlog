package Jokseia.servlet;

import Jokseia.dao.ArticleDAO;
import Jokseia.exception.BusinessException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends AbstractBaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String idsString = req.getParameter("ids");
        int[] ids = parseIds(idsString);
        int num = ArticleDAO.delete(ids);
        if (num == 0)
            throw new BusinessException("005","文章删除出错");
        // 把ids传递到数据库
        return null;
    }

    public static int[] parseIds(String idString) {
        String[] idsArray = idString.split(",");
        int[] ids = new int[idsArray.length];
        for (int i = 0; i < idsArray.length; ++i) {
            ids[i] = Integer.parseInt(idsArray[i]);
        }
        return ids;
    }
}
