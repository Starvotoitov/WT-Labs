package by.bsuir.machine.dao;

import java.io.IOException;

public interface DAO<T> {
    public void saveToFile(T dataToSave, String path) throws IOException;
    public T loadToFile(String path) throws ClassNotFoundException, IOException;
}
