package com.infoshareacademy.pomaranczowi.financialanalyser.financial.currency;

import com.infoshareacademy.pomaranczowi.financialanalyser.financial.domain.Price;
import com.infoshareacademy.pomaranczowi.financialanalyser.financial.domain.Quotation;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyLoader {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String getCurrencyCodeFromUser() {
        System.out.println("Podaj kod waluty (np. USD, EUR): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static Quotation load(String currencyCode) throws FileNotFoundException {

        Quotation currency = new Quotation();
        File file = new File("../cli/data/currency/" + currencyCode + ".txt");
        Scanner fileScanner = new Scanner(file);

        Pattern pattern = Pattern.compile("^([A-Z]{3}),([0-9]{8}),([0-9]\\.[0-9]{4}),([0-9]\\.[0-9]{4}),([0-9]\\.[0-9]{4}),([0-9]\\.[0-9]{4}),([0-9]+.?[0-9]*)$");

        try {
            while (fileScanner.hasNextLine()) {

                Matcher matcher = pattern.matcher(fileScanner.nextLine());
                if (matcher.matches()) {
                    Price price = new Price();
                    currency.setName(matcher.group(1));
                    price.setDate(LocalDate.parse(matcher.group(2), formatter));
                    price.setOpen(new BigDecimal(matcher.group(3)));
                    price.setHigh(new BigDecimal(matcher.group(4)));
                    price.setLow(new BigDecimal(matcher.group(5)));
                    price.setClose(new BigDecimal(matcher.group(6)));
                    price.setVolume(new BigDecimal(matcher.group(7)));

                    currency.getPrices().add(price);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Wystąpił błąd przy wczytywaniu pliku!");
        }
        return currency;
    }
}