import java.rmi.Naming;
import java.rmi.RemoteException;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class StubProtocolo implements IBiblioteca {

    private IServicoProtocolo servicoRemoto; 
    private static final String SERVICE_NAME = "ProtocoloService";
    private static final String RMI_HOST = "localhost";
    private static final int RMI_PORT = 1099;

    public StubProtocolo() throws Exception {
        String url = "rmi://" + RMI_HOST + ":" + RMI_PORT + "/" + SERVICE_NAME;
        this.servicoRemoto = (IServicoProtocolo) Naming.lookup(url);
    }

    private <T> T doOperation(String methodId, Object argument, Class<T> returnType) throws RemoteException {
        // 1. EMPACOTAMENTO (Marshalling)
        byte[] argsBytes = ProtocoloUtils.marshal(argument); 
        
        // 2. Cria a Requisição do Protocolo (Cabeçalho)
        RequisicaoProtocolo req = new RequisicaoProtocolo(
            "Biblioteca", 
            methodId, 
            argsBytes);
        
        // 3. ENVIO REMOTO (O RMI Cuida do Socket)
        System.out.print("[Stub - doOperation] Enviando requisição para: " + methodId);
        byte[] respostaBytes = servicoRemoto.processarRequisicao(req); 
        
        // 4. DESEMPACOTAMENTO (Unmarshal)
        return ProtocoloUtils.unmarshal(respostaBytes, returnType);
    }
    
    private <T> T doOperation(String methodId, Object argument, Type returnType) throws RemoteException {
        byte[] argsBytes = ProtocoloUtils.marshal(argument); 
        RequisicaoProtocolo req = new RequisicaoProtocolo("Biblioteca", methodId, argsBytes);
        byte[] respostaBytes = servicoRemoto.processarRequisicao(req); 
        return ProtocoloUtils.unmarshal(respostaBytes, returnType);
    }


    @Override
    public String adicionarLivro(Livro livro) {
        try {
            return doOperation("adicionarLivro", livro, String.class);
        } catch (RemoteException e) {
            return "Erro RMI: " + e.getMessage();
        }
    }

    @Override
    public Livro buscarLivro(String titulo) {
        try {
            return doOperation("buscarLivro", titulo, Livro.class);
        } catch (RemoteException e) {
            System.err.println("Erro RMI: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String emprestarLivro(Emprestimo emprestimo) {
        try {
            return doOperation("emprestarLivro", emprestimo, String.class);
        } catch (RemoteException e) {
            return "Erro RMI: " + e.getMessage();
        }
    }

    @Override
    public Usuario registrarUsuario(Usuario novoUsuario) {
        try {
            // CORREÇÃO: Tentar desserializar como Membro.class (subclasse concreta)
            return doOperation("registrarUsuario", novoUsuario, Membro.class);
        } catch (RemoteException e) {
            System.err.println("Erro RMI: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String listarTodosLivros() {
        try {
            return doOperation("listarTodosLivros", null, String.class);
        } catch (RemoteException e) {
            return "Erro RMI: " + e.getMessage();
        }
    }
}