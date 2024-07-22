package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.convertor.TimeService;
import edu.wgu.d387_sample_code.convertor.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
public class ResourceController {

    @Autowired
    private WelcomeService welcomeService;

    @Autowired
    private TimeService timeService;

//    @GetMapping("/welcome")
//    public String[] getWelcomeMessages() {
//        return welcomeService.getWelcomeMessages();
//    }

    @GetMapping("/welcome")
    public ResponseEntity<String[]> getWelcomeMessages() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(welcomeService.getWelcomeMessages());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new String[] {"Unable to retrieve welcome messages"});
        }
    }

    @GetMapping("/timezones")
    public ResponseEntity<Map<String, String>> getTimezones() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(timeService.getTimeZones());
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
