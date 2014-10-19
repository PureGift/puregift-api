package cn.puregift.Model;

import java.util.Date;

/**
 * 用户信息.
 * Created by elvin on 14-10-3.
 */
public class UserInfo {
    protected long uid;         //id
    protected String name;      //昵称
    protected String headImgUrl;    //头像Url
    protected Sex sex = Sex.UNKNOWN;         //性别，1表示男，2表示女
    protected Date birthday;    //生日

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String toString(){
        return new StringBuilder(256).append("uid=").append(uid).append(", name=").append(name).append(", headImgUrl=")
                .append(headImgUrl).append(", sex=").append(sex.toString()).append(", birthday=").append(birthday.toString()).toString();
    }
}
