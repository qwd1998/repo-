package cn.itcast.controller;
import cn.itcast.domain.Orders;

import cn.itcast.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

   /* //未分页的情况
    @RequestMapping("/findAllOrders")
    public ModelAndView findAllOrders(){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mv.addObject("ordersList",ordersList);
        System.out.println(ordersList);
        mv.setViewName("orders-list");
        return mv;
    }*/
    //分页情况
    @RequestMapping("/findAllOrders")
    public ModelAndView findAllOrders(@RequestParam(name="page",required = true,defaultValue = "1")Integer page,@RequestParam(name="size",required = true,defaultValue = "4")Integer size){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);

        //PageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findOne")
    public ModelAndView findOneOrders(String id){
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findOne(id);

        //PageInfo就是一个分页bean
        /*PageInfo pageInfo = new PageInfo(ordersList);*/
        mv.addObject("orders",orders);
       // System.out.println(orders);
        mv.setViewName("orders-show");
        return mv;
    }





    /*@RequestMapping("/save")
    public String saveOrders(Orders orders) {
       // ModelAndView mv = new ModelAndView();
        ordersService.save(orders);
        return "redirect:findAllOrders";
    }

    @RequestMapping("/delete")
    public String deleteOrders(String[] id) {
        // ModelAndView mv = new ModelAndView();
        ordersService.delete(id);
        return "redirect:findAllOrders";
    }

    @RequestMapping("/findOne")
    public ModelAndView findOneOrders(String id) {
         ModelAndView mv = new ModelAndView();
        Orders one = ordersService.findOne(id);
        mv.addObject("One",one);
        mv.setViewName("product-update");
        return mv;
    }

    @RequestMapping("/update")
    public String updateOrders(Orders orders) {
        ordersService.update(orders);
        return "redirect:findAllOrders";
    }*/

}
