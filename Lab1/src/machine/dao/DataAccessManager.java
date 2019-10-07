package machine.dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class DataAccessManager<T> {
    public void saveToFile(T dataToSave, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        XMLEncoder out = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
        out.writeObject(dataToSave);
        out.flush();
        out.close();
    }

    public T loadToFile(String path) throws ClassNotFoundException, IOException {
        XMLDecoder in = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
        T data = (T) in.readObject();
        in.close();
        return data;
    }
}
