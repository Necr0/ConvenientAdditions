package convenientadditions.base.block.block;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.StringHelper;
import convenientadditions.base.item.EnumItemCategory;
import ctg.BlockCTGTransparentA;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class CABlockCTGTransparentA extends BlockCTGTransparentA {

    protected boolean defaultInfo=true;
    protected boolean defaultAdditionalInfo=false;
    protected boolean defaultJoke=false;
    protected boolean baublesRequiredInfo=false;
    protected EnumItemCategory category=null;

    public CABlockCTGTransparentA(Material materialIn) {
        super(materialIn);
    }

    public CABlockCTGTransparentA(String name, Material materialIn) {
        super(materialIn);
        this.setUnlocalizedName(ModConstants.Mod.MODID + ":" + name).setCreativeTab(ConvenientAdditions.CREATIVETAB).setRegistryName(name);
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

    public CABlockCTGTransparentA setDefaultInfo(boolean defaultInfo) {
        this.defaultInfo = defaultInfo;
        return this;
    }

    public CABlockCTGTransparentA setDefaultAdditionalInfo(boolean defaultAdditionalInfo) {
        this.defaultAdditionalInfo = defaultAdditionalInfo;
        return this;
    }

    public CABlockCTGTransparentA setDefaultJoke(boolean defaultJoke) {
        this.defaultJoke = defaultJoke;
        return this;
    }

    public CABlockCTGTransparentA setCategory(EnumItemCategory category) {
        this.category=category;
        return this;
    }

    public CABlockCTGTransparentA setBaublesRequiredInfo(boolean baublesRequiredInfo) {
        this.baublesRequiredInfo = baublesRequiredInfo;
        return this;
    }

    @Override
    public CABlockCTGTransparentA setSoundType(SoundType sound)
    {
        this.blockSoundType = sound;
        return this;
    }
}
