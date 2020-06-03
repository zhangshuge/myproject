package com.zc.jdk.balls;

import lombok.Getter;
import lombok.Setter;


import lombok.extern.slf4j.Slf4j;



import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter

public class Sports {
    private List<Ball> balllist;

    public  Stream<Ball> getSports(){
//        log.debug("");
        return balllist.stream();
    }
}
