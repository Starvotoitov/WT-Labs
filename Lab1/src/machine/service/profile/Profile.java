package machine.service.profile;

import machine.service.coffee.Espresso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Profile implements Serializable {
    private String profileName;
    private Date creationDate;
    private ArrayList<Espresso> basicCoffeeSettings;

    public Profile() {
        basicCoffeeSettings = new ArrayList<Espresso>();
        creationDate = new Date();
        profileName = "No name selected";
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

    public Espresso getSetting(Class<? extends Espresso> coffeeClass) {
        int index = findSettings(coffeeClass);
        return index != -1 ? basicCoffeeSettings.get(index) : null;
    }

    public void setProfileName(String newProfileName) {
        if (newProfileName != null) {
            this.profileName = newProfileName;
        }
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date newDate) {
        if (newDate != null) {
            creationDate = newDate;
        }
    }

    public String getProfileName() {
        return profileName;
    }
}
