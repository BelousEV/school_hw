package ru.hogwarts.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Lob;

@Entity
public class Avatar {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath; //путь до файла на диске
    private long fileSize; // содержит информацию о размере файла в байтах
    private String mediaType; // тип файла
    private byte[] data; //хранится сама информация о файле, представленная в массиве байтов

    @Lob
    private byte [] preview; // обложка, уменьшенная в размере (хран. в БД)
    @OneToOne
    private Student student; //Привяжем аватарку к студенту

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    public byte[] getPreview() {
        return preview;
    }

    public void setPreview(byte[] preview) {
        this.preview = preview;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

