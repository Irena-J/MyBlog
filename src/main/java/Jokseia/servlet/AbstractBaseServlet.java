package Jokseia.servlet;

import Jokseia.exception.BaseException;
import Jokseia.model.Result;
import Jokseia.uti.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractBaseServlet extends HttpServlet {

    private static final ConcurrentMap<String,Integer> MAP = new ConcurrentHashMap<>();

    private static final ConcurrentMap<String, AtomicInteger> MAP2 = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        // 前端约定好的统一返回的json格式
        Result result = new Result();
        try {
            Object data = process(req,resp);
            result.setSuccess(true);
            result.setData(data);
        } catch (Exception e) {//捕获异常，需要设置前端需要的错误信息和堆栈信息
            if (e instanceof BaseException) {
                BaseException be = (BaseException) e;
                result.setMessage("错误码："+be.getCode()+"错误信息"+be.getMessage());
            } else {
                result.setMessage("服务器异常：位置错误");
            }

            StringWriter sw = new StringWriter();
            PrintWriter epw = new PrintWriter(sw);
            e.printStackTrace(epw);
            result.setStackTrace(sw.toString());
        }
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.serialize(result));//返回json字符串
        pw.flush();

        // 协议和
        System.out.println("Scheme:"+req.getScheme());
        // servlet路径 /articleList
        System.out.println("ServletPath:"+req.getServletPath());
        // 应用部署路径就是全路径 /blog
        System.out.println("ContexPath:"+req.getContextPath());
        // 请求的全路径
        System.out.println("RequestURL"+req.getRequestURL());
        // 上下文路径+servlet路径
        System.out.println("RequertURI"+req.getRequestURI());
        // null
        System.out.println("PathInfo"+req.getPathInfo());

        synchronized (MAP) {
            String path = req.getServletPath();
            Integer count = MAP.get(path);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            MAP.put(path, count);
        }

        // 方法二，通过AtomicTnteger结合ConcurentHashMap
        String path = req.getServletPath();
        // ConcurrentHashMap.putIfAbsent(),如果没有键存在，就保存给定的键值对，返回null
        AtomicInteger count = MAP2.putIfAbsent(path,new AtomicInteger(1));
        if (count != null)
            count.incrementAndGet();
    }

    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;

    public static ConcurrentMap<String,Integer> getMap() {
        return MAP;
    }

    public static ConcurrentMap<String,AtomicInteger> getMAP2() {
        return MAP2;
    }
}
