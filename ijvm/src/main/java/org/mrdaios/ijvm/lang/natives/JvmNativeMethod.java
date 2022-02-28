package org.mrdaios.ijvm.lang.natives;

import org.mrdaios.ijvm.lang.JvmClass;
import org.mrdaios.ijvm.lang.JvmMethod;
import org.mrdaios.ijvm.runtime.Environment;

import java.lang.reflect.Method;

public class JvmNativeMethod implements JvmMethod {

    private final JvmClass clazz;
    private final Method method;
    private final String name;

    public JvmNativeMethod(JvmClass clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
        this.name = method.getName();
    }

    @Override
    public void call(Environment environment, Object thiz, Object... args) throws Exception {
        assert thiz instanceof JvmNativeObject;


    }

    @Override
    public int getParameterCount() {
        return method.getParameterCount();
    }

    @Override
    public String getName() {
        return name;
    }
}
