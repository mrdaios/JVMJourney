package org.mrdaios.ijvm.lang.opcode;

import org.mrdaios.ijvm.runtime.Environment;
import org.mrdaios.ijvm.runtime.JvmStack;
import org.mrdaios.ijvm.runtime.JvmStackFrame;

public class JvmBytecodeInterpreter {

    //执行字节码
    public static void run(Environment environment) throws Exception {
        // 只执行最外层
        JvmStack stack = environment.getStack();
        if (stack.isRunning()) {
            return;
        }
        JvmStackFrame stackFrame;
        stack.setRunning(true);
        while (null != (stackFrame = stack.currentFrame())) {
            if (stackFrame.isReturned()) {
                JvmStackFrame oldFrame = stackFrame;
                stack.popFrame();
                stackFrame = stack.currentFrame();
                if (stackFrame != null && !"void".equals(oldFrame.getReturnType())) {
                    stackFrame.getOperandStack().push(oldFrame.getReturn());
                }
                continue;
            }

        }

    }
}
