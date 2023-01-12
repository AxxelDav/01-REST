package com.axel.project.shopping.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {


        try {
            int numero = 19 / 0;
        } catch (Exception e) {
            System.out.println("Hola amigo");
            log.error("error:", e);
//            log.trace("trace:", e);
        }
    }
}
