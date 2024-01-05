/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataproje2;

/**
 *
 * @author Zeynep ARAS
 */
public class Node <T extends Comparable <T>> {
    
    T data;
    Node <T> left;
    Node <T> right;
    
    public Node(T data){
        this.data=data;
        
    }
    
    
}
