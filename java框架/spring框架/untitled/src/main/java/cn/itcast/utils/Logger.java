package cn.itcast.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class Logger {

    /**
     * 用于打印日志，计划让其在切入点之前执行
     */

    /**
     * 前置通知
     */
    public void beforeprintLog(){
        System.out.println("准备打印日志.............前置通知");
    }

    /**
     * 后置通知
     */
    public void afterprintLog(){
        System.out.println("准备打印日志.............后置通知");
    }

    /**
     * 异常通知通知
     */
    public void errorPrintLog(){
        System.out.println("准备打印日志.............异常通知通知");
    }

    /**
     * 最终通知通知
     */
    public void lastPrintLog(){
        System.out.println("准备打印日志.............最终通知通知");
    }


    /**
     * 环绕通知
     * 问题：当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了
     * 分析：
     *      通过对比动态代理中焕然通知代码，发现动态代理的环绕通知有明确的切入点的调用
     *解决：
     *      spring框架为我们提供了一个接口：ProceedingJoinPoint,该接口有一个方法proceed();此方法相当于调用切入点方法m
     *      该接口可以作为want通知的方法参数，在程序执行时，spring框架为为我们执行
     *spring框架的环绕通知：
     *      它是spring框架为我们提供，增强方法在代码中手动控制何时执行的方法
     *
     */

    public void aroundPringLog(ProceedingJoinPoint pjp){
        try {
            System.out.println("环绕通知............前置");
            //获取执行方法的参数
            Object[] args=pjp.getArgs();
            //调用切入点方法方法
            pjp.proceed(args);
            System.out.println("环绕通知............后置");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知............异常");
        }finally{
            System.out.println("环绕通知............最终");
        }

    }
}
