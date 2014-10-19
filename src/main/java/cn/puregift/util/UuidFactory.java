package cn.puregift.util;

import java.util.*;

/**
 * UUID生成规则：
 *      timestamp(32位) + ip(16位) + 随机数(16位)
 *      timestamp(32位) = 当前系统时间 与 2014年10月1号时间的间隔秒数
 *      ip(16位) = 当前机器IP地址的后2个字节
 *      随机数(16位) = 0-65535之间的随机数，以ip做为种子.
 *
 * 备注：1. 在分布式环境下，要保证每个服务器IP的后2个字节不一样，不然生成的UUID可能会重复.
 *      2. 单台机器，若1秒内所需生成的号数量超过65535个，则生成的UUID也有可能重复.
 * Created by elvin on 14-9-30.
 */
public class UuidFactory {
    public static long ip = ApiUtil.ipToLong(ApiUtil.getLocalIp());
    public static final Random randomGenerator = new Random(ip);
    public static long baseTime = 1412092800l;   //2014年10月1号时间戳，以秒为单位.

    public static long nextId(){
        long uuid = 0;

        long currentTime = System.currentTimeMillis() / 1000;
        uuid = uuid | (currentTime-baseTime);

        uuid = (uuid << 16) | (ip & 0xffff);

        long random = (long)randomGenerator.nextInt(65535);
        uuid = (uuid << 16) | (random & 0xffff);

        return uuid;
    }

    public static long getTimeFromUuid(long uuid){
        long time = ((uuid >> 32) & 0xffffffff) + baseTime;
        return time;
    }

    public static void main(String[] args){
        long uuid = UuidFactory.nextId();

        long time = UuidFactory.getTimeFromUuid(uuid);
        System.out.println(time);

        Date date = new Date((time * 1000));
        System.out.println(date.toString());
    }
}
