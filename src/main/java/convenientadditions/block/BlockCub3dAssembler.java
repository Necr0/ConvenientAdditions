package convenientadditions.block;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.ModBlocks;
import convenientadditions.init.Reference;
import convenientadditions.tileentity.TileEntityCub3dFrame;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cub3d.api.Cub3dCraftingManager;

public class BlockCub3dAssembler extends Block {
	@SideOnly(Side.CLIENT)
	public IIcon blockIconTop;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconBottom;
	
	public BlockCub3dAssembler() {
		super(Material.iron);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.assemblerBlockName).setHardness(4F).setResistance(8F).setStepSound(soundTypeMetal);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
		return side==0?blockIconBottom:(side==1?blockIconTop:blockIcon);
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.assemblerBlockName+"_side");
        this.blockIconTop = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.assemblerBlockName+"_top");
        this.blockIconBottom = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.assemblerBlockName+"_bottom");
    }
	
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!world.isRemote)
        {
        	if(!player.isSneaking()){
	        	ItemStack[][][] items=new ItemStack[3][3][3];
	        	
	        	for(int i=0;i<3;i++){
	        		for(int j=0;j<3;j++){
	        			for(int k=0;k<3;k++){
	                		if(world.getTileEntity(x-2+(i*2), y+6-(j*2), z-2+(k*2)) instanceof TileEntityCub3dFrame){
	                			TileEntityCub3dFrame frame=(TileEntityCub3dFrame)world.getTileEntity(x-2+(i*2), y+6-(j*2), z-2+(k*2));
	                			items[j][i][k]=frame.getStackInSlot(0);
	                		}else{
	                			player.addChatMessage(new ChatComponentText("Frames not found!"));
	                			return true;
	                		}
	                	}
	            	}
	        	}
	        	
	        	ItemStack result=Cub3dCraftingManager.getInstance().findMatchingRecipe(items, world);
	        	if(result!=null){
	            	for(int i=0;i<3;i++){
	            		for(int j=0;j<3;j++){
	            			for(int k=0;k<3;k++){
	                    		if(world.getTileEntity(x-2+(i*2), y+6-(j*2), z-2+(k*2)) instanceof TileEntityCub3dFrame){
	                    			TileEntityCub3dFrame frame=(TileEntityCub3dFrame)world.getTileEntity(x-2+(i*2), y+6-(j*2), z-2+(k*2));
	                    			frame.setInventorySlotContents(0, null);
	                    		}
	                    	}
	                	}
	            	}
	            	world.spawnEntityInWorld(new EntityItem(world, x+.5, y+.5, z+.5, result));
	        	}else{
	    			player.addChatMessage(new ChatComponentText("Recipe not found!"));
	        	}
	        	return true;
	        }else
	        	return false;
        }else if(player.isSneaking())
        	return false;
        return true;
    }
}
