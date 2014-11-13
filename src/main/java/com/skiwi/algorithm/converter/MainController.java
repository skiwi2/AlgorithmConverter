package com.skiwi.algorithm.converter;

import com.skiwi.algorithm.converter.converters.AlgorithmConverter;
import com.skiwi.algorithm.converter.converters.LatexAlgoConverter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Frank van Heeswijk
 */
public class MainController implements Initializable {
    @FXML private AnchorPane anchorPane;
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
    private void handleLoadInputFromFileButtonAction(final ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load input from file");
        File selectedFile = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
        if (selectedFile != null) {
            Files.lines(selectedFile.toPath(), StandardCharsets.UTF_8).forEach(line -> inputTextArea.appendText(line + System.lineSeparator()));
        }
    }
    
    @FXML
    private void handleConvertButtonAction(final ActionEvent actionEvent) {
        Typeset typeset = typesetComboBox.getSelectionModel().getSelectedItem();
        String output = typeset.getAlgorithmConverter().convert(inputTextArea.getText());
        outputTextArea.setText(output);
    }
    
    private static enum Typeset {
        LATEX_ALGO("LaTeX (algo.sty)", new LatexAlgoConverter());
        
        private static final Map<String, Typeset> DESCRIPTION_MAPPING = Arrays.stream(Typeset.values())
            .collect(Collectors.toMap(Typeset::getDescription, i -> i));
        
        private final String description;
        private final AlgorithmConverter algorithmConverter;
        
        private Typeset(final String description, final AlgorithmConverter algorithmConverter) {
            this.description = Objects.requireNonNull(description, "description");
            this.algorithmConverter = Objects.requireNonNull(algorithmConverter, "algorithmConverter");
        }
        
        public String getDescription() {
            return description;
        }

        public AlgorithmConverter getAlgorithmConverter() {
            return algorithmConverter;
        }
        
        public static Typeset getByDescription(final String description) {
            Objects.requireNonNull(description, "description");
            return DESCRIPTION_MAPPING.get(description);
        }
    }
}
