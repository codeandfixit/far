package no.mbs.far;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FindAndReplace {

    private final Charset standardCharset = StandardCharsets.UTF_8;

    private final String srcDirectory;

    private final String srcFileExtension;

    private final String destinationFileExtension;

    private final String destinationDirectory;

    private final List<String> farTags;

    private final Map<String, String> farTagsContent;

    public FindAndReplace(@Value("${far.src-directory:./src}") final String srcDirectory,
                          @Value("${far.src-file-extension:html}") final String srcFileExtension,
                          @Value("${far.destination-file-extension:html}") final String destinationFileExtension,
                          @Value("${far.destination-directory:./dist}") final String destinationDirectory,
                          @Value("#{'${far.tags}'.split(',')}") final List<String> farTags) {
        this.srcDirectory = srcDirectory;
        this.srcFileExtension = srcFileExtension;
        this.destinationFileExtension = destinationFileExtension;
        this.destinationDirectory = destinationDirectory;
        this.farTags = farTags;

        farTagsContent = new HashMap<>();
        loadTagFiles();
    }

    private void loadTagFiles() {
        farTags.forEach(this::loadTagFile);
    }

    private void loadTagFile(String tag) {
        String tagContent;
        try {
            tagContent = new String(Files.readAllBytes(Paths.get( Paths.get("").toAbsolutePath() + srcDirectory + tag )));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        farTagsContent.put(tag, tagContent);
    }

    public void execute() throws IOException {
        readMapAndWriteFilesToDestination(getFilesFromSrc());
    }

    List<File> getFilesFromSrc() throws IOException {
        final Path path = Paths.get(Paths.get("").toAbsolutePath() + srcDirectory);
        final PathMatcher filter = path.getFileSystem().getPathMatcher("glob:**/*." + srcFileExtension);
        return Files.walk(path)
                .filter(Files::isRegularFile)
                .filter(filter::matches)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    private void readMapAndWriteFilesToDestination(final List<File> files) {
        files.forEach(this::readMapAndWriteFileToDestination);
    }

    private void readMapAndWriteFileToDestination(final File file) {

        Path filePath = Paths.get(file.getPath());
        String originalContent;
        try {
            originalContent = new String(Files.readAllBytes(filePath), standardCharset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String replacedContent = originalContent;
        for(String tag : farTags) {
            replacedContent = replacedContent.replace(tag, farTagsContent.get(tag));
        }

        if(!originalContent.equals(replacedContent)) {
            writeMappedFile(file, replacedContent);
        }
    }

    private String removeFileExtension(File file) {
        String fileName = file.getName();
        return fileName.replace("." + srcFileExtension, "");
    }

    private void writeMappedFile(final File file, final String newContent) {
        try {
            Path filePath = defineMappedFilePath(file);
            Files.createDirectories(filePath.getParent());
            Files.write(defineMappedFilePath(file),
                        newContent.getBytes(standardCharset),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path defineMappedFilePath(final File file) {
        return Paths.get( Paths.get("").toAbsolutePath()
                + destinationDirectory
                + removeFileExtension(file)
                + "."
                + destinationFileExtension);
    }
}
