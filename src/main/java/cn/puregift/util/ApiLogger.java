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

        infoLog.info(msg);
    }

    public static void warn(Object msg)
    {
        warnLog.warn(msg);
    }

    public static void warn(Object msg, Throwable e)
    {
        warnLog.warn(msg, e);
    }

    public static void error(Object msg)
    {
        errorLog.error(msg);
    }

    public static void error(Object msg, Throwable e)
    {
        errorLog.error(msg, e);
    }
}
