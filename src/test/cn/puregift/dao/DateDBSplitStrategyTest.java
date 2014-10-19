package cn.puregift.dao;

import cn.puregift.dao.impl.DateDBSplitStrategy;
import cn.puregift.util.UuidFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by elvin on 14-10-2.
 */
public class DateDBSplitStrategyTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        DateDBSplitStrategy strategy = new DateDBSplitStrategy();
        strategy.setDbCount(4);
        strategy.setTableCountPerDB(256);
        strategy.setDbPrefix("pure_gift_");
        strategy.setTablePrefix("pure_gift_");

        long uuid = UuidFactory.nextId();
        System.out.println("dbName: " + strategy.getDBName(uuid));
        System.out.println("tableName: " + strategy.getTableName(uuid));
    }
}
