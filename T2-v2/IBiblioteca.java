// Interface que define os métodos da Biblioteca (API LOCAL do Cliente/Stub)

// Não herda mais de Remote!
public interface IBiblioteca { 

    // Métodos originais, SEM 'throws RemoteException'
    public String adicionarLivro(Livro livro); 

    public Livro buscarLivro(String titulo); 

    public String emprestarLivro(Emprestimo emprestimo);

    public Usuario registrarUsuario(Usuario novoUsuario);
    
    public String listarTodosLivros();
}