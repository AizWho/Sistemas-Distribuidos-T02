// Arquivo: Emprestimo.java (MODIFICADO)

import java.io.Serializable;
import java.time.LocalDate;

public class Emprestimo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Agregação 1: Tem um Livro
    public Livro livro; 
    
    // Agregação 2: Tem um Membro (referência à Entidade Membro)
    public Membro membro; 
    
    // 💥 CORREÇÃO: Usar String para datas, que é serializável pelo Gson
    public String dataEmprestimo;
    public String dataPrevistaDevolucao;

    public Emprestimo(Livro livro, Membro membro) {
        this.livro = livro;
        this.membro = membro;
        
        // Converte LocalDate para String no formato ISO 8601 (Ex: "2025-11-17")
        LocalDate hoje = LocalDate.now();
        this.dataEmprestimo = hoje.toString();
        this.dataPrevistaDevolucao = hoje.plusDays(7).toString();
    }
    
    @Override
    public String toString() {
        return "Empréstimo [Livro: " + livro.titulo + ", Membro: " + membro.nome + 
               ", Data: " + dataEmprestimo + ", Devolução: " + dataPrevistaDevolucao + "]";
    }
}