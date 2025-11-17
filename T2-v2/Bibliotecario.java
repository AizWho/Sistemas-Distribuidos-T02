public class Bibliotecario extends Usuario {
    private static final long serialVersionUID = 1L;
    
    public String registroFuncional;

    public Bibliotecario(int id, String nome, String registroFuncional) {
        super(id, nome);
        this.registroFuncional = registroFuncional;
    }

    @Override
    public String toString() {
        return "Bibliotecário [ID=" + id + ", Nome=" + nome + ", Registro=" + registroFuncional + "]";
    }
}