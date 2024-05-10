package br.com.Ebanx.controllers;

import br.com.Ebanx.dtos.events.EventDTO;
import br.com.Ebanx.dtos.events.ReturnEventDTO;
import br.com.Ebanx.models.AccountModel;
import br.com.Ebanx.models.EventModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Events {

    @PostMapping("/reset")
    public ResponseEntity<String> reset() {
        AccountModel.reset();
        return ResponseEntity.status(200).body("OK");
    }

    @PostMapping("/event")
    public ResponseEntity<Object> events(@RequestBody EventDTO eventDTO) throws Exception {
        return EventModel.processEvent(eventDTO);
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getAccountBalance(@RequestParam String account_id) {
        return AccountModel.getAccountBalance(account_id);
    }

}
