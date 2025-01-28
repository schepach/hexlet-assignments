package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String str;

    public ReversedSequence(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        this.str = stringBuilder.reverse().toString();
    }

    @Override
    public int length() {
        return this.str.length();
    }

    @Override
    public char charAt(int index) {
        return str.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.str.substring(start, end);
    }

    @Override
    public String toString() {
        return this.str;
    }
}
// END
