package com.example.restexam.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
public class FileController {
    // 파일 다운로드
    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response){
        Path path = Paths.get("c:/Temp/DumpFile/upload/cat.jpg"); // 파일의 경로 지정
        response.setContentType("image/jpeg"); // 이미지의 타입 지정 (.jpeg)
        try(InputStream inputStream = Files.newInputStream(path)){// 파일이 추상화된 객체 = Files, path에서 가져와서 InputStream을 보내기)
            StreamUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        }catch(IOException e){
            log.error("파일 다운로드 중 오류 발생 : " + e.getMessage());
        }
    }
}
