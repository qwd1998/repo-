package cn.itcast.controller;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import cn.itcast.service.RoleService;
import cn.itcast.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    @RolesAllowed("ADMIN")  //表示只有ADMIN角色的用户可以查看
    public ModelAndView findAllRole(@RequestParam(name="page",required = true,defaultValue = "1")Integer page,@RequestParam(name="size",required = true,defaultValue = "4")Integer size){
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll(page,size);
        PageInfo roleList = new PageInfo(roles);
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

     @RequestMapping("/save")
    public String saveURole(Role role) {
       // ModelAndView mv = new ModelAndView();
         roleService.save(role);
        return "redirect:findAllRole";
    }

    @RequestMapping("/findById")
    public ModelAndView findByIdRole(String id) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role",role);
       // System.out.println(role);
        mv.setViewName("role-show");
        return mv;
    }


    @RequestMapping("/delete")
    public String deleteRole(String id) {
        // ModelAndView mv = new ModelAndView();
        roleService.delete(id);
        return "redirect:findAllRole";
    }

    @RequestMapping("/findRoleNotPermission")
    public ModelAndView findRoleNotPermission(@RequestParam(name = "id") String roleId) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role",role);
        List<Permission> permissions = roleService.findRoleNotPermission(roleId);
        mv.addObject("permissions",permissions);
        mv.setViewName("roleAndPermission-add");
        return mv;
    }
    @RequestMapping("/saveRoleAndPermission")
    public String saveRoleAndPermission(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "ids") String[] permissionIds) {

        roleService.saveRoleAndPermission(roleId,permissionIds);

        return "redirect:findAllRole";
    }



 /*
    @RequestMapping("/findOne2")
    public ModelAndView findOne2Product(String id) {
        ModelAndView mv = new ModelAndView();
        Product one = productService.findOne(id);
        mv.addObject("One",one);
        mv.setViewName("product-show");
        return mv;
    }

    @RequestMapping("/update")
    public String updateProduct(Product product) {
        productService.update(product);
        return "redirect:findAllProduct";
    }*/

}
