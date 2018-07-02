package no.mbs.far;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class FindAndReplaceTest {

    private final String destinationDirectory = "/target/dist/";

    @Test
    public void getFilesFromSrc_success_one_file_found() throws IOException {
        int expectedNumberOfFiles = 4;
        List<File> files = defineWithTestData().getFilesFromSrc();

        assertThat(files).isNotNull();
        assertThat(files.size()).isEqualTo(expectedNumberOfFiles);
    }

    @Test
    public void execute_success() throws IOException {
        defineWithTestData().execute();

        final List<String> expectedResult = ExpectedTestResult.expectedInTest_Html();
        final  List<String> actualResult = ExpectedTestResult.getExpectedContentInTest_Html(destinationDirectory);

        assertThat(expectedResult).isEqualTo(actualResult);
    }

    private FindAndReplace defineWithTestData() {
        final String srcDirectory = "/src/test/resources/";
        final String srcFileExtension = "html";
        final String destinationFileExtension = "html";

        final List<String> farTags = new ArrayList<>();
        farTags.add("far-head.html");
        farTags.add("far-footer.html");

        return new FindAndReplace( srcDirectory,
                srcFileExtension,
                destinationFileExtension,
                destinationDirectory,
                farTags );
    }
}