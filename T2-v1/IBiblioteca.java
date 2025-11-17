// Arquivo: IBiblioteca.java
// Interface que define os métodos remotos da Biblioteca.

import java.rmi.Remote; 
import java.rmi.RemoteException;

// Deve herdar de Remote para ser reconhecida pelo RMI
public interface IBiblioteca extends Remote { 

    // Método 1: Adicionar um Livro (Passagem por Valor)
    public String adicionarLivro(Livro livro) throws RemoteException; 

    // Método 2: Buscar Livro por Título (Passagem por Valor)
    public Livro buscarLivro(String titulo) throws RemoteException; 

    // Método 3: Realizar um Empréstimo (Passagem por Valor e Agregação)
    public String emprestarLivro(Emprestimo emprestimo) throws RemoteException;

    // Método 4: Registrar um Usuário (Simula Passagem por Referência)
    public Usuario registrarUsuario(Usuario novoUsuario) throws RemoteException;
    
    // Método Extra: Listar todos os livros
    public String listarTodosLivros() throws RemoteException;
}