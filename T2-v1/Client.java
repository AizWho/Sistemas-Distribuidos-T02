// Arquivo: Client.java
// Ponto de entrada do Cliente RMI.

import java.rmi.Naming;

public class Client {
    
    private static final String RMI_HOST = "localhost";
    private static final int RMI_PORT = 1099;
    private static final String SERVICE_NAME = "BibliotecaService";
    
    public static void main(String[] args) {
        try {
            // 1. Localiza o serviço no RMI Registry e obtém o Proxy (Stub)
            String url = "rmi://" + RMI_HOST + ":" + RMI_PORT + "/" + SERVICE_NAME;
            IBiblioteca biblioteca = (IBiblioteca) Naming.lookup(url);
            
            System.out.println("--- Cliente RMI Conectado ao Serviço ---");

            // --- TESTE 1: Adicionar Livro (Passagem por Valor) ---
            Livro novoLivro = new Livro("978-03", "Microservices", "Newman");
            String resultAdd = biblioteca.adicionarLivro(novoLivro);
            System.out.println("\n[RESULTADO 1] Adicionar Livro: " + resultAdd);
            
            // --- TESTE 2: Listar Acervo ---
            String lista = biblioteca.listarTodosLivros();
            System.out.println("\n[RESULTADO 2] Listar Acervo:\n" + lista);

            // --- TESTE 3: Registrar Usuário (Passagem por Referência SIMULADA) ---
            Membro membroLocal = new Membro(0, "Alice Silva", "MAT123");
            System.out.println("\n[TESTE 3] Membro LOCAL antes do registro: " + membroLocal.id);
            
            // O RMI envia o objeto por valor, o servidor modifica e retorna a nova cópia.
            Usuario membroRegistrado = biblioteca.registrarUsuario(membroLocal);
            System.out.println("[RESULTADO 3] Membro RETORNADO com ID atribuído no Servidor: " + membroRegistrado);
            
            // --- TESTE 4 & 5: Empréstimo e Status ---
            Livro livroBuscado = biblioteca.buscarLivro("Design Patterns");
            if (livroBuscado != null) {
                // Passa o Empréstimo (Agregação) por valor
                Emprestimo novoEmprestimo = new Emprestimo(livroBuscado, (Membro) membroRegistrado);
                String resultEmprestimo = biblioteca.emprestarLivro(novoEmprestimo);
                System.out.println("\n[RESULTADO 4] Empréstimo: " + resultEmprestimo);

                Livro livroStatus = biblioteca.buscarLivro("Design Patterns");
                System.out.println("\n[RESULTADO 5] Status do Livro (após empréstimo): " + livroStatus);
            }

        } catch (Exception e) {
            System.err.println("\n[ERRO DE CONEXÃO/RMI]: Certifique-se de que o Servidor está rodando.");
            e.printStackTrace();
        }
    }
}