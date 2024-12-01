package service;

import com.google.gson.Gson;
import model.transaction.cashBox.CashBoxMessage;

public class ConverterService {
    private final Gson g = new Gson();

    public String getJson(CashBoxMessage object) {
        return g.toJson(object);
    }

    public String getJson(Object object) {
        return g.toJson(object);
    }
}
