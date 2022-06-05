package download;

public class DependencyClazz {
    private String groupId;
    private String artifactId;
    private String version;

    public DependencyClazz(String groupId, String artifactId, String verion) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = verion;
    }

    public DependencyClazz() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    @Override
    public String toString() {
        return "DependencyClazz{" +
                "groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", verion='" + version + '\'' +
                '}';
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}