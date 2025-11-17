// Implementação real da lógica (Objeto LOCAL, chamado pelo Skeleton)

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// NÃO mais herda de UnicastRemoteObject nem usa RemoteException!
public class BibliotecaService implements IBiblioteca { 
    
    private final List<Livro> acervo = new ArrayList<>();
    private static int nextUserId = 200; 

    public BibliotecaService() { 
        acervo.add(new Livro("978-01", "Design Patterns", "Gamma et al."));
        acervo.add(new Livro("978-02", "Distributed Systems", "Coulouris"));
        System.out.println("[SERVICE] Serviço Biblioteca inicializado com 2 livros.");
    }

    @Override
    public String adicionarLivro(Livro livro) {
        if (livro == null || livro.isbn == null) {
            return "Erro: Dados do livro inválidos."; 
        }
        acervo.add(livro);
        System.out.println("[SERVICE] Livro adicionado: " + livro.titulo);
        return "Livro '" + livro.titulo + "' adicionado com sucesso.";
    }

    @Override
    public Livro buscarLivro(String titulo) {
        Optional<Livro> resultado = acervo.stream()
            .filter(l -> l.titulo.equalsIgnoreCase(titulo))
            .findFirst();
            
        System.out.println("[SERVICE] Busca por livro: " + titulo);
        return resultado.orElse(null);
    }

    @Override
    public String emprestarLivro(Emprestimo emprestimo) {
        if (emprestimo == null || emprestimo.livro == null || !emprestimo.livro.disponivel) {
            return "Livro não encontrado ou indisponível.";
        }

        Optional<Livro> acervoLivro = acervo.stream()
            .filter(l -> l.isbn.equals(emprestimo.livro.isbn))
            .findFirst();

        if (acervoLivro.isPresent()) {
            acervoLivro.get().disponivel = false;
            System.out.println("[SERVICE] Empréstimo realizado para: " + emprestimo.membro.nome);
            return "Empréstimo de '" + emprestimo.livro.titulo + "' para " + emprestimo.membro.nome + " realizado com sucesso.";
        }
        
        return "Falha no empréstimo: Livro não encontrado no acervo.";
    }

    @Override
    public Usuario registrarUsuario(Usuario novoUsuario) {
        novoUsuario.id = nextUserId++; 
        System.out.println("[SERVICE] Usuário registrado e ID atribuído: " + novoUsuario.id);
        
        return novoUsuario; 
    }
    
    @Override
    public String listarTodosLivros() {
        StringBuilder sb = new StringBuilder("--- ACERVO ATUAL ---\n");
        acervo.forEach(l -> sb.append(l.toString()).append("\n"));
        return sb.toString();
    }
}