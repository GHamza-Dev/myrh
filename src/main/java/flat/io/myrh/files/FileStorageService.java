package flat.io.myrh.files;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths
                .get(fileStorageProperties.getUploadDir())
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = generateUniqueFileName(file);

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Invalid file name");
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            System.out.println("Error: "+e);
        }
        return null;
    }

    private String generateUniqueFileName(MultipartFile file){
        String original = StringUtils.cleanPath(file.getOriginalFilename());

        int lastIndexOfPt = original.lastIndexOf(".");

        String fileExtension = original.substring(lastIndexOfPt);
        String fileName = original.substring(0,lastIndexOfPt);
        StringBuilder newUniqueFileName = new StringBuilder();

        newUniqueFileName.append(fileName).append("-").append(System.currentTimeMillis()).append(fileExtension);
        return newUniqueFileName.toString();
    }
}
