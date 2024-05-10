package br.com.Ebanx.models;

import br.com.Ebanx.dtos.events.EventDTO;
import br.com.Ebanx.dtos.events.ReturnEventDTO;
import br.com.Ebanx.entity.Account;
import br.com.Ebanx.enums.TypeEvent;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class EventModel {

    private ResponseEntity<Object> deposit(EventDTO eventDTO){
        AccountModel accountModel = new AccountModel();
        Account account = accountModel.getAccount(eventDTO.getDestination());
        ReturnEventDTO returnEventDTO = new ReturnEventDTO();
        if (account!=null){
            account.setBalance(account.getBalance()+eventDTO.getAmount());
            returnEventDTO.setDestination(account);
        } else {
            Account newAccount = new Account();
            newAccount.setId(eventDTO.getDestination());
            newAccount.setBalance(eventDTO.getAmount());
            accountModel.newAccount(newAccount);
            returnEventDTO.setDestination(newAccount);
        }
        return ResponseEntity.status(201).body(returnEventDTO);
    }

    private ResponseEntity<Object> transfer(EventDTO eventDTO){
        AccountModel accountModel = new AccountModel();
        Account accountOrigin = accountModel.getAccount(eventDTO.getOrigin());
        Account accountDestination = accountModel.getAccount(eventDTO.getDestination());
        ReturnEventDTO returnEventDTO = new ReturnEventDTO();
        if (accountOrigin!=null && accountDestination!=null){
            accountOrigin.setBalance(accountOrigin.getBalance()-eventDTO.getAmount());
            accountDestination.setBalance(accountDestination.getBalance()+eventDTO.getAmount());
            returnEventDTO.setOrigin(accountOrigin);
            returnEventDTO.setDestination(accountDestination);
            return ResponseEntity.status(201).body(returnEventDTO);
        } else {
            return ResponseEntity.status(404).body(0);
        }
    }

    private ResponseEntity<Object> withdraw(EventDTO eventDTO){
        AccountModel accountModel = new AccountModel();
        Account account = accountModel.getAccount(eventDTO.getOrigin());
        ReturnEventDTO returnEventDTO = new ReturnEventDTO();
        if (account!=null){
            account.setBalance(account.getBalance()-eventDTO.getAmount());
            returnEventDTO.setOrigin(account);
            return ResponseEntity.status(201).body(returnEventDTO);
        } else {
            return ResponseEntity.status(404).body(0);
        }
    }

    private ResponseEntity<Object> processEventImpl(EventDTO eventDTO) throws Exception {
        try {
            if (Objects.equals(eventDTO.getType(), TypeEvent.deposit.name())) {
                return this.deposit(eventDTO);
            } else if (Objects.equals(eventDTO.getType(), TypeEvent.transfer.name())) {
                return this.transfer(eventDTO);
            } else if (Objects.equals(eventDTO.getType(), TypeEvent.withdraw.name())) {
                return this.withdraw(eventDTO);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static ResponseEntity<Object> processEvent(EventDTO eventDTO) throws Exception {
        EventModel eventModel = new EventModel();
        return eventModel.processEventImpl(eventDTO);
    }

}
