import java.io.Serializable;

// Representa a mensagem de requisição do livro-texto
public class RequisicaoProtocolo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String objectReference; 
    private String methodID; 
    private byte[] argumentsData; 

    public RequisicaoProtocolo(String objectReference, String methodID, byte[] argumentsData) {
        this.objectReference = objectReference;
        this.methodID = methodID;
        this.argumentsData = argumentsData;
    }

    public String getObjectReference() {
        return objectReference;
    }

    public String getMethodID() {
        return methodID;
    }

    public byte[] getArgumentsData() {
        return argumentsData;
    }
}