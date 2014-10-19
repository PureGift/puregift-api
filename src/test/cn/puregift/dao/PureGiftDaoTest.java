package cn.puregift.dao;

import cn.puregift.Model.OuterAccountType;
import cn.puregift.Model.Sex;
import cn.puregift.Model.UserInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Date;

/**
 * Created by elvin on 14-10-3.
 */
public class PureGiftDaoTest {
    public static void main(String[] args){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/puregift-db.xml");

        PureGiftDao pureGiftDao = (PureGiftDao) ctx.getBean("pureGiftDao");

        /**
         * 测试UserInfo
         */
        /**
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(2l);
        userInfo.setName("xiaofan");
        userInfo.setSex(Sex.MALE);
        userInfo.setBirthday(new Date());

        System.out.println("[PureGiftDaoTest]userInfo: " + userInfo.toString());
        pureGiftDao.addUserInfo(userInfo);

        UserInfo queryUserInfo = pureGiftDao.getUserInfo(2l);
        System.out.println("[PureGiftDaoTest]queryUserInfo: " + queryUserInfo.toString());

        userInfo.setName("xiaoxiaofan");
        userInfo.setSex(Sex.FEMALE);
        pureGiftDao.updateUserInfo(userInfo);

        queryUserInfo = pureGiftDao.getUserInfo(2l);
        System.out.println("[PureGiftDaoTest]queryUserInfo: " + queryUserInfo.toString());
         */

        /**
         * 测试Outer Accout。
         */
        long outerUid = 1000l;
        OuterAccountType type = OuterAccountType.WEIBO;

        long innerUid = pureGiftDao.getInnerUidByOuterUid(outerUid, type);
        System.out.println("[PureGiftDaoTest]getInnerUidByOuterUid, innerUid=" + innerUid);

//        long newInnerUid = 2000l;
//        boolean addRet = pureGiftDao.addOuterAccount(outerUid, type, newInnerUid);
//        System.out.println("[PureGiftDaoTest]addOuterAccount, addRet=" + addRet);
        long updateInnerUid = 3000l;
        boolean updateRet = pureGiftDao.updateOuterAccount(outerUid, type, updateInnerUid);
        System.out.println("[PureGiftDaoTest]updateOuterAccount, updateRet=" + updateRet);

        innerUid = pureGiftDao.getInnerUidByOuterUid(outerUid, type);
        System.out.println("[PureGiftDaoTest]getInnerUidByOuterUid, innerUid=" + innerUid);




    }
}



