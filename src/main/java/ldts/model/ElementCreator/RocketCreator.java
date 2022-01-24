package ldts.model.ElementCreator;

import ldts.model.Element;
import ldts.model.ElementCreator.ElementCreator;
import ldts.model.Rocket;

public class RocketCreator extends ElementCreator {
    @Override
    public Element createElement() {
        return new Rocket();
    }
}
