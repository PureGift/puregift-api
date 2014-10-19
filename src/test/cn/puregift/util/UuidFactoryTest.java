package cn.puregift.util;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by elvin on 14-10-2.
 */
public class UuidFactoryTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        long uuid = UuidFactory.nextId();
        System.out.println("[UuidFactoryTest]nextId: " + uuid);

        long time = UuidFactory.getTimeFromUuid(uuid);
        Date date = new Date((time * 1000));
        System.out.println("[UuidFactoryTest]getTimeFromUuid: " + date.toString());

        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyMM");
        String dateStr1 = format.format(date);
        String dateStr2 = format.format(currentDate);

        assertTrue(dateStr2.endsWith(dateStr1));
    }
}
