package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

@RestController
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("Слишком большой файл.");
        }
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar-from-file")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.getAvatar(id);
        Path path = Path.of(avatar.getFilePath());
        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream();
                ) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    @GetMapping(value = "/{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadAvatarBD(@PathVariable Long id) {
        Avatar avatar = avatarService.getAvatar(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        httpHeaders.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(avatar.getData());
    }

    @DeleteMapping("{id}")
    public void deleteAvatar(@PathVariable Long id) {
        avatarService.deleteAvatar(id);
    }

    @GetMapping("all")
    public ResponseEntity<List<Avatar>> getAll(@RequestParam("page") Integer pageNum,
                                               @RequestParam("size") Integer pageSize) {
        return ResponseEntity.ok(avatarService.getAllAvatars(pageNum, pageSize));
    }
}
