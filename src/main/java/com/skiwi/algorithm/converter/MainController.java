package com.skiwi.algorithm.converter;

import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Frank van Heeswijk
 */
public class MainController implements Initializable {
    @FXML private ComboBox<Typeset> typesetComboBox;
    @FXML private TextArea inputTextArea;
    @FXML private TextArea outputTextArea;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        typesetComboBox.setConverter(new StringConverter<Typeset>() {
            @Override
            public String toString(final Typeset typeset) {
                return typeset.getDescription();
            }

            @Override
            public Typeset fromString(final String string) {
                return Typeset.getByDescription(string);
            }
        });
        typesetComboBox.getItems().addAll(Typeset.values());
        typesetComboBox.getSelectionModel().selectFirst();
    }
    
    @FXML
    private void handleLoadInputFromFileButtonAction(final ActionEvent actionEvent) {
        
    }
    
    private static enum Typeset {
        LATEX_ALGO("LaTeX (algo.sty)");
        
        private static final Map<String, Typeset> DESCRIPTION_MAPPING = Arrays.stream(Typeset.values())
            .collect(Collectors.toMap(Typeset::getDescription, i -> i));
        
        private final String description;
        
        private Typeset(final String description) {
            this.description = Objects.requireNonNull(description, "description");
        }
        
        public String getDescription() {
            return description;
        }
        
        public static Typeset getByDescription(final String description) {
            Objects.requireNonNull(description, "description");
            return DESCRIPTION_MAPPING.get(description);
        }
    }
}
