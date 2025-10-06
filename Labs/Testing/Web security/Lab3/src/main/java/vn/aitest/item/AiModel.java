package vn.aitest.item;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ai_models")
public class AiModel {

    @Id
    private String id;

    private String aiName;  
    private String strengths;  

    // Default constructor
    public AiModel() {}

    // Constructor with fields
    public AiModel(String aiName, String strengths) {
        this.aiName = aiName;
        this.strengths = strengths;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAiName() { return aiName; }
    public void setAiName(String aiName) { this.aiName = aiName; }

    public String getStrengths() { return strengths; }
    public void setStrengths(String strengths) { this.strengths = strengths; }

    @Override
    public String toString() {
        return "AiModel{" +
                "id='" + id + '\'' +
                ", aiName='" + aiName + '\'' +
                ", strengths='" + strengths + '\'' +
                '}';
    }
}
