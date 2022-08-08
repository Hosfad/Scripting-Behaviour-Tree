package Lib.Abstracts;

public abstract class Macro extends Behavior {
    public static final long serialVersionUID = 1;
    // Behaviors
    public static final int INITIAL_BEHAVIOR = 0;         // this will be "START"
    public static final int UNKNOWN_BEHAVIOR = -1;
    public static String lastBehaviorCall = "null";
    public Behavior[] behaviors;
    public int currentBehavior = UNKNOWN_BEHAVIOR;

    public Macro(String name) {
        super(name);

    }

    Behavior[] getBehaviors() {
        return behaviors;
    }

    public Behavior getCurrentBehavior() {
        return behaviors[currentBehavior];
    }

    public abstract String macroTransition(Object agent, Macro parent);

    public void behaviourStart(Object agent, Macro parent) {
        super.behaviourStart(agent, parent);
        currentBehavior = INITIAL_BEHAVIOR;
        if (behaviors == null) {
            throw new RuntimeException("BEHAVIOR ARRAY NOT POPULATED!!! " + this.getClass().getName());
        }
        if (behaviors[currentBehavior] == null)
            throw new RuntimeException("INITIAL BEHAVIOR is null.  This should never be allowed to happen.");
        behaviors[currentBehavior].behaviourStart(agent, this);
    }

    public void stop(Object agent, Macro parent) {
        super.stop(agent, parent);
        if (currentBehavior != UNKNOWN_BEHAVIOR)
            behaviors[currentBehavior].stop(agent, this);
        int oldBehavior = currentBehavior;
        currentBehavior = UNKNOWN_BEHAVIOR;
    }

    public void behaviourGo(Object agent, Macro parent) {
        super.parent = parent;

        if (currentBehavior == UNKNOWN_BEHAVIOR) { // should never happen
            throw new RuntimeException("go() called on UNKNOWN_BEHAVIOR. This should not be able to happen.");
        }

        int newBehavior = whichBehavior(macroTransition(agent, parent));

        if (newBehavior != currentBehavior) {
            behaviors[currentBehavior].stop(agent, this);
            behaviors[newBehavior].behaviourStart(agent, this);
        }
        currentBehavior = newBehavior;

        behaviors[currentBehavior].behaviourGo(agent, this);
    }

    public int whichBehavior(String name) {
        lastBehaviorCall = name;
        for (int i = 0; i < behaviors.length; i++) {
            if (behaviors[i].name.equals(name)) {
                return i;
            }
        }
        throw new RuntimeException();
    }

    protected StringBuilder getBehaviorBacktrace(StringBuilder builder) {
        // default:
        builder.append(toString()).append("\n");
        if (currentBehavior < 0) {
            builder.append("--> INVALID CHILD");
            return builder;
        } else
            return getBehaviors()[currentBehavior].getBehaviorBacktrace(builder);
    }
}
