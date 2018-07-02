package no.mbs.far;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FarApplicationTests {

    @Test
    public void success() {}

    @After
    public void assertSuccess() throws IOException {
        long expectedNumberOfFilesInDestioanDiretory = 1;
        final List<String> expectedResult = ExpectedTestResult.expectedInTest_Html();
        String destinationDirectory = "/target/dist/";
        final  List<String> actualResult = ExpectedTestResult.getExpectedContentInTest_Html(destinationDirectory);
        long actualNumberOfFilesInDestioanDiretory =
                ExpectedTestResult.getNumberOfFilesInDestinationDiretory(destinationDirectory).count();

        assertThat(expectedResult).isEqualTo(actualResult);
        assertThat(expectedNumberOfFilesInDestioanDiretory).isEqualTo(actualNumberOfFilesInDestioanDiretory);
    }
}
