package com.epickrram.lockit.gui;

import com.epickrram.lockit.KeyGenerator;
import com.epickrram.lockit.NoOpClipboardOwner;
import com.epickrram.lockit.Wheel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class Lockit extends Application
{

    private final PasswordField passphraseField = new PasswordField();
    private final PasswordField memorableWordField = new PasswordField();
    private final IndicatorComponent ic2 = new IndicatorComponent();
    private final IndicatorComponent ic1 = new IndicatorComponent();
    private final Label passphraseLabel = new Label("Passphrase:");
    private final Text scenetitle = new Text("Lock.it");
    private final Label memorableWorkLabel = new Label("Memorable word:");
    private final Label mnemonicLabel = new Label("Mnemonic:");
    private final PasswordField mnemonicField = new PasswordField();
    private final IndicatorComponent ic3 = new IndicatorComponent();
    private final Label info = new Label("");
    private final GridPane unlockedGrid = createUnlockedDisplay();
    private final Scene scene = createInitialScene();
    private Wheel wheel;

    public Lockit()
    {
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
    }

    @Override
    public void start(final Stage stage) throws Exception
    {
        componentWiring();
        stage.setScene(scene);
        stage.show();
    }

    private static Label setFont(final Label label)
    {
        label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        return label;
    }

    private static PasswordField setFont(final PasswordField field)
    {
        field.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        return field;
    }

    private void componentWiring()
    {
        passphraseField.setOnKeyReleased((event) -> ic1.updateValue(passphraseField.getText()));
        memorableWordField.setOnKeyReleased((evt) -> ic2.updateValue(memorableWordField.getText()));
        mnemonicField.setOnKeyReleased((evt) -> ic3.updateValue(mnemonicField.getText()));

        mnemonicField.setOnAction((e) -> {
            info.setTextFill(Color.BLACK);
            info.setText("");
            if (Wheel.isInvalidMnemonic(mnemonicField.getText()))
            {
                info.setText("Invalid mnemonic");
                info.setTextFill(Color.RED);
                return;
            }
            wheel.printCode(0, mnemonicField.getText(), (password) -> {
                final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection(password), new NoOpClipboardOwner());
                info.setText("Copied to clipboard");
                mnemonicField.setText("");
            });
        });

        final EventHandler<ActionEvent> unlockAction = (event) -> {
            final byte[] key = KeyGenerator.generateKey(passphraseField.getText().getBytes(Charset.forName("UTF-8")),
                    memorableWordField.getText().getBytes(Charset.forName("UTF-8")));
            wheel = new Wheel(key);

            scene.setRoot(unlockedGrid);
        };

        memorableWordField.setOnAction(unlockAction);
    }

    private GridPane createUnlockedDisplay()
    {
        final GridPane unlockedGrid = new GridPane();
        unlockedGrid.setAlignment(Pos.CENTER);
        unlockedGrid.setHgap(10);
        unlockedGrid.setVgap(10);
        unlockedGrid.setPadding(new Insets(25, 25, 25, 25));
        unlockedGrid.add(scenetitle, 0, 0, 3, 1);
        unlockedGrid.add(setFont(mnemonicLabel), 0, 1);
        unlockedGrid.add(setFont(mnemonicField), 1, 1);
        unlockedGrid.add(ic3.getCanvas(), 2, 1);
        unlockedGrid.add(setFont(info), 1, 2, 2, 1);
        return unlockedGrid;
    }

    private Scene createInitialScene()
    {
        final GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(scenetitle, 0, 0, 3, 1);
        grid.add(setFont(passphraseLabel), 0, 1);
        grid.add(setFont(passphraseField), 1, 1);
        grid.add(ic1.getCanvas(), 2, 1);
        grid.add(setFont(memorableWorkLabel), 0, 2);
        grid.add(setFont(memorableWordField), 1, 2);
        grid.add(ic2.getCanvas(), 2, 2);
        return new Scene(grid, 800, 200);
    }
}
