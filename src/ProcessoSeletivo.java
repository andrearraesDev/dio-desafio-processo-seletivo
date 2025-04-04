import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Sistema de Processo Seletivo Automatizado
 * 
 * Este programa simula um processo seletivo completo com as seguintes funcionalidades:
 * 1. Gerenciamento de candidatos
 * 2. Análise de compatibilidade salarial
 * 3. Tentativas de contato com os candidatos
 * 4. Relatório final do processo
 * 
 * Principais melhorias implementadas:
 * - Organização mais clara do código
 * - Separação de responsabilidades
 * - Mensagens mais descritivas
 * - Tratamento de edge cases
 * - Documentação completa
 */
public class ProcessoSeletivo {

    // Configurações do processo seletivo
    private static final int NUMERO_DE_VAGAS = 5;
    private static final double SALARIO_OFERECIDO = 2000.0;
    private static final int MAX_TENTATIVAS_CONTATO = 3;
    private static final double SALARIO_MINIMO = 1800.0;
    private static final double SALARIO_MAXIMO = 2200.0;

    // Lista de candidatos pré-selecionados
    private static final String[] CANDIDATOS = {
        "FELIPE", "MARCIA", "JULIA", "PAULO", "AUGUSTO",
        "MONICA", "FABRICIO", "MIRELA", "DANIELA", "JORGE"
    };

    public static void main(String[] args) {
        iniciarProcessoSeletivo();
    }

    /**
     * Fluxo principal do processo seletivo
     */
    private static void iniciarProcessoSeletivo() {
        exibirCabecalho();
        
        // Fase 1: Exibição dos candidatos
        exibirCandidatos();
        
        // Fase 2: Seleção dos candidatos
        List<String> candidatosSelecionados = realizarSelecao();
        
        // Fase 3: Contato com candidatos
        realizarContatos(candidatosSelecionados);
        
        // Fase 4: Relatório final
        gerarRelatorioFinal(candidatosSelecionados);
    }

    /**
     * Exibe o cabeçalho com informações iniciais do processo
     */
    private static void exibirCabecalho() {
        System.out.println("=== PROCESSO SELETIVO - SISTEMA AUTOMATIZADO ===");
        System.out.printf("Vagas disponíveis: %d | Salário oferecido: R$ %.2f%n%n", 
                         NUMERO_DE_VAGAS, SALARIO_OFERECIDO);
    }

    /**
     * Exibe a lista de candidatos de diferentes formas
     */
    private static void exibirCandidatos() {
        System.out.println("=== LISTA DE CANDIDATOS ===");
        
        System.out.println("\nLista com índice:");
        for (int i = 0; i < CANDIDATOS.length; i++) {
            System.out.printf("%02d - %s%n", i+1, CANDIDATOS[i]);
        }
        
        System.out.println("\nLista simplificada:");
        for (String candidato : CANDIDATOS) {
            System.out.println("- " + candidato);
        }
        System.out.println();
    }

    /**
     * Realiza o processo de seleção dos candidatos
     * @return Lista de candidatos selecionados
     */
    private static List<String> realizarSelecao() {
        System.out.println("=== FASE DE SELEÇÃO ===");
        System.out.printf("Analisando candidatos (salário base: R$ %.2f)...%n%n", SALARIO_OFERECIDO);
        
        List<String> selecionados = new ArrayList<>();
        int candidatosAnalisados = 0;

        while (selecionados.size() < NUMERO_DE_VAGAS && candidatosAnalisados < CANDIDATOS.length) {
            String candidatoAtual = CANDIDATOS[candidatosAnalisados];
            double pretensaoSalarial = gerarPretensaoSalarial();
            
            System.out.printf("Candidato %d/%d: %s%n", 
                            candidatosAnalisados+1, CANDIDATOS.length, candidatoAtual);
            System.out.printf("Pretensão salarial: R$ %.2f%n", pretensaoSalarial);
            
            if (avaliarCandidato(pretensaoSalarial)) {
                System.out.println("STATUS: SELECIONADO");
                selecionados.add(candidatoAtual);
            } else {
                System.out.println("STATUS: NÃO SELECIONADO (pretensão acima do orçamento)");
            }
            
            candidatosAnalisados++;
            System.out.println();
        }
        
        return selecionados;
    }

    /**
     * Avalia se o candidato está dentro do orçamento
     * @param pretensaoSalarial Pretensão salarial do candidato
     * @return true se o candidato pode ser contratado
     */
    private static boolean avaliarCandidato(double pretensaoSalarial) {
        return pretensaoSalarial <= SALARIO_OFERECIDO;
    }

    /**
     * Gera uma pretensão salarial aleatória para simulação
     * @return Valor da pretensão salarial
     */
    private static double gerarPretensaoSalarial() {
        return ThreadLocalRandom.current().nextDouble(SALARIO_MINIMO, SALARIO_MAXIMO);
    }

    /**
     * Realiza as tentativas de contato com os candidatos selecionados
     * @param candidatos Lista de candidatos a serem contactados
     */
    private static void realizarContatos(List<String> candidatos) {
        System.out.println("=== FASE DE CONTATO ===");
        System.out.printf("Iniciando tentativas de contato com %d candidatos selecionados...%n%n", 
                         candidatos.size());
        
        for (String candidato : candidatos) {
            System.out.printf("CANDIDATO: %s%n", candidato);
            boolean contatoSucesso = tentarContato(candidato);
            
            if (contatoSucesso) {
                System.out.println("STATUS: CONTATO ESTABELECIDO");
            } else {
                System.out.println("STATUS: NÃO FOI POSSÍVEL ESTABELECER CONTATO");
            }
            System.out.println();
        }
    }

    /**
     * Tenta contato com um candidato específico
     * @param candidato Nome do candidato
     * @return true se o contato foi estabelecido
     */
    private static boolean tentarContato(String candidato) {
        int tentativas = 0;
        boolean contatoSucesso = false;
        
        while (tentativas < MAX_TENTATIVAS_CONTATO && !contatoSucesso) {
            tentativas++;
            System.out.printf("Tentativa %d/%d... ", tentativas, MAX_TENTATIVAS_CONTATO);
            
            contatoSucesso = simularTentativaContato();
            
            if (contatoSucesso) {
                System.out.printf("Contato realizado com %s na %dª tentativa%n", 
                                  candidato, tentativas);
            } else {
                System.out.println("Sem resposta");
            }
        }
        
        return contatoSucesso;
    }

    /**
     * Simula uma tentativa de contato
     * @return true se o contato foi bem sucedido
     */
    private static boolean simularTentativaContato() {
        return new Random().nextInt(3) == 1;
    }

    /**
     * Gera o relatório final do processo seletivo
     * @param selecionados Lista de candidatos selecionados
     */
    private static void gerarRelatorioFinal(List<String> selecionados) {
        System.out.println("=== RELATÓRIO FINAL ===");
        System.out.printf("Total de vagas: %d%n", NUMERO_DE_VAGAS);
        System.out.printf("Candidatos selecionados: %d%n", selecionados.size());
        
        if (!selecionados.isEmpty()) {
            System.out.println("\nCandidatos contratados:");
            for (String candidato : selecionados) {
                System.out.println("- " + candidato);
            }
        }
        
        if (selecionados.size() == NUMERO_DE_VAGAS) {
            System.out.println("\nRESULTADO: TODAS AS VAGAS FORAM PREENCHIDAS COM SUCESSO");
        } else {
            System.out.printf("\nRESULTADO: APENAS %d DE %d VAGAS FORAM PREENCHIDAS%n", 
                            selecionados.size(), NUMERO_DE_VAGAS);
        }
    }
}