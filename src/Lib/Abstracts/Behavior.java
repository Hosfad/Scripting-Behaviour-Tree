package Lib.Abstracts;

public abstract class Behavior implements java.io.Serializable {
    public static final long serialVersionUID = 1;
    // FLAGS
    public static final int NO_FLAG = 0;
    public static final int FLAG_DONE = 1;
    public static final int FLAG_FAILED = 2;
    public static String crashMessage = "";
    /// PARENT
    transient Macro parent;
    // IDENTITY
    String name;
    Flags flag = Flags.NO_FLAG;
    boolean propagateFlags = true;

    public Behavior(String name) {
        this.name = name;
    }

    // BASIC BEHAVIORS
    public void behaviourStart(Object agent, Macro parent) {
        flag = Flags.NO_FLAG;
        fireFlag(Flags.NO_FLAG);
        this.parent = parent;
    }

    public void stop(Object agent, Macro parent) {
        this.parent = parent;
    }

    public abstract void behaviourGo(Object agent, Macro parent);

    public Macro getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public String getBehaviorBacktrace() {
        return getBehaviorBacktrace(new StringBuilder()).toString().trim();
    }
    protected StringBuilder getBehaviorBacktrace(StringBuilder builder) {
        return builder.append(toString()).append("\n");
    }

    public void fail() {
        setFlag(Flags.FAIL);
    }

    public void done() {
        setFlag(Flags.DONE);
    }

    public void crash(String message) {
        crashMessage = message;
        setFlag(Flags.CRASHED);
    }

    public void crash() {
        setFlag(Flags.CRASHED);
    }

    public boolean isDone() {
        return flag == Flags.DONE;
    }

    public boolean isCrashed() {
        return flag == Flags.CRASHED;
    }

    public boolean isFail() {
        return flag == Flags.FAIL;
    }

    public boolean getPropagateFlags() {
        return propagateFlags;
    }

    public void setPropagateFlags(boolean val) {
        propagateFlags = val;
    }

    public Flags getFlag() {
        return flag;
    }

    public void setFlag(Flags val) {
        this.flag = val;
        if (flag != Flags.NO_FLAG && parent != null) {
            parent.flag = val;
        }
//        flag = val;
//
        if (flag == Flags.CRASHED && parent != null) {
            parent.setFlag(val);
        }
//
//        }
    }

    public void fireFlag(Flags val) {
        if (parent != null) parent.setFlag(val);
    }
}