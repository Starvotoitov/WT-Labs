package machine.service.comparator.profile;

import machine.annotation.ComparatorInfo;
import machine.service.profile.Profile;
import java.util.Comparator;

@ComparatorInfo(name = "По имени профиля",
        description = "В алфавитном порядке")
public class ProfileNameComparator implements Comparator<Profile> {
    @Override
    public int compare(Profile profile1, Profile profile2) {
        return profile1.getProfileName().compareTo(profile2.getProfileName());
    }
}
