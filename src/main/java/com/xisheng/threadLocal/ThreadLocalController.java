package com.xisheng.threadLocal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengxisheng on 2018/5/20.
 *
 */
@RestController
@RequestMapping("/threadLocal/")
public class ThreadLocalController {

    /**
     *  threadLocal可以用于以下场景 传递参数，保存每个线程单独数据
     * @return
     */
    @RequestMapping("test")
    public Long test(){
        return RequestHolder.getId();
    }
}
