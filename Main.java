import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
Arvore tree = new Arvore();
    tree.inserir(" ", " ");
   /* tree.inserir("a", ".-");
    tree.inserir("b", "-...");
    tree.inserir("c", "-.-.");
    tree.inserir("d", "-..");
    tree.inserir("e", ".");
    tree.inserir("f", "..-.");
    tree.inserir("g", "--.");
    tree.inserir("h", "....");
    tree.inserir("i", "..");
    tree.inserir("j", ".---");
    tree.inserir("k", "-.-");
    tree.inserir("l", ".-..");
    tree.inserir("m", "--");
    tree.inserir("n", "-.");
    tree.inserir("o", "---");
    tree.inserir("p", ".--.");
    tree.inserir("q", "--.-");
    tree.inserir("r", ".-.");
    tree.inserir("s", "...");
    tree.inserir("t", "-");
    tree.inserir("u", "..-");
    tree.inserir("v", "...-");
    tree.inserir("w", ".--");
    tree.inserir("x", "-..-");
    tree.inserir("y", "-.--");
    tree.inserir("z", "--..");
    

    tree.inserir("0", "-----");
    tree.inserir("1", ".----");
    tree.inserir("2", "..---");
    tree.inserir("3", "...--");
    tree.inserir("4", "....-");
    tree.inserir("5", ".....");
    tree.inserir("6", "-....");
    tree.inserir("7", "--...");
    tree.inserir("8", "---..");
    tree.inserir("9", "----.");*/
	 Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Ola! O que voce deseja fazer?");
            System.out.println("--------------------------- FILA --------------------------------");
            System.out.println("1 - adicionar um caracter a arvore");
            System.out.println("2 - buscar o valor em morse de um caracter");
            System.out.println("3 - Imprimir arvore");
            System.out.println("4 - Converter uma mensagem para morse");
            System.out.println("-----------------------------------------------------------------");
            System.out.print("0 - sair: ");
            

            while (!scanner.hasNextInt()) {
                System.out.println("Opcao invalida. Tente novamente.");
                scanner.next();
                System.out.print("Escolha uma opcao: ");
            }
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha

            switch (choice) {
                case 1:
                    System.out.println("Digite o caracter (letra):");
                    String letra = scanner.nextLine();
            
                    System.out.println("Digite a correspondencia em morse:");
                    String morse = scanner.nextLine();
                    tree.inserir(letra,morse);
                    break;
                case 2:
                    System.out.println("Digite o caracter desejado:");
                    String caracter = scanner.nextLine();
                    System.out.println(tree.busca(caracter));
                    break;
                case 3:
                    tree.print();
                    break;
                case 4:
                    System.out.println("Digite a mensagem desejada:");
                    String msg_input = scanner.nextLine();
                    tree.converte_mensagem(msg_input);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    System.exit(0);
            }
        } while (choice != 0);

        scanner.close();
	}
	
}

class Node{
    String letra;
    String morse;
    Node menor;
    Node maior;
    Node (String letra, String morse){
        this.letra = letra;
        this.morse = morse;
        this.menor = null;
        this.maior = null;
    }
}

class Arvore{
    Node raiz;
    Arvore(){
        this.raiz = null;
    }
    
    void inserir(String letra, String morse){
        this.raiz = inserir_aux(this.raiz, letra, morse, 0);
    }
    Node inserir_aux(Node raiz, String letra, String morse, int index) {
        if (raiz == null) {
            raiz = new Node(letra, morse);
            return raiz;
        }
        if (index < morse.length()) {
            char caracterAtual = morse.charAt(index);
            if (caracterAtual == '.') {
                raiz.menor = inserir_aux(raiz.menor, letra, morse, index + 1);
            } else if (caracterAtual == '-') {
                raiz.maior = inserir_aux(raiz.maior, letra, morse, index + 1);
            }
        }
        return raiz;
    }
    void print() {
        print_auxiliar(this.raiz, 0);
    }

    void print_auxiliar(Node node, int nivel) {
        if (node != null) {
            print_auxiliar(node.maior, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("  ");
            }
            System.out.println(node.letra);
            print_auxiliar(node.menor, nivel + 1);
        }
    }
    String busca(String n){
        
        String p = busca_auxiliar(this.raiz,n);
        return p;
        }
   String busca_auxiliar(Node node, String n) {
        if (node == null) {
            return "Nao encontrado";
        }
        if (node.letra.equals(n)) {
            return node.morse;
        }
        String resultado = busca_auxiliar(node.menor, n);
        if (resultado != "Nao encontrado") {
            return resultado;
        }
        return busca_auxiliar(node.maior, n);
    }
    
    void converte_mensagem (String m){
        String msg = "";
        for (int i=0; i < m.length(); i++) {
            String p = String.valueOf(m.charAt(i));
            String aux = this.busca(p);
            msg = msg + aux;
        }
        System.out.println(msg);
    }
}
