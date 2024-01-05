/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataproje2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Zeynep ARAS
 */
public class Bst<T extends Comparable<T>> {

    Node<T> root;

    void inorder() {
        // root düğümünü parametre olarak alır ve binary search tree'deki 
        //tüm elemanları sıralı bir şekilde dolaşarak ekrana yazdırır.

        System.out.print("Inorder: ");
        inorder(root);
        System.out.println("");
    }

    void inorder(Node<T> node) {
        //metodu, bir düğümü parametre olarak alır ve recursive olarak 
        //kendini çağırarak binary search tree'deki tüm elemanları sıralı bir şekilde dolaşır.
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    void postorder() {
        //Bu metot, postorder gezisi sırasında düğümleri dolaşmak
        //için çağrılır ve root düğümünden başlar.
        System.out.print("Postorder: ");
        postorder(root);
        System.out.println("");
    }

    void postorder(Node<T> node) {
        //Metot recursive kullanarak soldaki alt ağacı dolaşır 
        //sonra sağdaki alt ağacı dolaşır ve son olarak düğümün kendisini dolaşır.
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + " ");
        }
    }

    void preorder() {
        //Bu metot, preorder gezisi sırasında düğümleri dolaşmak
        //için çağrılır ve root düğümünden başlar. 
        //Bu metot sadece preorder sonucunu ekrana yazdırır.
        System.out.print("Preorder: ");
        preorder(root);
        System.out.println("");
    }

    void preorder(Node<T> node) {
        //Bu metot, preorder methodunu gezme sırasında bir düğümün alt ağacındaki düğümleri dolaşmak için çağrılır.
        //Metot recursive olarak düğümün kendisini dolaşır, 
        //sonra soldaki alt ağacı dolaşır ve son olarak sağdaki alt ağacı dolaşır.
        if (node != null) {
            System.out.print(node.data + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    void insert(T newData) {
        //insert metodu, BST'ye yeni bir düğüm ekler. BST'nin root'a henüz atanmadıysa, yeni node root olarak atanır. 
        //Aksi takdirde, BST'de gezinme yapılır ve uygun yer bulunana kadar sağ veya sol çocuklardan birine atanır. 
        //Eklenecek veri zaten BST'de varsa, metot herhangi bir işlem yapmadan geri döner.

        Node<T> newNode = new Node<>(newData);

        if (root == null) {
            root = newNode;
        } else {
            Node<T> temp = root;

            while (temp != null) {
                if (newData.compareTo(temp.data) > 0) {

                    if (temp.right == null) {
                        temp.right = newNode;
                        return;
                    }

                    temp = temp.right;

                } else if (newData.compareTo(temp.data) < 0) {

                    if (temp.left == null) {
                        temp.left = newNode;
                        return;
                    }

                    temp = temp.left;

                } else {
                    return;
                }

            }

        }

    }

    public <T extends CharSequence> boolean checkWord(T word) {
        Bst dictionary = new Bst();
        //checkWord metodu, verilen bir kelimenin sözlükte olup olmadığını kontrol eder.
        //Bu işlemi yaparken, kelimeyi küçük harfe dönüştürür ve BST'de arama yapar. 
        //Kelime BST'de bulunursa true, bulunamazsa false döndürür.

        return dictionary.search(word.toString().toLowerCase());
    }

    void read() throws FileNotFoundException, IOException {
        //read() metodu, dosyaOkuma.csv dosyasındaki satırları okur ve her satırı insert() metoduyla ağaca ekler.
        File file = new File("words.txt");
        FileReader fileReader = new FileReader(file);
        String line;

        BufferedReader br = new BufferedReader(fileReader);

        while ((line = br.readLine()) != null) {
            insert((T) line);

        }

    }

    boolean search(T key) {
        Bst dictionary = new Bst();
        // search metodu, verilen key değerini tree'de olup olmadığını kontrol eder. 
        //key değeri, tree'deki düğümlerdeki data değerleri ile karşılaştırılır. 
        //Eğer key değeri, bir düğümdeki data değeri ile eşleşirse true değeri döndürülür. 
        //Eşleşme yoksa false değeri döndürülür.

        //Programda hatalı kelimeyi hata mesajı ile vurgulanması için kontrolleri sağladık ve hata mesajı yazdırdık.
        if (root == null || key != dictionary.root.data) {
            System.out.println("kelime bulunamadı ya da hatalı kelime girişi yapıldı.");

        } else {

            Node<T> temp = root;

            while (temp != null) {

                if (key.compareTo(temp.data) > 0) {
                    temp = temp.right;
                } else if (key.compareTo(temp.data) < 0) {
                    temp = temp.left;
                } else {
                    return true;
                }

            }

        }

        return false;
    }

    public static int levenshteinDistance(String s1, String s2) {

        //Verilen iki kelimenin Levenshtein mesafesini hesaplar.
        // Levenshtein mesafesi, bir kelimenin diğer kelimeye dönüştürülmesi için gereken minimum adım sayısıdır.
        // Bu adımlar, bir karakterin silinmesi, bir karakterin ekleme, veya bir karakterin değiştirilmesi olabilir.
        int wordone = s1.length();
        int wordtwo = s2.length();
        int[][] wordlist = new int[wordone + 1][wordtwo + 1];

        for (int i = 1; i <= wordone; i++) {
            wordlist[i][0] = i;
        }

        for (int j = 1; j <= wordtwo; j++) {
            wordlist[0][j] = j;
        }

        for (int i = 1; i <= wordone; i++) {
            for (int j = 1; j <= wordtwo; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    wordlist[i][j] = wordlist[i - 1][j - 1];
                } else {
                    wordlist[i][j] = 1 + Math.min(wordlist[i][j - 1], Math.min(wordlist[i - 1][j], wordlist[i - 1][j - 1]));
                }
            }
        }

        return wordlist[wordone][wordtwo];
    }

    public void suggestt(String word, int k) {
        //verilen bir kelimenin BST'deki düğümünü bulur ve ardından 
        //Levenshtein mesafesi k adet küçük olan kelime önerilerini bulur ve yazdırır.
        //İlk olarak, verilen kelimeyi ağaçta aramak için while döngüsü kullanılır. 
        //Düğüm,kelimeye eşitse, kelime BST'deki bir düğüme tam olarak eşleştiği anlamına 
        // gelir ve bu nedenle arama işlemi sona erer.
        //Düğüm bulunamazsa, geri döner. Daha sonra, findSuggestions adlı başka bir metot çağrılır. 
        //Bu metot, BST'deki düğümün alt ağaçlarında dolaşarak, verilen kelimeye bir 
        //Levenshtein mesafesi k'dan küçük olan kelimeleri aratır.
        Node<T> current = root;

        // Verilen kelimeyi ağaçta aranıyor.
        while (current != null) {
            int compare = word.compareTo(current.data.toString());
            if (compare < 0) {
                current = current.left;
            } else if (compare > 0) {
                current = current.right;
            } else {
                break; // Düğüm bulundu.
            }
        }

        // Düğüm bulunamadıysa geri dönülür.
        if (current == null) {
            return;
        }

        // Levenshtein mesafesi k'dan küçük olan kelimeleri bulur.
        Bst<T> suggestionsTree = new Bst<>();
        findSuggestions(current, word, k, suggestionsTree);

        // Önerileri yazdırır.
        suggestionsTree.inorder();
    }

    public void findSuggestions(Node<T> node, String word, int k, Bst<T> suggestionsTree) {
        //Bu method, verilen bir düğümden başlayarak, belirli bir kelime için 
        //Levenshtein mesafesi k'dan küçük olan kelimeleri bulmak için özyinelemeli olarak çağrılır.
        //İlk olarak, belirtilen kelime ve düğümün veri değeri arasındaki Levenshtein mesafesi hesaplanır. 
        //Bu mesafe, verilen kelime ile düğümdeki kelime arasındaki karakter farklarının sayısıdır. 
        //Daha sonra, bu mesafe belirtilen k'dan küçük veya eşitse, düğümün veri değeri öneriler ağacına eklenir.
        //Sonra, belirtilen kelime ve düğümün veri değeri arasındaki farkı kullanarak, 
        //ağacın sol ve sağ alt ağaçlarına doğru arama yapılır. 
        //Ağacın sol alt ağacındaki düğümler, kelimenin ilk karakterinin k - 1 azaltılmış 
        //hali ile başlayan kelimeleri içerir. Benzer şekilde, ağacın sağ alt ağacındaki düğümler, 
        //kelimenin ilk karakterinin k - 1 artırılmış hali ile başlayan kelimeleri içerir. 
        //Bu yöntem, ağacın sadece k kadar düğümünü aramasını sağlar ve böylece arama süresini azaltır.
        // Levenshtein mesafesi k'dan küçük olan kelimeleri bulur.
        int distance = levenshteinDistance(word, node.data.toString());
        if (distance <= k) {
            suggestionsTree.insert(node.data);
        }

        if (node.left != null && node.data.toString().charAt(0) >= word.charAt(0) - k) {
            findSuggestions(node.left, word, k, suggestionsTree);
        }

        if (node.right != null && node.data.toString().charAt(0) <= word.charAt(0) + k) {
            findSuggestions(node.right, word, k, suggestionsTree);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        NewJFrame frame = new NewJFrame();
        frame.setVisible(true);

        Bst dictionary = new Bst();
        Bst suggestdictionary = new Bst();

        Scanner scan = new Scanner(System.in);
        System.out.println("enter a word:");
        String oneword = scan.nextLine();

        dictionary.insert(oneword);
        dictionary.read();
        dictionary.inorder();
        dictionary.checkWord(oneword);
        //  levenshteinDistance();
        boolean control = dictionary.search(oneword);

        if (control == true) {
            System.out.println("The word is spelled correctly.");
        } else {
            System.out.println("The word is misspelled.");
        }

        dictionary.findSuggestions(suggestdictionary.root, oneword, 3, suggestdictionary);
        // suggestdictionary.insert();
        dictionary.suggestt(oneword, 5);

    }
}
