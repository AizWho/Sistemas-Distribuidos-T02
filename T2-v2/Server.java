import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    
    private static final int RMI_PORT = 1099; 
    private static final String SERVICE_NAME = "ProtocoloService"; 

    public static void main(String[] args) {
        try {
            // 1. Inicia o RMI Registry (O RMI cria o socket de escuta)
            LocateRegistry.createRegistry(RMI_PORT); 
            System.out.println("RMI Registry iniciado na porta: " + RMI_PORT);
            
            // 2. Instancia o Objeto de Serviço LOCAL (Lógica)
            BibliotecaService logicService = new BibliotecaService();
            
            // 3. Instancia e Exporta o Skeleton (Objeto Remoto RMI)
            SkeletonProtocolo skeleton = new SkeletonProtocolo(logicService);
            
            // 4. Registra o Skeleton no Registry (Binding)
            Naming.rebind(SERVICE_NAME, skeleton); 
            
            System.out.println("Serviço '" + SERVICE_NAME + "' (Skeleton) registrado e pronto.");

        } catch (Exception e) {
            System.err.println("Erro ao iniciar o Servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}