package org.mrdaios.ijvm.runtime;

import org.mrdaios.ijvm.VirtualMachine;

/**
 * 线程上下文
 */
public class Environment {

    private final VirtualMachine virtualMachine;
    private final JvmStack stack = new JvmStack();

    public Environment(VirtualMachine virtualMachine) {
        this.virtualMachine = virtualMachine;
    }

    public VirtualMachine getVirtualMachine() {
        return virtualMachine;
    }

    public JvmStack getStack() {
        return stack;
    }
}
