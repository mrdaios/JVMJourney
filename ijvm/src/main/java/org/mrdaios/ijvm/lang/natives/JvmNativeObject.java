package org.mrdaios.ijvm.lang.natives;

import org.mrdaios.ijvm.lang.JvmClass;
import org.mrdaios.ijvm.lang.JvmObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JvmNativeObject implements JvmObject {

    private final JvmNativeClass clazz;

    public JvmNativeObject(JvmNativeClass clazz) {
        this.clazz = clazz;
    }

    @Override
    public JvmObject getSuper() {
        throw new NotImplementedException();
    }

    @Override
    public JvmClass getClazz() {
        return clazz;
    }
}
