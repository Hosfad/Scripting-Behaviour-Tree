package Example;

import Example.Banking.Banking;
import Lib.Abstracts.Behavior;
import Lib.Abstracts.Macro;
import Lib.RSMacro;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.map.Area;

public class MainBranch extends RSMacro {

    Area grandExchange = new Area(3156, 3487, 3172, 3483);

    public MainBranch(String name) {
        super(name);
        this.behaviors = new Behavior[] {
            new Banking("bank"),
                new WalkToArea("walkToGe" ,grandExchange)
        };
    }

    @Override
    public String getTransition(Object agent, Macro parent) {
        if (Inventory.contains("Coins")) {
            return "walkToGe";
        }else {
            return "bank";
        }
    }
}
