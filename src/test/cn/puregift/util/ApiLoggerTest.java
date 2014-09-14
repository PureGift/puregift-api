package cn.puregift.util;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by elvin on 14-7-2.
 */
public class ApiLoggerTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        ApiLogger.info("hello");
        ApiLogger.warn("warn");
        ApiLogger.error("error");
    }
}
