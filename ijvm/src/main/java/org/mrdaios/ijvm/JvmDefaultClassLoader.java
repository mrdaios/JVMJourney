package org.mrdaios.ijvm;

import org.mrdaios.ijvm.lang.JvmClass;
import org.mrdaios.ijvm.lang.JvmClassLoader;
import org.mrdaios.ijvm.lang.natives.JvmNativeClass;
import org.mrdaios.ijvm.lang.opcode.JvmOpcodeClass;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JvmDefaultClassLoader implements JvmClassLoader {

    private final Path classPath;

    public JvmDefaultClassLoader(Path classPath) {
        this.classPath = classPath;
    }

    @Override
    public JvmClass loadClass(String className) throws ClassNotFoundException {
        String filePath = classPath + "/" + className.replace(".", "/") + ".class";
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            return new JvmOpcodeClass(this, path);
        } else {
            return new JvmNativeClass(this, className);
        }
    }
}
