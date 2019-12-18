package com.bachelor.logiword.server.controller_test;

import com.bachelor.logiword.server.api.FriendController;
import com.bachelor.logiword.server.service.FriendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendController.class)
public class FriendControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FriendService friendService;

    @Test
    public void getFriends_thenReturnStringList() throws Exception{
        String[] friendsArray = new String[]{"Katheryn Winnick", "Donald Trump", "Emma Watson", "Arnold Schwarzenegger"};
        List<String> friends = Arrays.asList(friendsArray);
        int playerId = 420;

        given(friendService.getFriends(playerId)).willReturn(friends);

        mvc.perform(get("/friends/" + playerId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)))
            .andExpect(jsonPath("$[0]", is(friends.get(0))))
            .andExpect(jsonPath("$[1]", is(friends.get(1))))
            .andExpect(jsonPath("$[2]", is(friends.get(2))))
            .andExpect(jsonPath("$[3]", is(friends.get(3))));
    }

    @Test
    public void getFriendRequests_thenReturnStringList() throws Exception{
        String[] friendsArray = new String[]{"Katheryn Winnick", "Donald Trump", "Emma Watson", "Arnold Schwarzenegger"};
        List<String> friends = Arrays.asList(friendsArray);
        int playerId = 420;

        given(friendService.getFriendRequests(playerId)).willReturn(friends);

        mvc.perform(get("/friends/requests/" + playerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0]", is(friends.get(0))))
                .andExpect(jsonPath("$[1]", is(friends.get(1))))
                .andExpect(jsonPath("$[2]", is(friends.get(2))))
                .andExpect(jsonPath("$[3]", is(friends.get(3))));
    }
}
