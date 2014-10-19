package cn.puregift.dao;

import cn.puregift.dao.impl.HashDBSplitStrategy;
import cn.puregift.util.ApiUtil;
import cn.puregift.util.UuidFactory;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by elvin on 14-9-30.
 */
public class HashDBSplitStrategyTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        HashDBSplitStrategy strategy = new HashDBSplitStrategy();
        strategy.setDbPrefix("pure_gift_");
        strategy.setDbCount(4);
        strategy.setTablePrefix("pure_gift_");
        strategy.setTableCountPerDB(256);

        long uuid = UuidFactory.nextId();
        System.out.println("[HashDBSplitStrategyTest]dbName: " + strategy.getDBName(uuid));
        System.out.println("[HashDBSplitStrategyTest]tableName: " + strategy.getTableName(uuid));
    }
}
