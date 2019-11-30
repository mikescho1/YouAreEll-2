package controllers;

import java.util.ArrayList;

import models.Id;

public class IdController {
    Id myId;
    private TransactionController transactionController;

    public IdController(TransactionController transController) {                  //now you can talk to the server via the transaction controller.
        this.transactionController = transController;

    }
    public ArrayList<Id> getIds() {
        return null;
    }

    public Id postId(Id id) {
        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}