package Lib;

import Lib.Abstracts.Behavior;
import Lib.Abstracts.Macro;

public abstract class RSBehavior extends Behavior {

    public RSBehavior(String name) {
        super(name);
    }

    public abstract void go(Object agent, Macro parent);

    public void start(Object agent, Macro parent) {

    }

    @Override
    public void behaviourStart(Object agent, Macro parent) {
        super.behaviourStart(agent, parent);
        behaviourStart(agent, parent);
    }

    @Override
    public void behaviourGo(Object agent, Macro parent) {
        try {
            behaviourGo(agent, parent);
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
