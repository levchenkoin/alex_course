package com.example.teamcity.api;

import com.example.teamcity.api.models.Agent;
import org.awaitility.Awaitility;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SetupAgentTest extends BaseApiTest {
    @Test
    public void authorizeAgent() {
        var agentId = getUnauthorizedAgent().getId();
        softy.assertThat(checkedWithSuperUser.getAgentsRequest()
                        .authorize(agentId))
                .isEqualTo(true);
    }

    private Agent getUnauthorizedAgent() {
        var agents = new AtomicReference<List<Agent>>();
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> {
                    agents.set(checkedWithSuperUser.getAgentsRequest()
                            .get("authorized:false")
                            .getAgent());
                    return !agents.get()
                            .isEmpty();
                });
        return agents.get()
                .get(0);
    }
//    @Test
//    public void setupAgentTest() {
//        //create new user and login data
//        var testData = testDataStorage.addTestData();
//        checkedWithSuperUser.getUserRequest()
//                .create(testData.getUser());
//        var checkedAgents = new CheckedAgents(Specifications.getSpec().authSpec(testData.getUser()));
//        await().atMost(Duration.ofMinutes(5)).pollInterval(Duration.ofSeconds(5)).until(() -> !checkedAgents.getAllUnauthorizedAgents().getAgent().isEmpty());
//        //get the list of unauthorized agents
//        var allUnauthorizedAgents = checkedAgents
//                .getAllUnauthorizedAgents();
//        //make agent authorized
//        var unauthorizedAgents = allUnauthorizedAgents.getAgent();
//        softy.assertThat(unauthorizedAgents.size()).isGreaterThan(0);
//        String agentName = unauthorizedAgents.get(0).getName();
//        checkedAgents.updateAgentAuthorizationToTrue(agentName);
//
//        //check that the agent is authorized
//        await().atMost(Duration.ofMinutes(5)).pollInterval(Duration.ofSeconds(5)).until(() -> !checkedAgents.getAllAuthorizedAgents().getAgent().isEmpty());
//        var allAuthorizedAgents = checkedAgents.getAllAuthorizedAgents();
//        var authorizedAgents = allAuthorizedAgents.getAgent();
//
//        softy.assertThat(authorizedAgents.get(0).getName()).isEqualTo(agentName);
//    }
}
