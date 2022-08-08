package Example;

import Lib.RSMacro;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(category = Category.MISC, name = "AwesomeScript", author = "AwesomeScripter", version = 1.0)
public class DreambotMainClass extends AbstractScript {

    RSMacro macro = new MainBranch("super name");

    @Override
    public int onLoop() {
        // The arguments are null because this behaviour has no parent
        macro.behaviourGo(null,null);


        return 100;
    }


}