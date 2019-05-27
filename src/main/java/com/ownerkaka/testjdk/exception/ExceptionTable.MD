 ``` 
public class ExceptionTableTest {
  public ExceptionTableTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void test();
    Code:
       0: iconst_0
       1: istore_1
       2: iconst_1
       3: istore_1
       4: iconst_3
       5: istore_1
       6: goto          22
       9: astore_2
      10: iconst_2
      11: istore_1
      12: iconst_3
      13: istore_1
      14: goto          22
      17: astore_3
      18: iconst_3
      19: istore_1
      20: aload_3
      21: athrow
      22: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
      25: iload_1
      26: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
      29: return
    Exception table:
       from    to  target type
           2     4     9   Class java/lang/Exception
           2     4    17   any
           9    12    17   any
}
 ```