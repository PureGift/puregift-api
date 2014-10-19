package cn.puregift.Model;

/**
 * 第三方账号类别.
 * Created by elvin on 14-10-19.
 */
public enum OuterAccountType {
    WEIBO((byte)1),        //微博
    QQ((byte)2),        //QQ
    WEIXIN((byte)3),    //微信
    UNKNOWN((byte)0);     //未知

    OuterAccountType(byte value){
        this.value = value;
    }

    public byte get() {
        return value;
    }

    public static OuterAccountType valueOf(int value){
        for( OuterAccountType type: OuterAccountType.values() ){
            if ( value == type.value ) return type;
        }
        return UNKNOWN;
    }

    private final byte value;
}
