package ldts.control;


import ldts.model.Coin;
import ldts.model.Element;
import ldts.model.Laser;
import ldts.model.Rocket;

public class ElementFactory {
    private final int delta;

    public ElementFactory(int delta) {
        this.delta = delta;
    }

    public Element generateElements(int i) {
        if (i % delta == 0) {
            int random = (int) (Math.random() * 6) + 1;
            if (random <= 4) {
                return new Laser();
            }
            else if (random <= 5) {
                return new Coin();
            }
            else {
                return new Rocket();
            }
        }
        return null;
    }

}
