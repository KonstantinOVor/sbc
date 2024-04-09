package org.example;

public interface SnapshotsRepository {

    void takeSnapshot(char[] chars,  int length);

    char[] getSnapshot(char[] chars,  int length);

}
