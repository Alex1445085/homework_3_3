package hogwartsschoolhw33.service;

import hogwartsschoolhw33.model.Avatar;
import hogwartsschoolhw33.model.Student;
import hogwartsschoolhw33.repository.AvatarRepository;
import hogwartsschoolhw33.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    public AvatarService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository; }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.getById(studentId);
        Path filePath = Path.of(avatarsDir, student.getId() + "." +
                      getExtensions(Objects.requireNonNull(avatarFile.getOriginalFilename())));

//        Avatar avatar = new Avatar();
//        if (Files.exists(filePath)) {
//            avatar = findAvatar(studentId);
//        }

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);


        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Collection<Avatar> avatarsByPage(int page, int size) {
        //PageRequest pageRequest = PageRequest.of(page - 1, size);
        //return avatarRepository.findAll(pageRequest).getContent();
        int offSet = (page - 1) * size;
        return avatarRepository.avatarsByPage(offSet, size);
    }
}
