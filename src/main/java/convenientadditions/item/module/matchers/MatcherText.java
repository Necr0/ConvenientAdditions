package convenientadditions.item.module.matchers;

import convenientadditions.api.IMatcher;

/**
 * Created by Necro on 5/18/2017.
 */
public class MatcherText implements IMatcher {

    String text;

    public MatcherText(String text) {
        this.text = text;
    }

    @Override
    public boolean matches(IMatcher matcher) {
        if (matcher instanceof MatcherText)
            return ((MatcherText) matcher).text.equals(text);
        return false;
    }

}
