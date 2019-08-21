package com.ownerkaka.testjdk.designpattern23.budiler;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
class User {
    private String name;
    private Integer num;
    private Date created;

    public User(String name, Integer num, Date created) {
        this.name = name;
        this.num = num;
        this.created = created;
    }

    public static class UserBuilder {
        private String name;
        private Integer num;
        private Date created;

        private static final UserBuilder userBuilder = new UserBuilder();

        private UserBuilder() {
        }

        public static UserBuilder builder() {
            return userBuilder;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setNum(Integer num) {
            this.num = num;
            return this;
        }

        public UserBuilder setCreated(Date created) {
            this.created = created;
            return this;
        }

        public User build() {
            return new User(name, num, created);
        }
    }
}
