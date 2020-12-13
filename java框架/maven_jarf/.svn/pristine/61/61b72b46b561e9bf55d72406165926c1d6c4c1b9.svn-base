package cn.itcast.controller;

import cn.itcast.domain.Items;
import cn.itcast.service.impl.ItemsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsServiceImpl is;

    @RequestMapping("/findById")
    public ModelAndView findById(Integer id){
        Items item = is.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("item",item);
        mv.setViewName("success");
        System.out.println(item);
        return mv;
    }

}
