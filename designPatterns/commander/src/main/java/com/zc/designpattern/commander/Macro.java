package com.zc.designpattern.commander;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令宏
 */
public class Macro {
    private  final List<Action> actionList;

    public Macro() {
        this.actionList = new ArrayList<Action>();
    }

    public void record(Action action){
        actionList.add(action);
    }

    /**
     * 发起者
     */
    public void run(){
        actionList.forEach(Action::perform);
    }
}
