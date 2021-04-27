public class KeyValuePair {
    private int key;
    private int value;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public KeyValuePair(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
