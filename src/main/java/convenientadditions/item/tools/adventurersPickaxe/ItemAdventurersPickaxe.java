package convenientadditions.item.tools.adventurersPickaxe;

import convenientadditions.ModConstants;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.item.IPlayerInventoryTick;
import convenientadditions.api.item.ISoulbound;
import convenientadditions.api.util.Helper;
import convenientadditions.base.item.CAItem;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ItemAdventurersPickaxe extends CAItem implements ISoulbound, IPlayerInventoryTick {
    public List<ItemStack> subitems;

    public ItemAdventurersPickaxe() {
        super(ModConstants.ItemNames.adventurersPickaxe);
        this.setMaxStackSize(1).setHasSubtypes(true);
        initSubItems();
        this.setCategory(EnumItemCategory.TOOL);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if(isBroken(stack))
            return .8f;
        if (isEffective(state)) {
            float f = (float) getToolProperty(stack, "mining_speed");
            return f + (state.getBlock().getHarvestLevel(state) < getHarvestLevel(stack, "pickaxe", null, state) ? (float) getToolProperty(stack, "mining_soft_speed") : 0f);
        }
        if (state.getBlock().getHarvestTool(state)!=null && state.getBlock().getHarvestTool(state).equals("shovel")) {
            return (float) getToolProperty(stack, "digging_speed");
        }
        return 1.0f;
    }

    public boolean isEffective(IBlockState state) {
        Material m=state.getMaterial();
        String tool=state.getBlock().getHarvestTool(state);
        return m == Material.IRON || m == Material.ANVIL || m == Material.ROCK || (tool != null && tool.equals("pickaxe"));
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @Nullable EntityPlayer player, @Nullable IBlockState state) {
        if (isBroken(stack))
            return -1;
        if (toolClass.equals("pickaxe"))
            return (int) getToolProperty(stack, "mining_level");
        else
            return super.getHarvestLevel(stack, toolClass, player, state);
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn, ItemStack item) {
        return blockIn.getBlock().getHarvestTool(blockIn) == null || blockIn.getBlock().getHarvestLevel(blockIn) <= getHarvestLevel(item, blockIn.getBlock().getHarvestTool(blockIn), null, blockIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Override
    public java.util.Set<String> getToolClasses(ItemStack stack)
    {
        HashSet<String> s=new HashSet<>();
        s.add("pickaxe");
        return s;
    }

    public void setToolProperty(ItemStack s, String p, Object o) {
        initTagCompound(s);
        NBTTagCompound nbt = s.getTagCompound();
        NBTTagCompound props = (NBTTagCompound) nbt.getTag("TOOL_PROPERTIES");
        NBTTagCompound stats = (NBTTagCompound) props.getTag("STATISTICS");
        NBTTagCompound specs = (NBTTagCompound) props.getTag("SPECIFICATIONS");
        switch (p.toLowerCase()) {
            case "durability":
                specs.setInteger("DURABILITY", (int) o);
                break;
            case "mining_speed":
                specs.setFloat("MINING_SPEED", (float) o);
                break;
            case "mining_soft_speed":
                specs.setFloat("MINING_SOFT_SPEED", (float) o);
                break;
            case "mining_level":
                specs.setInteger("MINING_LEVEL", (int) o);
                break;
            case "mining_veins":
                specs.setInteger("MINING_VEINS", (int) o);
                break;
            case "mining_luck":
                specs.setInteger("MINING_LUCK", (int) o);
                break;
            case "mining_silktouch":
                specs.setBoolean("MINING_SILKTOUCH", (boolean) o);
                break;
            case "digging_speed":
                specs.setFloat("DIGGING_SPEED", (float) o);
                break;
            case "blocks_digged":
                stats.setInteger("BLOCKS_DIGGED", (int) o);
                break;
            case "blocks_mined":
                stats.setInteger("BLOCKS_MINED", (int) o);
                break;
            case "ore_mined":
                stats.setInteger("ORE_MINED", (int) o);
                break;
            case "stone_mined":
                stats.setInteger("STONE_MINED", (int) o);
                break;
            case "xp":
                props.setInteger("XP", (int) o);
                break;
            case "lvl":
                props.setInteger("LVL", (int) o);
                break;
            case "magnetic":
                specs.setBoolean("MAGNETIC", (boolean) o);
                break;
            case "soulbound":
                specs.setBoolean("SOULBOUND", (boolean) o);
                break;
        }
    }

    public Object getToolProperty(ItemStack s, String p) {
        initTagCompound(s);
        NBTTagCompound nbt = s.getTagCompound();
        NBTTagCompound props = (NBTTagCompound) nbt.getTag("TOOL_PROPERTIES");
        NBTTagCompound stats = (NBTTagCompound) props.getTag("STATISTICS");
        NBTTagCompound specs = (NBTTagCompound) props.getTag("SPECIFICATIONS");
        switch (p.toLowerCase()) {
            case "durability":
                return specs.getInteger("DURABILITY");
            case "mining_speed":
                return specs.getFloat("MINING_SPEED");
            case "mining_soft_speed":
                return specs.getFloat("MINING_SOFT_SPEED");
            case "mining_level":
                return specs.getInteger("MINING_LEVEL");
            case "mining_veins":
                return specs.getInteger("MINING_VEINS");
            case "mining_luck":
                return specs.getInteger("MINING_LUCK");
            case "mining_silktouch":
                return specs.getBoolean("MINING_SILKTOUCH");
            case "digging_speed":
                return specs.getFloat("DIGGING_SPEED");
            case "blocks_digged":
                return stats.getInteger("BLOCKS_DIGGED");
            case "blocks_mined":
                return stats.getInteger("BLOCKS_MINED");
            case "ore_mined":
                return stats.getInteger("ORE_MINED");
            case "stone_mined":
                return stats.getInteger("STONE_MINED");
            case "xp":
                return props.getInteger("XP");
            case "lvl":
                return props.getInteger("LVL");
            case "magnetic":
                return specs.getBoolean("MAGNETIC");
            case "soulbound":
                return specs.getBoolean("SOULBOUND");
            default:
                return null;
        }
    }

    public void initTagCompound(ItemStack s) {
        if (!s.hasTagCompound()) {
            s.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound nbt = s.getTagCompound();
        if (!nbt.hasKey("TOOL_PROPERTIES")) {
            nbt.setTag("TOOL_PROPERTIES", new NBTTagCompound());
        }

        NBTTagCompound prop = (NBTTagCompound) nbt.getTag("TOOL_PROPERTIES");
        if (!prop.hasKey("STATISTICS")) {
            prop.setTag("STATISTICS", new NBTTagCompound());
        }
        NBTTagCompound stats = (NBTTagCompound) prop.getTag("STATISTICS");
        initStatistics(stats);

        if (!prop.hasKey("SPECIFICATIONS")) {
            prop.setTag("SPECIFICATIONS", new NBTTagCompound());
        }
        NBTTagCompound specs = (NBTTagCompound) prop.getTag("SPECIFICATIONS");
        initSpecifications(specs);

        if (!prop.hasKey("XP")) {
            prop.setInteger("XP", 0);
        }
        if (!prop.hasKey("LVL")) {
            prop.setInteger("LVL", 0);
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        super.addInformation(stack,player,list,par4);
        boolean shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
        int lvl = (int) getToolProperty(stack, "lvl");
        list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".miningLevel", TextFormatting.BLUE+Helper.localize(ModConstants.Items.AdvPick.unlocalizedMiningLevelNames[(int) getToolProperty(stack, "mining_level")])));
        list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".lvl", lvl));
        list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".xp", getToolProperty(stack, "xp"), LevelUp.getXPRequiredForLvlUp(lvl)));
        if (!shift) {
            list.add(TextFormatting.DARK_GRAY + Helper.localize("tooltip." + ModConstants.Mod.MODID + ":shiftInfo"));
        } else {
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".miningSpeed", getToolProperty(stack, "mining_speed")));
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".diggingSpeed", getToolProperty(stack, "digging_speed")));
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".miningVeins", getToolProperty(stack, "mining_veins")));
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".softSpeed", getToolProperty(stack, "mining_soft_speed")));
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".luck", getToolProperty(stack, "mining_luck")));
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".repairMaterial", Helper.localize(ModConstants.Items.AdvPick.unlocalizedMiningRepairMaterialNames[(int) getToolProperty(stack, "mining_level")])));
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".durability", ((int) getToolProperty(stack, "durability") - stack.getItemDamage()), getToolProperty(stack, "durability")));
            if((boolean)getToolProperty(stack,"magnetic"))
                list.add(TextFormatting.YELLOW+Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".magnetic"));
            if(isSoulbound(stack,player))
                list.add(TextFormatting.YELLOW+Helper.localize("tooltip." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + ".soulbound"));
        }
    }

    public void initStatistics(NBTTagCompound c) {
        if (!c.hasKey("BLOCKS_MINED")) {
            c.setInteger("BLOCKS_MINED", 0);
        }
        if (!c.hasKey("STONE_MINED")) {
            c.setInteger("STONE_MINED", 0);
        }
        if (!c.hasKey("ORE_MINED")) {
            c.setInteger("ORE_MINED", 0);
        }
        if (!c.hasKey("BLOCKS_DIGGED")) {
            c.setInteger("BLOCKS_DIGGED", 0);
        }
    }

    public void initSpecifications(NBTTagCompound c) {
        if (!c.hasKey("DURABILITY")) {
            c.setInteger("DURABILITY", 30);
        }
        if (!c.hasKey("MINING_LEVEL")) {
            c.setInteger("MINING_LEVEL", 0);
        }
        if (!c.hasKey("MINING_SPEED")) {
            c.setFloat("MINING_SPEED", 2.0F);
        }
        if (!c.hasKey("MINING_SOFT_SPEED")) {
            c.setFloat("MINING_SOFT_SPEED", 0F);
        }
        if (!c.hasKey("MINING_VEINS")) {
            c.setInteger("MINING_VEINS", 0);
        }
        if (!c.hasKey("MINING_LUCK")) {
            c.setInteger("MINING_LUCK", 0);
        }
        if (!c.hasKey("MINING_LUCK")) {
            c.setInteger("MINING_LUCK", 0);
        }
        if (!c.hasKey("MINING_SILKTOUCH")) {
            c.setBoolean("MINING_SILKTOUCH", false);
        }
        if (!c.hasKey("DIGGING_SPEED")) {
            c.setFloat("DIGGING_SPEED", 1.0F);
        }
        if (!c.hasKey("MAGNETIC")) {
            c.setBoolean("MAGNETIC", false);
        }
        if (!c.hasKey("SOULBOUND")) {
            c.setBoolean("SOULBOUND", false);
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if (state.getBlock().getHarvestTool(state) == null)
            return false;

        if (!worldIn.isRemote||isBroken(stack)) {

            EntityPlayer e = (entityLiving != null && entityLiving instanceof EntityPlayer) ? ((EntityPlayer) entityLiving) : null;

            if (state.getBlock().getHarvestTool(state).equals("pickaxe")){
                setToolProperty(stack, "blocks_mined", (int) getToolProperty(stack, "blocks_mined") + 1);
                if (Helper.doesOreDictMatch(state, "stone", false)) {
                    setToolProperty(stack, "stone_mined", (int) getToolProperty(stack, "stone_mined") + 1);
                    LevelUp.gainXP(e, stack, 1);
                }
                if (Helper.doesOreDictMatch(state, "ore", true)) {
                    setToolProperty(stack, "ore_mined", (int) getToolProperty(stack, "ore_mined") + 1);
                    LevelUp.gainXP(e, stack, ModConstants.Items.AdvPick.miningLevelOreExperience[state.getBlock().getHarvestLevel(state)]);
                }else{
                    LevelUp.gainXP(e, stack, 1);
                }
            }

            if (state.getBlock().getHarvestTool(state).equals("shovel")) {
                setToolProperty(stack, "blocks_digged", (int) getToolProperty(stack, "blocks_digged") + 1);
                if(worldIn.rand.nextBoolean())
                    LevelUp.gainXP(e, stack, 1);
            }
        }

        stack.setItemDamage(stack.getItemDamage() + 1);
        if(isBroken(stack))
            entityLiving.renderBrokenItemStack(stack);

        return false;
    }

    public boolean isBroken(ItemStack item) {
        return item.getItemDamage() >= (int) getToolProperty(item, "durability");
    }

    @Override
    public boolean showDurabilityBar(ItemStack item) {
        return item.getItemDamage() != 0;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack item) {
        return ((double) item.getItemDamage() / (int) getToolProperty(item, "durability"));
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        return (int)getToolProperty(stack, "durability");
    }

    public String getRepairMaterial(ItemStack s) {
        int lvl = (int) getToolProperty(s, "mining_level");
        String[] ores = new String[]{"plankWood", "cobblestone", "ingotIron", "gemDiamond"};
        return ores[lvl];
    }

    @Override
    public boolean isSoulbound(ItemStack i, EntityPlayer p) {
        return !isBroken(i) && (boolean)ModItems.itemAdventurersPickaxe.getToolProperty(i, "soulbound");
    }

    @Override
    public boolean isDamaged(ItemStack stack)
    {
        return false;
    }

    @Override
    public void onPlayerInventoryTick(ItemStack item, SlotNotation slot, EntityPlayer player) {
        if (player.getHeldItemMainhand() == item && !isBroken(item) && (boolean)getToolProperty(item,"magnetic")) {
            OreMagnet.attractOres(player);
        }
    }

    private void initSubItems() {
        subitems = new ArrayList<>();
        ItemStack s = new ItemStack(this);
        subitems.add(s);
        s = s.copy();
        setToolProperty(s, "lvl", 5);
        setToolProperty(s, "mining_level", 1);
        setToolProperty(s, "durability", (int) getToolProperty(s, "durability") + 45);
        setToolProperty(s, "mining_speed", ToolMaterial.STONE.getEfficiencyOnProperMaterial());
        subitems.add(s);
        s = s.copy();
        setToolProperty(s, "lvl", 15);
        setToolProperty(s, "mining_level", 2);
        setToolProperty(s, "magnetic", true);
        setToolProperty(s, "durability", (int) getToolProperty(s, "durability") + 360);
        setToolProperty(s, "mining_speed", ToolMaterial.IRON.getEfficiencyOnProperMaterial());
        subitems.add(s);
        s = new ItemStack(this);
        setToolProperty(s, "lvl", 25);
        setToolProperty(s, "mining_level", 3);
        setToolProperty(s, "magnetic", true);
        setToolProperty(s, "mining_luck", 1);
        setToolProperty(s, "durability", (int) getToolProperty(s, "durability") + 975);
        setToolProperty(s, "mining_speed", ToolMaterial.DIAMOND.getEfficiencyOnProperMaterial());
        subitems.add(s);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, NonNullList<ItemStack> l) {
        l.addAll(subitems);
    }
}
