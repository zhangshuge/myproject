package com.zc.designpattern.commander;

import org.junit.Test;

/**
 *  客户端
 */
public class RunTest {
    @Test
    public void run(){
        Macro macro = new Macro();
        EditorImpl editor = new EditorImpl();
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);

        macro.run();
    }
}
