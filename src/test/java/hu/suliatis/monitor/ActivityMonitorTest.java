package hu.suliatis.monitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class ActivityMonitorTest {

    @Autowired
    private MockMvc mvc;

    @SpyBean
    private SimpMessagingTemplate webSocket;

    @Test
    public void monitorAnyInsertOperation() throws Exception {
        mvc.perform(get("/sample/save")).andExpect(status().isOk());
        verify(webSocket, times(1)).convertAndSend(eq("/topic/activity-monitor"), matches("timestamp=[0-9]* :: a row with ID=1 was INSERTED"));
    }

    @Test
    public void monitorAnyUpdateOperation() throws Exception {
        mvc.perform(get("/sample/save")).andExpect(status().isOk());
        mvc.perform(get("/sample/save/1")).andExpect(status().isOk());
        verify(webSocket, times(1)).convertAndSend(eq("/topic/activity-monitor"), matches("timestamp=[0-9]* :: a row with ID=1 was UPDATED"));
    }
}
