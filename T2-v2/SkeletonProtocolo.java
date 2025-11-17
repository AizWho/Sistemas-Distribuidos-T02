import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

// Implementa a interface RMI de baixo nível (IServicoProtocolo)
public class SkeletonProtocolo extends UnicastRemoteObject implements IServicoProtocolo {

    private BibliotecaService service; 

    public SkeletonProtocolo(BibliotecaService service) throws RemoteException {
        super();
        this.service = service;
    }

    @Override
    public byte[] processarRequisicao(RequisicaoProtocolo requisicao) throws RemoteException {
        
        String methodId = requisicao.getMethodID();
        byte[] argsBytes = requisicao.getArgumentsData();
        Object resultado = null;
        
        // ------------------ 1. getRequest CONCEITUAL (Desempacotar a Requisição) ------------------
        System.out.println("\n[Skeleton - getRequest] Recebida requisição para: " + methodId);

        try {
            switch (methodId) {
                case "adicionarLivro":
                    Livro livro = ProtocoloUtils.unmarshal(argsBytes, Livro.class);
                    resultado = service.adicionarLivro(livro); 
                    break;
                case "buscarLivro":
                    String titulo = ProtocoloUtils.unmarshal(argsBytes, String.class);
                    resultado = service.buscarLivro(titulo);
                    break;
                case "emprestarLivro":
                    Emprestimo emprestimo = ProtocoloUtils.unmarshal(argsBytes, Emprestimo.class);
                    resultado = service.emprestarLivro(emprestimo);
                    break;
                case "registrarUsuario":
                    // Garante que a desserialização do Membro funcione
                    Membro usuario = ProtocoloUtils.unmarshal(argsBytes, Membro.class); 
                    resultado = service.registrarUsuario(usuario);
                    break;
                case "listarTodosLivros":
                    resultado = service.listarTodosLivros();
                    break;
                default:
                    throw new IllegalArgumentException("Método ID desconhecido: " + methodId);
            }
        } catch (Exception e) {
            System.err.println("Erro na invocação do serviço local (" + methodId + "): " + e.getMessage());
            
            // CORREÇÃO: Lançar RemoteException clara para o cliente
            throw new RemoteException("Erro no servidor ao processar " + methodId + ": " + e.getMessage(), e);
        }

        // ------------------ 2. sendReply CONCEITUAL (Empacotar e Retornar) ------------------
        System.out.println("[Skeleton - sendReply] Empacotando resultado de " + methodId + "...");
        return ProtocoloUtils.marshal(resultado); 
    }
}