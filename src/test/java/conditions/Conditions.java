package conditions;

import io.qameta.allure.Step;

public class Conditions {

    @Step("Проверяю, что \"{key}\": \"{value}\"")
    public static BodyStringCondition attr(String key, String value) {
        return new BodyStringCondition(key, value);
    }

    @Step("Проверяю, что \"{key}\": {value}")
    public static BodyIntCondition attr(String key, int value) {
        return new BodyIntCondition(key, value);
    }

    @Step("Проверяю, что \"{key}\": {value}")
    public static BodyLongCondition attr(String key, long value) {
        return new BodyLongCondition(key, value);
    }

    @Step("Проверяю, что \"{key}\": {value}")
    public static BodyDoubleCondition attr(String key, Double value) {
        return new BodyDoubleCondition(key, value);
    }

    @Step("Проверяю, что \"{key}\": {value}")
    public static BodyBooleanCondition attr(String key, boolean value) {
        return new BodyBooleanCondition(key, value);
    }

    @Step("Проверяю, что \"{key}\": содержит '{value}'")
    public static BodyContainsStrCondition contains(String key, String value) {
        return new BodyContainsStrCondition(key, value);
    }

    @Step("Проверяю, что \"{key}\": содержит '{value}'")
    public static BodyContainsIntCondition contains(String key, Integer value) {
        return new BodyContainsIntCondition(key, value);
    }

    @Step("Проверяю, что \"{key}\": содержит '{value}' {count} раз")
    public static BodyContainsStrCountCondition contains(String key, String value, Integer count) {
        return new BodyContainsStrCountCondition(key, value, count);
    }

    @Step("Проверяю, что \"{key}\": не содержит '{value}'")
    public static BodyNotContainsCondition notContains(String key, String value) {
        return new BodyNotContainsCondition(key, value);
    }

    @Step("Проверяю, что \"{key}\": начинается с '{value}'")
    public static BodyStartsWithCondition startsWith(String key, String value) {
        return new BodyStartsWithCondition(key, value);
    }

    @Step("Проверяю, что \"{key}\": начинается с '{value}'")
    public static BodyEndsWithCondition endsWith(String key, String value) {
        return new BodyEndsWithCondition(key, value);
    }

    /*@Step("Проверяю, что \"refinements[0].{key}\": \"{value}\"")*/
    public static BodyStringCondition refinements(String key, String value) {
        return refinements(0, key, value);
    }

    /*@Step("Проверяю, что \"refinements[0].{key}\": {value}")*/
    public static BodyIntCondition refinements(String key, int value) {
        return refinements(0, key, value);
    }

    @Step("Проверяю, что \"refinements[{number}].{key}\": \"{value}\"")
    public static BodyStringCondition refinements(int number, String key, String value) {
        return new BodyStringCondition("refinements[" + number + "]." + key, value);
    }

    @Step("Проверяю, что \"refinements[{number}].{key}\": {value}")
    public static BodyIntCondition refinements(int number, String key, int value) {
        return new BodyIntCondition("refinements[0]." + key, value);
    }

    @Step("Проверяю, что \"ecr_params.{key}\": \"{value}\"")
    public static BodyStringCondition ecrParams(String key, String value) {
        return new BodyStringCondition("ecr_params." + key, value);
    }

    @Step("Проверяю, что \"ecr_params.{key}\": {value}")
    public static BodyIntCondition ecrParams(String key, int value) {
        return new BodyIntCondition("ecr_params." + key, value);
    }

    @Step("Проверяю, что \"params.{key}\": {value}")
    public static BodyIntCondition params(String key, int value) {
        return new BodyIntCondition("params." + key, value);
    }

    @Step("Проверяю, что \"params.{key}\": \"{value}\"")
    public static BodyStringCondition params(String key, String value) {
        return new BodyStringCondition("params." + key, value);
    }

    @Step("Проверяю, что \"{key}\": не пустой")
    public static BodyNotNullCondition notNull(String key) {
        return new BodyNotNullCondition(key);
    }

    @Step("Проверяю, чек \"{xPath}\": {value}")
    public static ReceiptByXPath byXpath(String xPath, String value) {
        return new ReceiptByXPath(xPath, value);
    }

    @Step("Проверяю, чек <div id={id}>{value}</div>")
    public static ReceiptById byId(String id, String value) {
        return new ReceiptById(id, value);
    }

    @Step("Проверяю, чек <div class={divClass}>{value}</div>")
    public static ReceiptByClass byClass(String divClass, String value) {
        return new ReceiptByClass(divClass, value);
    }

    @Step("Проверяю, чек <div class={class1} {class2}>{value}</div>")
    public static ReceiptByClasses byClasses(String class1, String class2, String value) {
        return new ReceiptByClasses(class1, class2, value);
    }
}
