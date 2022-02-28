package org.mrdaios.ijvm;

import org.mrdaios.ijvm.lang.JvmClass;
import org.mrdaios.ijvm.lang.JvmMethod;
import org.mrdaios.ijvm.runtime.Environment;

import java.nio.file.Path;
import java.util.Hashtable;

public class VirtualMachine {

    private final JvmDefaultClassLoader classLoader;
    private final String initialClass;

    /**
     * 方法区，用于存放虚拟机加载的信息.
     */
    private final Hashtable<String, Object> methodArea = new Hashtable<>();

    public VirtualMachine(Path classPath, String initialClass) {
        this.classLoader = new JvmDefaultClassLoader(classPath);
        this.initialClass = initialClass;
    }

    public void run(String[] args) throws Exception {
        Environment environment = new Environment(this);
        JvmClass clazz = getClass(initialClass);
        // 查询入口方法--void main(String[] args)--
        JvmMethod method = clazz.getMethod("main", "([Ljava/lang/String;)V");
        // 执行入口方法
        method.call(environment, clazz, args);
    }

    public JvmClass getClass(String className) throws ClassNotFoundException {
        Object found = methodArea.get(className);
        if (null == found) {
            found = classLoader.loadClass(className);
            methodArea.put(className, found);
        }
        if (found instanceof JvmClass) {
            return (JvmClass) found;
        } else {
            return null;
        }
    }
}
