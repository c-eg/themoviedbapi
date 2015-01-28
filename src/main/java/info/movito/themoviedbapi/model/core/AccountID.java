package info.movito.themoviedbapi.model.core;

import com.google.common.base.Preconditions;

/**
 * Simple wrapper to make method signatures less ambiguous as movie ids and page numbers are also just integers.
 */
public class AccountID {

    private final int accountId;


    public AccountID(int accountId) {
        Preconditions.checkArgument(accountId > 0);
        this.accountId = accountId;
    }


    @Override
    public String toString() {
        return accountId + "";
    }
}
