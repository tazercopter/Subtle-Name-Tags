package dev.tazer.subtlety;

import dev.tazer.subtlety.platform.Services;
import folk.sisby.kaleido.api.ReflectiveConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.FloatRange;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.SerializedName;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;

public class Config extends ReflectiveConfig {
    public static final Config INSTANCE = ReflectiveConfig.createToml(Services.PLATFORM.configPath(), Subtlety.ID,  "client", Config.class);

    @Comment("If the armor bar should appear on the hud")
    @SerializedName("showArmor")
    public final TrackedValue<Boolean> SHOW_ARMOR = value(false);

    @Comment("If item tooltips render above the hotbar when switching to a new held item")
    @SerializedName("heldItemTooltips")
    public final TrackedValue<Boolean> HELD_TOOLTIPS = value(true);

    @Comment("If the experience bar is hidden while not gaining experience")
    @SerializedName("dynamicExperienceBar")
    public final TrackedValue<Boolean> DYNAMIC_EXP_BAR = value(true);

    // SECTION: NAME TAGS
    public static class NameTags extends Section {
        @Comment("If nametags render above the head")
        @SerializedName("headNameTags")
        public final TrackedValue<Boolean> HEAD_NAMES = value(true);

        // SECTION: HEAD NAME TAGS
        public static class HeadNameTags extends Section {
            @Comment("The radius around a nametag above the head where it is visible")
            @SerializedName("nameRadius")
            @FloatRange(min = 1F, max = 128F)
            public final TrackedValue<Float> NAME_RADIUS = value(7F);
            @Comment("If nametags above the head are only visible when in line of sight")
            @SerializedName("requireLineOfSight")
            public final TrackedValue<Boolean> LINE_OF_SIGHT = value(true);
            @Comment("If nametags above the head are only visible when the view is unobstructed")
            @SerializedName("requireUnobstructedView")
            public final TrackedValue<Boolean> UNOBSTRUCTED_VIEW = value(true);
        }
        // END SECTION: HEAD NAME TAGS
        public final HeadNameTags HEAD_NAME_TAGS = new HeadNameTags();

        @Comment("If nametags render under the crosshair when pointing at an entity")
        @SerializedName("crosshairNameTags")
        public final TrackedValue<Boolean> CROSSHAIR_NAMES = value(true);
    }
    // END SECTION: NAME TAGS
    public final NameTags NAME_TAGS = new NameTags();

}
