package br.com.Ebanx.models;

import br.com.Ebanx.dtos.account.AccountDTO;
import br.com.Ebanx.entity.Account;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountModel {

    public static List<Account> accounts = new ArrayList<>();

    public AccountModel(){
        Account account = new Account();
        account.setId("300");
        account.setBalance(0D);
        accounts.add(account);
    }

    public void newAccount(Account newAccount){
        accounts.add(newAccount);
    }

    public static void reset() {
        accounts.clear();
    }

    public Account getAccount(String id) {
        for (Account account: accounts) {
            if (Objects.equals(account.getId(), id)) {
                return account;
            }
        }
        return null;
    }

    public static ResponseEntity<Double> getAccountBalance(String id) {
        AccountModel accountModel = new AccountModel();
        Account account = accountModel.getAccount(id);
        if (account == null){
            return ResponseEntity.status(404).body(0D);
        } else {
            AccountDTO accountDTO = new AccountDTO(account);
            return ResponseEntity.status(200).body(accountDTO.getBalance());
        }
    }

}
