package hu.suliatis.info;

import hu.suliatis.Application;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.jar.Attributes;
import java.util.jar.Manifest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class InfoController {

    @RequestMapping(value = "/info/version", method = GET)
    String version() throws Exception {
        // returns with nothing :/
        Manifest manifest = new Manifest(Application.class.getResourceAsStream("/META-INF/MANIFEST.MF"));
        return manifest.getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_VERSION);
    }
}
