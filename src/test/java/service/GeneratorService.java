package service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static helpers.ConfigHelper.terminalConfig;
import static helpers.Randomizer.randomAlphabetic;
import static helpers.Randomizer.randomDouble;

public class GeneratorService {

    public static GeneratorService generator() {
        return new GeneratorService();
    }

    public String getTransaction() {
        String serialNumber = terminalConfig().getSerialNumber();
        String date = getDate();
        String timestamp = getTimestamp();

        return "TRANSACTION" + serialNumber + "-" + date + timestamp;
    }

    public String getSessionId() {
        String serialNumber = terminalConfig().getSerialNumber();
        String date = getDate();
        String timestamp = getTimestamp();

        return "SESSION" + serialNumber + "-" + date + timestamp;
    }

    /**
     * @return format yyyyMMdd
     **/
    private String getDate() {
        return String.valueOf(
                        LocalDate.now(ZoneOffset.ofHours(2)))
                .replace("-", "");
    }

    /**
     * @return format yyyy-MM-ddTHH:mm:ss.SSSZ
     **/
    public String getDateFull() {
        return LocalDateTime.now(ZoneOffset.ofHours(2)) + "Z";
    }

    public String getAuthorisation() {
        return getDate().substring(2) + randomAlphabetic(14);
    }

    /**
     * @return example 1725025028979
     **/
    public String getTimestamp() {
        return String.valueOf(
                ZonedDateTime.now().toInstant().toEpochMilli());
    }

    public String amountStr(Double max) {
        return String.valueOf(amountDouble(0.01, max));
    }

    public String amountStr(Double min, Double max) {
        return String.valueOf(amountDouble(min, max));
    }

    public Double amountDouble(Double max) {
        return amountDouble(0.01, max);
    }

    public Double amountDouble(Double min, Double max) {
        return randomDouble(min, max);
    }

    public Timestamp getDateTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
