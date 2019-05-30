package com.maizi.snowflake;

import java.util.UUID;

/**
 * 高效GUID产生算法(sequence),基于Snowflake实现64位自增ID算法。 <br>
 * 优化开源项目 http://git.oschina.net/yu120/sequence
 *
 */
public class IdWorker {

    /**
     * 主机和进程的机器码
     */
    private static final Sequence worker = new Sequence();

    public static long getId() {
        return worker.nextId();
    }
    /**
     * long转换成string
     * @return string
     */
    public static String getIdLongToString() {
    	return String.valueOf(worker.nextId());
    }

    /**
     * 获取去掉"-" UUID
     * @return string
     */
    public static synchronized String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}