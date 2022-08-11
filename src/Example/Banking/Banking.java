package Example.Banking;


import Example.WalkToArea;
import Lib.Abstracts.Behavior;
import Lib.Abstracts.Macro;
import Lib.RSMacro;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;

public class Banking extends RSMacro {

    Area closestBank = org.dreambot.api.methods.container.impl.bank.Bank.getClosestBankLocation().getArea(8);

    public Banking(String name) {
        super(name);
        // These are the current behaviors in our Banking Macro
        this.behaviors = new Behavior[] {
                new WalkToArea("walkToBank", closestBank),
                new InteractWithBank("withdrawCoins")
        };
    }

    @Override
    public String getTransition(Object agent, Macro parent) {
        // You call the different behaviours in the macro by returning their super String
        // eg: to call the InteractWithBank behaviour return "withdrawCoins";
        if (closestBank.contains(Players.localPlayer())) {
            return "withdrawCoins";
        } else {
            return "walkToBank";
        }
    }
}
