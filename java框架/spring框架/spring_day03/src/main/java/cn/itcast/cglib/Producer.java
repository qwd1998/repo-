package cn.itcast.cglib;

public class Producer {
    public void seleProduct(float money){

        System.out.println("销售产品:"+money);
    }

    public void afterService(float money){

        System.out.println("售后服务:"+money);
    }

}
