package com.zc.designpattern.commander.impl;

import com.zc.designpattern.commander.Action;
import com.zc.designpattern.commander.Editor;

/**
 * close command Implementation class
 */
public class Close implements Action {
    private final Editor editor;

    public Close(Editor editor) {
        this.editor = editor;
    }

    public void perform() {
        editor.close();
    }
}
