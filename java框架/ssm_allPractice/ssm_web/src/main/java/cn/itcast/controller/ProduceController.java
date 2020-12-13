package cn.itcast.controller;

import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProduceController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAllProduct")
    public ModelAndView findAllProduct(){
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("productList",products);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/save")
    public String saveProduct(Product product) {
       // ModelAndView mv = new ModelAndView();
        productService.save(product);
        return "redirect:findAllProduct";
    }

    @RequestMapping("/delete")
    public String deleteProduct(@RequestParam(name = "ids") String[] ids) {
        // ModelAndView mv = new ModelAndView();
        productService.delete(ids);
        return "redirect:findAllProduct";
    }

    @RequestMapping("/findOne")
    public ModelAndView findOneProduct(String id) {
         ModelAndView mv = new ModelAndView();
        Product one = productService.findOne(id);
        mv.addObject("One",one);
        mv.setViewName("product-update");
        return mv;
    }

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
    }

}
