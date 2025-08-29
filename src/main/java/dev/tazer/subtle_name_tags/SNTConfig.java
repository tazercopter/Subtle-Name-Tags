package dev.tazer.subtle_name_tags;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SNTConfig {

    public static ModConfigSpec CLIENT_CONFIG;

    public static final String CATEGORY_OVERRIDES = "overrides";
    public static ModConfigSpec.DoubleValue NAME_RADIUS;
    public static ModConfigSpec.BooleanValue REQUIRE_LINE_OF_SIGHT;
    public static ModConfigSpec.BooleanValue REQUIRE_UNOBSTRUCTED_VIEW;


    static {
        ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

        CLIENT_BUILDER.push(CATEGORY_OVERRIDES);
        NAME_RADIUS = CLIENT_BUILDER
                .comment("The radius around a name tag where it is visible")
                .defineInRange("nameRadius", 7D, 1, 1000);
        REQUIRE_LINE_OF_SIGHT = CLIENT_BUILDER
                .comment("If name tags are only visible when in line of sight")
                .define("requireLineOfSight", true);
        REQUIRE_UNOBSTRUCTED_VIEW = CLIENT_BUILDER
                .comment("If name tags are only visible when the view is unobstructed")
                .define("requireUnobstructedView", true);

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

}