package hu.suliatis;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ActivityMonitor implements PostInsertEventListener, PostUpdateEventListener {
    @Autowired
    private SimpMessagingTemplate webSocket;

    @Override
    public void onPostInsert(PostInsertEvent event) {
        notifyActivityMonitor(event.getId(), "INSERTED");
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        notifyActivityMonitor(event.getId(), "UPDATED");
    }

    private void notifyActivityMonitor(Serializable id, String event) {
        final String msg = String.format("timestamp=%s :: a row with ID=%s was %s", System.currentTimeMillis(), id, event);
        webSocket.convertAndSend("/topic/activity-monitor", msg);
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}
