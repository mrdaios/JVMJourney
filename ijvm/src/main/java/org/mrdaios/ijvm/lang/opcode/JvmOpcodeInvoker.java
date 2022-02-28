package org.mrdaios.ijvm.lang.opcode;

import org.mrdaios.ijvm.runtime.Environment;
import org.mrdaios.ijvm.runtime.JvmStackFrame;

public interface JvmOpcodeInvoker {
    void invoke(Environment env, JvmStackFrame frame) throws Exception;
}