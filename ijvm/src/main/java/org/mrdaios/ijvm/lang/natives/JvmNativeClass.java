package org.mrdaios.ijvm.lang.natives;

import jdk.internal.org.objectweb.asm.Type;
import org.mrdaios.ijvm.lang.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class JvmNativeClass implements JvmClass {

    private final JvmClassLoader classLoader;
    private final Class nativeClass;
    private final String className;
    private final Map<Map.Entry<String, String>, JvmMethod> methods = new HashMap<>();

    public JvmNativeClass(JvmClassLoader classLoader, String className) throws ClassNotFoundException {
        Class nativeClass = Class.forName(className.replace("/", "."));
        this.classLoader = classLoader;
        this.nativeClass = nativeClass;
        this.className = nativeClass.getName();

        for (Method method : nativeClass.getMethods()) {
            methods.put(new AbstractMap.SimpleEntry<>(method.getName(), Type.getMethodDescriptor(method)), null);
        }
        for (Constructor constructor : nativeClass.getConstructors()) {
            methods.put(new AbstractMap.SimpleEntry<>(constructor.getName(), Type.getConstructorDescriptor(constructor)), null);
        }
    }

    @Override
    public JvmObject newInstance() throws InstantiationException {
        return null;
    }

    @Override
    public JvmMethod getMethod(String name, String desc) throws NoSuchMethodException {
        return null;
    }

    @Override
    public boolean hasMethod(String name, String desc) {
        return false;
    }

    @Override
    public JvmField getField(String name) throws NoSuchFieldException {
        return null;
    }

    @Override
    public JvmClassLoader getClassLoader() {
        return null;
    }

    @Override
    public JvmClass getSuperClass() throws ClassNotFoundException {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
