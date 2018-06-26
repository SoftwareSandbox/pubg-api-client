package be.swsb.pubgclient;

import be.swsb.pubgclient.model.player.PlayerResponse;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PubgApiClientIntegrationTest {

    @Test
    @SuppressWarnings("ConstantConditions")
    public void getPlayerByNameReturnsExpectedResult() throws PubgApiClientException {
        Optional<PlayerResponse> playerResponse = new PubgApiClientBuilder().build().getPlayerByName("shroud", "pc-na");

        assertThat(playerResponse).isNotEmpty();
        assertThat(playerResponse.get().getPlayer().getPlayerAttributes().getName()).isEqualTo("shroud");
    }
}
