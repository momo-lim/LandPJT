package io.ssafy.ssafyland.api.controller;

import io.ssafy.ssafyland.api.dto.FileDto;
import io.ssafy.ssafyland.api.service.FileService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Api(value = "File api", tags = {"File"})
@RestController
@RequestMapping("/api/file")
public class FileController {

//    @Value("${spring.servlet.multipart.location}")
    String filePath = "/data/files";

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    @ApiOperation(value = "file upload", notes = "파일을 업로드한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<?> upload(
            @RequestParam("files") @ApiParam(value = "파일 정보", required = true) List<MultipartFile> files) throws IOException {
        log.info("file upload", "INFO");

        fileService.upload(files);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @ApiOperation(value = "get file list", notes = "파일 목록을 보여준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<List<FileDto>> getFileList() {
        log.info("file list", "INFO");
        List<FileDto> fileDtos = fileService.getFileList();
        return ResponseEntity.ok().body(fileDtos);
    }

    @GetMapping("/download")
    @ApiOperation(value = "file download", notes = "파일을 다운로드한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<Resource> download(
            @RequestBody @ApiParam(value = " 파일 정보", required = true) FileDto fileDto) throws IOException {
        log.info("file download", "INFO");

        String fileUUID = fileService.getUUID(fileDto.getFileName());
        Path path = Paths.get(filePath + "/" + fileUUID);
        log.info(path.toString(), "INFO");
        String contentType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.builder("attachment")
                        .filename(fileDto.getFileName(), StandardCharsets.UTF_8)
                        .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
