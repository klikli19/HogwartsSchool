package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;


import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("${avatar.dir.path}")
    private String avatarDir;

    private final StudentService service;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService service, AvatarRepository avatarRepository) {
        this.service = service;
        this.avatarRepository = avatarRepository;
    }

    Logger logger = LoggerFactory.getLogger(AvatarService.class);
    public void uploadAvatar(long studentId, MultipartFile file) throws IOException {
        Student student = service.findStudent(studentId);
        Path path = Path.of(avatarDir, student + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(path, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = getAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(path.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());

        logger.info("Request to upload avatar by  student id {}", studentId);
        avatarRepository.save(avatar);

    }

    public Avatar getAvatar(Long studentId) {
        logger.info("Request to getting avatar by student id {}", studentId);
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtension(String fileName) {
        logger.info("Was invoked method getting extension");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public void deleteAvatar(Long id) {
        logger.info("Request to delete avatar by id {}", id);
        avatarRepository.delete(getAvatar(id));
    }

    public List<Avatar> getAllAvatars(Integer pageNum, Integer pageSize) {
        PageRequest request = PageRequest.of(pageNum - 1, pageSize);
        logger.info("Request to getting avatars by page num {}", pageNum);
        return avatarRepository.findAll(request).getContent();
    }

}
