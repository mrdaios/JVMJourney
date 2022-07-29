package org.mrdaios.ijvm.lang.opcode;

public enum JvmOpcodeRout {

    ALOAD_0,
    ALOAD_1,
    ALOAD_2,
    ALOAD_3,
    ALOAD_4,// TODO:
    RETURN,
    GETSTATIC,
    /**
     * 调用超类构造方法
     */
    INVOKESPECIAL,
    NEW;

    public static JvmOpcodeRout valueOf(short code) {
        return null;
    }
}
