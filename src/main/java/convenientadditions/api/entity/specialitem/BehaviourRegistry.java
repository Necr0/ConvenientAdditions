package convenientadditions.api.entity.specialitem;

import convenientadditions.api.entity.specialitem.behaviours.*;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class BehaviourRegistry {
    public static final HashMap<String, IEntitySpecialItemBehaviour> REGISTRY = new HashMap<>();

    public static final String BEHAVIOUR_AUTO_CROPS=addBehaviour("autoCrops",new BehaviourAutoCrops());
    public static final String BEHAVIOUR_FLOATY=addBehaviour("floaty", new BehaviourFloaty());
    public static final String BEHAVIOUR_HEAVY=addBehaviour("heavy", new BehaviourHeavy());
    public static final String BEHAVIOUR_IMMUNITY_EXPLOSION=addBehaviour("immunityExplosion", new BehaviourImmunityExplosion());
    public static final String BEHAVIOUR_IMMUNITY_FIRE=addBehaviour("immunityFire", new BehaviourImmunityFire());
    public static final String BEHAVIOUR_SENSITIVITY_SUNLIGHT=addBehaviour("sensitivitySunlight", new BehaviourSensitivitySunlight());
    public static final String BEHAVIOUR_SENSITIVITY_WATER=addBehaviour("sensitivityWater", new BehaviourSensitivityWater());

    public static String addBehaviour(ResourceLocation location, IEntitySpecialItemBehaviour behaviour) {
        REGISTRY.put(location.toString(), behaviour);
        return location.toString();
    }

    public static String addBehaviour(String discriminator, IEntitySpecialItemBehaviour behaviour) {
        REGISTRY.put(discriminator, behaviour);
        return discriminator;
    }

    public static IEntitySpecialItemBehaviour getBehaviour(String discriminator) {
        return REGISTRY.get(discriminator);
    }

    public static IEntitySpecialItemBehaviour getBehaviour(ResourceLocation location) {
        return REGISTRY.get(location.toString());
    }

    public static String getDiscriminator(IEntitySpecialItemBehaviour behaviour) {
        for (String n : REGISTRY.keySet()) {
            if (REGISTRY.get(n).equals(behaviour))
                return n;
        }
        return null;
    }
}
