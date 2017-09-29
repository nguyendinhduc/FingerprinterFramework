package com.uet.fingerpinter.demo;

import com.uet.fingerpinter.db.tables.FingerprinterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FingerPrinterController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/postFingerPrinter")
    public String postFingerPrinter(
            @RequestParam("appName") String appName,
            @RequestParam("macAddress") String macAddress,
            @RequestParam("ipAddress") String ipAddress,
            @RequestParam("rss") float rss
    ){
        bookRepository.getDb().insertInto(FingerprinterInfo.FINGERPRINTER_INFO,
                FingerprinterInfo.FINGERPRINTER_INFO.AP_NAME,
                FingerprinterInfo.FINGERPRINTER_INFO.MAC_ADDRESS,
                FingerprinterInfo.FINGERPRINTER_INFO.RSS)
                .values(appName, macAddress, (double)rss).execute();
        return "success";
    }

}
