# Sistemas-Distribuidos-T02
Trabalho 02 de Sistemas Distribuídos (2025.2) - Kayky e Kauã

# 📚 Sistema de Gerenciamento de Biblioteca (RMI Híbrido)

Este projeto demonstra a implementação de um sistema de biblioteca utilizando o **Java RMI (Remote Method Invocation)**, explorando duas abordagens arquiteturais distintas para o transporte de dados:

* **V1 (RMI Nativo Padrão):** Utiliza a serialização nativa do Java.
* **V2 (RMI Híbrido com Protocolo JSON/Gson):** Utiliza o RMI como **camada de transporte**, mas implementa o protocolo de requisição-resposta com *marshalling* e *unmarshalling* de dados via **JSON (Gson)**.

---

## 🏗️ Estrutura e Entidades do Projeto

O projeto é baseado em entidades que representam o domínio da biblioteca:

* **Entidades:** `Livro.java`, `Membro.java`, `Bibliotecario.java`, `Usuario.java`.
* **Agregação:** `Emprestimo.java` (que **tem um** `Livro` e **tem um** `Membro`).
* **Lógica de Negócios:** `BibliotecaService.java` (Implementa o acervo e regras).

---

## ⚖️ Comparativo V1 (RMI Nativo) vs. V2 (RMI/JSON)

Esta tabela destaca as diferenças na arquitetura de cada versão:

| Recurso | V1: RMI Nativo Padrão | V2: RMI Híbrido com JSON/Gson |
| :--- | :--- | :--- |
| **Serialização** | Serialização Java Nativa (`Serializable`). | **JSON** (via Gson) como protocolo de dados. |
| **Interface RMI** | `IBiblioteca` estende `Remote`. | **`IServicoProtocolo`** estende `Remote` (camada de transporte). |
| **Argumentos/Retorno** | Objetos Java (`Livro`, `Membro`). | Array de Bytes (`byte[]`) empacotado. |
| **Componentes** | Apenas classes RMI padrão. | Adiciona **StubProtocolo**, **SkeletonProtocolo** e **Gson**. |
| **Objetivo** | Transparência e **eficiência** de rede. | Demonstração do *Marshalling* RPC e **protocolo manual**. |

---

## 🚀 Como Compilar e Rodar

### Versão 1: RMI Nativo Padrão

#### Compilação V1 (`javac`)

| Sistema Operacional | Comando de Compilação |
| :--- | :--- |
| **Windows** (CMD/PowerShell) | `javac *.java` |
| **Linux/macOS** | `javac *.java` |

#### Execução V1 (Servidor)

| Sistema Operacional | Comando do Servidor |
| :--- | :--- |
| **Windows** | `java Server` |
| **Linux/macOS** | `java Server` |

#### Execução V1 (Cliente)

| Sistema Operacional | Comando do Cliente |
| :--- | :--- |
| **Windows** | `java Client` |
| **Linux/macOS** | `java Client` |

---

### Versão 2: RMI Híbrido com JSON/Gson

Esta versão **requer** o Gson. O arquivo **Gson JAR** deve estar na mesma pasta para compilação e execução.

#### Compilação V2 (`javac`)

| Sistema Operacional | Comando de Compilação |
| :--- | :--- |
| **Windows** (CMD/PowerShell) | `javac -cp ".;gson-2.10.1.jar" *.java` |
| **Linux/macOS** | `javac -cp ".:gson-2.10.1.jar" *.java` |

#### Execução V2 (Servidor)

| Sistema Operacional | Comando do Servidor |
| :--- | :--- |
| **Windows** | `java -cp ".;gson-2.10.1.jar" Server` |
| **Linux/macOS** | `java -cp ".:gson-2.10.1.jar" Server` |

#### Execução V2 (Cliente)

| Sistema Operacional | Comando do Cliente |
| :--- | :--- |
| **Windows** | `java -cp ".;gson-2.10.1.jar" Client` |
| **Linux/macOS** | `java -cp ".:gson-2.10.1.jar" Client` |

---

## APRESENTAÇÃO T02

[Link para a Apresentação do Projeto](https://youtu.be/8HWAT7heXrQ)
