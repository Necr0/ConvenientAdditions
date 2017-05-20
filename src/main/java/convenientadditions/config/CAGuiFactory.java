package convenientadditions.config;

import convenientadditions.ModConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Necro on 5/19/2017.
 */
public class CAGuiFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft minecraftInstance) {}

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new GuiCAConfig(parentScreen);
    }

    @Override
    @Deprecated
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return GuiCAConfig.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        Set<RuntimeOptionCategoryElement> categories = new HashSet<>();
        return categories;
    }

    @Nullable
    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

    public static class GuiCAConfig extends GuiConfig {

        public GuiCAConfig(GuiScreen parentScreen) {
            super(parentScreen,ModConstants.Mod.MODID, false, false, "convenientadditions",
                    ModConfigGeneral.class,
                    ModConfigBuildingBlocks.class,
                    ModConfigCompat.class,
                    ModConfigConsumables.class,
                    ModConfigCraftingItems.class,
                    ModConfigMachines.class,
                    ModConfigMisc.class,
                    ModConfigModules.class,
                    ModConfigRelics.class,
                    ModConfigTools.class,
                    ModConfigTrinkets.class);
        }

    }
}
