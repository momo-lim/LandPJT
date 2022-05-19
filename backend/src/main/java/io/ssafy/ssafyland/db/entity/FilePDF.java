package io.ssafy.ssafyland.db.entity;

import io.ssafy.ssafyland.api.dto.FileDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FilePDF {
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long fileId;
    @Column(name = "file_path", nullable = false)
    String filePath;
    @Column(name = "uuid", nullable = false, unique = true)
    String uuid;
    @Column(name = "file_name", nullable = false, unique = true)
    String fileName;
    @Column(name = "content_type", nullable = false)
    String contentType;

    @Builder
    public FilePDF(String filePath, String uuid, String fileName, String contentType) {
        this.filePath = filePath;
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public FileDto toFileDto() {
        return new FileDto(this.fileName);
    }
}
