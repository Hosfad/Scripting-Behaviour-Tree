package Example;

import Lib.Abstracts.Macro;
import Lib.RSBehavior;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;

import java.io.IOException;

public class WalkToArea extends RSBehavior {
    Area area;
    public WalkToArea(String name , Area area) {
        super(name);
        this.area = area;
    }

    @Override
    public void go(Object agent, Macro parent) {
        if (area.contains(Players.localPlayer()) || area.contains(Walking.getDestination())){
            return;
        }
        if (Walking.shouldWalk(6)){
            Walking.walk(area.getTile());
            MethodProvider.sleep(600);
        }
    }
}
