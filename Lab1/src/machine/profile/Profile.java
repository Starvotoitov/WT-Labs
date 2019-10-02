package machine.profile;

import machine.coffee.Espresso;
import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {
    private String profileName;
    private ArrayList<Espresso> basicCoffeeSettings;

    public Profile() {
        basicCoffeeSettings = new ArrayList<Espresso>();
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

    public String getProfileName() {
        return profileName;
    }
}
