package hogwartsschoolhw33.controller;

import hogwartsschoolhw33.service.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    private final InfoService infoService;
    public InfoController(InfoService infoService) { this.infoService = infoService; }

    @GetMapping("/info")
    public Integer getInfo() {
        return infoService.getInfo();
    }
}
