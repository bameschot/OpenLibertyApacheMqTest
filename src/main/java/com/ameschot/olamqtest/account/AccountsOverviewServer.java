package com.ameschot.olamqtest.account;

import com.ameschot.olamqtest.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("accounts-overview")
public class AccountsOverviewServer {

    @Inject
    AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountsOverview(){
        return Response.accepted(accountService.getAccounts()).build();
    }
}
