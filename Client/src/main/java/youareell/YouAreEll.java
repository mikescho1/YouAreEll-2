package youareell;

import com.sun.xml.internal.bind.v2.model.core.ID;
import controllers.*;
import models.Id;
import views.IdTextView;

public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;
    private TransactionController transactionController;


    public YouAreEll () {
        // used j because i seems awkward
        this.transactionController = new TransactionController();
        this.idCtrl = new IdController(transactionController);

    }


    public String get_ids() {
       return new IdTextView().toString(idCtrl.getIds());
    }

    public Id putOrPostIds(String name, String gitHubId)    {
        for(Id dbId: idCtrl.getIds()) {
            if (gitHubId.equals(dbId.getGitHubId())) {
               Id id = new Id(name, gitHubId);
                return idCtrl.putId(name, gitHubId);
            }
        }   Id newId = new Id(name, gitHubId);
        return idCtrl.postId(newId);
    }

    public String get_messages() {
       return null;
    }


}
