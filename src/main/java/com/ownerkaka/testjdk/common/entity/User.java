package com.ownerkaka.testjdk.common.entity;

import lombok.*;

/**
 * Created by gk499 on 2016/3/7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private String username;
    private Integer age;

//    public User(Integer age, String username) {
//        this.age = age;
//        this.username = username;
//    }
//    public User() {
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }

    public static  void main(String arg[]){
        User user = new User();
        user.setAge(90);
        user.setUsername("sf");
        System.out.print(user.getUsername());
    }
}
