package cn.puregift.Model;

import java.util.Date;

/**
 * 第三方账号.
 * Created by elvin on 14-10-19.
 */
public class OuterAccount {
    protected long outerUid;    //第三方账号uid
    protected OuterAccountType type;    //第三方账号类别
    protected long innerUid;    //站内账号uid
    protected String token;     //用于请求第三方服务时用的token
    protected Date lastLoginDate;   //最近一次登录时间戳
    protected String lastLoginIP;   //最近一次登录IP


    public long getOuterUid() {
        return outerUid;
    }

    public void setOuterUid(long outerUid) {
        this.outerUid = outerUid;
    }

    public OuterAccountType getType() {
        return type;
    }

    public void setType(OuterAccountType type) {
        this.type = type;
    }

    public long getInnerUid() {
        return innerUid;
    }

    public void setInnerUid(long innerUid) {
        this.innerUid = innerUid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }
}
