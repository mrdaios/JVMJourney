package org.mrdaios.ijvm.runtime;

import com.sun.tools.classfile.ConstantPool;
import org.mrdaios.ijvm.lang.JvmClass;
import org.mrdaios.ijvm.lang.JvmMethod;
import org.mrdaios.ijvm.lang.opcode.JvmOpcodeInvoker;

public class JvmStack {

    private final SlotsStack<JvmStackFrame> frames = new SlotsStack<>(1024);
    private boolean running = false;

    public JvmStackFrame newFrame(JvmClass clazz, JvmMethod method) {
        JvmStackFrame frame = new JvmStackFrame(clazz, method, null, null, 0, 0);
        frames.push(frame, 1);
        return frame;
    }

    public JvmStackFrame newFrame(JvmClass clazz,
                                  JvmMethod method,
                                  ConstantPool constantPool,
                                  JvmOpcodeInvoker[] opcodes,
                                  int variables,
                                  int stackSize) {
        JvmStackFrame frame = new JvmStackFrame(clazz, method, constantPool, opcodes, variables, stackSize);
        frames.push(frame, 1);
        return frame;
    }

    public JvmStackFrame currentFrame() {
        return frames.pick();
    }

    public JvmStackFrame popFrame() {
        return frames.pop();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
