package org.mrdaios.ijvm.lang;

import org.mrdaios.ijvm.runtime.Environment;

public interface JvmMethod {

    void call(Environment environment, Object thiz, Object... args) throws Exception;

    int getParameterCount();

    String getName();
}
