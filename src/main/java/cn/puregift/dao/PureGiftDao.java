package cn.puregift.dao;

import cn.puregift.Model.OuterAccount;
import cn.puregift.Model.OuterAccountType;
import cn.puregift.Model.UserInfo;

/**
 * DB操作接口.
 * Created by elvin on 14-7-5.
 */
public interface PureGiftDao {
    /**
     * 保存用户信息.
     * @param newUser
     * @return
     */
    public boolean addUserInfo(UserInfo newUser);

    /**
     * 获取某个用户的信息.
     * @param userId
     * @return
     */
    public UserInfo getUserInfo(long userId);

    /**
     * 更新用户信息.
     * @param updateUser
     * @return
     */
    public boolean updateUserInfo(UserInfo updateUser);

    /**
     * 获取第三方账号对应的站内uid.
     * @param outerUid
     * @param type
     * @return
     */
    public long getInnerUidByOuterUid(long outerUid, OuterAccountType type);

    /**
     * 新增第三方账号
     * @param outerUid
     * @param type
     * @param innerUid
     * @return
     */
    public boolean addOuterAccount(long outerUid, OuterAccountType type, long innerUid);

    /**
     * 更新第三方账号.
     * @param outerUid
     * @param type
     * @param innerUid
     * @return
     */
    public boolean updateOuterAccount(long outerUid, OuterAccountType type, long innerUid);
}
