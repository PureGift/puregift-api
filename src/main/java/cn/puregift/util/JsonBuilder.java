package cn.puregift.util;

import java.util.Iterator;
import java.util.List;

/**
 * 用来构建json字符串.
 * Created by elvin on 14-10-3.
 */
public class JsonBuilder {
    private StringBuilder sb;

    public JsonBuilder()
    {
        this.sb = new StringBuilder();
        this.sb.append("{");
    }

    public JsonBuilder(int initCapacity) {
        this.sb = new StringBuilder(initCapacity);
        this.sb.append("{");
    }

    public JsonBuilder(String value, boolean isRawData)
    {
        this.sb = new StringBuilder();
        if (isRawData)
            this.sb.append(value);
        else
            this.sb.append("\"").append(JsonUtil.toJsonStr(value)).append("\"");
    }

    public JsonBuilder append(String name, String value) {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1)
            this.sb.append(",");

        this.sb.append("\"").append(name).append("\":");
        if (value != null)
            this.sb.append("\"").append(JsonUtil.toJsonStr(value)).append("\"");
        else {
            this.sb.append("null");
        }

        return this;
    }

    public JsonBuilder appendStrArr(String name, String[] value) {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1)
            this.sb.append(",");

        this.sb.append("\"").append(name).append("\":");
        if (value != null) {
            this.sb.append("[");
            int i = 0;
            for (String s : value) {
                if (i++ > 0) {
                    this.sb.append(",");
                }
                this.sb.append("\"").append(JsonUtil.toJsonStr(s)).append("\"");
            }
            this.sb.append("]");
        } else {
            this.sb.append("[]");
        }

        return this;
    }

    public JsonBuilder appendStrList(String name, List<String> value) {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1)
            this.sb.append(",");

        this.sb.append("\"").append(name).append("\":");
        if (value != null) {
            this.sb.append("[");
            int i = 0;
            for (String s : value) {
                if (i++ > 0) {
                    this.sb.append(",");
                }
                this.sb.append("\"").append(JsonUtil.toJsonStr(s)).append("\"");
            }
            this.sb.append("]");
        } else {
            this.sb.append("[]");
        }

        return this;
    }

    public JsonBuilder appendJsonArr(String name, JsonBuilder[] jsonArr) {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1)
            this.sb.append(",");

        this.sb.append("\"").append(name).append("\":");
        if (jsonArr != null) {
            this.sb.append("[");
            int i = 0;
            for (JsonBuilder s : jsonArr) {
                if (i++ > 0) {
                    this.sb.append(",");
                }
                this.sb.append(s.toString());
            }
            this.sb.append("]");
        } else {
            this.sb.append("[]");
        }

        return this;
    }

    public JsonBuilder appendJsonList(String name, List<JsonBuilder> jsonArr) {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1)
            this.sb.append(",");

        this.sb.append("\"").append(name).append("\":");
        if (jsonArr != null) {
            this.sb.append("[");
            int i = 0;
            for (JsonBuilder s : jsonArr) {
                if (i++ > 0) {
                    this.sb.append(",");
                }
                this.sb.append(s.toString());
            }
            this.sb.append("]");
        } else {
            this.sb.append("[]");
        }

        return this;
    }

    public JsonBuilder appendIntArr(String name, int[] values)
    {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1) {
            this.sb.append(",");
        }

        this.sb.append("\"").append(name).append("\":");
        if (values != null) {
            this.sb.append("[");
            for (int i = 0; i < values.length; ++i) {
                if (i > 0) {
                    this.sb.append(",");
                }
                this.sb.append(JsonUtil.toJsonStr(String.valueOf(values[i])));
            }
            this.sb.append("]");
        } else {
            this.sb.append("[]");
        }

        return this;
    }

    public JsonBuilder appendIntList(String name, List<Integer> values)
    {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1) {
            this.sb.append(",");
        }

        this.sb.append("\"").append(name).append("\":");
        if (values != null) {
            this.sb.append("[");
            int i = 0;
            for (Iterator iterator = values.iterator(); iterator.hasNext(); ) {
                int value = ((Integer)iterator.next()).intValue();
                if (i++ > 0) {
                    this.sb.append(",");
                }
                this.sb.append(JsonUtil.toJsonStr(String.valueOf(value)));
            }
            this.sb.append("]");
        } else {
            this.sb.append("[]");
        }

        return this;
    }

    public JsonBuilder appendLongArr(String name, long[] values)
    {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1) {
            this.sb.append(",");
        }

        this.sb.append("\"").append(name).append("\":");
        if (values != null) {
            this.sb.append("[");
            for (int i = 0; i < values.length; ++i) {
                if (i > 0) {
                    this.sb.append(",");
                }
                this.sb.append(JsonUtil.toJsonStr(String.valueOf(values[i])));
            }
            this.sb.append("]");
        } else {
            this.sb.append("[]");
        }

        return this;
    }

    public JsonBuilder appendLongList(String name, List<Long> values)
    {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1) {
            this.sb.append(",");
        }

        this.sb.append("\"").append(name).append("\":");
        if (values != null) {
            this.sb.append("[");
            int i = 0;
            for (Iterator iterator = values.iterator(); iterator.hasNext(); ) {
                long value = ((Long)iterator.next()).longValue();
                if (i++ > 0) {
                    this.sb.append(",");
                }
                this.sb.append(JsonUtil.toJsonStr(String.valueOf(value)));
            }
            this.sb.append("]");
        } else {
            this.sb.append("[]");
        }

        return this;
    }

    public JsonBuilder append(String name, boolean value) {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1)
            this.sb.append(",");

        this.sb.append("\"").append(name).append("\":").append(value);
        return this;
    }

    public JsonBuilder append(String name, int value) {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1)
            this.sb.append(",");

        this.sb.append("\"").append(name).append("\":").append(value);
        return this;
    }

    public JsonBuilder append(String name, long value) {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1)
            this.sb.append(",");

        this.sb.append("\"").append(name).append("\":").append(value);
        return this; }

    public JsonBuilder append(String name, double value) {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1)
            this.sb.append(",");

        this.sb.append("\"").append(name).append("\":").append(value);
        return this;
    }

    public JsonBuilder append(String name, JsonBuilder value) {
        if (name == null) {
            return this;
        }

        if (this.sb.length() > 1)
            this.sb.append(",");

        this.sb.append("\"").append(name).append("\":");
        if(value != null)
            this.sb.append(value.toString());
        else
            this.sb.append("{}");

        return this;
    }

    public JsonBuilder flip() {
        this.sb.append('}');
        return this;
    }

    public JsonBuilder reset() {
        this.sb.setLength(0);
        this.sb.append("{");
        return this;
    }

    public String toString()
    {
        return this.sb.toString();
    }

    public void setLength(int length) {
        this.sb.setLength(length);
    }

    public int length() {
        return this.sb.length();
    }
}
