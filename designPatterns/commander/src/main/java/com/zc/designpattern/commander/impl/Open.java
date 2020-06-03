package com.zc.designpattern.commander.impl;

import com.zc.designpattern.commander.Action;
import com.zc.designpattern.commander.Editor;

/**
 * open command Implementation class
 */
public class Open implements Action {

    private final Editor editor;

    public Open(Editor editor) {
        this.editor = editor;
    }

    public void perform() {
        editor.open();
    }
}
