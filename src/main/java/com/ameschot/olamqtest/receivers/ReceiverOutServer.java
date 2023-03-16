package com.ameschot.olamqtest.receivers;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ameschot.olamqtest.common.MessageUnMarshaller;
import com.ameschot.olamqtest.generated.model.TestOutMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("receiver-out")
public class ReceiverOutServer {

    @Inject
    ReceiverOut receiver;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response received() {
        TestOutMessage outMessage = receiver.receive();
        if (outMessage != null) {
            return Response.status(Response.Status.ACCEPTED).entity(outMessage).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
