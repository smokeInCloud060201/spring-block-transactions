package vn.com.smoke.springlocktransaction.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.smoke.springlocktransaction.dto.LikeDTO;
import vn.com.smoke.springlocktransaction.service.SpeakerService;

@RestController
@RequestMapping("/speaker")
@RequiredArgsConstructor
@Slf4j
public class SpeakerController {
    private final SpeakerService service;


    @PostMapping("/addlikes")
    public ResponseEntity<String> updateSpeaker(@RequestBody LikeDTO likes) {
        try {
            service.addLikeToSpeaker(likes);
            return new ResponseEntity<>("Likes successfully added.", HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.warn("Exception in controller:", ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
