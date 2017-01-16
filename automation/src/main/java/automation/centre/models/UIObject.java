package automation.centre.models;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class UIObject extends Model {
    public UIObject(){
        this.setType("UIObject");
    }

    public String getUiname() {
        return uiname;
    }

    public void setUiname(String uiname) {
        this.uiname = uiname;
    }

    public String getUiid() {
        return uiid;
    }

    public void setUiid(String uiid) {
        this.uiid = uiid;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String uiname;
    private String uiid;
    private String xpath;
    private String image;

}
