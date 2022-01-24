package ldts.model.ElementCreator;

import ldts.model.Coin;
import ldts.model.Element;

public class CoinCreator extends ElementCreator {
    @Override
    public Element createElement() {
        return new Coin();
    }
}
