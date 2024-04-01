import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      ArvoreBinaria arvore = new ArvoreBinaria();
  
      while (true) {
        System.out.println("\nMenu");
        System.out.println("1 - Inserir número");
        System.out.println("2 - Mostrar todos");
        System.out.println("3 - Mostrar a subárvore da direita");
        System.out.println("4 - Mostrar a subárvore da esquerda");
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
            System.out.println("\nElementos da árvore:");
            arvore.mostrarTodos(arvore.raiz);
            System.out.println();
            break;
          case 3:
            System.out.print("Subárvore da direita: ");
            arvore.mostrarSubarvoreDireita(arvore.raiz.direita);
            System.out.println();
            break;
          case 4:
            System.out.print("Subárvore da esquerda: ");
            arvore.mostrarSubarvoreEsquerda(arvore.raiz.esquerda);
            System.out.println();
            break;
          case 5:
            System.out.print("Digite o número do nó: ");
            int chave = scanner.nextInt();
            No no = arvore.encontrarNo(arvore.raiz, chave);
            if (no == null) {
              System.out.println("Nó não encontrado na árvore.");
              continue;
            }
  
            System.out.println("Nó pai: " + no.valor);
            System.out.print("Nós filhos: ");
            if (no.esquerda != null) {
              System.out.print(no.esquerda.valor + " ");
            } else {
              System.out.print("Nó filho à esquerda não existe. ");
            }
            if (no.direita != null) {
              System.out.println(no.direita.valor);
            } else {
              System.out.println("Nó filho à direita não existe.");
            }
            break;
          case 6:
            System.out.println("Saindo...");
            scanner.close();
            System.exit(0);
            break;
          default:
            System.out.println("Opção inválida. Por favor, escolha novamente.");
            break;
        }
      }
    }
  }
  

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

    void mostrarTodos(No no) {
        if (no != null) {
            mostrarTodos(no.esquerda);
            System.out.print(no.valor + " ");
            mostrarTodos(no.direita);
        }
    }

    No encontrarNo(No no, int chave) {
        if (no == null || no.valor == chave)
            return no;

        if (no.valor < chave)
            return encontrarNo(no.direita, chave);

        return encontrarNo(no.esquerda, chave);
    }

    void mostrarSubarvoreEsquerda(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            mostrarSubarvoreEsquerda(no.esquerda);
        } else {
            System.out.println("Subárvore da esquerda não existe.");
        }
    }

    void mostrarSubarvoreDireita(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            mostrarSubarvoreDireita(no.direita);
        } else {
            System.out.println("Subárvore da direita não existe.");
        }
    }

    void mostrarNoPaiENosFilhos(int chave) {
        No no = encontrarNo(raiz, chave);
        if (no == null) {
            System.out.println("Nó não encontrado na árvore.");
            return;
        }

        System.out.println("Nó pai: " + no.valor);
        System.out.print("Nós filhos: ");
        if (no.esquerda != null)
            System.out.print(no.esquerda.valor + " ");
        if (no.direita != null)
            System.out.println(no.direita.valor);
    }
}

