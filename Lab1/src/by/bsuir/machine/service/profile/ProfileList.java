package by.bsuir.machine.service.profile;

import by.bsuir.machine.dao.DAO;
import by.bsuir.machine.dao.DataAccessManager;
import by.bsuir.machine.service.coffee.Espresso;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Класс, объекты которого представляют список профилей кофемашины.
 * Элементами списка являются объекты класса Profile.
 * Предоставляет методы для добавления, удаления элементов, сортировки, сохранения и загрузки списка профилей.
 * Также позволяет выбирать активный профиль и редактировать его содержимое.
 */
public class ProfileList {
    private Profile currentProfile;
    private ArrayList<Profile> profileList;
    private DAO<ArrayList<Profile>> profileFileManager;

    /**
     * Создает объект, устанавливает значение активного профиля равным null, создает объект для сохранения и загрузки
     * данных из файла.
     */
    public ProfileList() {
        currentProfile = null;
        profileFileManager = new DataAccessManager<ArrayList<Profile>>();
    }

    /**
     * Добавляет новый профиль в списков профилей.
     * @param newProfile Профиль, добавляемый в список.
     */
    public void addProfile(Profile newProfile) {
        if (newProfile != null) {
            profileList.add(newProfile);
        }
    }

    /**
     * Проверяет получемый индекс и удаляет профиль, соответствующий индексу, из списка профилей.
     * @param index Индекс удаляемого профиля.
     */
    public void deleteProfile(int index) {
        if ((index >= 0) && (index < profileList.size())) {
            if (profileList.get(index).equals(currentProfile)) {
                currentProfile = null;
            }
            profileList.remove(index);
        }
    }

    /**
     * Выбирает новый активный профиль.
     * @param index Индекс пового профиля. Если индекс некорректный - активному профилю будет присвоено значение null.
     */
    public void chooseProfile(int index) {
        if ((index >= 0) && (index < profileList.size())) {
            currentProfile = profileList.get(index);
        } else {
            currentProfile = null;
        }
    }

    /**
     * Изменяет настройки кофе активного профиля.
     * @param newSettings Значение новых настроек для кофе.
     */
    public void updateSettings(Espresso newSettings) {
        if (currentProfile != null) {
            currentProfile.updateSettings(newSettings);
        }
    }

    /**
     * Позволяет получить имя активного профиля.
     * @return Имя активного профиля. Если активный профиль не выбран - возвращает значение null.
     */
    public String getProfileName() {
        if (currentProfile != null) {
            return currentProfile.getProfileName();
        } else {
            return null;
        }
    }

    /**
     * Возвращает профиль из списка профилей.
     * @param index Индекс профиля в списке профилей.
     * @return Если индекс корректный, то возвращает профиль с выбранным индексов. В противном случае
     *         возвращает null.
     */
    public Profile getProfile(int index) {
        if ((index >= 0) && (index < profileList.size())) {
            return profileList.get(index);
        } else {
            return null;
        }
    }

    /**
     * Возвращает настройки кофе, сохраненные для активного профиля, для выбранного типа кофе
     * @param coffeeClass Тип кофе, для которого получаются настройки.
     * @return Если активный профиль выбран и в нем сохранены настройки для выбранного типа кофе - возвращает настройки,
     *         иначе возвращает null.
     */
    public Espresso getCoffeeSettings(Class<? extends Espresso> coffeeClass) {
        if (currentProfile != null) {
            return currentProfile.getSetting(coffeeClass);
        } else {
            return null;
        }
    }

    /**
     * Возвращает количество профилей в списке профилей.
     * @return Количество профилей в списке профилей.
     */
    public int getProfileCount() {
        return profileList.size();
    }

    /**
     * Сортирует список профилей в соответствиие с правилами, задаваемыми переданным компаратором.
     * @param comparator Компаратор, в соответствии с которым выполняется сортировка.
     */
    public void sortProfiles(Comparator<Profile> comparator) {
        Collections.sort(profileList, comparator);
    }

    /**
     * Сохраняет списков профилей в файл в XML-формате.
     * @param path Путь к файлу, в который будет сохранен список. Файл будет создан или, если он уже существует,
     *             будет перезаписан.
     * @throws IOException Если возникла ошибка ввода/вывода.
     */
    public void save(String path) throws IOException {
            profileFileManager.saveToFile(profileList, path);
    }

    /**
     * Загружает списков профилей из файла, сохраненного в XML-формате.
     * @param path Путь к файлу, из которого загружается список.
     */
    public void load(String path) {
        try {
            profileList = profileFileManager.loadToFile(path);
        } catch (IOException | ClassNotFoundException ex) {
            profileList = new ArrayList<Profile>();
        }
    }
}