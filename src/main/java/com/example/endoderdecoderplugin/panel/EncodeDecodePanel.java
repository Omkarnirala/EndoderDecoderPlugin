package com.example.endoderdecoderplugin.panel;

import javax.swing.*;
import java.awt.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EncodeDecodePanel extends JPanel {
    private JTextArea textArea; // Combined input/output area
    private JButton encodeButton;
    private JButton decodeButton;
    private JPanel topPanel;

    public EncodeDecodePanel() {
        // Initialize the components
        initializeComponents();
        // Layout the components
        layoutComponents();
        // Add action listeners to buttons
        addActionListeners();
    }

    private void initializeComponents() {
        textArea = new JTextArea();
        encodeButton = new JButton("Encode");
        decodeButton = new JButton("Decode");

        // Configure text areas
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 16));

        // Layout configuration for the main panel
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        // Text area in the center
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Buttons in the north (header)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Top panel for buttons
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        topPanel.add(encodeButton);
        topPanel.add(decodeButton);

        // Add components to panel
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void addActionListeners() {
        encodeButton.addActionListener(e -> encodeAction());
        decodeButton.addActionListener(e -> decodeAction());
    }

    private void encodeAction() {
        try {
            String inputText = textArea.getText();
            String encodedText = URLEncoder.encode(inputText, StandardCharsets.UTF_8)
                    .replace("+", "%20")
                    .replace("'", "%27")
                    .replace("\"", "%22");
            textArea.setText(encodedText);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error during encoding: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void decodeAction() {
        try {
            String inputText = textArea.getText();
            String decodedText = URLDecoder.decode(inputText, StandardCharsets.UTF_8).replace("+", " ");
            textArea.setText(decodedText);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error during decoding: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}