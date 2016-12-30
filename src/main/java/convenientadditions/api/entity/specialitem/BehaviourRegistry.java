package convenientadditions.api.entity.specialitem;

import convenientadditions.api.entity.specialitem.behaviours.*;

import java.util.HashMap;

public class BehaviourRegistry {
    public static final HashMap<Long, IEntitySpecialItemBehaviour> REGISTRY = new HashMap<>();
    public static HashMap<String, Long> API_DISCRIMINATORS = new HashMap<>();

    public static long addBehaviour(IEntitySpecialItemBehaviour behaviour) {
        long ret = getUnoccupiedDiscrimiator();
        REGISTRY.put(ret, behaviour);
        return ret;
    }

    public static long addBehaviour(long discriminator, IEntitySpecialItemBehaviour behaviour) {
        REGISTRY.put(discriminator, behaviour);
        return discriminator;
    }

    public static IEntitySpecialItemBehaviour getBehaviour(long discriminator) {
        return REGISTRY.get(discriminator);
    }

    public static Long getDiscriminator(IEntitySpecialItemBehaviour behaviour) {
        for (Long n : REGISTRY.keySet()) {
            if (REGISTRY.get(n) == behaviour)
                return n;
        }
        return null;
    }

    public static long getUnoccupiedDiscrimiator() {
        long r = 0;
        for (long n : REGISTRY.keySet()) {
            if (n == r || n > r) {
                r = n + 1;
            }
        }
        return r;
    }

    static {
        API_DISCRIMINATORS.put("autoCrops", addBehaviour(new BehaviourAutoCrops()));
        API_DISCRIMINATORS.put("autoFeed", addBehaviour(new BehaviourAutoFeed()));
        API_DISCRIMINATORS.put("floaty", addBehaviour(new BehaviourFloaty()));
        API_DISCRIMINATORS.put("heavy", addBehaviour(new BehaviourHeavy()));
        API_DISCRIMINATORS.put("immunityExplosion", addBehaviour(new BehaviourImmunityExplosion()));
        API_DISCRIMINATORS.put("immunityFire", addBehaviour(new BehaviourImmunityFire()));
        API_DISCRIMINATORS.put("sensitivitySunlight", addBehaviour(new BehaviourSensitivitySunlight()));
        API_DISCRIMINATORS.put("sensitivityWater", addBehaviour(new BehaviourSensitivityWater()));
    }
}
