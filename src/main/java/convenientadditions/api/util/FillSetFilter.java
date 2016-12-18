package convenientadditions.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class FillSetFilter {
	boolean ignoreNBT;
	boolean ignoreDamage;
	
	NonNullList<ItemStack> input;
	NonNullList<ItemStack> filter;
	NonNullList<ItemStack> output;
	boolean ready=false;
	
	public FillSetFilter(NonNullList<ItemStack> input,NonNullList<ItemStack> filter,boolean ignoreDamage,boolean ignoreNBT){
		this.input=ItemHelper.deepCopyItemList(input);
		this.filter=ItemHelper.deepCopyItemList(filter);
		this.output=NonNullList.withSize(input.size(),ItemStack.EMPTY);
		this.ignoreDamage=ignoreDamage;
		this.ignoreNBT=ignoreNBT;
	}

	public FillSetFilter(NonNullList<ItemStack> input,NonNullList<ItemStack> filter,NonNullList<ItemStack> output,boolean ignoreDamage,boolean ignoreNBT){
		this.input=ItemHelper.deepCopyItemList(input);
		this.filter=ItemHelper.deepCopyItemList(filter);
		this.output=ItemHelper.deepCopyItemList(output);
		this.ignoreDamage=ignoreDamage;
		this.ignoreNBT=ignoreNBT;
	}
	
	public boolean isReadyForOutput(){
		if(ready||goThrough())
			return !filtersEmpty();
		return false;
	}
	
	public NonNullList<ItemStack> getOutput(){
		if(!ready)
			goThrough();
		return output;
	}
	
	public NonNullList<ItemStack> getInput(){
		if(!ready)
			goThrough();
		return input;
	}
	
	public boolean goThrough(){
		NonNullList<ItemStack> in=ItemHelper.deepCopyItemList(input);
		NonNullList<ItemStack> fil=ItemHelper.deepCopyItemList(filter);
		NonNullList<ItemStack> out=ItemHelper.deepCopyItemList(output);
		for(int i=0;i<in.size();i++){
			ItemStack stack_in=in.get(i);
			if(!stack_in.isEmpty()){
				for(int j=0;j<fil.size();j++){
					ItemStack stack_f=fil.get(j);
					if(!stack_f.isEmpty()&&ItemHelper.match(stack_in,stack_f,this.ignoreDamage,this.ignoreNBT)){
						for(int k=0;k<out.size();k++){
							ItemStack stack_o=out.get(k);
							if(!stack_o.isEmpty()&&ItemHelper.match(stack_in, stack_o,false,false)&&stack_o.getCount()<stack_o.getItem().getItemStackLimit(stack_o)){
								int amount=Math.min(Math.min(stack_o.getItem().getItemStackLimit(stack_o)-stack_o.getCount(),stack_f.getCount()),stack_in.getCount());
								stack_o.grow(amount);
								stack_in.shrink(amount);
								stack_f.shrink(amount);
							}else if(stack_o.isEmpty()){
								out.set(k,stack_in.copy());
								int amount=Math.min(stack_f.getCount(),stack_in.getCount());
								out.get(k).setCount(amount);
								stack_in.shrink(amount);
								stack_f.shrink(amount);
							}
							if(stack_f.isEmpty()||stack_in.isEmpty())
								break;
						}
					}
					if(stack_in.isEmpty())
						break;
				}
			}
		}
		for(ItemStack f:fil){
			if(!f.isEmpty())
				return false;
		}
		this.input=in;
		this.output=out;
		this.ready=true;
		return true;
	}
	
	public boolean filtersEmpty(){
		for(ItemStack f:filter){
			if(!f.isEmpty())
				return false;
		}
		return true;
	}
}
