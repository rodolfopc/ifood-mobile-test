package br.com.rodolfopascoalcoelho.ifood_test.tweetsList.NaturalLanguage;

import com.google.api.services.language.v1.model.AnnotateTextRequest;
import com.google.api.services.language.v1.model.Document;
import com.google.api.services.language.v1.model.Features;

public class AnnotateTextRequestBuilder {
    private String documentType = "PLAIN_TEXT";
    private String documentLanguage;
    private boolean extractEntities = false;
    private boolean extractDocumentSentiment = false;
    private boolean extractSyntax = false;
    private String content;
    private FeaturesTypeEnum[] features = new FeaturesTypeEnum[]{};

    private String[] acceptedLanguages = {"en"};

    public AnnotateTextRequestBuilder(String content) {
        this.content = content;
    }

    private Document createDocument(){
        Document document = new Document();
        document.setType(documentType);
        document.setLanguage(documentLanguage);
        document.setContent(content);

        return document;
    }

    private AnnotateTextRequest createAnnotateTextRequest(){
        AnnotateTextRequest request = new AnnotateTextRequest();
        request.setDocument(createDocument());
        request.setFeatures(createFeatures());
        return request;
    }

    public AnnotateTextRequest build(){
        return createAnnotateTextRequest();
    }

    private Features createFeatures(){
        Features features = new Features();
        features.setExtractEntities(extractEntities);
        features.setExtractDocumentSentiment(extractDocumentSentiment);
        features.setExtractSyntax(extractSyntax);
        return features;
    }

    public AnnotateTextRequestBuilder setDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public AnnotateTextRequestBuilder setDocumentLanguage(String documentLanguage) throws AcceptedLanguagesException {
        this.documentLanguage = AcceptedLanguages.isLanguageAcepted(documentLanguage);
        return this;
    }


    public AnnotateTextRequestBuilder setFeatures(FeaturesTypeEnum... features) {
        this.features = features;
        for (FeaturesTypeEnum feature : features) {
            switch (feature){
                case SYNTAX:
                    extractSyntax = true;
                    break;
                case ENTITIES:
                    extractEntities = true;
                    break;
                case DOCUMENTSENTIMENT:
                    extractDocumentSentiment = true;
                    break;
            }
        }
        return this;
    }

    public enum FeaturesTypeEnum{
        ENTITIES,
        DOCUMENTSENTIMENT,
        SYNTAX
    }

    public enum AcceptedLanguages{
        EN("en");

        String language;
        AcceptedLanguages(String language) {
            this.language = language;
        }

        public static String isLanguageAcepted(String language) throws AcceptedLanguagesException{
            for (AcceptedLanguages lang : AcceptedLanguages.values()) {
                if (lang.language.equals(language)) {
                    return lang.language;
                }
            }
            throw new AcceptedLanguagesException("Language not accepted");
        }

    }

    public static class AcceptedLanguagesException extends Exception {
        public AcceptedLanguagesException(String message) {
            super(message);
        }
    }
}
