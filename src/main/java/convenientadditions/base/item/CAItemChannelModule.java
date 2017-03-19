package convenientadditions.base.item;

import convenientadditions.api.item.IMatcherProvider;

/**
 * Created by Necro on 3/18/2017.
 */
public abstract class CAItemChannelModule extends CAItem implements IMatcherProvider {
    public CAItemChannelModule(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setCategory(EnumItemCategory.MODULE);
    }
}
