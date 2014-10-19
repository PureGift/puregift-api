package cn.puregift.Model;

/**
 * 性别.
 * Created by elvin on 14-10-3.
 */
public enum Sex {
    MALE((byte)1),        //男
    FEMALE((byte)2),      //女
    UNKNOWN((byte)0);     //未知

    Sex(byte value){
        this.value = value;
    }

    public byte get() {
        return value;
    }

    public static Sex valueOf(int value){
        for( Sex type: Sex.values() ){
            if ( value == type.value ) return type;
        }
        return UNKNOWN;
    }

    private final byte value;
}
