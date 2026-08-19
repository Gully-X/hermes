package ar.com.odra.hermes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ollama")
public class OllamaProperties {

    private String url;
    private Model model;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public static class Model {

        private String general;
        private String programming;
        private String creative;

        public String getGeneral() {
            return general;
        }

        public void setGeneral(String general) {
            this.general = general;
        }

        public String getProgramming() {
            return programming;
        }

        public void setProgramming(String programming) {
            this.programming = programming;
        }

        public String getCreative() {
            return creative;
        }

        public void setCreative(String creative) {
            this.creative = creative;
        }
    }
}