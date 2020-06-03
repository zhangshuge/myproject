package com.zc.designpattern.commander.impl;

import com.zc.designpattern.commander.Action;
import com.zc.designpattern.commander.Editor;

/**
 * save command Implementation class
 */
public class Save implements Action {

    private final Editor editor;

    public Save(Editor editor) {
        this.editor = editor;
    }

    public void perform() {
        editor.save();
    }
}
