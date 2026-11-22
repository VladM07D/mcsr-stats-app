package mcsrstats.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserProfile(
        String uuid,
        String nickname,
        Integer roleType,
        Integer eloRate,   // null if placements aren't finished
        Integer eloRank,   // null if placements aren't finished
        String country) {
}