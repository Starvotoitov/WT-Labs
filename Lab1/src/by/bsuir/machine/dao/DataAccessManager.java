package by.bsuir.machine.dao;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Предоставляет методы для сохранения и загрузки данных из файла в XML-формате.
 * @param <T> Тип сохраняемых и загружаемый из файла данных.
 */
public class DataAccessManager<T> implements DAO<T> {
    /**
     * Сохраняет данные в файл в XML-формате.
     * @param dataToSave Данные, сохраняемые в файл.
     * @param path Путь к файлу.
     * @throws IOException Если возникла ошибка ввода/вывода.
     */
    public void saveToFile(T dataToSave, String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
        XMLEncoder out = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
        out.writeObject(dataToSave);
        out.flush();
        out.close();
    }

    /**
     * Загружает данные из файла.
     * @param path Путь к файлу.
     * @return Загруженные данные.
     * @throws IOException Если возникла ошибка ввода/вывода.
     */
    public T loadToFile(String path) throws IOException {
        XMLDecoder in = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
        T data = (T)in.readObject();
        in.close();
        return data;
    }
}
