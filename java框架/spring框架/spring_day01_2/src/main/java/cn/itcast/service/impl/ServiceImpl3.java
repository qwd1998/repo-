package cn.itcast.service.impl;


import cn.itcast.service.Service;

import java.util.*;

public class ServiceImpl3 implements Service {
    private String[] myStr;
    private List<String> myList;
    private Map<String,String> myMap;
    private Set<String> mySet;
    private Properties myPro;

    public void setMyStr(String[] myStr) {
        this.myStr = myStr;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyPro(Properties myPro) {
        this.myPro = myPro;
    }

    @Override
    public void saveAccount() {
        System.out.println(Arrays.toString(myStr));
        System.out.println(myList);
        System.out.println(myMap);
        System.out.println(mySet);
        System.out.println(myPro);
    }
}
