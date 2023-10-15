package gradle.tag.plugin.util;

public class CreatorTagVersion {

    public static final int TAG_MAJOR_BEGIN_INDEX = 1;
    public static final int TAG_MAJOR_END_INDEX = 2;
    public static final int TAG_MINOR_BEGIN_INDEX = 3;
    public static final int TAG_MINOR_END_INDEX = 4;
    public static final int TAG_MAJOR_NUMBER_VERSION = 0;

    public static final String TAG_NAME = "v%d.%d";

    public static  String incrementVersion(String tagVersion, boolean flagMajorVersion) {
        Integer majorVersion = Integer.parseInt(tagVersion.substring(TAG_MAJOR_BEGIN_INDEX, TAG_MAJOR_END_INDEX));
        System.out.println(tagVersion.substring(TAG_MAJOR_BEGIN_INDEX, TAG_MAJOR_END_INDEX));
        Integer minorVersion = Integer.parseInt(tagVersion.substring(TAG_MINOR_BEGIN_INDEX, TAG_MINOR_END_INDEX));
        String version;
        if (flagMajorVersion) {
            majorVersion++;
            version = String.format(TAG_NAME, majorVersion, TAG_MAJOR_NUMBER_VERSION);
        } else {
            minorVersion++;
            version = String.format(TAG_NAME, majorVersion, minorVersion);
        }
        return version;
    }
}
