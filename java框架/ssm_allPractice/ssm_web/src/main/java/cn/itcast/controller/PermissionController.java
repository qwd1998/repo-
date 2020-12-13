package cn.itcast.controller;


import cn.itcast.domain.Permission;
import cn.itcast.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAllPermission")
    public ModelAndView findAllPermission(@RequestParam(name="page",required = true,defaultValue = "1")Integer page,@RequestParam(name="size",required = true,defaultValue = "4")Integer size){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(permissions);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String savePermission(Permission permission){
        ModelAndView mv = new ModelAndView();
         permissionService.save(permission);

        mv.setViewName("permission-list");
        return "redirect:findAllPermission";
    }

    @RequestMapping("/findOne")
    public String findByIdPermission(String id){
        ModelAndView mv = new ModelAndView();
        permissionService.findById(id);
        mv.setViewName("permission-list");
        return "redirect:findAllPermission";
    }

    @RequestMapping("/findById")
    public ModelAndView findOnePermission(String id){
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findOne(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/delete")
    public String deletePermission(String id) {
        // ModelAndView mv = new ModelAndView();
        permissionService.delete(id);
        return "redirect:findAllPermission";
    }

}
