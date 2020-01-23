package de.fzj.atlascore.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the static pages of the application
 *
 * @author Vadim Marcenko
 */
@Controller
public class StaticPageController {

    @RequestMapping(value = "/tos")
    public String getTos() {
        return "tos";
    }

    @RequestMapping(value = "/")
    public String getMain() { return "index"; }
}
