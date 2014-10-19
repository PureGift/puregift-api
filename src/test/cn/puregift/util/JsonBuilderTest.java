package cn.puregift.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by elvin on 14-10-4.
 */
public class JsonBuilderTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        JsonBuilder jb = new JsonBuilder();

        jb.append("boolean", true);
        jb.append("integer", 1);
        jb.append("long", 2l);
        jb.append("string", "hello");

        JsonBuilder jb1 = new JsonBuilder();
        jb1.append("jb11", "test");
        jb1.append("jb12", 3l);
        jb1.flip();

        jb.append("jsonbuilder", jb1);

        JsonBuilder jb2 = new JsonBuilder();
        jb2.append("jb21", "test");
        jb2.append("jb22", 3l);
        jb2.flip();

        JsonBuilder[] jbArray = new JsonBuilder[2];
        jbArray[0] = jb1;
        jbArray[1] = jb2;

        jb.appendJsonArr("jbArray", jbArray);

        List<JsonBuilder> jbList = new ArrayList<JsonBuilder>();
        jbList.add(jb1);
        jbList.add(jb2);
        jb.appendJsonList("jbList", jbList);

        String[] strArr = new String[]{"str1", "str2"};
        List<String> strList = new ArrayList<String>();
        strList.add("str1");
        strList.add("str2");
        jb.appendStrArr("strArr", strArr);
        jb.appendStrList("strList", strList);

        int[] intArr = new int[]{1, 2};
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(1);
        intList.add(2);
        jb.appendIntArr("intArr", intArr);
        jb.appendIntList("intList", intList);

        long[] longArr = new long[]{1l, 2l};
        List<Long> longList = new ArrayList<Long>();
        longList.add(1l);
        longList.add(2l);
        jb.appendLongArr("longArr", longArr);
        jb.appendLongList("longList", longList);

        jb.flip();

        System.out.println(jb.toString());
    }
}
