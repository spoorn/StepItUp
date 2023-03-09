package org.spoorn.stepitup.forge;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;
import org.spoorn.stepitup.StepItUp;

@Mod(StepItUp.MOD_ID)
public class StepItUpForge {
    
    // Client side only
    public StepItUpForge() {
        // Submit our event bus to let architectury register our content on the right time
        //EventBuses.registerModEventBus(StepItUp.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        //Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        
        StepItUp.init();
    }
}