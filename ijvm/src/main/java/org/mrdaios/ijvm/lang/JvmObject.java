package org.mrdaios.ijvm.lang;

public interface JvmObject {

    JvmObject getSuper();

    JvmClass getClazz();
}
