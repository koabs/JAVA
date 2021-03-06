package com.koabs.bytecode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.objectweb.asm.*;

/**
 *
 * https://www.ibm.com/developerworks/cn/java/j-lo-asm30/index.html
 * https://github.com/ThinkpadNC5/MyPerf4J
 * https://segmentfault.com/a/1190000009956534
 */
public class Generator{

    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("com.koabs.bytecode.Account");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();

//        File file = new File("core\\target\\classes\\com\\koabs\\bytecode\\Account.class");
//        FileOutputStream fout = new FileOutputStream(file);
//        fout.write(data);
//        fout.close();
//        SecurityChecker.checkSecurity();
        ASMDemo.MyClassLoader classLoader = new ASMDemo.MyClassLoader(Thread.currentThread().getContextClassLoader());
        Class<?> testClass = classLoader.defineClass("com.koabs.bytecode.Account",
                data);
        // invoke static
        Method staticMain = testClass.getMethod("operation");
        staticMain.invoke(null,null);
//        Account account = new Account();
//        account.operation();
    }

}