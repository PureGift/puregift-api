package cn.puregift.util;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by elvin on 14-9-30.
 */
public class ApiUtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        long id3 = 3l;
        System.out.println("[ApiUtilTest]hash4Split: " + ApiUtil.getHash4Split(id3, 10));

        String localIPAddress1 = ApiUtil.getLocalIp();
        System.out.println("[ApiUtilTest]getLocalIp: " + localIPAddress1);

        long ipByLong = ApiUtil.ipToLong(localIPAddress1);
        System.out.println("[ApiUtilTest]ipToLong: " + ipByLong);

        String localIPAddress2 = ApiUtil.intToIp((int)ipByLong);
        System.out.println("[ApiUtilTest]intToIp: " + localIPAddress2);

        assertTrue(localIPAddress2.endsWith(localIPAddress1));
    }
}
