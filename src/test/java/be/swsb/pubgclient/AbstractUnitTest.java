package be.swsb.pubgclient;

import org.junit.Rule;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.net.URISyntaxException;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Objects.requireNonNull;
import static org.mockito.junit.MockitoJUnit.rule;

public class AbstractUnitTest {

    @Rule
    public MockitoRule rule = rule();


    protected String readFile(String path) {
        try {
            return new String(readAllBytes(get(requireNonNull(PubgApiClientTest.class.getClassLoader().getResource(path)).toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
