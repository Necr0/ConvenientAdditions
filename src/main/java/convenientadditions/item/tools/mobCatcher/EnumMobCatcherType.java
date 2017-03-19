package convenientadditions.item.tools.mobCatcher;

/**
 * Created by Necro on 2/11/2017.
 */
public enum EnumMobCatcherType {
    DEFAULT(1f,false,false),
    SUPER(1.5f, false,false),
    HYPER(2.15f,true,false),
    MEGA(3f,true,false),
    MASTER(-1f,true,true);

    public float captureStrength;
    public boolean captureHostile;
    public boolean captureBoss;

    EnumMobCatcherType(float captureStrength,boolean captureHostile, boolean captureBoss){
        this.captureStrength=captureStrength;
        this.captureHostile=captureHostile;
        this.captureBoss=captureBoss;
    }
}
