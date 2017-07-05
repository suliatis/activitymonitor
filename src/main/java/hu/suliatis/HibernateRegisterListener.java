package hu.suliatis;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class HibernateRegisterListener {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ActivityMonitor auditEventListener;

    @PostConstruct
    public void registerListeners() {
        if(entityManagerFactory instanceof HibernateEntityManagerFactory) {
            final HibernateEntityManagerFactory  hibernateEntityManagerFactory = (HibernateEntityManagerFactory) entityManagerFactory;
            final SessionFactoryImpl sessionFactory =  (SessionFactoryImpl) hibernateEntityManagerFactory.getSessionFactory();
            final EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
            registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(auditEventListener);
            registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(auditEventListener);
        }
    }
}
