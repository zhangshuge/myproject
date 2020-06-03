package com.zc.jdk.stream;

import lombok.Getter;
import lombok.Setter;

/**
 * 艺术家实体
 * @author zhangchi
 */

@Getter
@Setter
public class Artist {

    private String isFrom;
    private String name;

    boolean isFrom(String from){
        return from.equals(isFrom);
    }
}
