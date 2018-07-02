package no.mbs.far;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class ExpectedTestResult {

    static List<String> expectedInTest_Html() {

        final String expectedFileResult = "dfsdfsdfdsf\n" +
                "dasdasd\n" +
                "zxczxczxc\n" +
                "\n" +
                "<a class=\"header\" href=\"./a.html\">a</a>\n" +
                "<a class=\"header\" href=\"./b.html\">b</a>\n" +
                "<a class=\"header\" href=\"./c.html\">c</a>\n" +
                "<a class=\"header\" href=\"./d.html\">d</a>\n" +
                "<a class=\"header\" href=\"./e.html\">e</a>\n" +
                "<a class=\"header\" href=\"./f.html\">f</a>\n" +
                "<a class=\"header\" href=\"./g.html\">g</a>\n" +
                "<a class=\"header\" href=\"./h.html\">h</a>\n" +
                "<a class=\"header\" href=\"./i.html\">i</a>\n" +
                "<a class=\"header\" href=\"./j.html\">j</a>\n" +
                "<a class=\"header\" href=\"./k.html\">k</a>\n" +
                "\n" +
                "\n" +
                "df.kgjldfgjldfgjk\n" +
                ".,vmxv\n" +
                "d.fgldkfldsf\n" +
                "sd,fjsdjfsdjf\n" +
                "\n" +
                "<a class=\"footer\" href=\"./a.html\">a</a>\n" +
                "<a class=\"footer\" href=\"./b.html\">b</a>\n" +
                "<a class=\"footer\" href=\"./c.html\">c</a>\n" +
                "<a class=\"footer\" href=\"./d.html\">d</a>\n" +
                "<a class=\"footer\" href=\"./e.html\">e</a>\n" +
                "<a class=\"footer\" href=\"./f.html\">f</a>\n" +
                "<a class=\"footer\" href=\"./g.html\">g</a>\n" +
                "<a class=\"footer\" href=\"./h.html\">h</a>\n" +
                "<a class=\"footer\" href=\"./i.html\">i</a>\n" +
                "<a class=\"footer\" href=\"./j.html\">j</a>\n" +
                "\n" +
                "gøkjdfgljgjidf\n" +
                "fdøgldfgøaødfgasldk\n" +
                "\n" +
                "dlasdkfalsdkm\n" +
                "sd.kdljoanla rvlansll";

        return Arrays.asList(expectedFileResult.split("\n"));
    }

    static List<String> getExpectedContentInTest_Html(String destinationDirectory) throws IOException {
        final Path pathToDestinationFile =
                Paths.get( Paths.get("").toAbsolutePath() + destinationDirectory + "test.html" );
        return Files.readAllLines(pathToDestinationFile);
    }

    static Stream<Path> getNumberOfFilesInDestinationDiretory(String destinationDirectory) throws IOException {
        final Path pathToDestinationFile =
                Paths.get( Paths.get("").toAbsolutePath() + destinationDirectory  );
        return Files.list(pathToDestinationFile);
    }
}
