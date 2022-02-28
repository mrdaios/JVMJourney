package org.mrdaios.ijvm.lang;

public interface JvmClass {

    JvmObject newInstance() throws InstantiationException;

    JvmMethod getMethod(String name, String desc) throws NoSuchMethodException;

    boolean hasMethod(String name, String desc);

    JvmField getField(String name) throws NoSuchFieldException;

    JvmClassLoader getClassLoader();

    JvmClass getSuperClass() throws ClassNotFoundException;

    String getName();

}
