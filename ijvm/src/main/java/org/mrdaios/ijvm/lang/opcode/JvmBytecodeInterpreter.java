package org.mrdaios.ijvm.lang.opcode;

import com.sun.org.apache.bcel.internal.Constants;
import org.mrdaios.ijvm.runtime.Environment;
import org.mrdaios.ijvm.runtime.JvmStack;
import org.mrdaios.ijvm.runtime.JvmStackFrame;

import java.util.ArrayList;
import java.util.List;

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
            JvmOpcodeInvoker[] opcodes = stackFrame.getOpcodes();
            int pc = stackFrame.increasePc();
            opcodes[pc].invoke(environment, stackFrame);
            StringBuilder sb = new StringBuilder();
            sb.append("> ");
            sb.append(frame.getCurrentClass().getName());
            sb.append(".");
            sb.append(frame.getCurrentMethod().getName());
            sb.append("@");
        }
    }

    public static JvmOpcodeInvoker[] parseCode(byte[] codes) {
        List<JvmOpcodeInvoker> opcodes = new ArrayList<>();
        for (int i = 0; i < codes.length; i++) {
            short code = codes[i];
            JvmOpcodeRout opcodeRout = JvmOpcodeRout.valueOf(code);
            short noOfOperand = Constants.NO_OF_OPERANDS[code];

        }

    }
}
