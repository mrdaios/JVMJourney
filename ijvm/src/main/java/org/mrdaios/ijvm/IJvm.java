package org.mrdaios.ijvm;

import java.nio.file.Paths;
import java.util.Arrays;

/**
 * 参考https://github.com/caoym/jjvm
 */
public class IJvm {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: <classpath> <class> [args...]");
            return;
        }
        VirtualMachine virtualMachine = new VirtualMachine(Paths.get(args[0]), args[1]);
        args = Arrays.copyOfRange(args, 2, args.length);
        virtualMachine.run(args);
    }
}
