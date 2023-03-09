package org.spoorn.stepitup;

import lombok.extern.log4j.Log4j2;
import org.spoorn.stepitup.config.ModConfig;

@Log4j2
public class StepItUp {
    public static final String MOD_ID = "stepitup";

    public static void init() {
        log.info("Hello from StepItUp!");

        ModConfig.init();
    }
}