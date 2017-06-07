package convenientadditions.base.block;

import convenientadditions.ModConstants;
import convenientadditions.StringHelper;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class CABlockFluidClassic extends BlockFluidClassic {

    protected boolean defaultInfo=false;
    protected boolean defaultAdditionalInfo=false;
    protected boolean defaultJoke=false;
    protected boolean baublesRequiredInfo=false;
    protected EnumItemCategory category=null;

    public CABlockFluidClassic(Fluid fluidIn,Material materialIn) {
        super(fluidIn,materialIn);
    }

    public CABlockFluidClassic(String name, Fluid fluidIn, Material materialIn) {
        super(fluidIn,materialIn);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + name).setRegistryName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        if(defaultJoke)
            tooltip.add(StringHelper.getJoke(stack));
        if(defaultInfo)
            tooltip.add(StringHelper.getInfo(stack));
        if(defaultAdditionalInfo)
            tooltip.add(StringHelper.getAdditionalInfo(stack));
        if(category!=null)
            category.addTooltip(tooltip);
        super.addInformation(stack,player,tooltip,advanced);
    }

    public CABlockFluidClassic setDefaultInfo(boolean defaultInfo) {
        this.defaultInfo = defaultInfo;
        return this;
    }

    public CABlockFluidClassic setDefaultAdditionalInfo(boolean defaultAdditionalInfo) {
        this.defaultAdditionalInfo = defaultAdditionalInfo;
        return this;
    }

    public CABlockFluidClassic setDefaultJoke(boolean defaultJoke) {
        this.defaultJoke = defaultJoke;
        return this;
    }

    public CABlockFluidClassic setCategory(EnumItemCategory category) {
        this.category=category;
        return this;
    }

    public CABlockFluidClassic setBaublesRequiredInfo(boolean baublesRequiredInfo) {
        this.baublesRequiredInfo = baublesRequiredInfo;
        return this;
    }

    @Override
    public CABlockFluidClassic setSoundType(SoundType sound)
    {
        this.blockSoundType = sound;
        return this;
    }
}
