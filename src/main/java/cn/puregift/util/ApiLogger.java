package cn.puregift.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by elvin on 14-7-1.
 */
public class ApiLogger {
    private static Logger infoLog = Logger.getLogger("info");
    private static Logger warnLog = Logger.getLogger("warn");
    private static Logger errorLog = Logger.getLogger("error");

    static
    {
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run() {
                LogManager.shutdown();
            }

        });
    }

    public static void info(Object msg) {
        if (!(infoLog.isInfoEnabled()))
            return;

        if(null != msg)
            infoLog.info(msg.toString());
        else
            infoLog.info("null");
    }

    public static void warn(Object msg)
    {
        if(null != msg)
            warnLog.warn(msg.toString());
        else
            warnLog.warn("null");
    }

    public static void warn(Object msg, Throwable e)
    {
        if(null != msg)
            warnLog.warn(msg.toString(), e);
        else
            warnLog.warn("null", e);
    }

    public static void error(Object msg)
    {
        if(null != msg)
            errorLog.error(msg.toString());
        else
            errorLog.error("null");
    }

    public static void error(Object msg, Throwable e)
    {
        if(null != msg)
            errorLog.error(msg.toString(), e);
        else
            errorLog.error("null");
    }
}
