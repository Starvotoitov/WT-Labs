package by.bsuir.machine.beans.profile;

import by.bsuir.machine.beans.coffee.Espresso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Хранит информацию о профиле, включающую в себя имя профиля, дату создания, а также информацию о
 * сохраненных в профиле настройках кофе.
 */
public class Profile implements Serializable {
    private String profileName;
    private Date creationDate;
    private ArrayList<Espresso> basicCoffeeSettings;
    private String id;

    /**
     * Создает объект и устанавливает значения времени создания. В качестве имени профиля устанавливается значение
     * "Имя не выбранно". Создается пустой список настроек для кофе.
     */
    public Profile() {
        basicCoffeeSettings = new ArrayList<Espresso>();
        creationDate = new Date();
        profileName = "Имя не выбранно";
    }

    public void setId(String newValue) {
        id = newValue;
    }

    public String getId() {
        return id;
    }

    private int findSettings(Class<? extends Espresso> coffeeClass) {
        for (int i = 0; i < basicCoffeeSettings.size(); i++) {
            if (coffeeClass == basicCoffeeSettings.get(i).getClass()) {
                return i;
            }
        }
        return -1;
    }

    public void updateSettings(Espresso newSettings) {
        Class<? extends Espresso> coffeeClass = newSettings.getClass();
        int index = findSettings(coffeeClass);
        if (index != -1) {
            basicCoffeeSettings.set(index, newSettings);
        } else {
            basicCoffeeSettings.add(newSettings);
        }
    }

    /**
     * Позволяет получить значение сохраненных в профиле настроек для выбранного типа кофе.
     * @param coffeeClass Тип кофе, для которого ведется поиск настроек.
     * @return Если настройки были найдены - значение настроек, в противном случае - null.
     */
    public Espresso getSetting(Class<? extends Espresso> coffeeClass) {
        int index = findSettings(coffeeClass);
        return index != -1 ? basicCoffeeSettings.get(index) : null;
    }

    /**
     * Устанавливает новое значение имени профиля.
     * @param newProfileName Новое имя профиля.
     */
    public void setProfileName(String newProfileName) {
        if (newProfileName != null) {
            this.profileName = newProfileName;
        }
    }

    /**
     * Позволяет получить время создания профиля.
     * @return Время создания профиля.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Устанавливает новое значения для времени создания.
     * @param newDate Новое время.
     */
    public void setCreationDate(Date newDate) {
        if (newDate != null) {
            creationDate = newDate;
        }
    }

    /**
     * Позволяет получить имя профиля.
     * @return Имя профиля.
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * Позволяет получить список сохраненных настроек кофе.
     * @return Список сохраненных настроек.
     */
    public ArrayList<Espresso> getBasicCoffeeSettings() {
        return basicCoffeeSettings;
    }

    /**
     * Устанавливает новое значние для списка настроек.
     * @param newSet Новое значение для списка настроек.
     */
    public void setBasicCoffeeSettings(ArrayList<Espresso> newSet) {
        basicCoffeeSettings = newSet;
    }

    /**
     * Сравнивает профиль с объектов.
     * @param o Объект для сравнения.
     * @return true - если равны, иначе false.
     */
    @Override
    public boolean equals(Object o) {
        if (null == o) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Profile profile = (Profile) o;
        return profileName.equals(profile.profileName) &&
                creationDate.equals(profile.creationDate) &&
                basicCoffeeSettings.equals(profile.basicCoffeeSettings);
    }

    /**
     * Вычисляет значение хэшкода профиля.
     * @return Значение хэшкод профиля.
     */
    @Override
    public int hashCode() {
        return Objects.hash(profileName, creationDate, basicCoffeeSettings);
    }
}
