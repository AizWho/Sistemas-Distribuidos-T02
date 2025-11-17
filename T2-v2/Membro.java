public class Membro extends Usuario {
    private static final long serialVersionUID = 1L;
    
    public String matricula;

    public Membro(int id, String nome, String matricula) {
        super(id, nome);
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Membro [ID=" + id + ", Nome=" + nome + ", Matrícula=" + matricula + "]";
    }
}