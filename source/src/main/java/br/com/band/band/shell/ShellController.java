package br.com.band.band.shell;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShellController {

    @GetMapping("/")
    public String shell() {
        return "shell";
    }
}
