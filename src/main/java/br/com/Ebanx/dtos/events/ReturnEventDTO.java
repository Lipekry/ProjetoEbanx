package br.com.Ebanx.dtos.events;

import br.com.Ebanx.entity.Account;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ReturnEventDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Account origin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Account destination;

    public Account getOrigin() {
        return origin;
    }

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }
}
