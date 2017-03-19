package convenientadditions.item.module.matchers;

import convenientadditions.api.IMatcher;
import net.minecraft.item.EnumDyeColor;

public class MatcherEnumDyeColor implements IMatcher {

    public EnumDyeColor color0;
    public EnumDyeColor color1;
    public EnumDyeColor color2;

    public MatcherEnumDyeColor(EnumDyeColor color0, EnumDyeColor color1, EnumDyeColor color2) {
        this.color0 = color0;
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    public boolean matches(IMatcher matcher) {
        if (matcher instanceof MatcherEnumDyeColor) {
            MatcherEnumDyeColor m = (MatcherEnumDyeColor) matcher;
            return (m.color0.equals(color0) && m.color1.equals(color1) && m.color2.equals(color2));
        }
        return false;
    }

}
