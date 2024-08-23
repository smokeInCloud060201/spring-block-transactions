package vn.com.smoke.springlocktransaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LikeDTO {
    @JsonProperty("talker_name")
    private String talkerName;

    @JsonProperty("like")
    private int like;
}
