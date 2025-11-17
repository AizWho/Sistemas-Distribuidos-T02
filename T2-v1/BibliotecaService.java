// Arquivo: BibliotecaService.java
// Implementação real da interface IBiblioteca (Objeto Remoto).

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Deve estender UnicastRemoteObject para ser um objeto remoto
public class BibliotecaService extends UnicastRemoteObject implements IBiblioteca { 
    
    private final List<Livro> acervo = new ArrayList<>();
    private static int nextUserId = 200; 

    // Construtor obrigatório para UnicastRemoteObject
    public BibliotecaService() throws RemoteException { 
        // Inicializa acervo para testes
        acervo.add(new Livro("978-01", "Design Patterns", "Gamma et al."));
        acervo.add(new Livro("978-02", "Distributed Systems", "Coulouris"));
        System.out.println("[SERVICE] Serviço Biblioteca inicializado com 2 livros.");
    }

    // Método Remoto 1 (Passagem por Valor: Livro)
    @Override
    public String adicionarLivro(Livro livro) throws RemoteException {
        if (livro == null || livro.isbn == null) {
            throw new RemoteException("Dados do livro inválidos.");
        }
        acervo.add(livro);
        System.out.println("[SERVICE] Livro adicionado: " + livro.titulo);
        return "Livro '" + livro.titulo + "' adicionado com sucesso.";
    }

    // Método Remoto 2 (Passagem por Valor: String -> Retorna Livro)
    @Override
    public Livro buscarLivro(String titulo) throws RemoteException {
        Optional<Livro> resultado = acervo.stream()
            .filter(l -> l.titulo.equalsIgnoreCase(titulo))
            .findFirst();
            
        System.out.println("[SERVICE] Busca por livro: " + titulo);
        return resultado.orElse(null);
    }

    // Método Remoto 3 (Passagem por Valor: Emprestimo)
    @Override
    public String emprestarLivro(Emprestimo emprestimo) throws RemoteException {
        if (emprestimo == null || emprestimo.livro == null || !emprestimo.livro.disponivel) {
            throw new RemoteException("Livro não encontrado ou indisponível.");
        }

        Optional<Livro> acervoLivro = acervo.stream()
            .filter(l -> l.isbn.equals(emprestimo.livro.isbn))
            .findFirst();

        if (acervoLivro.isPresent()) {
            acervoLivro.get().disponivel = false;
            System.out.println("[SERVICE] Empréstimo realizado para: " + emprestimo.membro.nome);
            return "Empréstimo de '" + emprestimo.livro.titulo + "' para " + emprestimo.membro.nome + " realizado com sucesso.";
        }
        
        throw new RemoteException("Falha no empréstimo: Livro não encontrado no acervo.");
    }

    // Método Remoto 4 (Passagem por Referência - SIMULADA)
    @Override
    public Usuario registrarUsuario(Usuario novoUsuario) throws RemoteException {
        // Simulação de passagem por referência: A instância é modificada no servidor.
        novoUsuario.id = nextUserId++; 
        System.out.println("[SERVICE] Usuário registrado e ID atribuído: " + novoUsuario.id);
        
        // Retorna o objeto modificado, simulando o efeito de referência.
        return novoUsuario; 
    }
    
    // Método Remoto 5 (Extra)
    @Override
    public String listarTodosLivros() throws RemoteException {
        StringBuilder sb = new StringBuilder("--- ACERVO ATUAL ---\n");
        acervo.forEach(l -> sb.append(l.toString()).append("\n"));
        return sb.toString();
    }
}