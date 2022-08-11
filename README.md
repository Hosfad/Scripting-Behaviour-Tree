# Scripting-Behaviour-Tree

Behaviour tree to better structure your scripts.

I have included an example at `src/Example`

This is a behaviour, this is where you will write code for interacting with the game.

```Java
public class InteractWithBank extends RSBehavior {
    
    public InteractWithBank(String name) {
        super(name);
    }

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
```

This is a macro, this is what controls your different behaviours.

<span style="background-color: #000"><strong>NOTE: Macros can be nested, meaning a macro can also be a behaviour inside another macro.</strong></span>


```Java
public class Banking extends RSMacro {
    
    Area closestBank = Bank.getClosestBankLocation().getArea(8);

    public Banking(String name) {
        super(name);
        // These are the current behaviors in our Banking Macro
        this.behaviors = new Behavior[] {
                new WalkToArea("walkToBank" ,closestBank),
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
```

Calling the behaviour tree.

```Java
RSMacro macro = new MainBranch("super name");
@Override
public int onLoop() {
    // The arguments are null because this behaviour has no parent
    macro.behaviourGo(null,null);
    return 100;
}
```
