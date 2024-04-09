package org.example;

public class MyStringBuilder {
    private char[] chars;
    private int length;
    private SnapshotsRepository snapshotsRepository = new SnapshotsRepositoryImpl();

    public MyStringBuilder() {
        this(16);
    }

    public MyStringBuilder(int capacity) {
        chars = new char[capacity];
    }

    public MyStringBuilder(String str) {
        this(str.length());
        append(str);
    }

    public int length() {
        return length;
    }
    public MyStringBuilder undo() {
            chars = snapshotsRepository.getSnapshot(chars, length);
            length = chars.length;
        return this;
    }

    private void takeSnapshot() {
        snapshotsRepository.takeSnapshot(chars, length);
    }

    public MyStringBuilder append(String str) {
        takeSnapshot();
        if (str == null) {
            return append("");
        }
        int len = str.length();
        ensureCapacity(length + len);
        for (int i = 0; i < len; i++) {
            chars[length++] = str.charAt(i);
        }
        return this;
    }

    public MyStringBuilder insert(int index, String str) {
        takeSnapshot();
        if (str == null) {
            return insert(index, "");
        }
        int len = str.length();
        ensureCapacity(length + len);
        System.arraycopy(chars, index, chars, index + len, length - index);
        for (int i = 0; i < len; i++) {
            chars[index++] = str.charAt(i);
        }
        length += len;
        return this;
    }

    public MyStringBuilder delete(int start, int end) {
        takeSnapshot();
        if (start < 0 || end > length || start > end) {
            throw new StringIndexOutOfBoundsException();
        }
        System.arraycopy(chars, end, chars, start, length - end);
        length -= (end - start);
        return this;
    }

    public MyStringBuilder reverse() {
        takeSnapshot();
        for (int i = 0, j = length - 1; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return this;
    }

    public String toString() {
        return new String(chars, 0, length);
    }

    private void ensureCapacity(int capacity) {
        if (capacity > chars.length) {
            int newCapacity = (chars.length * 3) / 2 + 1;
            if (newCapacity < capacity) {
                newCapacity = capacity;
            }
            char[] newChars = new char[newCapacity];
            System.arraycopy(chars, 0, newChars, 0, length);
            chars = newChars;
        }
    }
}
