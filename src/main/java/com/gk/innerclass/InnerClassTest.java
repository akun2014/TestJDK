package com.gk.innerclass;

/**
 * Created by gk499 on 2016/3/13.
 */
public class InnerClassTest {
    private  int age = 1;
    static final String name = "guikun";
    /**
     * 成员内部类
     */
    class MemberClass{
        int num =  1;

    }
    /**
     * 局部内部类
     */
    public void testPartial(){
        class PartialClass{
            int num = 1;

            public void test() {
                System.out.print(name+age);
            }
        }
    }
    /**
     * 静态内部类
     */
    static class InnerStaticClass{
        int num = 1;

        public void test() {
            System.out.print(name);
        }
    }

    /**
     * 匿名内部类
     */

    public InnerClassTest cont() {
        return new InnerClassTest() {
            private int i = 11;

            public int value() {
                return i;
            }
        }; // 在这里需要一个分号
    }

    public void test() {
        MemberClass memberClass = new MemberClass();
        InnerStaticClass innerStaticClass = new InnerStaticClass();
    }

    public static void test1() {
        InnerClassTest innerStaticClass = new InnerClassTest();
        MemberClass memberClass = innerStaticClass.new MemberClass();

        InnerStaticClass innerStaticClass1 = new InnerStaticClass();


    }

    public static  void main(String[] arg){
        InnerClassTest innerClassTest = new InnerClassTest();

        MemberClass memberClass = innerClassTest.new MemberClass();

        InnerStaticClass innerStaticClass = new InnerStaticClass();

        System.out.println("hello is me inner class test");
    }
}
