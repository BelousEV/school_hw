package ru.hogwarts.school.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("avatars")
public class AvatarController {

    private final AvatarService avatarService;
    private final AvatarRepository avatarRepository;

    public AvatarController(AvatarService avatarService, AvatarRepository avatarRepository) {
        this.avatarService = avatarService;
        this.avatarRepository = avatarRepository;
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // загружаем в базу
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId,
                                               @RequestParam MultipartFile avatarFile)
    throws IOException {
        /// check file size
        avatarService.uploadAvatar(studentId, avatarFile);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar-from-db") //Чтение файлов из БД
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }
    @GetMapping(value = "/{id}/avatar-from-file")//Чтение файлов с локального диска
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException{
        Avatar avatar = avatarService.findAvatar(id);
        Path path = Path.of(avatar.getFilePath());
        try(InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
//4.1
    @GetMapping(value = "allAvatars_4.1")
    public Collection<Avatar> getAll(@RequestParam("page") Integer pageNumber,
                                                     @RequestParam("size") Integer pageSize) {
        var pageRequest = PageRequest.of(0, 10);
        var answer = avatarRepository.findAll(pageRequest);

        return answer.getContent();
    }

}
