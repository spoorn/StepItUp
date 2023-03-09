package org.spoorn.stepitup.config;


import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import org.spoorn.stepitup.StepItUp;

@Config(name = StepItUp.MOD_ID)
public class ModConfig implements ConfigData {
    
    @Comment("True to enable Step Up and disable Auto Jump, false to disable Step Up and allow Auto Jump [default = true]")
    public boolean enableStepUp = true;
    
    @Comment("True to enable Step Up while sneaking (only if enableStepUp = true), false to disable \n" +
            "Step Up when sneaking [default = false]")
    public boolean enableStepUpWhenSneaking = false;

    public static void init() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
    }

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}
