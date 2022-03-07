package org.mrdaios.ijvm.runtime;

import com.sun.tools.classfile.ConstantPool;
import org.mrdaios.ijvm.lang.JvmClass;
import org.mrdaios.ijvm.lang.JvmMethod;
import org.mrdaios.ijvm.lang.opcode.JvmOpcodeInvoker;

/**
 * 栈帧-代表一次方法调用的上下文
 * <p>
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.6
 */
public class JvmStackFrame {

    private final JvmClass clazz;
    private final JvmMethod method;
    /**
     * 局部变量表(Local Variables)
     * 用于存储方法的局部变量表
     */
    private final Slots<Object> localVariables;
    /**
     * 操作数栈(Operand Stack)
     * 用于存储操作指令的输入输出
     */
    private final SlotsStack<Object> operandStack;
    /**
     * 字节吗
     */
    private final JvmOpcodeInvoker[] opcodes;
    /**
     * 常量池
     */
    private final ConstantPool constantPool;
    private final boolean isReturned = false;
    /**
     * 程序计数器
     */
    private int pc = 0;
    /**
     * 返回值
     */
    private Object returnVal;
    /**
     * 返回类型
     */
    private Object returnType;

    JvmStackFrame(JvmClass clazz, JvmMethod method, ConstantPool constantPool, JvmOpcodeInvoker[] opcodes, int variables, int stackSize) {
        this.clazz = clazz;
        this.method = method;
        this.constantPool = constantPool;
        this.opcodes = opcodes;
        this.localVariables = new Slots<>(variables);
        this.operandStack = new SlotsStack<>(stackSize);
    }

    public int increasePc() {
        return pc++;
    }

    public int getPc() {
        return pc;
    }

    public Slots<Object> getLocalVariables() {
        return localVariables;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public Object getReturnType() {
        return returnType;
    }

    public SlotsStack<Object> getOperandStack() {
        return operandStack;
    }

    public Object getReturn() {
        return returnVal;
    }

    public JvmOpcodeInvoker[] getOpcodes() {
        return opcodes;
    }
}
