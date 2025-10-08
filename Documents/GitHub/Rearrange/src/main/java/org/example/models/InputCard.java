package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputCard {
    public String productName;
    public String cardNumber;
    public String accountNumber;
    public String balance;

    @JsonProperty("expire-date")
    public String expire_date;

}
