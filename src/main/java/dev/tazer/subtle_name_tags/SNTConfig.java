package dev.tazer.subtle_name_tags;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SNTConfig {

    public static ModConfigSpec CLIENT_CONFIG;

    public static final String CATEGORY_OVERRIDES = "overrides";
    public static ModConfigSpec.IntValue NAME_RADIUS;
    public static ModConfigSpec.BooleanValue REQUIRE_UNOBSTRUCTED_VIEW;


    static {
        ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

        CLIENT_BUILDER.push(CATEGORY_OVERRIDES);
        NAME_RADIUS = CLIENT_BUILDER
                .comment("The radius around a player where you can see their name")
                .defineInRange("nameRadius", 7, 1, 1000);
        REQUIRE_UNOBSTRUCTED_VIEW = CLIENT_BUILDER
                .comment("Does a name tag only render when there is an unobstructed view to it?")
                .define("requireUnobstructedView", true);

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

}