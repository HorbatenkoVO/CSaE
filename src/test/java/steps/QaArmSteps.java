package steps;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import service.QaArmService;

import java.util.concurrent.TimeUnit;

public class QaArmSteps {
    private QaArmService qaArm;

    public QaArmSteps() {
        qaArm().handShake();
    }

    public String getVersion() {
        qaArm().write("9999");
        return qaArm().read();
    }

    /**
     * Работает с прошивками до v.1f.02 и v.2f.02
     */
    @Deprecated
    @SneakyThrows
    @Step("Чтение NFC")
    public void readNfc() {
        readNfcDown();
        TimeUnit.SECONDS.sleep(4);
        readNfcUp();
    }

    @Step("Прикладывание карты к NFC.")
    public void readNfcDown() {
        qaArm().write("1");
        qaArm().read();
    }

    @Step("Убирание карты от NFC.")
    public void readNfcUp() {
        qaArm().write("2");
        qaArm().read();
    }

    @Step("Чтение чипа")
    public void readIcc() {
        System.out.println("Чтение чипа.");
        //todo реализовать физическую руку
    }

    private QaArmService qaArm() {
        if (qaArm == null) {
            qaArm = new QaArmService();
        }
        return qaArm;
    }
}
