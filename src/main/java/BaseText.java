
public class BaseText{
    private Integer btId;
    private String btText;
    private String btTitle;
    private String btUrl;
    private String btSource;
    private Integer btUserTonality;
    private Integer btMachineTonality;
    private Integer btNeuralTonality;
    private String btTopic;

    public BaseText(String btText, String btTitle, String btUrl, String btSource) {
        this.btText = btText;
        this.btTitle = btTitle;
        this.btUrl = btUrl;
        this.btSource = btSource;
    }

    public BaseText() {
    }

    public Integer getBtId() {
        return btId;
    }

    public void setBtId(Integer btId) {
        this.btId = btId;
    }

    public String getBtText() {
        return btText;
    }

    public void setBtText(String btText) {
        this.btText = btText;
    }

    public String getBtTitle() {
        return btTitle;
    }

    public void setBtTitle(String btTitle) {
        this.btTitle = btTitle;
    }

    public String getBtUrl() {
        return btUrl;
    }

    public void setBtUrl(String btUrl) {
        this.btUrl = btUrl;
    }

    public String getBtSource() {
        return btSource;
    }

    public void setBtSource(String btSource) {
        this.btSource = btSource;
    }

    public Integer getBtUserTonality() {
        return btUserTonality;
    }

    public void setBtUserTonality(Integer btUserTonality) {
        this.btUserTonality = btUserTonality;
    }

    public Integer getBtMachineTonality() {
        return btMachineTonality;
    }

    public Integer getBtNeuralTonality() {
        return btNeuralTonality;
    }

    public void setBtMachineTonality(Integer btMachineTonality) {
        this.btMachineTonality = btMachineTonality;
    }

    public void setNeuralTonality(Integer btNeuralTonality) {
        this.btNeuralTonality = btNeuralTonality;
    }

    public String getBtTopic() {
        return btTopic;
    }

    public void setBtTopic(String btTopic) {
        this.btTopic = btTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseText that = (BaseText) o;

        if (btId != that.btId) return false;
        if (btText != null ? !btText.equals(that.btText) : that.btText != null) return false;
        if (btTitle != null ? !btTitle.equals(that.btTitle) : that.btTitle != null) return false;
        if (btUrl != null ? !btUrl.equals(that.btUrl) : that.btUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = btId;
        result = 31 * result + (btText != null ? btText.hashCode() : 0);
        result = 31 * result + (btTitle != null ? btTitle.hashCode() : 0);
        result = 31 * result + (btUrl != null ? btUrl.hashCode() : 0);
        return result;
    }
}
