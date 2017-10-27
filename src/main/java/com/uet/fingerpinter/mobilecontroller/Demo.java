package com.uet.fingerpinter.mobilecontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {
    @GetMapping(value = "/")
    public String get() {
        return "Hello FingerPrinnter wifi location";
    }
}
