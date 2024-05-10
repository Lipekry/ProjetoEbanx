package br.com.Ebanx.entity;

import br.com.Ebanx.dtos.account.AccountDTO;

public class Account {
    private String id;
    private Double balance;

    public Account(){
        this.setBalance(0D);
        this.setId("0");
    }

    public Account(AccountDTO accountDTO){
        this.setId(accountDTO.getId());
        this.setBalance(accountDTO.getBalance());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
