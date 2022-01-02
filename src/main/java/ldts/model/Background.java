package ldts.model;

public class Background {
    private final String wallColor;
    private final String groundColor;
    private final int lowerLimit;

    public Background(String wallColor, String groundColor, int lowerLimit) {
        this.wallColor = wallColor;
        this.groundColor = groundColor;
        this.lowerLimit = lowerLimit;
    }

    public String getWallColor() {
        return wallColor;
    }

    public String getGroundColor() {
        return groundColor;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }
}
