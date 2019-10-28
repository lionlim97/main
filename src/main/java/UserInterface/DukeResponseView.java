package UserInterface;

import javafx.scene.text.Text;

public class DukeResponseView {
    String index;
    Text response;

    public DukeResponseView(String index, Text response) {
        this.index = index;
        this.response = response;
    }

    public String getIndex() {
        return index;
    }

    public Text getResponse() {
        return response;
    }
}