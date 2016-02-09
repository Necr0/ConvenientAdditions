package convenientadditions.item.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDigging;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import convenientadditions.Reference;
import convenientadditions.api.IChargable;
import convenientadditions.api.ISunlightChargable;

public class EnchantmentUtil {
	public static final EnumEnchantmentType sunlightChargable=EnumHelper.addEnchantmentType("sunlightChargable");
	public static final EnumEnchantmentType chargable=EnumHelper.addEnchantmentType("chargable");
	
	private static final java.lang.reflect.Field enchantmentSetter = Enchantment.class.getDeclaredFields()[0];
	
	public static final EnchantmentCapacity capacity=new EnchantmentCapacity();
	public static final EnchantmentChargeEfficiency chargeEfficiency=new EnchantmentChargeEfficiency();
	public static final EnchantmentDrain drain=new EnchantmentDrain();
	
	public static final double[] enchantmentScaleFactor=new double[]{1,1.8191959205d,2.32456436379d,2.75447075381d,3.14169859725d,3.5d};
	
	public static abstract class EnchantmentBase extends Enchantment{
		public EnchantmentBase(int enchantmentId,int enchantmentWeight,EnumEnchantmentType enchantmentType) {
			super(enchantmentId,enchantmentWeight,enchantmentType);
		}

	    public boolean canApply(ItemStack stack)
	    {
	        return ((this.type==chargable)&&(stack.getItem() instanceof IChargable))||
	        		((this.type==sunlightChargable)&&(stack.getItem() instanceof ISunlightChargable));
	    }
	    
	    public int getMinEnchantability(int p_77321_1_)
	    {
	        return 1 + 10 * (p_77321_1_ - 1);
	    }
	    
	    public int getMaxEnchantability(int p_77317_1_)
	    {
	        return super.getMinEnchantability(p_77317_1_) + 50;
	    }
	    
	    public int getMaxLevel()
	    {
	        return 5;
	    }
	}
	
	public static class EnchantmentCapacity extends EnchantmentBase{
		public EnchantmentCapacity() {
			super(Reference.enchantmentIdBase+Reference.enchantmentCapacityId, 10, chargable);
			this.setName("capacity");
		}

	    public boolean canApply(ItemStack stack)
	    {
	        return super.canApply(stack)&&((IChargable)stack.getItem()).canApplyCapacity(stack);
	    }
	}
	
	public static class EnchantmentChargeEfficiency extends EnchantmentBase{
		public EnchantmentChargeEfficiency() {
			super(Reference.enchantmentIdBase+Reference.enchantmentChargeEfficiencyId, 10, chargable);
			this.setName("chargeEfficiency");
		}

	    public boolean canApply(ItemStack stack)
	    {
	        return super.canApply(stack)&&((IChargable)stack.getItem()).canApplyChargeEfficiency(stack);
	    }
	}
	
	public static class EnchantmentDrain extends EnchantmentBase{
		public EnchantmentDrain() {
			super(Reference.enchantmentIdBase+Reference.enchantmentDrainId, 10, sunlightChargable);
			this.setName("drain");
		}

	    public boolean canApply(ItemStack stack)
	    {
	        return super.canApply(stack)&&((ISunlightChargable)stack.getItem()).canApplyDrain(stack);
	    }
	}
	
	public static void init(){
		//Enchantment.addToBookList(capacity);
		//Enchantment.addToBookList(chargeEfficiency);
		//Enchantment.addToBookList(drain);
		addToEnchantmentList(capacity);
		addToEnchantmentList(chargeEfficiency);
		addToEnchantmentList(drain);
	}
	

    public static void addToEnchantmentList(Enchantment enchantment)
    {
        try
        {
            net.minecraftforge.common.util.EnumHelper.setFailsafeFieldValue(enchantmentSetter, null,
                com.google.common.collect.ObjectArrays.concat(Enchantment.enchantmentsList, enchantment));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e); //Rethrow see what happens
        }
    }
}
