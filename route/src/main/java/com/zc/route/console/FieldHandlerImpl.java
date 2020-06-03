package com.zc.route.console;

public class FieldHandlerImpl implements FieldHandler{
    @Override
    public String[] getFieldStringArr() {
        String[] strArray = new String[10];
        strArray[0] = "工商银行";
        strArray[1] = "5.1.8.12";
        strArray[2] = "10";
        strArray[3] = "99";
        strArray[4] = "启用";
        return strArray;
    }
}
