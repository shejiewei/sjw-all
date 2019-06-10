package java细节大全.enum的key_value;

/**
 * Created by shejiewei on 2019/6/10.
 */
public enum DataTypeEnums {
    SHORT("short", 1),
    INT("int", 2),
    LONG("long", 3),
    FLOAT("float", 4),
    DOUBLE("double", 5);

    private String niceName;
    private int code;


    private DataTypeEnums(String s, int code) {
        this.niceName = s;
        this.code = code;
    }

    public static DataTypeEnums getType(String name) {
        if (name == null) return null;
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) { // lame!
            return null;
        }
    }

    public static String getType(int index) {
        for (DataTypeEnums type : DataTypeEnums.values()) {
            if (type.getCode() == index) {
                return type.getNiceName();
            }
        }
        return null;
    }

    public String getNiceName() {
        return niceName;
    }

    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}