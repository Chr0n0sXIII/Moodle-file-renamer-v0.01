package comp3607_group_14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileFilter;

/**
 * This class is responsible for copying files form a directory to another
 */
public class FileCopier {

    /**
     * This variable stores the file path of the files to be renamed folder
     */
    private String source;

    /**
     * This variable stores the file path of the renamed assignment files
     */
    private String destination;

    /**
     * This variable is used to filter certain files while copying
     */
    private FileFilter filter;

    /**
     * This method instantiates a FileCopier object
     */
    public FileCopier(String source, String destination) {
        this.destination = destination;
        this.source = source;
        filter = new FileFilter() {

            @Override
            public boolean accept(File file) {
                if (file.getName().endsWith(".pdf")) {
                    return true;
                }
                return false;
            }

        };
    }

    /**
     * This method copies files from the source to the destination
     * 
     * @throws IOException
     */
    public void copyFiles() throws IOException {
        Path src = Paths.get(source);
        Path dest = Paths.get(destination);

        Stream<Path> files = Files.walk(src, 1);

        files.forEach(file -> {

            try {
                if (filter.accept(file.toFile())) {
                    Files.copy(file, dest.resolve(src.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        files.close();
    }
}
