import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public int id;
    public String nome;
    
    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}