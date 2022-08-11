package Lib;


import Lib.Abstracts.Flags;
import Lib.Abstracts.Macro;

public abstract class RSMacro extends Macro {
    public RSMacro(String name) {
        super(name);
    }

    public abstract String getTransition(Object agent, Macro parent);

    @Override
    public String macroTransition(Object agent, Macro parent) {
        String trans = macroTransition(agent, parent);
        setFlag(Flags.NO_FLAG);
        return trans;
    }
}
