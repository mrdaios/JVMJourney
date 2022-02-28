package org.mrdaios.ijvm.lang.opcode;

import com.sun.tools.classfile.*;
import org.mrdaios.ijvm.lang.*;
import org.mrdaios.ijvm.runtime.Environment;

import java.io.IOException;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class JvmOpcodeClass implements JvmClass {

    private final JvmClassLoader classLoader;
    private final ClassFile classFile;
    private final String className;
    private final Map<Map.Entry<String, String>, JvmOpcodeMethod> methods = new HashMap<>();
    private final Map<String, JvmField> fields = new HashMap<>();

    /**
     * 是否已经初始化
     */
    private boolean inited = false;

    public JvmOpcodeClass(JvmClassLoader classLoader, Path path) throws ClassNotFoundException {
        try {
            this.classLoader = classLoader;
            this.classFile = ClassFile.read(path);
            this.className = classFile.getName();
            for (Method method : classFile.methods) {
                String name = method.getName(classFile.constant_pool);
                String desc = method.descriptor.getValue(classFile.constant_pool);
                methods.put(new AbstractMap.SimpleEntry<>(name, desc), new JvmOpcodeMethod(this, method));
            }

            prepare();
        } catch (IOException e) {
            throw new ClassNotFoundException(e.toString(), e);
        } catch (ConstantPoolException e) {
            throw new InternalError(e.toString(), e);
        }
    }

    /**
     * 准备阶段，分配静态变量，并初始化默认值，但不会执行任何字节码，在clinit会有显式的初始化器来初始化这些静态字段。
     *
     * @throws ConstantPoolException
     */
    private void prepare() throws ConstantPoolException {
        for (Field field : this.classFile.fields) {
            if (field.access_flags.is(AccessFlags.ACC_STATIC)) {
                fields.put(field.getName(classFile.constant_pool), new JvmOpcodeField.JvmOpcodeStaticField(this, field));
            } else {
                fields.put(field.getName(classFile.constant_pool), new JvmOpcodeField.JvmOpcodeObjectField(this, field));
            }
        }
    }

    /**
     * 初始化阶段
     *
     * @param environment
     * @throws Exception
     */
    public void clinit(Environment environment) throws Exception {
        if (inited) {
            return;
        }
        inited = true;
        JvmOpcodeMethod clinitMethod = methods.get(new AbstractMap.SimpleEntry<>("<clinit>", "()V"));
        if (clinitMethod != null) {
            clinitMethod.call(environment, null);
        }
    }

    @Override
    public JvmObject newInstance() throws InstantiationException {
        return null;
    }

    @Override
    public JvmMethod getMethod(String name, String desc) throws NoSuchMethodException {
        JvmOpcodeMethod method = methods.get(new AbstractMap.SimpleEntry<>(name, desc));
        if (method == null) {
            throw new NoSuchMethodException("method " + name + ":" + desc + " not exist.");
        }
        return method;
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

    public ClassFile getClassFile() {
        return classFile;
    }
}
