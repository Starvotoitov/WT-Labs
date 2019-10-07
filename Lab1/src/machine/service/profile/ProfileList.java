package machine.service.profile;

import machine.dao.DataAccessManager;
import machine.service.coffee.Espresso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProfileList {
    private int currentProfileIndex;
    private ArrayList<Profile> profileList;
    private DataAccessManager<ArrayList<Profile>> profileFileManager;

    public ProfileList() {
    //    profileList = new ArrayList<Profile>();
        currentProfileIndex = -1;
        profileFileManager = new DataAccessManager<ArrayList<Profile>>();
    //    load("");
    }

    public void addProfile(Profile newProfile) {
        if (newProfile != null) {
            profileList.add(newProfile);
        }
    }

    public void deleteProfile(int index) {
        if ((index >= 0) && (index < profileList.size())) {
            profileList.remove(index);
            if (index == currentProfileIndex) {
                currentProfileIndex = -1;
            }
        }
    }

    public void chooseProfile(int index) {
        if ((index >= 0) && (index < profileList.size())) {
            currentProfileIndex = index;
        } else {
            currentProfileIndex = -1;
        }
    }

    public void updateProfileName(String newName) {
        if (currentProfileIndex != -1) {
            profileList.get(currentProfileIndex).setProfileName(newName);
        }
    }

    public void updateSettings(Espresso newSettings) {
        if (currentProfileIndex != -1) {
            profileList.get(currentProfileIndex).updateSettings(newSettings);
        }
    }

    public String getProfileName() {
        if (currentProfileIndex != -1) {
            return profileList.get(currentProfileIndex).getProfileName();
        } else {
            return null;
        }
    }

    public Profile getProfile(int index) {
        if ((index >= 0) && (index < profileList.size())) {
            return profileList.get(index);
        } else {
            return null;
        }
    }

    public Espresso getCoffeeSettings(Class<? extends Espresso> coffeeClass) {
        if (currentProfileIndex != -1) {
            return profileList.get(currentProfileIndex).getSetting(coffeeClass);
        } else {
            return null;
        }
    }

    public int getProfileCount() {
        return profileList.size();
    }

    public boolean isCurrentProfileSet() {
        return currentProfileIndex != -1;
    }

    public void sortProfiles(Comparator<Profile> comparator) {
        Profile currentProfile = null;
        if (currentProfileIndex != -1) {
            currentProfile = profileList.get(currentProfileIndex);
        }
        Collections.sort(profileList, comparator);
        if (currentProfile != null) {
            currentProfileIndex = profileList.indexOf(currentProfile);
        }
    }

    public void save(String path) throws IOException {
            profileFileManager.saveToFile(profileList, path);
    }

    public void load(String path) {
        try {
            profileList = profileFileManager.loadToFile(path);
        } catch (IOException | ClassNotFoundException ex) {
            profileList = new ArrayList<Profile>();
        }
    }
}