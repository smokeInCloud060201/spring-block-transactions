package vn.com.smoke.springlocktransaction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.smoke.springlocktransaction.dto.LikeDTO;
import vn.com.smoke.springlocktransaction.entities.History;
import vn.com.smoke.springlocktransaction.entities.Speaker;
import vn.com.smoke.springlocktransaction.repository.HistoryRepository;
import vn.com.smoke.springlocktransaction.repository.SpeakerRepository;
import org.springframework.retry.annotation.Retryable;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpeakerService {

    private final SpeakerRepository speakerRepository;
    private final HistoryRepository historyRepository;


    @Transactional(timeout = 10)
    @Retryable
    public void addLikeToSpeaker(LikeDTO likeDTO) {
        if (Optional.ofNullable(likeDTO).map(LikeDTO::getTalkerName).isPresent()) {

            Speaker speaker = speakerRepository.findBySpeakerName(likeDTO.getTalkerName()).orElse(null);
            if (speaker != null) {
                saveMessageToHistory(likeDTO, "RECEIVED");
                speaker.setLikes(speaker.getLikes() + likeDTO.getLike());
                speakerRepository.save(speaker);
                log.info("{} likes added to {}", likeDTO.getLike(), speaker.getFirstName() + " " + speaker.getLastName());
            } else {
                log.warn("Speaker with talk {} not found", likeDTO.getTalkerName());
                saveMessageToHistory(likeDTO, "ORPHANED");
            };
        }
    }

    private void saveMessageToHistory(LikeDTO likes, String status) {
        try {
            historyRepository.save(History.builder()
                    .talkName(likes.getTalkerName())
                    .likes(likes.getLike())
                    .status(status)
                    .build());
        } catch (RuntimeException ex) {
            log.warn("Failed to save message to history.", ex);
        }
    }
}
