package io.ssafy.ssafyland.api.service;

import io.ssafy.ssafyland.api.dto.FileDto;
import io.ssafy.ssafyland.common.exception.BusinessException;
import io.ssafy.ssafyland.common.exception.ErrorCode;
import io.ssafy.ssafyland.db.entity.FilePDF;
import io.ssafy.ssafyland.db.repository.FilePDFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FilePDFRepository filePDFRepository;

    @Override
    public void upload(List<MultipartFile> multipartFiles) throws IOException {
        String absolutePath = new File("").getAbsolutePath();
        String path = "data/files/";

        File file = new File(path);
        if (!file.exists())
            file.mkdirs();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                String contentType = multipartFile.getContentType();

                if (ObjectUtils.isEmpty(contentType))
                    continue;
            }

            FilePDF filePDF = FilePDF.builder()
                    .filePath("/data/files")
                    .uuid(UUID.randomUUID().toString())
                    .fileName(multipartFile.getOriginalFilename())
                    .contentType(multipartFile.getContentType())
                    .build();
            filePDFRepository.save(filePDF);

            file = new File(absolutePath + path + filePDF.getUuid() + ".pdf");
            multipartFile.transferTo(file);
        }
    }

    @Override
    public List<FileDto> getFileList() {
        return filePDFRepository.findAll().stream().map(FilePDF::toFileDto).collect(Collectors.toList());
    }

    @Override
    public String getUUID(String fileName) {
        FilePDF filePDF = filePDFRepository.findByFileName(fileName).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_FILE));

        String result = filePDF.getUuid() + ".pdf";
        return result;
    }
}
