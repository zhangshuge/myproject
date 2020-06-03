package com.zc.route;

import com.zc.route.console.ConsoleTables;
import com.zc.route.console.FieldHandler;
import com.zc.route.console.FieldHandlerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("请输入要保存的字符串：");// 提示用户输入字符串
        Scanner scanner = new Scanner(System.in);// 获得控制台输入流
        String text = scanner.nextLine();// 获得用户输入
        System.out.println(text);

       String[] titleArray = new String[5];
       titleArray[0] = "银行编码";
       titleArray[1] = "专线地址";
       titleArray[2] = "IDC标识";
       titleArray[3] = "当前权重";
       titleArray[4] = "当前状态";

        List<FieldHandler> handlerList = new ArrayList<>();
        handlerList.add(new FieldHandlerImpl());
        handlerList.add(new FieldHandlerImpl());
        handlerList.add(new FieldHandlerImpl());
       ConsoleTables.printObjectTable(handlerList,titleArray,5,true);
    }
}
