package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputDetail {
    public String cardNumber;
    public String balance;

    @JsonProperty("expire-date")
    public String expire_date;
}
