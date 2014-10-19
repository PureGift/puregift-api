package cn.puregift.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.zip.CRC32;

/**
 * Created by elvin on 14-9-27.
 */
public class ApiUtil {
    /**
     * 将id映射到[0,splitCount]里的某一个数.
     * @param id
     * @param splitCount
     * @return
     */
    public static int getHash4Split(long id, int splitCount){
        try{
            long crc32Value = getCrc32(String.valueOf(id).getBytes("utf-8"));
            if(crc32Value < 0l){
                crc32Value = -1l * crc32Value;
            }

            int hash = (int)(crc32Value / splitCount % splitCount);
            return hash;
        }catch (UnsupportedEncodingException e) {
            ApiLogger.warn("[ApiUtil getHash4Split]unsupported encoding.", e);
            return -1;
        }
    }

    /**
     * 基于字节数组计算CRC32.
     * @param data
     * @return
     */
    public static long getCrc32(byte[] data){
        CRC32 crc = (CRC32)crc32Provider.get();
        crc.reset();
        crc.update(data);
        return crc.getValue();
    }

    /**
     * 基于字符串计算CRC32.
     * @param data
     * @return
     */
    public static long getCrc32(String data){
        try {
            return getCrc32(data.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            ApiLogger.warn("[ApiUtil getCrc32]data encode error. data=" + data, e);
            return -1l;
        }
    }

    /**
     * 将int型表示的IP转换成点分十进制IP.
     * @param i
     * @return
     */
    public static String intToIp(int i) {
        return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + (i & 0xFF);
    }

    /**
     * 将点分十进制IP转换成int型表示的IP.
     * @param addr
     * @return
     */
    public static int ipToInt(final String addr) {
        final String[] addressBytes = addr.split("\\.");

        int ip = 0;
        for (int i = 0; i < 4; i++) {
            ip <<= 8;
            ip |= Integer.parseInt(addressBytes[i]);
        }
        return ip;
    }

    /**
     * 将点分十进制IP转换成int型表示的IP.
     * @param addr
     * @return
     */
    public static long ipToLong(final String addr) {
        final String[] addressBytes = addr.split("\\.");

        long ip = 0;
        for (int i = 0; i < 4; i++) {
            ip <<= 8;
            ip |= Integer.parseInt(addressBytes[i]);
        }
        return ip;
    }

    /**
     * 获取本地内网IP，多块网卡时取到一次立即返回，非localhost地址
     *
     * @return
     */
    public static String getLocalIp() {
        String localIp = null;
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress inet = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    inet = (InetAddress) addresses.nextElement();
                    if (inet.isSiteLocalAddress() && !inet.isLoopbackAddress() // 127.开头的都是lookback地址
                            && inet.getHostAddress().indexOf(":") == -1) {
                        localIp = inet.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            ApiLogger.warn("IPUtil getLocalIp fail.", e);
        }
        return localIp;
    }

    public static ThreadLocal<CRC32> crc32Provider = new ThreadLocal<CRC32>(){
        protected CRC32 initialValue(){
            return new CRC32();
        }
    };
}
