package convenientadditions.block.technical;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;

public class MaterialNotQuiteAir extends MaterialTransparent{
    public static final Material NOT_QUITE_AIR=new MaterialNotQuiteAir(MapColor.AIR);

    public MaterialNotQuiteAir(MapColor color) {
        super(color);
        this.setNoPushMobility().setReplaceable();
    }

}
