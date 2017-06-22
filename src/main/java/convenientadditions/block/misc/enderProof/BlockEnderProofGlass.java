package convenientadditions.block.misc.enderProof;

import convenientadditions.ModConstants;
import convenientadditions.api.block.IEnderProof;
import convenientadditions.base.block.block.CABlockCTGTransparentA;
import convenientadditions.base.item.EnumItemCategory;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockEnderProofGlass extends CABlockCTGTransparentA implements IEnderProof {
    public BlockEnderProofGlass() {
        super(ModConstants.BlockNames.enderProofGlass, Material.GLASS);
        this.setSoundType(SoundType.GLASS).setHardness(5.0F).setResistance(10.0F);
        this.setCategory(EnumItemCategory.MISC);
    }
}
