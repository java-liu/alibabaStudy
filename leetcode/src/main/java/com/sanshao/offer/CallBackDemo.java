package com.sanshao.offer;

import java.util.concurrent.Callable;

public class CallBackDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        return getStr(1);
    }

    private String getStr(int i){
        return String.valueOf(i);
    }
}
