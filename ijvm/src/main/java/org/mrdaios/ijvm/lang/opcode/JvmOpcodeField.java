package org.mrdaios.ijvm.lang.opcode;

import com.sun.tools.classfile.Field;
import org.mrdaios.ijvm.lang.JvmField;

public abstract class JvmOpcodeField implements JvmField {

    protected final JvmOpcodeClass clazz;
    protected final Field field;

    protected JvmOpcodeField(JvmOpcodeClass clazz, Field field) {
        this.clazz = clazz;
        this.field = field;

    }

    public static class JvmOpcodeObjectField extends JvmOpcodeField {

        protected String fieldName;

        public JvmOpcodeObjectField(JvmOpcodeClass clazz, Field field) {
            super(clazz, field);
//         this.fieldName= field.getName(clazz.);

        }
    }

    public static class JvmOpcodeStaticField extends JvmOpcodeField {

        protected String fieldName;

        public JvmOpcodeStaticField(JvmOpcodeClass clazz, Field field) {
            super(clazz, field);
//         this.fieldName= field.getName(clazz.);

        }
    }
}
