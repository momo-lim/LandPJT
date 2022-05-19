package io.ssafy.ssafyland.api.service;

import io.ssafy.ssafyland.api.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    void upload(List<MultipartFile> files) throws IOException;

    List<FileDto> getFileList();
    String getUUID(String fileName);
}
