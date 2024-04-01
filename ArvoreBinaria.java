import java.util.Scanner;

class No {
    int valor;
    No esquerda, direita;

    No(int item) {
        valor = item;
        esquerda = direita = null;
    }
}

class ArvoreBinaria {
    No raiz;

    ArvoreBinaria() {
        raiz = null;
    }

    No inserir(No no, int chave) {
        if (no == null) {
            no = new No(chave);
            return no;
        }

        if (chave < no.valor)
            no.esquerda = inserir(no.esquerda, chave);
        else if (chave > no.valor)
            no.direita = inserir(no.direita, chave);

        return no;
    }

    void imprimirNosFolha(No no) {
        if (no == null)
            return;

        if (no.esquerda == null && no.direita == null)
            System.out.print(no.valor + " ");

        imprimirNosFolha(no.esquerda);
        imprimirNosFolha(no.direita);
    }

    boolean imprimirAncestrais(No no, int chave) {
        if (no == null)
            return false;

        if (no.valor == chave)
            return true;

        if (imprimirAncestrais(no.esquerda, chave) || imprimirAncestrais(no.direita, chave)) {
            System.out.print(no.valor + " ");
            return true;
        }

        return false;
    }

    void imprimirDescendentes(No no, int chave) {
        if (no == null)
            return;

        if (no.valor == chave)
            imprimirSubarvore(no);
        else {
            imprimirDescendentes(no.esquerda, chave);
            imprimirDescendentes(no.direita, chave);
        }
    }

    void imprimirSubarvore(No no) {
        if (no == null)
            return;

        System.out.print(no.valor + " ");
        imprimirSubarvore(no.esquerda);
        imprimirSubarvore(no.direita);
    }

    No encontrarPai(No no, int chave) {
        if (no == null)
            return null;

        if ((no.esquerda != null && no.esquerda.valor == chave) || (no.direita != null && no.direita.valor == chave))
            return no;

        No paiEsquerda = encontrarPai(no.esquerda, chave);
        if (paiEsquerda != null)
            return paiEsquerda;

        return encontrarPai(no.direita, chave);
    }

    void encontrarPaiEFilhos(No no, int chave) {
        No pai = encontrarPai(no, chave);
        if (pai == null) {
            System.out.println("Nó não encontrado na árvore.");
            return;
        }

        System.out.println("Pai: " + pai.valor);
        System.out.print("Filhos: ");
        if (pai.esquerda != null)
            System.out.print(pai.esquerda.valor + " ");
        if (pai.direita != null)
            System.out.println(pai.direita.valor);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreBinaria arvore = new ArvoreBinaria();

        while (true) {
            System.out.println("\nMenu");
            System.out.println("1 - Inserir número");
            System.out.println("2 - Mostrar os nós folha");
            System.out.println("3 - Mostrar os nós ancestrais de um nó");
            System.out.println("4 - Mostrar os nós descendentes de um nó");
            System.out.println("5 - Mostrar o nó pai e os nós filhos de um nó");
            System.out.println("6 - Sair");

            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Digite o número a ser inserido na árvore: ");
                    int num = scanner.nextInt();
                    arvore.raiz = arvore.inserir(arvore.raiz, num);
                    System.out.println("Número inserido com sucesso.");
                    break;
                case 2:
                    System.out.println("\nNós folha:");
                    arvore.imprimirNosFolha(arvore.raiz);
                    break;
                case 3:
                    System.out.print("Digite o número do nó: ");
                    int chave = scanner.nextInt();
                    System.out.println("\nNós ancestrais de " + chave + " são:");
                    arvore.imprimirAncestrais(arvore.raiz, chave);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Digite o número do nó: ");
                    chave = scanner.nextInt();
                    System.out.println("\nNós descendentes de " + chave + " são:");
                    arvore.imprimirDescendentes(arvore.raiz, chave);
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Digite o número do nó: ");
                    chave = scanner.nextInt();
                    arvore.encontrarPaiEFilhos(arvore.raiz, chave);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
                    break;
            }
        }
    }
}
