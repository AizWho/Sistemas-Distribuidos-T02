// Interface do Serviço RMI de baixo nível (doOperation adaptado)

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServicoProtocolo extends Remote {
    
    // O método remoto que transporta os bytes empacotados
    public byte[] processarRequisicao(RequisicaoProtocolo requisicao) throws RemoteException;
}