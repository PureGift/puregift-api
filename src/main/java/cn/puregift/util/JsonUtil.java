package cn.puregift.util;

/**
 * Created by elvin on 14-10-4.
 */
public class JsonUtil {
    public static String toJsonStr(String value)
    {
        if (value == null) {
            return null;
        }

        StringBuilder buf = new StringBuilder(value.length());
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            switch (c)
            {
                case '"':
                    buf.append("\\\"");
                    break;
                case '\\':
                    buf.append("\\\\");
                    break;
                case '\n':
                    buf.append("\\n");
                    break;
                case '\r':
                    buf.append("\\r");
                    break;
                case '\t':
                    buf.append("\\t");
                    break;
                case '\f':
                    buf.append("\\f");
                    break;
                case '\b':
                    buf.append("\\b");
                    break;
                default:
                    if ((c < ' ') || (c == ''))
                        buf.append(" ");
                    else {
                        buf.append(c);
                    }
            }
        }

        return buf.toString();
    }
}
