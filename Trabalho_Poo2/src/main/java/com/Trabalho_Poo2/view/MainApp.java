package com.Trabalho_Poo2.view;

import com.Trabalho_Poo2.controller.ExperienciaViagemService;
import com.Trabalho_Poo2.model.ExperienciaViagem;
import com.Trabalho_Poo2.model.Midia;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.Trabalho_Poo2.model.TipoMidia;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private ExperienciaViagemService experienciaViagemService = new ExperienciaViagemService();
    private Stage novaTelaStage; // Variável de instância
    private VBox experienciasBox; // Adiciona variável para experiências

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sua Aplicação JavaFX");

        VBox root = new VBox();
        Scene scene = new Scene(root, 600, 400);

        Button adicionarExperienciaButton = new Button("Adicionar Nova Experiencia");
        adicionarExperienciaButton.setOnAction(event -> abrirNovaTela());

        experienciasBox = new VBox(); // Inicializa experienciasBox

        // Adiciona as experiências já existentes
        for (ExperienciaViagem experiencia : experienciaViagemService.obterExperiencias()) {
            experienciasBox.getChildren().add(criarExperienciaNode(experiencia));
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(experienciasBox);

        root.getChildren().addAll(adicionarExperienciaButton, scrollPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void abrirNovaTela() {
        novaTelaStage = new Stage();
        novaTelaStage.setTitle("Adicionar Nova Experiencia");

        VBox novaTelaRoot = new VBox(10);  // Espaçamento entre os elementos

        // Campo para inserir o título da experiência
        TextField tituloExperiencia = new TextField();
        tituloExperiencia.setPromptText("Digite o título da experiência");

        // Campo para inserir o texto da experiência
        TextArea textoExperiencia = new TextArea();
        textoExperiencia.setPromptText("Digite o texto da experiência");

        // Botão para adicionar mídia
        Button adicionarMidiaButton = new Button("Adicionar Mídia");
        adicionarMidiaButton.setOnAction(event -> adicionarMidia());

        // Container para as mídias adicionadas
        HBox midiasBox = new HBox(10);  // Espaçamento entre as mídias

        // Botão para salvar a experiência
        Button salvarExperienciaButton = new Button("Salvar Experiência");
        salvarExperienciaButton.setOnAction(event -> salvarExperiencia(tituloExperiencia.getText(), textoExperiencia.getText(), midiasBox));

        // Adicionando todos os elementos ao root da nova tela
        novaTelaRoot.getChildren().addAll(tituloExperiencia, textoExperiencia, adicionarMidiaButton, midiasBox, salvarExperienciaButton);

        Scene novaTelaScene = new Scene(novaTelaRoot, 400, 300);
        novaTelaStage.setScene(novaTelaScene);
        novaTelaStage.showAndWait();

        // Atualiza a lista de experiências após adicionar uma nova
        atualizarListaExperiencias();
    }

    private void salvarExperiencia(String titulo, String texto, HBox midiasBox) {
        List<Midia> midias = new ArrayList<>();
        for (Node node : midiasBox.getChildren()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                String caminhoArquivo = (String) imageView.getUserData(); // Recupera o caminho do arquivo

                // Aqui, você precisa ajustar de acordo com os campos necessários para sua classe Midia
                Midia midia = new Midia("", caminhoArquivo, TipoMidia.IMAGEM, null); // Ajuste conforme necessário
                midias.add(midia);
            }
        }

        // Cria a experiência de viagem
        ExperienciaViagem novaExperiencia = new ExperienciaViagem(titulo, texto, midias);
        experienciaViagemService.criarExperienciaViagem(novaExperiencia);

        novaTelaStage.close();
        atualizarListaExperiencias();
    }





    private void adicionarMidia() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha uma mídia");
        File selectedFile = fileChooser.showOpenDialog(novaTelaStage);

        if (selectedFile != null) {
            Image image = new Image("file:" + selectedFile.getAbsolutePath());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);

            // Armazenando o caminho do arquivo no ImageView para uso posterior
            imageView.setUserData(selectedFile.getAbsolutePath());

            VBox novaTelaRoot = (VBox) novaTelaStage.getScene().getRoot();

            // Encontrando o HBox para adicionar a imagem
            HBox midiasBox = null;
            for (Node node : novaTelaRoot.getChildren()) {
                if (node instanceof HBox) {
                    midiasBox = (HBox) node;
                    break;
                }
            }

            if (midiasBox != null) {
                midiasBox.getChildren().add(imageView);
            } else {
                System.err.println("HBox para mídias não encontrado.");
            }
        }
    }



    private VBox criarExperienciaNode(ExperienciaViagem experiencia) {
        VBox experienciaNode = new VBox(10); // Espaçamento entre os elementos

        // Título da experiência
        Label tituloLabel = new Label(experiencia.getTitulo());
        tituloLabel.setFont(new Font("Arial", 16)); // Ajuste o estilo conforme necessário

        // Texto da experiência
        TextArea textoArea = new TextArea(experiencia.getTexto());
        textoArea.setEditable(false); // Torna o TextArea não editável
        textoArea.setWrapText(true); // Quebra de linha automática
        textoArea.setMaxHeight(Double.MAX_VALUE); // Permite que o TextArea cresça verticalmente

        // Container para as imagens
        HBox imagensBox = new HBox(10); // Espaçamento entre as imagens
        for (Midia midia : experiencia.getMidias()) {
            String caminhoArquivo = "file:" + midia.getCaminhoArquivo();
            Image image = new Image(caminhoArquivo, false); // false para não carregar em background

            if (image.isError()) {
                System.err.println("Erro ao carregar a imagem: " + caminhoArquivo);
            } else {
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                imagensBox.getChildren().add(imageView);
            }
        }

        experienciaNode.getChildren().addAll(tituloLabel, textoArea, imagensBox);
        return experienciaNode;
    }


    private HBox criarMidiasNode(List<Midia> midias) {
        HBox midiasNode = new HBox();
        for (Midia midia : midias) {
            try {
                Image image = new Image("file:" + midias);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                midiasNode.getChildren().add(imageView);
            } catch (Exception e) {
                System.err.println("Erro ao carregar a mídia: " + midias);
                e.printStackTrace();
            }
        }
        return midiasNode;
    }

    private void atualizarListaExperiencias() {
        experienciasBox.getChildren().clear();
        for (ExperienciaViagem experiencia : experienciaViagemService.obterExperiencias()) {
            experienciasBox.getChildren().add(criarExperienciaNode(experiencia));
        }
    }
}
