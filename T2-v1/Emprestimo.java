// Arquivo: Emprestimo.java
// Agregação (tem-um Livro e tem-um Membro)

import java.io.Serializable;
import java.time.LocalDate;

public class Emprestimo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Agregação 1: Tem um Livro
    public Livro livro; 
    
    // Agregação 2: Tem um Membro (referência à Entidade Membro)
    public Membro membro; 
    
    public LocalDate dataEmprestimo;
    public LocalDate dataPrevistaDevolucao;

    public Emprestimo(Livro livro, Membro membro) {
        this.livro = livro;
        this.membro = membro;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = this.dataEmprestimo.plusDays(7);
    }
    
    @Override
    public String toString() {
        return "Empréstimo [Livro: " + livro.titulo + ", Membro: " + membro.nome + 
               ", Data: " + dataEmprestimo + ", Devolução: " + dataPrevistaDevolucao + "]";
    }
}