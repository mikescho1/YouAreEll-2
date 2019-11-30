package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO for an Id object
 */
public class Id {

    private String name;
    @JsonProperty("github")
    private String gitHubId;
    @JsonProperty("userid")
    private String userId;
    
    public Id (String name, String gitHubId, String userId) {
        this.name = name;
        this.gitHubId = gitHubId;
        this.userId = userId;
    }
    public Id () {};

    public Id(String name, String gitHubId) {
        this.name = name;
        this.gitHubId = gitHubId;
        this.userId = "-";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGitHubId() {
        return gitHubId;
    }

    public void setGitHubId(String gitHubId) {
        this.gitHubId = gitHubId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}