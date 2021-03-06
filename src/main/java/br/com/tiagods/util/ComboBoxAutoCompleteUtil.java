package br.com.tiagods.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

import java.text.Normalizer;
import java.util.stream.Stream;

/**
 * Created by Tiago on 23/08/2017.
 */
public class ComboBoxAutoCompleteUtil<T> {

    private ComboBox<T> cmb;
    String filter = "";
	private ObservableList<T> originalItems;

    public ComboBoxAutoCompleteUtil(ComboBox<T> cmb) {
        this.cmb = cmb;
        originalItems = FXCollections.observableArrayList(cmb.getItems());
        cmb.setTooltip(new Tooltip());
        cmb.setOnKeyPressed(this::handleOnKeyPressed);
        cmb.setOnHidden(this::handleOnHiding);
    }
    public void handleOnKeyPressed(KeyEvent e) {
        ObservableList<T> filteredList = FXCollections.observableArrayList();
        KeyCode code = e.getCode();

        if (code.isLetterKey()) {
            filter += e.getText();
        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            cmb.getItems().setAll(originalItems);
        }
        if (code == KeyCode.ESCAPE) {
            if(filter.trim().length()>0)
                filter += " ";
            else
                filter="";
        }
        if (filter.length() == 0) {
            filteredList = originalItems;
            cmb.getTooltip().hide();
        }
        else {
            Stream<T> itens = cmb.getItems().stream();
            String txtUsr = filter.toString().toLowerCase();
            itens.filter(el ->
            		Normalizer.normalize(el==null?"":(el.toString().toLowerCase()), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
                    .contains(txtUsr)).forEach(filteredList::add);
            cmb.getTooltip().setText(txtUsr);
            Window stage = cmb.getScene().getWindow();
            double posX = stage.getX() + cmb.getBoundsInParent().getMinX();
            double posY = stage.getY() + cmb.getBoundsInParent().getMinY();
            cmb.getTooltip().show(stage, posX, posY);
            cmb.show();
        }
        cmb.getItems().setAll(filteredList);
    }

    public void handleOnHiding(Event e) {
        filter = "";
        cmb.getTooltip().hide();
        T s = cmb.getSelectionModel().getSelectedItem();
        cmb.getItems().setAll(originalItems);
        cmb.getSelectionModel().select(s);
    }
}
