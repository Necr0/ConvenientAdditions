package convenientadditions.item.tools.adventurersPickaxe;

import convenientadditions.api.util.Helper;
import convenientadditions.ModConstants;
import convenientadditions.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelUp {

    public static int getXPRequiredForLvlUp(int current) {
        return (int) (13 * Math.pow(1.13, current));
    }

    public static void gainXP(EntityPlayer p, ItemStack s, int amount) {
        ModItems.itemAdventurersPickaxe.setToolProperty(s, "xp", (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "xp") + amount);
        int lvl = (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "lvl");
        int xp = (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "xp");
        if (xp >= getXPRequiredForLvlUp(lvl)) {
            lvlUp(p, s);
        }
    }

    public static void lvlUp(EntityPlayer p, ItemStack s) {
        ModItems.itemAdventurersPickaxe.setToolProperty(s, "lvl", (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "lvl") + 1);
        ModItems.itemAdventurersPickaxe.setToolProperty(s, "xp", 0);
        int lvl = (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "lvl");

        ModItems.itemAdventurersPickaxe.setToolProperty(s, "durability", (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "durability") + (lvl * 2));
        s.setItemDamage(s.getItemDamage() + (int) (lvl * 1.8));

        applyRandomUpgrade(s);

        boolean msg=p != null && !p.world.isRemote;

        switch (lvl) {
            case 5:
                ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_level", 1);
                if (msg) {
                    p.sendMessage(
                            new TextComponentString(
                                    Helper.localize("message." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + "LevelUpMiningLevel",
                                            Helper.localize(ModConstants.Items.AdvPick.unlocalizedMiningLevelNames[1])
                                    )
                            )
                    );
                }
                break;
            case 10:
                ModItems.itemAdventurersPickaxe.setToolProperty(s, "magnetic", true);
                if (msg) {
                    p.sendMessage(
                            new TextComponentString(
                                    Helper.localize("message." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + "LevelUpMagnetic")
                            )
                    );
                }
                break;
            case 15:
                ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_level", 2);
                if (msg) {
                    p.sendMessage(
                            new TextComponentString(
                                    Helper.localize("message." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + "LevelUpMiningLevel",
                                            Helper.localize(ModConstants.Items.AdvPick.unlocalizedMiningLevelNames[2])
                                    )
                            )
                    );
                }
                break;
            case 20:
                ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_luck", 1);
                if (msg) {
                    p.sendMessage(
                            new TextComponentString(
                                    Helper.localize("message." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + "LevelUpLuck",1)
                            )
                    );
                }
                break;
            case 25:
                ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_level", 3);
                if (msg) {
                    p.sendMessage(
                            new TextComponentString(
                                    Helper.localize("message." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + "LevelUpMiningLevel",
                                            Helper.localize(ModConstants.Items.AdvPick.unlocalizedMiningLevelNames[3])
                                    )
                            )
                    );
                }
                break;
            case 30:
                ModItems.itemAdventurersPickaxe.setToolProperty(s, "soulbound", true);
                if (msg) {
                    p.sendMessage(
                            new TextComponentString(
                                    Helper.localize("message." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + "LevelUpSoulbound")
                            )
                    );
                }
                break;
            case 35:
                ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_luck", 2);
                if (msg) {
                    p.sendMessage(
                            new TextComponentString(
                                    Helper.localize("message." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + "LevelUpLuck",2)
                            )
                    );
                }
                break;
            case 45:
                ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_luck", 3);
                if (msg) {
                    p.sendMessage(
                            new TextComponentString(
                                    Helper.localize("message." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + "LevelUpLuck",3)
                            )
                    );
                }
                break;
            case 55:
                ModItems.itemAdventurersPickaxe.setToolProperty(s, "mining_luck", 4);
                if (msg) {
                    p.sendMessage(
                            new TextComponentString(
                                    Helper.localize("message." + ModConstants.Mod.MODID + ":" + ModConstants.ItemNames.adventurersPickaxe + "LevelUpLuck",4)
                            )
                    );
                }
                break;
        }

        NBTTagCompound nbt = s.getTagCompound();
        NBTTagCompound props = (NBTTagCompound) nbt.getTag("TOOL_PROPERTIES");
        props.setTag("STATISTICS", new NBTTagCompound());
        p.getEntityWorld().playSound(null, p.posX, p.posY, p.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    public static void applyRandomUpgrade(ItemStack s) {
        List<Tuple<String, Integer>> u = new ArrayList<>();
        int sum = 0;
        for (String p : getUpgradableStats(s)) {
            switch (p) {
                case "mining_speed":
                    u.add(new Tuple<>(p, (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "blocks_mined") + 20));
                    sum += (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "blocks_mined") + 20;
                    break;
                case "digging_speed":
                    u.add(new Tuple<>(p, (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "blocks_digged") + 20));
                    sum += (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "blocks_digged") + 20;
                    break;
                case "mining_veins":
                    u.add(new Tuple<>(p, (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "ore_mined") + 13));
                    sum += (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "ore_mined") + 13;
                    break;
                case "mining_soft_speed":
                    u.add(new Tuple<>(p, (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "stone_mined") + 20));
                    sum += (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "stone_mined") + 20;
                    break;
            }
        }
        int num = new Random().nextInt(sum) + 1;
        int track = 0;
        for (Tuple<String, Integer> y : u) {
            track += y.getSecond();
            if (num <= track) {
                applyUpgrade(s, y.getFirst());
                return;
            }
        }
    }

    public static void applyUpgrade(ItemStack s, String type) {
        switch (type) {
            case "mining_speed":
                ModItems.itemAdventurersPickaxe.setToolProperty(s, type, (float) ModItems.itemAdventurersPickaxe.getToolProperty(s, type) + 0.6f);
                break;
            case "digging_speed":
                ModItems.itemAdventurersPickaxe.setToolProperty(s, type, (float) ModItems.itemAdventurersPickaxe.getToolProperty(s, type) + 0.5f);
                break;
            case "mining_veins":
                ModItems.itemAdventurersPickaxe.setToolProperty(s, type, (int) ModItems.itemAdventurersPickaxe.getToolProperty(s, type) + 12);
                break;
            case "mining_soft_speed":
                ModItems.itemAdventurersPickaxe.setToolProperty(s, type, (float) ModItems.itemAdventurersPickaxe.getToolProperty(s, type) + 0.75f);
                break;
        }
    }

    public static List<String> getUpgradableStats(ItemStack s) {
        ArrayList<String> ret = new ArrayList<>();
        if ((float) ModItems.itemAdventurersPickaxe.getToolProperty(s, "mining_speed") < 33f)
            ret.add("mining_speed");
        if ((float) ModItems.itemAdventurersPickaxe.getToolProperty(s, "digging_speed") < 7.5f)
            ret.add("digging_speed");
        if ((int) ModItems.itemAdventurersPickaxe.getToolProperty(s, "mining_veins") < 48)
            ret.add("mining_veins");
        if ((float) ModItems.itemAdventurersPickaxe.getToolProperty(s, "mining_soft_speed") < 7.5f)
            ret.add("mining_soft_speed");
        return ret;
    }

}
