package org.example.utils;

import org.example.models.*;
import java.util.*;
import static org.example.utils.DateUtil.convertDate;

public class CardUtil {
    public static OutputResult process(List<InputCard> cards) throws Exception {
        OutputResult result = new OutputResult();
        result.accounts = new ArrayList<>();

        Map<String, List<InputCard>> byAccount = new TreeMap<>();
        for (InputCard c : cards) {
            byAccount.computeIfAbsent(c.accountNumber, k -> new ArrayList<>()).add(c);
        }

        for (String accNum : byAccount.keySet()) {
            OutputAccount acc = new OutputAccount();
            acc.accountNumber = accNum;
            acc.products = new ArrayList<>();

            Map<String, List<InputCard>> byProduct = new TreeMap<>();
            for (InputCard c : byAccount.get(accNum)) {
                byProduct.computeIfAbsent(c.productName, k -> new ArrayList<>()).add(c);
            }

            for (String productName : byProduct.keySet()) {
                OutputProduct product = new OutputProduct();
                product.name = productName;
                product.details = new ArrayList<>();
                double total = 0;

                List<InputCard> productCards = byProduct.get(productName);
                productCards.sort(Comparator.comparing(c -> c.cardNumber));

                for (InputCard c : productCards) {
                    OutputDetail detail = new OutputDetail();
                    detail.cardNumber = c.cardNumber;
                    detail.balance = c.balance;
                    detail.expire_date = convertDate(c.expire_date);
                    product.details.add(detail);

                    total += Double.parseDouble(c.balance);
                }

                product.totalBalance = String.format("%.2f", total);
                acc.products.add(product);
            }

            result.accounts.add(acc);
        }

        result.accountTotal = result.accounts.size();
        return result;
    }
}
