package org.mrdaios.ijvm.lang.opcode;

import com.sun.tools.classfile.AccessFlags;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.Method;
import org.mrdaios.ijvm.lang.JvmMethod;
import org.mrdaios.ijvm.runtime.Environment;
import org.mrdaios.ijvm.runtime.JvmStack;
import org.mrdaios.ijvm.runtime.JvmStackFrame;
import org.mrdaios.ijvm.runtime.Slots;


public class JvmOpcodeMethod implements JvmMethod {

    private final JvmOpcodeClass clazz;
    private final Method method;
    private final JvmOpcodeInvoker[] opcodes;
    private final Code_attribute codeAttribute;
//    private final String name;
//    private final int paremeterCount;

    public JvmOpcodeMethod(JvmOpcodeClass clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
        this.opcodes = new JvmOpcodeInvoker[]{};
        this.codeAttribute = (Code_attribute) method.attributes.get("Code");
    }

    @Override
    public void call(Environment environment, Object thiz, Object... args) throws Exception {
        JvmStack stack = environment.getStack();
        JvmStackFrame jvmStackFrame = stack.newFrame(clazz,
                this,
                clazz.getClassFile().constant_pool,
                opcodes,
                codeAttribute.max_locals,
                codeAttribute.max_stack);

        // 通过栈帧的局部变量表来传递方法的参数
        Slots<Object> localVariables = jvmStackFrame.getLocalVariables();
        int pos = 0;
        // 非静态方法,第一个slot为this.
        if (!method.access_flags.is(AccessFlags.ACC_STATIC)) {
            localVariables.set(pos++, thiz, 1);
        }
        for (Object arg : args) {
            localVariables.set(pos++, arg, 1);
        }

        // 执行初始化方法
        clazz.clinit(environment);
        JvmBytecodeInterpreter.run(environment);
    }

    @Override
    public int getParameterCount() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
