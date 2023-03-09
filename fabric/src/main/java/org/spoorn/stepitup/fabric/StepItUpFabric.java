package org.spoorn.stepitup.fabric;

import net.fabricmc.api.ClientModInitializer;
import org.spoorn.stepitup.StepItUp;

// Mod is only client side
public class StepItUpFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        StepItUp.init();
    }
}