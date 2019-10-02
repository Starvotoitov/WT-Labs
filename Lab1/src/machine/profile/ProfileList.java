package machine.profile;

import machine.coffee.Espresso;
import java.util.ArrayList;

public class ProfileList {
    private int currentProfileIndex;
    private ArrayList<Profile> profileList;

    public void addProfile(Profile newProfile) {
        if (newProfile != null) {
            profileList.add(newProfile);
        }
    }

    public void deleteProfile(int index) {
        if ((index >= 0) && (index < profileList.size())) {
            profileList.remove(index);
        }
    }

  /*  private int findProfile(String profileName) {
        for (int i = 0; i < profileList.size(); i++) {
            if (profileList.get(i).getProfileName().equals(profileName)) {
                return i;
            }
        }
        return -1;
    } */

    /* public void chooseProfile(String profileName) {
        currentProfileIndex = findProfile(profileName);
    } */

    public void chooseProfile(int index) {
        if ((index >= 0) && (index < profileList.size())) {
            currentProfileIndex = index;
        } else {
            currentProfileIndex = -1;
        }
    }

    public void updateProfileName(String newName) {
        profileList.get(currentProfileIndex).setProfileName(newName);
    }

    public void updateSettings(Espresso newSettings) {
        profileList.get(currentProfileIndex).updateSettings(newSettings);
    }

    public String getProfileName() {
        return profileList.get(currentProfileIndex).getProfileName();
    }

    public Espresso getCoffeeSettings(Class<? extends Espresso> coffeeClass) {
        return profileList.get(currentProfileIndex).getSetting(coffeeClass);
    }
}