package hu.suliatis;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

@Component
public class AuditEventListener implements PostInsertEventListener, PostUpdateEventListener {
    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        System.out.println("Entity inserted with id: " + postInsertEvent.getId());
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        System.out.println("Entity updated with id: " + postUpdateEvent.getId());
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}
