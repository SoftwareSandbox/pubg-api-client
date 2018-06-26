package be.swsb.pubgclient;

import be.swsb.pubgclient.model.player.PlayerResponse;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PubgApiClientIntegrationTest {

    // TODO add key to travis and make sure it works locally as well and then remove @ignore from here
    @Test
    @SuppressWarnings("ConstantConditions")
    @Ignore
    public void getPlayerByNameReturnsExpectedResult() throws PubgApiClientException {
        Optional<PlayerResponse> playerResponse = new PubgApiClientBuilder().build().getPlayerByName("shroud", "pc-na");

        assertThat(playerResponse).isNotEmpty();
        assertThat(playerResponse.get().getPlayer().getPlayerAttributes().getName()).isEqualTo("shroud");
    }
}
