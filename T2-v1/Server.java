// Arquivo: Server.java
// Ponto de entrada do Servidor RMI.

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    
    private static final int RMI_PORT = 1099; 
    private static final String SERVICE_NAME = "BibliotecaService";

    public static void main(String[] args) {
        try {
            // 1. Inicia o RMI Registry (Necessário apenas uma vez por porta)
            LocateRegistry.createRegistry(RMI_PORT); 
            System.out.println("RMI Registry iniciado na porta: " + RMI_PORT);
            
            // 2. Instancia e Exporta o objeto de serviço
            BibliotecaService service = new BibliotecaService();
            
            // 3. Registra o objeto no Registry (Binding)
            Naming.rebind(SERVICE_NAME, service); 
            
            System.out.println("Serviço '" + SERVICE_NAME + "' registrado e pronto.");

        } catch (Exception e) {
            System.err.println("Erro ao iniciar o Servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}