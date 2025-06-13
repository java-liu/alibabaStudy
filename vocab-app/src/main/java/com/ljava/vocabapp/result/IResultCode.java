package com.ljava.vocabapp.result;

import java.io.Serializable;

public interface IResultCode extends Serializable {

    /**
     * 获取消息
     *
     * @return 消息字符串
     */
    String getMessage();

    /**
     * 获取状态码
     *
     * @return 状态码整数
     */
    int getCode();

    /**
     * 错误码
     *
     * @return 错误码字符串
     */
    String getErrorCode();
}
