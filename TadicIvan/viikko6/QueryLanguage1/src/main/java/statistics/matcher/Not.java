package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {

    private Matcher[] matchers;

    public Not(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        And and = new And(matchers);
        if (and.matches(p)){
            return false;
        }
        return true;
    }
}
