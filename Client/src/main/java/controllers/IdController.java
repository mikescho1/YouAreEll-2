package controllers;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

public class IdController {
    Id myId;
    private TransactionController transactionController;
    private ArrayList<Id> idList;

    public IdController(TransactionController transController) {                  //now you can talk to the server via the transaction controller.
        this.transactionController = transController;

    }

    public ArrayList<Id> getIds() {
        try {
            String response = transactionController.makeURLCall("/ids", "GET", "");
            System.out.println(response);
            ObjectMapper objectMapper = new ObjectMapper();
            this.idList = objectMapper.readValue(response, new TypeReference<ArrayList<Id>>() {});
            return this.idList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public Id postId(Id id) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jSonPayload = objectMapper.writeValueAsString(id);
            String response = transactionController.makeURLCall("/ids", "POST", jSonPayload);
            getIds();
            return getByGitHubId(id.getGitHubId());

        }   catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Id putId(String name, String gitHubId) {
        try {
            getIds();
            for(Id id: idList)      {
                if(id.getGitHubId().equals(gitHubId))   {
                    id.setName(name);
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jSonPayload = objectMapper.writeValueAsString(id);
                    String response = transactionController.makeURLCall("/ids", "PUT", jSonPayload);
                    getIds();
                    return getByGitHubId(id.getGitHubId());
                }
            }

        }   catch (Exception e) {
            e.printStackTrace();;
        }
        return null;
    }

    public Id getByGitHubId(String gitHubId)   {
        for(Id id: idList)  {
            if(id.getGitHubId().equals(gitHubId))   {
                return id;
            }
        }   return null;
    }

}