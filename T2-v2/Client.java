public class Client {
    
    public static void main(String[] args) {
        try {
            // 1. Instancia o Stub (Proxy)
            IBiblioteca biblioteca = new StubProtocolo(); 
            
            System.out.println("--- Cliente RMI/Protocolo Conectado ao Serviço ---");

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
            
            // O Stub faz o doOperation e envia o membro empacotado
            Usuario membroRegistrado = biblioteca.registrarUsuario(membroLocal);
            // Retorna o objeto modificado pelo servidor
            System.out.println("[RESULTADO 3] Membro RETORNADO com ID atribuído no Servidor: " + membroRegistrado.id);
            
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