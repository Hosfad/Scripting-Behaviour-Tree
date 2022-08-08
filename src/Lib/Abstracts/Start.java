package Lib.Abstracts;

/**
 * Start does absolutely nothing.  Except that we start there in any initial macro.
 */
public class Start extends Behavior {
    public static final long serialVersionUID = 1;

    public Start() {
        super("Start");

    }

    @Override
    public void behaviourGo(Object agent, Macro parent) {

    }
}

