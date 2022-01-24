package ldts.model.ElementCreator;

import ldts.model.Element;
import ldts.model.ElementCreator.ElementCreator;
import ldts.model.Laser;

public class LaserCreator extends ElementCreator {
    @Override
    public Element createElement() {
        return new Laser();
    }
}
