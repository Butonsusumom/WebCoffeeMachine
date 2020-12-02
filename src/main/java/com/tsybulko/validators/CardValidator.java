package com.tsybulko.validators;

import com.tsybulko.entity.Card;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardValidator {
    public static boolean validateCard(Card card, String amount) {
        if (card == null || amount == null)
            return false;
        return validateNumber(card.getCardNumber()) && validateSecureCode(card.getSecureCode()) && validateAmount(amount);
    }

    private static boolean validateNumber(String cardNumber) {
        boolean result = false;
        if (hasScript(cardNumber))
            return result;
        String cardNumberRegex = new String("^[0-9]{16,19}$");
        Pattern pattern = Pattern.compile(cardNumberRegex);
        Matcher matcher = pattern.matcher(cardNumber);
        while (matcher.find())
            result = matcher.group().equals(cardNumber) ? true : result;
        return result;
    }

    private static boolean validateSecureCode(String secureCode) {
        boolean result = false;
        if (hasScript(secureCode))
            return result;
        String secureCodeRegex = new String("^[0-9]{3,3}$");
        Pattern pattern = Pattern.compile(secureCodeRegex);
        Matcher matcher = pattern.matcher(secureCode);
        while (matcher.find())
            result = matcher.group().equals(secureCode) ? true : result;
        return result;
    }

    private static boolean validateAmount(String amount) {
        if (hasScript(amount))
            return false;
        return Integer.parseInt(amount) > 0 && amount.length() < 6;
    }

    private static boolean validateDate() {
        return true;
    }

    private static boolean hasScript(String line) {
        String scriptRegex = new String("<script");
        Pattern pattern = Pattern.compile(scriptRegex);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    public static boolean validateExpiryDate(String month, String year) {
        if (month == null || year == null)
            return false;
        if (hasScript(month) || hasScript(year))
            return false;
        int numMonth = Integer.parseInt(month);
        if (numMonth < 1 || numMonth > 12)
            return false;
        int numYear = Integer.parseInt(year);
        Calendar calendar = GregorianCalendar.getInstance();
        if (calendar.get(Calendar.YEAR) - 2000 > numYear || numYear > (calendar.get(Calendar.YEAR) - 2000 + 12))
            return false;
        try {
            if (new Date().getTime() > new SimpleDateFormat("MMyy").parse(month + year).getTime())
                return false;
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
