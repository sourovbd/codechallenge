package com.ehrlich.codechallenge.util;

import com.ibm.icu.text.CharsetDetector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@Slf4j
public class DetectorUtil {

    public static String getCharsetName(MultipartFile file) throws IOException {
        log.info("START getCharsetName");
        CharsetDetector detector = new CharsetDetector();
        detector.setText(file.getBytes());
        String detectedCharset = detector.detect().getName();
        log.info("END getCharsetName: [CHARSET:{}]", detectedCharset);
        return detectedCharset;
    }
}
