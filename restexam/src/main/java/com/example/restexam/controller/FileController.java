package com.example.restexam.controller;

import com.example.restexam.domain.UploadInfo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
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

    // 파일 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(name = "info", required = false) UploadInfo uploadInfo){
        log.info(file.getOriginalFilename());

        try(InputStream inputStream = file.getInputStream()){
            StreamUtils.copy(inputStream, new FileOutputStream("c:/Temp/DumpFile/upload/" + file.getOriginalFilename()));
            return ResponseEntity.ok().body("파일저장이 완료되었습니다." + file.getOriginalFilename());
        }catch(IOException e){
            return ResponseEntity
                    .badRequest()
                    .body("파일 업로드 실패 : " + file.getOriginalFilename());
        }
    }
}
