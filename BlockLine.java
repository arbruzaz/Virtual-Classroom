import javafx.scene.control.TextField;

public class BlockLine extends TextField {
    
    public BlockLine() {
        setDisable(true);
        setScaleY(0.08);
        setStyle("-fx-background-color: #FFFFFF;");
        setMinHeight(8);
        setMaxHeight(8);
    }    
}