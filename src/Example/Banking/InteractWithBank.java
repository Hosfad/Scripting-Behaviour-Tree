package Example.Banking;


import Lib.Abstracts.Macro;
import Lib.RSBehavior;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.bank.Bank;

import java.io.IOException;

public class InteractWithBank extends RSBehavior {

    public InteractWithBank(String name) {
        super(name);
    }

    // This is your behaviour, your go function is where you write your code.
    @Override
    public void go(Object agent, Macro parent) {
        if (Bank.isOpen()) {
            Bank.withdrawAll("Coins");
            MethodProvider.sleep(600);
            Bank.close();
        } else {
            Bank.open();
        }
    }
}
