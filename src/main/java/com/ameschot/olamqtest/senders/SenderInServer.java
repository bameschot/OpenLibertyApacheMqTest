package com.ameschot.olamqtest.senders;

import com.ameschot.olamqtest.generated.model.TestInMessage;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("sender-in")
public class SenderInServer {

    @Inject
    SenderIn senderIn;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMessage(TestInMessage testInMessage){
        log.warn("sending: {}",testInMessage);

        senderIn.send(testInMessage);

        return Response.accepted("SEND: "+testInMessage.getAccount()).build();
    }

}
