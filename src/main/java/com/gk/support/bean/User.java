package com.gk.support.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by akun on 2017/5/26.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer age;
    private String name;
    private String gender;

    static class A {
    }

    private class B {
    }

    public class CC {
    }

    protected class Cc {
    }

    abstract class D {
    }

    final class E {
    }


}
