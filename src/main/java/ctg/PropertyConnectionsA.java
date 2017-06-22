package ctg;

import com.google.common.base.Optional;
import net.minecraft.block.properties.IProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Necro on 6/19/2017.
 */
public class PropertyConnectionsA implements IProperty<String> {
    private String name;
    private static final List<String> variants=new ArrayList<>();

    public PropertyConnectionsA(String name)
    {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<String> getAllowedValues() {
        return variants;
    }

    @Override
    public Class<String> getValueClass() {
        return String.class;
    }

    @Override
    public Optional<String> parseValue(String value) {
        return getAllowedValues().contains(value)?Optional.of(value):Optional.absent();
    }

    @Override
    public String getName(String value) {
        return value;
    }

    public static String fromBooleans(boolean up,boolean down,boolean north,boolean east,boolean south,boolean west){
        String variant=(up?"u":"")+(down?"d":"")+(north?"n":"")+(east?"e":"")+(south?"s":"")+(west?"w":"");
        return variant.equals("")?"normal":variant;
    }

    static{
        for(int i=0;i<64;i++){
            String variant=(((i&1)>0)?"u":"")+(((i&2)>0)?"d":"")+(((i&4)>0)?"n":"")+(((i&8)>0)?"e":"")+(((i&16)>0)?"s":"")+(((i&32)>0)?"w":"");
            variant=variant.equals("")?"normal":variant;
            variants.add(variant);
        }
    }
}
