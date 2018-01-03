package com.gk.serializable;

import java.io.*;

/**
 * Created by akun on 2017/5/19.
 * serialVersionUID作用：
 * 序列化时为了保持版本的兼容性，即在版本升级时反序列化是否保存向后兼容。
 * 有两种生成方式：
 * 一个是默认的1L，比如：private static final long serialVersionUID = 1L;
 * 一个是根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段，比如：
 * private static final   long     serialVersionUID = xxxxL;
 */
public class SerializableTest {
    public static void main(String[] args) throws Exception {
//        FileOutputStream fos = new FileOutputStream("temp.out");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        TestObject testObject = new TestObject();
//        oos.writeObject(testObject);
//        oos.flush();
//        oos.close();

        FileInputStream fis = new FileInputStream("temp.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        TestObject deTest = (TestObject) ois.readObject();
        System.out.println(deTest.testValue);
        System.out.println(deTest.parentValue);
        System.out.println(deTest.innerObject.innerValue);
        System.out.println(deTest.name);
    }
}

class Parent implements Serializable {

    private static final long serialVersionUID = -4963266899668807475L;

    public int parentValue = 100;
}

class InnerObject implements Serializable {

    private static final long serialVersionUID = 5704957411985783570L;

    public int innerValue = 200;
}

/**
 * 如果你的类Serialized存到硬盘上面后，可是后来你却更改了类别的field(增加或减少或改名)，当你Deserialize时，
 * 就会出现Exception的，这样就会造成不兼容性的问题。
 * <p>
 * 但当serialVersionUID相同时，它就会将不一样的field以type的预设值Deserialize，可避开不兼容性问题
 */
class TestObject extends Parent implements Serializable {

    private static final long serialVersionUID = -3186721026267206914L;
//    private static final long serialVersionUID = -14L;

    public int testValue = 300;
    public String name;

    public InnerObject innerObject = new InnerObject();
}

