package com.goormfj.hanzan.drinkTest.domain;

import lombok.Getter;

@Getter
public enum Response {

        A("A"),
        B("B"),
        C("C"),
        D("D");

        private final String value;

        Response(String value) {
            this.value = value;
        }

}
