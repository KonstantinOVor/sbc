package org.example;

import java.util.Stack;

public class SnapshotsRepositoryImpl implements SnapshotsRepository {
    private Stack<char[]> snapshots = new Stack<>();

    @Override
    public void takeSnapshot(char[] chars, int length) {
        char[] snapshot = new char[length];
        System.arraycopy(chars, 0, snapshot, 0, length);
        snapshots.push(snapshot);
    }

    @Override
    public char[] getSnapshot(char[] chars, int length) {
        if (!snapshots.isEmpty()) {
            return snapshots.pop();
        } else {
            throw new IllegalStateException("No snapshots available for undo.");
        }
    }
}
