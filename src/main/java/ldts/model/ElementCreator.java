package ldts.model;


public class ElementCreator {

    public Element generateElements(int i) {
        if (i % 5 == 0) {
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
