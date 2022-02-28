package org.mrdaios.ijvm.lang;

public interface JvmClassLoader {

    JvmClass loadClass(String className) throws ClassNotFoundException;
}
