package machine.service.comparator.profile;

import machine.annotation.ComparatorInfo;
import machine.service.profile.Profile;
import java.util.Comparator;

@ComparatorInfo(name = "По времени создания",
        description = "Вначале ранее созданные профили")
public class CreationDateComparator implements Comparator<Profile> {
    @Override
    public int compare(Profile profile1, Profile profile2) {
        return profile1.getCreationDate().compareTo(profile2.getCreationDate());
    }
}
