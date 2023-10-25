package ma.youcode.gathergrid.repositories;

import jakarta.inject.Inject;
import ma.youcode.gathergrid.domain.Event;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(Arquillian.class)
public class EventRepositoryImplTest {

    @Inject
    EventRepository eventRepository;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class,"test.war")
                .addClass(EventRepositoryImpl.class)
                .addClass(EventRepository.class)
                .addClass(Event.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

   @Test
    public void should_return_null_when_event_not_found() {
    }
}
