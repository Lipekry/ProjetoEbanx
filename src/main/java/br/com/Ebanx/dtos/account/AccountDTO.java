package br.com.Ebanx.dtos.account;

import br.com.Ebanx.entity.Account;

public class AccountDTO {
    private String id;
    private Double balance;

    public AccountDTO(){
        this.setBalance(0.0);
        this.setId("0");
    }

    public AccountDTO(Account account) {
        this.setBalance(account.getBalance());
        this.setId(account.getId());
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

}
