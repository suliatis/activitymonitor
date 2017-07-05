package hu.suliatis.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class SampleEntityController {

    @Autowired
    private SampleEntityRepository repository;

    @RequestMapping(value = { "/sample/save/{id}", "/sample/save" }, method = RequestMethod.GET)
    String save(@PathVariable Optional<Long> id) {
        final SampleEntity entity = id.map(repository::findOne).orElseGet(SampleEntity::new);
        entity.setText(UUID.randomUUID().toString());
        return "Saved: " + repository.save(entity);
    }

}
