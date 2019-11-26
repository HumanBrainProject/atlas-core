package de.fzj.atlascore.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the static page to deliver the Terms of Service
 */
@Controller
public class TosController {

    @RequestMapping(value = "/tos")
    public String getTos() {
        return "/tos";
    }
}
