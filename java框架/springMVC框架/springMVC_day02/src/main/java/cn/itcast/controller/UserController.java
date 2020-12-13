package cn.itcast.controller;

import cn.itcast.domain.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    /**
     * 返回值是String类型,存入request域中
     * @param model
     * @return
     */
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString执行了........");

        User user = new User();
        user.setName("张三丰");
        user.setAddress("武当山");
        user.setAge(180);

        model.addAttribute("user",user);
        return "success";
    }

    /**
     * 返回值是void类型
     * @param
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("testVoid........");
        //转发
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);

        /**
         * 重定向
         */
        //response.sendRedirect(request.getContextPath()+"/index.jsp");

        /**
         * 直接响应
         */
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        response.getWriter().write("我带你们打");

        return;

    }


    @RequestMapping("/testModeAndView")
    public ModelAndView testModeAndView(){
        ModelAndView mv = new ModelAndView();

        System.out.println("testModeAndView执行了........");

        User user = new User();
        user.setName("张无忌");
        user.setAddress("明教");
        user.setAge(30);

        //把user对象存储到mv对象中，也会把user对象存入request域中
        mv.addObject("user",user);

        //跳转到那个页面
        mv.setViewName("success");
        return mv;
    }


    @RequestMapping("/testForwardOrSendRedirect")
    public String testForwardOrSendRedirect(Model model){
        System.out.println("testForwardOrSendRedirect执行了........");

        /*//转发的关键字
        return "forward:/WEB-INF/pages/success.jsp";*/

        //重定向
        return "redirect:/index.jsp";
    }

    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax执行了........");
        System.out.println(user);

        //做响应
        user.setAge(90);
        user.setName("未达到");
        System.out.println(user);
        return user;
    }

    /**
     * 传统方式上传文件
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/testFileUpload")
    public String testFileUpload(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");
        //使用flieupload组件完成上传
        //上传位置
        String path=request.getSession().getServletContext().getRealPath("/upload/");

        //判断该路径是否存在
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        //解析request
        List<FileItem> items = upload.parseRequest(request);

        //遍历
        for (FileItem item : items) {
            //进行判断，当前item对象是否是上传文件
            if (item.isFormField()){
                //说明普通表单向
            }else{
                //说明上传文件项
                //获取上传文件的名称
                String filename = item.getFieldName();
                //完成文件上传
                item.write(new File(path,filename));

                //删除临时文件
                item.delete();


            }
        }
        return "success";
    }

    /**
     * springMVC的方式上传文件
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/testFileUpload2")
    public String testFileUpload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("springMVC文件上传");
        //使用flieupload组件完成上传
        //上传位置
        String path=request.getSession().getServletContext().getRealPath("/upload/");

        //判断该路径是否存在
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }

        //说明上传文件项
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件名称变成唯一值
        String s = String.valueOf(System.currentTimeMillis());
        filename=s+filename;
        //完成文件上传
        upload.transferTo(new File(path,filename));

        return "success";
    }

    /**
     * springMVC跨服务器上传文件
     * @param upload
     * @return
     * @throws Exception
     */
    @RequestMapping("/testFileUpload3")
    public String testFileUpload3(MultipartFile upload) throws Exception {
        System.out.println("springMVC跨服务器文件上传");
        //定义上传文件服务器路径
        String path = "http://localhost:9090/uploads/";
        //判断该路径是否存在

        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件名称变成唯一值
        String s = String.valueOf(System.currentTimeMillis());
        filename = s + filename;
        //创建客户端的对象
        Client client = Client.create();

        //和图片服务器进行进行连接
        WebResource webResource = client.resource(path + filename);

        //上传文件
        webResource.put(upload.getBytes());




        return "success";
    }

}
