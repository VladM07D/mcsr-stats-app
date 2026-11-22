package mcsrstats.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MatchInfo(
        int id,
        int type,          // 2 = ranked
        int season,
        String category,
        long date,         // epoch SECONDS
        List<UserProfile> players,
        Result result,
        boolean forfeited,
        boolean decayed) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Result(String uuid, Long time) {   // time is in MILLISECONDS
    }
}