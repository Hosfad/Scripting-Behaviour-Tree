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
        this.behaviors = new Behavior[]{
                new WalkToArea("walkToBank" ,closestBank),
                new InteractWithBank("withdrawCoins")
        };
    }
    @Override
    public String getTransition(Object agent, Macro parent) {
        if (closestBank.contains(Players.localPlayer())){
            return "withdrawCoins";
        }else {
            return "walkToBank";
        }

    }
}
